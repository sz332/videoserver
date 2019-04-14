package com.acme.videoserver.core.mediaserver;

public interface Media {

    String id();
    
    String mimeType();
    
    byte[] data();

}
