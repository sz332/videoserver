package com.acme.videoserver.storage.filesystem;

import java.nio.file.Path;

import com.acme.videoserver.core.storage.Storage;
import com.acme.videoserver.core.storage.StorageAccessException;
import com.acme.videoserver.core.storage.StorageConnection;

public class FilesystemStorage implements Storage {

    private final Path root;
    
    public FilesystemStorage(Path root) {
        this.root = root;
    }
    
    @Override
    public StorageConnection connect(String user, String password) throws StorageAccessException {
        return new FileSystemStorageConnection(root);
    }
    
}
