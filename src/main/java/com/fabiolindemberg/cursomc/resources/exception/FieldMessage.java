package com.fabiolindemberg.cursomc.resources.exception;

import java.io.Serializable;

public class FieldMessage implements Serializable{
	
	private static final long serialVersionUID = 1;
	
	private String fieldName;
	private String message;
	
	public FieldMessage(String field, String defaultMessage) {
		super();
		this.fieldName = field;
		this.message = defaultMessage;
	}

    public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;

	}
}
