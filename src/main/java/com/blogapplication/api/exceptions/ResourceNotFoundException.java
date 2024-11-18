package com.blogapplication.api.exceptions;

public class ResourceNotFoundException  extends RuntimeException {

	String resourceName;
	String fieldName;
	Integer fieldvalue;
	
	public ResourceNotFoundException(String resourceName, String fieldName, Integer fieldvalue) {
		super(String.format("%s not found with this %s : %s", resourceName, fieldName, fieldvalue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldvalue = fieldvalue;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public long getFieldvalue() {
		return fieldvalue;
	}

	public void setFieldvalue(Integer fieldvalue) {
		this.fieldvalue = fieldvalue;
	}

}
