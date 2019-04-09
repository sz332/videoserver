package com.acme.videoserver.core.storage;

public interface Storage {

    StorageConnection connect() throws StorageAccessException;

}
