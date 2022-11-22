package com.student.student.helpers;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * The Class RestTemplateLoggingInterceptor.
 */
@Component
public class RestTemplateLoggingInterceptor implements ClientHttpRequestInterceptor {

	/** The unique identifier. */
	private String uniqueIdentifier;

	private String url;

	/**
	 * Instantiates a new rest template logging interceptor.
	 */
	RestTemplateLoggingInterceptor() {
		this.uniqueIdentifier = null;
	}

	/**
	 * Instantiates a new rest template logging interceptor.
	 *
	 * @param uniqueIdentifier the unique identifier
	 */
	RestTemplateLoggingInterceptor(String uniqueIdentifier) {

		this.uniqueIdentifier = uniqueIdentifier;
		this.url = "";
	}

	RestTemplateLoggingInterceptor(String uniqueIdentifier, String url) {

		this.uniqueIdentifier = uniqueIdentifier;
		this.url = url;
	}



	/**
	 * Intercept.
	 *
	 * @param request   the request
	 * @param body      the body
	 * @param execution the execution
	 * @return the client http response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		//logRequest(request, body);
		ClientHttpResponse response = execution.execute(request, body);
		//logResponse(response);

		return response;
	}



}
