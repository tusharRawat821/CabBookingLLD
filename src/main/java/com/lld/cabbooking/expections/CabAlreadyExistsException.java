package com.lld.cabbooking.expections;

public class CabAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public CabAlreadyExistsException() {
		super();
	}
	
	public CabAlreadyExistsException(final String message) {
		super(message);
	}
}
