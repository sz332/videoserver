package com.acme.videoserver.core.storage;

public interface StorageConnection {

    RemoteLocation root();
    
    RemoteLocation resolve(String path);
    
}
