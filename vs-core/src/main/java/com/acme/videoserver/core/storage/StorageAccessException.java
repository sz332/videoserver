package com.acme.videoserver.core.storage;

public class StorageAccessException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -1486366889943850225L;

    public StorageAccessException(Exception e) {
        super(e);
    }

}
