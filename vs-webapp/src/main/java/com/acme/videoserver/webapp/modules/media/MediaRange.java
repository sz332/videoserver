package com.acme.videoserver.webapp.modules.media;

import org.takes.Response;

import com.acme.videoserver.core.mediaserver.MediaChunk;
import com.acme.videoserver.core.mediaserver.MediaServerAccessException;
import com.acme.videoserver.core.mediaserver.MediaStream;

public class MediaRange {

	private final MediaStream stream;
	private final Iterable<String> head;
	private final int limit;

	public MediaRange(MediaStream stream, Iterable<String> head, int limit) {
		this.stream = stream;
		this.head = head;
		this.limit = limit;
	}

	public Response asResponse() throws MediaServerAccessException {

		MediaChunk chunk = null;

		for (String s : head) {
			if (s.trim().startsWith("Range:")) {
				String[] values = s.substring(s.indexOf("=") + 1).split("-");
				if (values.length == 1 || values.length == 2) {
					int start = Integer.parseInt(values[0]);
					chunk = stream.chunk(start, start + limit);
				}
			}
		}

		if (chunk == null) {
			chunk = stream.chunk(0);
		}

		return chunk.asResponse();
	}

}
