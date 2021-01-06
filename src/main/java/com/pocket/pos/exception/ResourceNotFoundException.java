package com.pocket.pos.exception;

import java.lang.reflect.Method;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String notFound;
	private Long resourceId;
	private Class<?> resourceClass;

	public ResourceNotFoundException(Long id, Class<?> resourceClass) {
		resourceId = id;
		this.resourceClass = resourceClass;
		notFound = "Not Found " + resourceClass.getSimpleName() + " with ID : " + resourceId;
	}

	public ResourceNotFoundException(Class<?> resourceClass, Method queryFunction, String... params) {

		this.resourceClass = resourceClass;
		buildMessage(queryFunction.getName(),params);
	}

	private void buildMessage(String queryFunctionName, String... params) {
		String[] parametersName = queryFunctionName.split("By")[1].split("And");
		notFound = "Not Found " + resourceClass.getSimpleName() + " with ";

		for (int i = 0; i < params.length; i++) {
			notFound += parametersName[i] + ": " + params[i] + " And ";
		}
	}

	@Override
	public String getMessage() {

		return notFound;
	}

}
