package com.acme.videoserver.core.storage;

public interface StorageConnection extends AutoCloseable {

	RemoteLocation root() throws StorageAccessException;

	RemoteLocation resolve(String path) throws StorageAccessException;
	
	@Override
	void close() throws StorageAccessException;

}
