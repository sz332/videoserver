package com.acme.video.mediaserver;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acme.videoserver.core.mediaserver.MediaChunk;
import com.acme.videoserver.core.mediaserver.MediaServerAccessException;
import com.acme.videoserver.core.mediaserver.MediaStream;

public class DefaultMediaStream implements MediaStream {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMediaStream.class);

	private final Path localPath;

	public DefaultMediaStream(Path localPath) {
		this.localPath = localPath;
	}

	@Override
	public MediaChunk chunk(int start, int end) throws MediaServerAccessException {
		return chunkPart(start, end);
	}

	@Override
	public MediaChunk chunk(int start) throws MediaServerAccessException {
		
		if (start == 0) {
			return new FullMediaChunk(localPath, "video/mp4");
		} else {
			return chunkPart(start, (int) localPath.toFile().length());
		}
	}

	private MediaChunk chunkPart(int start, int end) throws MediaServerAccessException {

		try (FileInputStream fis = new FileInputStream(localPath.toFile())) {

			int fileSize = (int) localPath.toFile().length();

			// FIXME ugly
			
			end = Math.min(end, fileSize - 1);

			int chunkSize = (end - start) + 1;

			LOGGER.info("Returing file chunk start = {} end = {} chunk = {} total = {}", start, end, chunkSize, fileSize);

			ByteBuffer bytes = ByteBuffer.wrap(new byte[chunkSize]);

			fis.getChannel().read(bytes, start);

			byte[] data = bytes.array();

			return new DefaultMediaChunk(start, end, fileSize, "video/mp4", data);
		} catch (IOException e) {
			throw new MediaServerAccessException(e);
		}
	}

}
