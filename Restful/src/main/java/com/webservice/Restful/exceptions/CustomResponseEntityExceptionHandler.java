package com.webservice.Restful.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.webservice.Restful.user.UserNotFoundException;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorHandling> handleAllException(Exception ex, WebRequest request) throws Exception {
		
		ErrorHandling errorHandling = new ErrorHandling(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorHandling>(errorHandling,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorHandling> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
		
		ErrorHandling errorHandling = new ErrorHandling(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorHandling>(errorHandling,HttpStatus.NOT_FOUND);
	}
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		
		ErrorHandling errorHandling = new ErrorHandling(LocalDateTime.now(), "Number Of Errors are: " + ex.getErrorCount() +"first error is:" + ex.getFieldError().getDefaultMessage(), request.getDescription(false));
		return new ResponseEntity(errorHandling,HttpStatus.BAD_REQUEST);	}
	
}
