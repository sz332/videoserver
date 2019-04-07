package com.acme.videoserver.library.common;

public class DataAccessException extends RuntimeException {

	private static final long serialVersionUID = 3728157838453847047L;

	public DataAccessException(Exception e) {
		super(e);
	}

}
