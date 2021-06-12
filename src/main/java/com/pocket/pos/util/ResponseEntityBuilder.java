package com.pocket.pos.util;

import org.springframework.http.ResponseEntity;

import com.pocket.pos.exception.ApiSimpleResponse;

public class ResponseEntityBuilder {
	
	public static ResponseEntity<Object> build(ApiSimpleResponse apiError) {
        return new ResponseEntity<>(apiError, apiError.status);
  }
}
