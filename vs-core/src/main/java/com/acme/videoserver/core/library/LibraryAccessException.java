package com.acme.videoserver.core.library;

public class LibraryAccessException extends Exception {

	private static final long serialVersionUID = -2356984032058241969L;

	public LibraryAccessException(Exception e) {
		super(e);
	}
	
}
