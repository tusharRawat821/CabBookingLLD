package com.lld.cabbooking.expections;

public class CabNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CabNotFoundException(final String msg) {
		super(msg);
	}
	
	public CabNotFoundException() {
		super();
	}
}
