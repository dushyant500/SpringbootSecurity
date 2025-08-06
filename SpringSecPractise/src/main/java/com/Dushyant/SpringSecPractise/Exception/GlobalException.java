package com.Dushyant.SpringSecPractise.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

	
	  @ExceptionHandler(CustomException.class) 
	  public ResponseEntity<?> handleException(CustomException e){ 
		  ErrorResponse er = new
	      ErrorResponse(HttpStatus.INSUFFICIENT_STORAGE.value(), e.getMessage());
	      return new ResponseEntity(er,HttpStatus.INSUFFICIENT_STORAGE); 
	  }
	 
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> handleException1(RuntimeException e){
		ErrorResponse er = new ErrorResponse(HttpStatus.GATEWAY_TIMEOUT.value(), e.getMessage());
		return new ResponseEntity(er,HttpStatus.BAD_GATEWAY);
	}
	
}
