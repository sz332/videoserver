package com.acme.videoserver.core.mediaserver;

import org.takes.Response;

public interface MediaChunk {

	Response asResponse() throws MediaServerAccessException;

}
