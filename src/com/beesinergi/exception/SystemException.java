package com.beesinergi.exception;

import java.util.LinkedList;
import java.util.List;

public class SystemException extends RuntimeException {

	private static final long serialVersionUID = -7912772858921407612L;
	private List<ErrorHolder> errors = new LinkedList<ErrorHolder>();

	public SystemException(ErrorHolder errorHolder) {
		getErrors().add(errorHolder);
	}

	public SystemException(Throwable e) {
		super(e);
	}

	public SystemException(List<ErrorHolder> errors) {
		this.errors = errors;
	}

	public List<ErrorHolder> getErrors() {
		return errors;
	}

	public SystemException(String message) {
		super(message);
	}

	public Boolean isEmpty() {
		return errors.size() == 0;
	}

}
