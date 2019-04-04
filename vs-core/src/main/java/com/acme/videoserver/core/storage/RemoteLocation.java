package com.acme.videoserver.core.storage;

import java.util.List;

public interface RemoteLocation {

    String name();
    
    String path();
    
    byte[] download();
    
    boolean hasChildren();
    
    List<RemoteLocation> children();
    
}
