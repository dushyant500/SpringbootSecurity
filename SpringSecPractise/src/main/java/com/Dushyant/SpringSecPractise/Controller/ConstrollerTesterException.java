package com.Dushyant.SpringSecPractise.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Dushyant.SpringSecPractise.Exception.CustomException;
import com.Dushyant.SpringSecPractise.Exception.ErrorResponse;

@RestController
@RequestMapping("/exception")
public class ConstrollerTesterException {

	// 400 bad request
	@GetMapping("/test")
	public void test() {
		throw new CustomException(HttpStatus.BAD_REQUEST, "Custom Exception occured");
	}

	/*
	 * @ExceptionHandler public ResponseEntity<?> handleException(CustomException
	 * e){ ErrorResponse er = new ErrorResponse(e.getHts().value(), e.getMessage());
	 * return new ResponseEntity(er,e.getHts()); }
	 */

	@GetMapping("/test1")
	public void test1() {
		throw new IllegalArgumentException("Custom second Exception occured");
	}

}
