package com.acme.videoserver.core.mediaserver;

public class MediaServerAccessException extends Exception {

	private static final long serialVersionUID = -7497366555933322063L;
	
	public MediaServerAccessException(Exception e) {
		super(e);
	}
	
	public MediaServerAccessException(String s) {
		super(s);
	}

}
