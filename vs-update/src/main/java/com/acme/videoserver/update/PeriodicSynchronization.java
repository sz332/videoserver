package com.acme.videoserver.update;

import com.acme.videoserver.core.library.Library;
import com.acme.videoserver.core.storage.Storage;

public class PeriodicSynchronization {

	private final Storage storage;
	private final Library library;
	
	public PeriodicSynchronization(Storage storage, Library library) {
		this.storage = storage;
		this.library = library;
	}
	
	public void synchronize() {
		
	}
	
}
