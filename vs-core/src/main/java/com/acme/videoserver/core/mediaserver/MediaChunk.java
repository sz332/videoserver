package com.acme.videoserver.core.mediaserver;

public interface MediaChunk {

    String mimeType();
    
    int totalBytes();
    
    int startBytes();
    
    int endBytes();

    byte[] data();
    
}
