package com.student.student.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomResponse<T> extends CustomStatus {
	private T data;

	public CustomResponse(CustomStatus customStatus) {
		this.status = customStatus.isStatus();
		this.code = customStatus.getCode();
		this.message = customStatus.getMessage();
		this.httpStatus = customStatus.getHttpStatus();

		// For logging purpose only
		this.setLogCategory(customStatus.getLogCategory());
		this.setUniqueIdentity(customStatus.getUniqueIdentity());

    }

	public CustomResponse(CustomStatus customStatus, T obj) {
		System.out.println("Data Object"+obj);
		this.status = customStatus.isStatus();
		this.code = customStatus.getCode();
		this.message = customStatus.getMessage();
		this.data = obj;
		this.httpStatus = customStatus.getHttpStatus();

		// For logging purpose only
		this.setLogCategory(customStatus.getLogCategory());
		this.setUniqueIdentity(customStatus.getUniqueIdentity());
		this.data = obj;

    }

}
