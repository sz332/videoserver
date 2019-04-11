package com.acme.videoserver.storage.filesystem;

import java.io.File;
import java.nio.file.Path;

import com.acme.videoserver.core.storage.RemoteLocation;
import com.acme.videoserver.core.storage.StorageAccessException;
import com.acme.videoserver.core.storage.StorageConnection;

public class FileSystemStorageConnection implements StorageConnection {

    private final Path root;
    
    public FileSystemStorageConnection(Path root) {
        this.root = root;
    }

    @Override
    public RemoteLocation root() throws StorageAccessException {
        return new FileSystemRemoteLocation(root);
    }

    @Override
    public RemoteLocation resolve(String path) throws StorageAccessException {
        return new FileSystemRemoteLocation(new File(path).toPath());
    }
    
    @Override
    public void close() throws StorageAccessException {
    	// Do nothing 
    }
    
}
