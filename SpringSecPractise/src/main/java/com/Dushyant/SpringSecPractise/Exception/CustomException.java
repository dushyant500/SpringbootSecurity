package com.Dushyant.SpringSecPractise.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomException extends RuntimeException{

	private String message;
	private HttpStatus hts;
	
    public CustomException(HttpStatus hts, String message) {
        super(message);    // calls RuntimeException's constructor
        this.hts = hts;
    }

    public HttpStatus getHts() {
        return hts;
    }

    @Override
    public String toString() {
        return "CustomException{status=" + hts + ", message=" + getMessage() + "}";
    }
}
