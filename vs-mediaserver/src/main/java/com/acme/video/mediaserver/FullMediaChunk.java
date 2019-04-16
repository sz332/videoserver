package com.acme.video.mediaserver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.takes.Response;
import org.takes.rs.RsFluent;

import com.acme.videoserver.core.mediaserver.MediaChunk;
import com.acme.videoserver.core.mediaserver.MediaServerAccessException;

public class FullMediaChunk implements MediaChunk {

	private final Path localPath;
	private final String mimeType;

	public FullMediaChunk(Path localPath, String mimeType) {
		this.localPath = localPath;
		this.mimeType = mimeType;
	}

	@Override
	public Response asResponse() throws MediaServerAccessException {

		try {

			return new RsFluent().withStatus(200)
					.withHeader("Content-Type", mimeType)
					.withHeader("Content-Disposition", "inline; filename=\"output.mp4\"")
					.withBody(Files.readAllBytes(localPath));

		} catch (IOException e) {
			throw new MediaServerAccessException(e);
		}
	}

}
