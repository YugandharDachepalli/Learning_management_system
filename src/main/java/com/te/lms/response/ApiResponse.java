package com.te.lms.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiResponse<T> {
	
	public ApiResponse(String message, T data) {
		super();
		this.message = message;
		this.data = data;
	}

	private String message;
	private LocalDateTime timestamp = LocalDateTime.now();
	private String token;
	private T data;

//	public ApiResponse(String message, String token, T data) {
//		super();
//		this.message = message;
//		this.token = token;
//		this.data = data;
//	}
}
