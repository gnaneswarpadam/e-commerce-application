package com.dev.ecommerceapp.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<String, String>();
		ex.getFieldErrors().stream().forEach((error) -> errorMap.put(error.getField(), error.getDefaultMessage()));

		return new ResponseEntity<Map<String, String>>(errorMap, HttpStatusCode.valueOf(400));
	}
	
	@ExceptionHandler(AppException.class)
	public ResponseEntity<Map<String, String>> handleAppException(AppException ex) {
		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("error", ex.getMessage());
		return new ResponseEntity<Map<String, String>>(errorMap, HttpStatusCode.valueOf(400));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleGlobalException(Exception ex){
		log.error(ex.toString());
		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("error", "UNHANDLED EXCEPTION OCCURED");
		return new ResponseEntity<Map<String, String>>(errorMap, HttpStatusCode.valueOf(500));
	}

}
