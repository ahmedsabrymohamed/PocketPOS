package com.pocket.pos.exception;

public class MissingParametersException extends RuntimeException{
	

	private static final long serialVersionUID = 1L;
	Class<?> resourceClass;
	
	public MissingParametersException(Class<?> resourceClass) {
		this.resourceClass = resourceClass;
	}
	
	@Override
	public String getMessage() {
		
		return resourceClass.getSimpleName()+"Missing required fields";
	}
	
	

}
