package com.acme.videoserver.update;

import java.nio.charset.StandardCharsets;

import com.acme.videoserver.core.library.Library;
import com.acme.videoserver.core.storage.RemoteLocation;
import com.acme.videoserver.core.storage.Storage;
import com.acme.videoserver.core.storage.StorageAccessException;
import com.acme.videoserver.core.storage.StorageConnection;
import com.acme.videoserver.core.storage.Traversal;
import com.acme.videoserver.storage.metadata.XmlMetadata;

public class PeriodicSynchronization {

	private final Storage storage;
	private final Library library;
	
	public PeriodicSynchronization(Storage storage, Library library) {
		this.storage = storage;
		this.library = library;
	}
	
	public void synchronize() {
		try {
			StorageConnection connection = storage.connect();
			
			Traversal traversal = new Traversal(connection.root());

			traversal.each(this::processNode);
			
		} catch (StorageAccessException e) {
			e.printStackTrace();
		} 
	}
	
	private void processNode(RemoteLocation location) {
		try {
			if ("vxml".equals(location.extension())) {
				XmlMetadata metadata = new XmlMetadata(new String(location.download(), StandardCharsets.UTF_8));
				
			}
		} catch (StorageAccessException e) {
			e.printStackTrace();
		}
	}
	
}
