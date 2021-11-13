package com.lld.cabbooking.expections;

public class RiderAlreadyExistsException extends RuntimeException {
	public RiderAlreadyExistsException() {
		super();
	}
	
	public RiderAlreadyExistsException(final String message) {
		super(message);
	}
}
