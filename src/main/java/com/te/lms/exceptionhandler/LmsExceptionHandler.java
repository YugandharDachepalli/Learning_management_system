package com.te.lms.exceptionhandler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.google.common.collect.Maps;
import com.te.lms.exception.BatchDeletionFailedException;
import com.te.lms.exception.BatchUpdationFailedException;
import com.te.lms.exception.EmployeeCannotBeApprovedException;
import com.te.lms.exception.EmployeeNotFoundException;
import com.te.lms.exception.MentorDeletionFailedException;
import com.te.lms.exception.MentorUpdationFailedException;
import com.te.lms.exception.NoMentorsFoundException;
import com.te.lms.exception.RegistrationFailedException;

@RestControllerAdvice
public class LmsExceptionHandler {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(RegistrationFailedException.class)
	public Map<String, String> handler(RegistrationFailedException ex) {
		Map<String, String> map = Maps.newHashMap();
		map.put("message", ex.getMessage());
		return map;
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MentorUpdationFailedException.class)
	public Map<String, String> handler(MentorUpdationFailedException ex) {
		Map<String, String> map = Maps.newHashMap();
		map.put("message", ex.getMessage());
		return map;
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MentorDeletionFailedException.class)
	public Map<String, String> handler(MentorDeletionFailedException ex) {
		Map<String, String> map = Maps.newHashMap();
		map.put("message", ex.getMessage());
		return map;
	}
	
	
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BatchUpdationFailedException.class)
	public Map<String, String> handler(BatchUpdationFailedException ex) {
		Map<String, String> map = Maps.newHashMap();
		map.put("message", ex.getMessage());
		return map;
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BatchDeletionFailedException.class)
	public Map<String, String> handler(BatchDeletionFailedException ex) {
		Map<String, String> map = Maps.newHashMap();
		map.put("message", ex.getMessage());
		return map;
	}
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(EmployeeCannotBeApprovedException.class)
	public Map<String, String> handler(EmployeeCannotBeApprovedException ex) {
		Map<String, String> map = Maps.newHashMap();
		map.put("message", ex.getMessage());
		return map;
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(EmployeeNotFoundException.class)
	public Map<String, String> handler(EmployeeNotFoundException ex) {
		Map<String, String> map = Maps.newHashMap();
		map.put("message", ex.getMessage());
		return map;
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NoMentorsFoundException.class)
	public Map<String, String> handler(NoMentorsFoundException ex) {
		Map<String, String> map = Maps.newHashMap();
		map.put("message", ex.getMessage());
		return map;
	}

}
