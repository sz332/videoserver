package com.acme.videoserver.core.storage;

public interface StorageConnection {

    RemoteLocation root() throws StorageAccessException;
    
    RemoteLocation resolve(String path) throws StorageAccessException;
    
}
