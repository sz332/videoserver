package com.acme.videoserver.core.mediaserver;

import java.util.UUID;

public interface MediaServer {

    Media stream(UUID mediaId);

}
