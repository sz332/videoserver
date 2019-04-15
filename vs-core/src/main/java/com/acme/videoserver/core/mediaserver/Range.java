package com.acme.videoserver.core.mediaserver;

public interface Range {

	int start();
	int end();
	
	boolean openEnded();
	
}
