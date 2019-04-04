package com.acme.videoserver.core.mediaserver;

import java.util.UUID;

public interface Media {

    UUID id();
    
    String mimeType();
    
    byte[] data();

}
