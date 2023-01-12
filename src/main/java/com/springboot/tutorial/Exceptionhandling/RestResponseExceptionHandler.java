package com.springboot.tutorial.Exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springboot.tutorial.entity.ErrorMessage;

@ControllerAdvice
@ResponseStatus
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {
//	@ExceptionHandler(DepartmentException.class)
//	public ResponseEntity<ErrorMessage> departmentNotFoundException(DepartmentException exception, WebRequest request) {
//		ErrorMessage message=new ErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
//		return ResponseEntity.status(HttpStatus.CREATED).body(message);
//	}
}
