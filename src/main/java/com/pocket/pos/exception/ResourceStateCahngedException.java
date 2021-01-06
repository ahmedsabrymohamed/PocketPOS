package com.pocket.pos.exception;

public class ResourceStateCahngedException  extends RuntimeException{

	private static final long serialVersionUID = 1L;
	Long resourceId ;
	Class<?> resourceClass;
	public ResourceStateCahngedException(Long id, Class<?> resourceClass) {
		resourceId = id;
		this.resourceClass = resourceClass;
	}
	
	@Override
	public String getMessage() {
		return "State changed for "+resourceClass.getSimpleName()+" with ID : "+resourceId;
	}
	
	
}
