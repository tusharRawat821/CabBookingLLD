package com.lld.cabbooking.expections;

public class RiderNotFoundException extends RuntimeException {
	public RiderNotFoundException() {
		super();
	}
	public RiderNotFoundException(final String msg) {
		super(msg);
	}
}
