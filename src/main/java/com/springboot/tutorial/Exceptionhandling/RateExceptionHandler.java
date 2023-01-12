package com.springboot.tutorial.Exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.springboot.tutorial.entity.ErrorMessage;

@ControllerAdvice
@ResponseStatus
public class RateExceptionHandler {
	@ExceptionHandler(RateException.class)
	public ResponseEntity<ErrorMessage> rateNotFoundException(RateException exception, WebRequest request) {
		ErrorMessage description=new ErrorMessage(400,exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(description);
	}
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorMessage> internalServerException(RuntimeException exception, WebRequest request) {
		ErrorMessage description=new ErrorMessage(500,exception.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(description);
	}
}
