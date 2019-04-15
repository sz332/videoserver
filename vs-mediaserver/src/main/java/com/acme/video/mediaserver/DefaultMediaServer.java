package com.acme.video.mediaserver;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.cactoos.scalar.StickyScalar;
import org.cactoos.scalar.UncheckedScalar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acme.videoserver.core.library.Videoclip;
import com.acme.videoserver.core.mediaserver.ConstantMediaChunk;
import com.acme.videoserver.core.mediaserver.ConstantRange;
import com.acme.videoserver.core.mediaserver.MediaChunk;
import com.acme.videoserver.core.mediaserver.MediaServer;
import com.acme.videoserver.core.mediaserver.MediaServerAccessException;
import com.acme.videoserver.core.mediaserver.Range;
import com.acme.videoserver.core.storage.RemoteLocation;
import com.acme.videoserver.core.storage.Storage;
import com.acme.videoserver.core.storage.StorageAccessException;

public class DefaultMediaServer implements MediaServer {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMediaServer.class);

	private final Map<String, String> storageLocations = new HashMap<>();
	private final Map<String, Path> localCopies = new HashMap<>();

	private final Storage storage;
	private final UncheckedScalar<Path> temporaryDirectoryPath;

	public DefaultMediaServer(Storage storage) {
		this.storage = storage;
		this.temporaryDirectoryPath = new UncheckedScalar<>(new StickyScalar<>(() -> {
			Path tempDir = Files.createTempDirectory("videoserver");
			LOGGER.info("Created temporary directory: {}", tempDir);
			return tempDir;
		}));
	}

	@Override
	public void add(Videoclip videoclip, String location) {
		storageLocations.put(videoclip.uuid(), location);
	}

	@Override
	public MediaChunk stream(String mediaId) throws MediaServerAccessException {
		return stream(mediaId, new ConstantRange(0));
	}

	@Override
	public MediaChunk stream(String mediaId, Range range) throws MediaServerAccessException {

		try {

			if (!storageLocations.containsKey(mediaId)) {
				throw new MediaServerAccessException("Video not found in media server database");
			}

			if (!localCopies.containsKey(mediaId)) {
				RemoteLocation remoteLocation = storage.connect().resolve(storageLocations.get(mediaId));
				byte[] bytes = remoteLocation.download();
				Path path = Files.createTempFile(temporaryDirectoryPath.value(), "clip", ".mp4");
				Files.write(path, bytes);
				localCopies.put(mediaId, path);
			}

			Path localPath = localCopies.get(mediaId);

			byte[] data;

			// handle chunks based on
			// https://medium.com/@daspinola/video-stream-with-node-js-and-html5-320b3191a6b6

			try (FileInputStream fis = new FileInputStream(localPath.toFile())) {

				int fileSize = (int) localPath.toFile().length();

				int start = range.start();
				int end = range.openEnded() ? fileSize - 1 : range.end();

				end = Math.min(end, fileSize - 1);
				
				int chunkSize = (end - start) + 1;
				
				LOGGER.info("Returing file chunk start = {} end = {} chunk = {} total = {}", start, end, chunkSize, fileSize);

				ByteBuffer bytes = ByteBuffer.wrap(new byte[chunkSize]);

				fis.getChannel().read(bytes, start);
				data = bytes.array();

				return new ConstantMediaChunk(start, end, fileSize, "video/mp4", data);
			}

		} catch (StorageAccessException | IOException e) {
			throw new MediaServerAccessException(e);
		}
	}

}
