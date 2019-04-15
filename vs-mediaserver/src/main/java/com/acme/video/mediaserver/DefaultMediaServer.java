package com.acme.video.mediaserver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.cactoos.scalar.StickyScalar;
import org.cactoos.scalar.UncheckedScalar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acme.videoserver.core.library.Videoclip;
import com.acme.videoserver.core.mediaserver.ConstantMedia;
import com.acme.videoserver.core.mediaserver.Media;
import com.acme.videoserver.core.mediaserver.MediaServer;
import com.acme.videoserver.core.mediaserver.MediaServerAccessException;
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
	public Media stream(String mediaId) throws MediaServerAccessException {

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

			return new ConstantMedia(mediaId, "video/mp4", Files.readAllBytes(localCopies.get(mediaId)));

		} catch (StorageAccessException | IOException e) {
			throw new MediaServerAccessException(e);
		}
	}

}
