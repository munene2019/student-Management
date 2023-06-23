package com.paymentGateway.student.DTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paymentGateway.student.Utils.Util;
import com.paymentGateway.student.constant.AppConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class CustomStatus {
    boolean status;
    int code;
    String message;

	Map<String, Object> metadata = new HashMap<>();

    // For logging purpose only
    @JsonIgnore
    String logCategory;
    @JsonIgnore
    String uniqueIdentity;

    @JsonIgnore
    HttpStatus httpStatus = HttpStatus.OK;

    public CustomStatus() {
        this.status = true;

    }

    public CustomStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

	public CustomStatus(int httpCode, int code, String message) {
		this.code = code;
		this.message = message;
		this.httpStatus = AppConstants.HttpStatus.getStatus(httpCode);
	}

	public CustomStatus(int httpCode, int code, String message, Map<String, Object> metadata) {
		this.code = code;
		this.message = message;
		this.httpStatus = AppConstants.HttpStatus.getStatus(httpCode);
		this.metadata = metadata;
	}

	public CustomStatus(int code, String message, Map<String, Object> metadata) {
		this.code = code;
		this.message = message;
		this.metadata = metadata;
	}

    public CustomStatus(boolean status, int code, String message) {
        this.status =  status;
        this.code =  code;
        this.message =  message;
    }

    public CustomStatus(int code, String message, int httpCode) {
        this.code = code;
        this.status = code == 200;
        this.message = message;
		this.httpStatus = AppConstants.HttpStatus.getStatus(httpCode);
    }

    public void reset(CustomStatus cs) {
        this.code = cs.getCode();
        this.status =cs.isStatus();
        this.message = cs.getMessage();
    }

    public static CustomStatus map(boolean status, int code, String message){
        CustomStatus customStatus = new CustomStatus();
        customStatus.setStatus(status);
        customStatus.setCode(code);
        customStatus.setMessage(message);
        return customStatus;
    }

    public static CustomStatus map(int code, String message){
        CustomStatus customStatus = new CustomStatus();
        customStatus.setStatus(code == AppConstants.HttpStatusCode.OK);
        customStatus.setCode(code);
        customStatus.setMessage(message);
        return customStatus;
    }

    public static CustomStatus strip(String message){
        CustomStatus customStatus = Util.stripMessage(message);
        customStatus.setStatus(customStatus.getCode() == AppConstants.HttpStatusCode.OK);
        return customStatus;
    }

	public static CustomStatus strip(String message, Map<String, Object> metadata) {
		CustomStatus customStatus = Util.stripMessage(message);
		customStatus.setMetadata(metadata);
		customStatus.setStatus(customStatus.getCode() == AppConstants.HttpStatusCode.OK);
		return customStatus;
	}
}
