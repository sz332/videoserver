package com.acme.videoserver.core.storage;

import java.util.List;

public interface RemoteLocation {

    String name() throws StorageAccessException;
    
    String extension() throws StorageAccessException;

    String path() throws StorageAccessException;

    byte[] download() throws StorageAccessException;

    boolean hasChildren() throws StorageAccessException;

    List<RemoteLocation> children() throws StorageAccessException;

}
