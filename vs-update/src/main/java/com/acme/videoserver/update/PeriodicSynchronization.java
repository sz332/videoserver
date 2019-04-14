package com.acme.videoserver.update;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acme.videoserver.core.library.Library;
import com.acme.videoserver.core.library.LibraryAccessException;
import com.acme.videoserver.core.library.Videoclip;
import com.acme.videoserver.core.mediaserver.MediaServer;
import com.acme.videoserver.core.storage.RemoteLocation;
import com.acme.videoserver.core.storage.Storage;
import com.acme.videoserver.core.storage.StorageAccessException;
import com.acme.videoserver.core.storage.StorageConnection;
import com.acme.videoserver.core.storage.Traversal;
import com.acme.videoserver.storage.common.Extension;
import com.acme.videoserver.storage.metadata.XmlVideoclip;

public class PeriodicSynchronization {

	private static final Logger LOGGER = LoggerFactory.getLogger(PeriodicSynchronization.class);

	private final Storage storage;
	private final Library library;
	private final MediaServer mediaServer;

	public PeriodicSynchronization(Storage storage, Library library, MediaServer mediaServer) {
		this.storage = storage;
		this.library = library;
		this.mediaServer = mediaServer;
	}

	public void synchronize() {
		try (StorageConnection connection = storage.connect()) {

			Traversal traversal = new Traversal(connection.root());

			traversal.each(this::processNode);

		} catch (StorageAccessException e) {
			LOGGER.error("", e);
		}
	}

	private void processNode(RemoteLocation parent, RemoteLocation location) {
		try {
			if ("vxml".equals(location.extension())) {
				Videoclip clip = new XmlVideoclip(new String(location.download(), StandardCharsets.UTF_8));
				library.add(clip);

				mediaServer.add(clip, videoPath(parent, location));
			}
		} catch (StorageAccessException | LibraryAccessException e) {
			LOGGER.error("", e);
		}
	}
	
	// FIXME this assumes that we have a video with the same name as the vxml file but with .mp4 extension
	private String videoPath(RemoteLocation parent, RemoteLocation location) throws StorageAccessException {

		Path path = Paths.get(location.path());
		
		String extension = new Extension(path.getFileName().toString()).value();
		
		String filename = path.getFileName().toString();
		
		String newFileName = filename.substring(0, filename.length() - extension.length()) + "mp4";

		return Paths.get(parent.path(), newFileName).toString();
	}

}
