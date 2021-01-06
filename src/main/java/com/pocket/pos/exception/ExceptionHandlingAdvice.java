package com.pocket.pos.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pocket.pos.util.ResponseEntityBuilder;

@ControllerAdvice
@ResponseBody
public class ExceptionHandlingAdvice extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<Object> handelConstraintViolation(ConstraintViolationException ex) {

		logger.error(ex.getSQLException().getMessage());
		List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());
        details.add(ex.getConstraintName());
        details.add(ex.getSQLState());
        details.add(ex.getSQLException().getMessage());
        
        
        ApiError err = new ApiError(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST, 
            "Data integrity violation" ,
            details);
        
        return ResponseEntityBuilder.build(err);
	}
	
	@ExceptionHandler(MissingParametersException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<Object> handelMissingParamter(MissingParametersException ex) {

		
		List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());
       
        
        
        ApiError err = new ApiError(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST, 
            "Missing Paramters" ,
            details);
        
        return ResponseEntityBuilder.build(err);
	}

	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	ResponseEntity<Object> handelResourceNotFound(ResourceNotFoundException ex) {
		
		logger.warn(ex.getMessage());
		List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());
        
        ApiError err = new ApiError(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND, 
            "Resource Not Found" ,
            details);
        
        return ResponseEntityBuilder.build(err);
	}
	
	
	@ExceptionHandler(BulkNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	ResponseEntity<Object> handelBulkNotFoundHandler(BulkNotFoundException ex) {
		
		logger.warn(ex.getMessage());
		List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());
        
        ApiError err = new ApiError(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND, 
            "Resource Not Found" ,
            details);
        
        return ResponseEntityBuilder.build(err);
	}

	
	@ExceptionHandler(ResourceStateCahngedException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	ResponseEntity<Object> resourceStateCahngedHandler(ResourceStateCahngedException ex) {
		
		logger.warn(ex.getMessage());
		List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());
        
        ApiError err = new ApiError(
            LocalDateTime.now(),
            HttpStatus.CONFLICT, 
            "Resource State Changed" ,
            details);
        
        return ResponseEntityBuilder.build(err);
	}
}
