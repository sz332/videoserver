package com.acme.videoserver.core.storage;

public interface Storage {

    StorageConnection connect(String user, String password) throws StorageAccessException;

}
