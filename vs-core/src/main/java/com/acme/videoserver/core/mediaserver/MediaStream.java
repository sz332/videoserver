package com.acme.videoserver.core.mediaserver;

public interface MediaStream {

	MediaChunk chunk(int start, int end) throws MediaServerAccessException ;
	MediaChunk chunk(int start) throws MediaServerAccessException ;
	
}
