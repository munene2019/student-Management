package com.paymentGateway.student.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymentGateway.exceptions.BaseException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * 
 * @author ERIC
 *
 */
@Component
public class ManagedResponseEntity {

	static final ManagedResponseEntity MRE = new ManagedResponseEntity();

	public static ResponseEntity<String> resolve(Object o) {

		ResponseObject responseObject = cleanResponse(o.toString());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Length", String.valueOf(responseObject.body.length()));

		return ResponseEntity.status(responseObject.httpStatus).headers(headers).body(o.toString());

	}

	public static ResponseEntity<String> resolve(String o) {

		ResponseObject responseObject = cleanResponse(o);

		return ResponseEntity.status(responseObject.httpStatus)
				.header("Content-Length", String.valueOf(responseObject.body.length())).body(o);
	}

	public static ResponseEntity<?> resolve(ResponseEntity<?> o) {

		String response = cleanResponse(o.getBody()).body;

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Length", String.valueOf(response.length()));

		return ResponseEntity.status(o.getStatusCodeValue()).body(o.getBody());
	}

	private static ResponseObject cleanResponse(String originalResponseBody) {
		HttpStatus httpStatus = HttpStatus.OK;

		JSONObject httpResponseObject = new JSONObject(originalResponseBody);

		if (httpResponseObject.has("metadata") && httpResponseObject.getJSONObject("metadata").has("httpCode")) {
			httpStatus = Util.httpCodeMapping(httpStatus,
					Integer.parseInt(httpResponseObject.getJSONObject("metadata").get("httpCode").toString().trim()));
		}

		httpResponseObject.remove("metadata");

		return MRE.new ResponseObject(httpResponseObject.toString(), httpStatus);
	}

	private static ResponseObject cleanResponse(Object object) {
		HttpStatus httpStatus = HttpStatus.OK;
		try {
			String originalResponseBody = new ObjectMapper().writeValueAsString(object);
			JSONObject httpResponseObject = new JSONObject(originalResponseBody);

			if (httpResponseObject.has("metadata") && httpResponseObject.getJSONObject("metadata").has("httpCode")) {
				httpStatus = Util.httpCodeMapping(httpStatus, Integer
						.parseInt(httpResponseObject.getJSONObject("metadata").get("httpCode").toString().trim()));
			}

			httpResponseObject.remove("metadata");

			return MRE.new ResponseObject(httpResponseObject.toString(), httpStatus);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			BaseException.handleBaseException(e);
			return MRE.new ResponseObject("");
		}

	}

	private class ResponseObject {

		private String body;
		private HttpStatus httpStatus;

		public ResponseObject(String body, HttpStatus httpStatus) {
			this.body = body;
			this.httpStatus = httpStatus;

		}

		public ResponseObject(String body) {
			this.body = body;
			this.httpStatus = HttpStatus.OK;

		}

	}

}
