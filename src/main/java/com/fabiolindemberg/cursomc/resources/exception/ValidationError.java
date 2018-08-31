package com.fabiolindemberg.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> erros = new ArrayList<FieldMessage>();
	
	public ValidationError(Integer status, String msg, Long timesTamp) {
		super(status, msg, timesTamp);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessage> getErros() {
		return erros;
	}

	public void addError(String field, String defaultMessage) {
		erros.add(new FieldMessage(field, defaultMessage));
	}

}
