package com.pocket.pos.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiSimpleResponse {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    public LocalDateTime timestamp;
    public HttpStatus status;
    public String message;
    public List<String> errors;
	public ApiSimpleResponse( HttpStatus status, String message, List<String> errors) {
		super();
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.message = message;
		this.errors = errors;
	}
	
	public ApiSimpleResponse(HttpStatus status, String message) {
		super();
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.message = message;
		this.errors = null;
	}
    
    
}
