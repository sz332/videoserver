package com.acme.videoserver.webapp.modules.media;

import org.takes.Response;

import com.acme.videoserver.core.mediaserver.MediaServerAccessException;
import com.acme.videoserver.core.mediaserver.MediaStream;

public class RsMediaStreamChunk {

	private final MediaStream stream;
	private final Iterable<String> head;
	private final int limit;

	public RsMediaStreamChunk(MediaStream stream, Iterable<String> head, int limit) {
		this.stream = stream;
		this.head = head;
		this.limit = limit;
	}

	public Response asResponse() throws MediaServerAccessException {
	
		for (String s : head) {
			if (s.trim().startsWith("Range:")) {
				String[] values = s.substring(s.indexOf("=") + 1).split("-");
				if (values.length == 1 || values.length == 2) {
					int start = Integer.parseInt(values[0]);
					return stream.chunk(start, start + limit).asResponse();
				}
			}
		}

		return stream.chunk(0).asResponse();
	}

}
