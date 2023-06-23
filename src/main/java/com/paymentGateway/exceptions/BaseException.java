package com.paymentGateway.exceptions;
import com.paymentGateway.student.DTO.CustomResponse;
import com.paymentGateway.student.DTO.CustomStatus;
import com.paymentGateway.student.Utils.ManagedResponseEntity;
import com.paymentGateway.student.Utils.Translator;
import com.paymentGateway.student.Utils.Util;
import com.paymentGateway.student.constant.AppConstants;
import com.paymentGateway.student.constant.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

import javax.management.ServiceNotFoundException;

@Slf4j
@Component
@ControllerAdvice
public class BaseException {
	static Logger logger = LoggerFactory.getLogger(BaseException.class);

	@ExceptionHandler
	public static ResponseEntity<?> handleBaseException(Exception exception) {
		exception.printStackTrace();
		log.info("handleBaseException   \n" + exception.getMessage());
		String errorMsg = exception.getMessage();
		Integer code = AppConstants.SystemCode.EXCEPTION;
		Map<String, Object> metadata = new HashMap<>();
		if (errorMsg == null) {
			errorMsg = Translator.toLocale(ResponseMessage.NULL_POINTER_ERROR);
			code = AppConstants.SystemCode.NULL_POINTER_EXCEPTION;
		}

		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		boolean isJSON = isJSON(errorMsg);
		if (isJSON) {
			JSONObject errorObject = new JSONObject(errorMsg);
			code = Integer.parseInt(errorObject.get("code").toString());
			errorMsg = errorObject.get("message").toString();

			int httpCode = errorObject.has("httpCode") ? Integer.parseInt(errorObject.get("httpCode").toString()) : 200;

			httpStatus = Util.httpCodeMapping(httpStatus, httpCode);

			metadata = errorObject.has("metadata") ? errorObject.getJSONObject("metadata").toMap() : new HashMap<>();

		} else if (errorMsg.contains("~")) {
			String[] errArr = errorMsg.split("~");

			if (errArr.length == 3) {
				int httpCode = Integer.parseInt(errArr[0].trim());
				code = Integer.parseInt(errArr[1].trim());
				errorMsg = errArr[2];

				httpStatus = Util.httpCodeMapping(httpStatus, httpCode);

			}

		}
		ResponseEntity<CustomStatus> en = new ResponseEntity<CustomStatus>(
				isJSON ? new CustomStatus(code, errorMsg, metadata) : new CustomStatus(code, errorMsg), httpStatus);
		return ManagedResponseEntity.resolve(en);
	}

	@ExceptionHandler(ServiceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public static CustomStatus handleBaseException(ServiceNotFoundException exception) {
		exception.printStackTrace();
		log.info("ServiceNotFoundException   \n" + exception.getMessage());
		Map<String, Object> metadata = new HashMap<>();
		String errorMsg = exception.getMessage();
		Integer code = AppConstants.SystemCode.EXCEPTION;
		if (errorMsg == null) {
			errorMsg = Translator.toLocale(ResponseMessage.NULL_POINTER_ERROR);
			code = AppConstants.SystemCode.NULL_POINTER_EXCEPTION;
		}

		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		boolean isJSON = isJSON(errorMsg);

		if (isJSON) {
			JSONObject errorObject = new JSONObject(errorMsg);
			code = Integer.parseInt(errorObject.get("code").toString());
			errorMsg = errorObject.get("message").toString();

			int httpCode = errorObject.has("httpCode") ? Integer.parseInt(errorObject.get("httpCode").toString()) : 200;

			httpStatus = Util.httpCodeMapping(httpStatus, httpCode);

			metadata = errorObject.has("metadata") ? errorObject.getJSONObject("metadata").toMap() : new HashMap<>();

		} else if (errorMsg.contains("~")) {
			String[] errArr = errorMsg.split("~");

			if (errArr.length == 3) {
				int httpCode = Integer.parseInt(errArr[0].trim());
				code = Integer.parseInt(errArr[1].trim());
				errorMsg = errArr[2];

				httpStatus = Util.httpCodeMapping(httpStatus, httpCode);

			}

		}

		return isJSON && !metadata.isEmpty() ? new CustomStatus(httpStatus.value(), code.intValue(), errorMsg, metadata)
				: new CustomStatus(httpStatus.value(), code.intValue(), errorMsg);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public CustomStatus requestHandlingNoHandlerFound(NoHandlerFoundException exception) {
		String errorMsg = exception.getMessage();
		log.info("requestHandlingNoHandlerFound   \n" + exception.getMessage());
		//handleException(exception, AppConstants.LogCategory.UNIVERSAL_LOG, null, null);
		CustomStatus customStatus = new CustomStatus(AppConstants.SystemCode.RESOURCE_NOT_FOUND, errorMsg);
		return new CustomResponse<>(customStatus);
	}

	public static void handleException(Exception exception) {
		String errorMsg = exception.getMessage();
		Integer errorCode = AppConstants.SystemCode.EXCEPTION;
		if (errorMsg == null) {
			errorCode = AppConstants.SystemCode.NULL_POINTER_EXCEPTION;
			errorMsg = "Null Pointer Exception Occurred!";
		}


	}

	public static void handleException(Exception exception, String uniqueIdentity) {
		log.info("requestHandlingNoHandlerFound  " + uniqueIdentity + " \n" + exception.getMessage());
		String errorMsg = exception.getMessage();
		Integer errorCode = AppConstants.SystemCode.EXCEPTION;
		if (errorMsg == null) {
			errorCode = AppConstants.SystemCode.NULL_POINTER_EXCEPTION;
			errorMsg = "Null Pointer Exception Occurred!";
		}


	}

	/**
	 * Handle exception.
	 *
	 * @param exception      the exception
	 * @param uniqueIdentity the unique identity
	 * @param otherDetails   the other details
	 */
	public static void handleException(Exception exception, String uniqueIdentity, String otherDetails) {
		log.info("handleException  " + uniqueIdentity + " \n" + exception.getMessage() + "\n" + otherDetails);

		String errorMsg = exception.getMessage();
		Integer errorCode = AppConstants.SystemCode.EXCEPTION;
		if (errorMsg == null) {
			errorCode = AppConstants.SystemCode.NULL_POINTER_EXCEPTION;
			errorMsg = "Null Pointer Exception Occurred!";
		}
		// Dispatch event


	}

//	public static CustomStatus handleException(Exception exception, String logCategory, String uniqueIdentity,
//			String otherDetails) {
//		log.info("handleException  " + uniqueIdentity + " \n" + exception.getMessage() + "\n" + otherDetails);
//
//		String errorMsg = exception.getMessage();
//		Integer code = AppConstants.SystemCode.EXCEPTION;
//		if (errorMsg == null) {
//			errorMsg = Translator.toLocale(ResponseMessage.NULL_POINTER_ERROR);
//			code = AppConstants.SystemCode.NULL_POINTER_EXCEPTION;
//		}
//		CustomStatus status = CustomStatus.strip(Translator.toLocale(ResponseMessage.UNEXPECTED_ERROR));
//		// Dispatch event
//
//
//		return status;
//	}

	public static CustomStatus handleException(Exception exception, String logCategory, String uniqueIdentity,
			String otherDetails) {


		String errorMsg = exception.getMessage();
		Integer code = AppConstants.SystemCode.EXCEPTION;
		if (errorMsg == null) {
			errorMsg = Translator.toLocale(ResponseMessage.NULL_POINTER_ERROR);
			code = AppConstants.SystemCode.NULL_POINTER_EXCEPTION;
		}

		// Dispatch event


		return CustomStatus.strip(Translator.toLocale(ResponseMessage.UNEXPECTED_ERROR));
	}

	private static boolean isJSON(String value) {
		try {
			new JSONObject(value.trim());
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

}
