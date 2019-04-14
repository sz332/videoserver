package com.acme.video.mediaserver;

import com.acme.videoserver.core.mediaserver.Media;
import com.acme.videoserver.core.mediaserver.MediaServer;
import com.acme.videoserver.core.storage.Storage;

public class DefaultMediaServer implements MediaServer {

	private final Storage storage;
	
	public DefaultMediaServer(Storage storage) {
		this.storage = storage;
	}
	
	@Override
	public Media stream(String mediaId) {
		return null;
	}

}
