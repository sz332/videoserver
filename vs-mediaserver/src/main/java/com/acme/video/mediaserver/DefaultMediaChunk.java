package com.acme.video.mediaserver;

import org.takes.Response;
import org.takes.rs.RsFluent;

import com.acme.videoserver.core.mediaserver.MediaChunk;

public class DefaultMediaChunk implements MediaChunk {

	private final int startBytes;
	private final int endBytes;
	private final int totalBytes;
	private final String mimeType;
	private final byte[] data;

	public DefaultMediaChunk(int startBytes, int endBytes, int totalBytes, String mimeType, byte[] data) {
		this.startBytes = startBytes;
		this.endBytes = endBytes;
		this.totalBytes = totalBytes;
		this.mimeType = mimeType;
		this.data = data;
	}
	
	@Override
	public Response asResponse() {
		return new RsFluent().withStatus(206)
				.withHeader("Content-Range", String.format("bytes %d-%d/%d", startBytes, endBytes, totalBytes))
				.withHeader("Accept-Ranges", "bytes")
				.withHeader("Content-Length", String.valueOf(data.length))
				.withHeader("Content-Type", mimeType)
				.withBody(data);
	}
	
}
