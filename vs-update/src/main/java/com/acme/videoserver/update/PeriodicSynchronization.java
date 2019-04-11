package com.acme.videoserver.update;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acme.videoserver.core.library.Library;
import com.acme.videoserver.core.library.LibraryAccessException;
import com.acme.videoserver.core.library.Videoclip;
import com.acme.videoserver.core.storage.RemoteLocation;
import com.acme.videoserver.core.storage.Storage;
import com.acme.videoserver.core.storage.StorageAccessException;
import com.acme.videoserver.core.storage.StorageConnection;
import com.acme.videoserver.core.storage.Traversal;
import com.acme.videoserver.storage.metadata.XmlVideoclip;

public class PeriodicSynchronization {

	private static final Logger LOGGER = LoggerFactory.getLogger(PeriodicSynchronization.class);

	private final Storage storage;
	private final Library library;

	public PeriodicSynchronization(Storage storage, Library library) {
		this.storage = storage;
		this.library = library;
	}

	public void synchronize() {
		try (StorageConnection connection = storage.connect()) {

			Traversal traversal = new Traversal(connection.root());

			traversal.each(this::processNode);

		} catch (StorageAccessException e) {
			LOGGER.error("", e);
		}
	}

	private void processNode(RemoteLocation location) {
		try {
			if ("vxml".equals(location.extension())) {
				Videoclip clip = new XmlVideoclip(new String(location.download(), StandardCharsets.UTF_8));
				library.add(clip);
			}
		} catch (StorageAccessException | LibraryAccessException e) {
			LOGGER.error("", e);
		}
	}

}
