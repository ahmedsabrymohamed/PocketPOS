package com.pocket.pos.util;

import org.springframework.http.ResponseEntity;

import com.pocket.pos.exception.ApiError;

public class ResponseEntityBuilder {
	
	public static ResponseEntity<Object> build(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.status);
  }
}
