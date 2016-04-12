package com.beesinergi.exception;

public class ErrorHolder {

	private String errorKey ;
	private Object[] parameter;
	
	public ErrorHolder(String error) {
		this.errorKey = error;
	}

	public ErrorHolder(String error, Object...parameter) {
		this.errorKey = error;
		this.parameter = parameter;
	}
	
	public String getError() {
		return errorKey;
	}

	
	public Object[] getParameter() {
		return parameter;
	}

	public void setParameter(Object[] parameter) {
		this.parameter = parameter;
	}
	
}
