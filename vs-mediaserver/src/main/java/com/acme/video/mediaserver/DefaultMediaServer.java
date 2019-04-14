package com.acme.video.mediaserver;

import java.util.HashMap;
import java.util.Map;

import com.acme.videoserver.core.library.Videoclip;
import com.acme.videoserver.core.mediaserver.Media;
import com.acme.videoserver.core.mediaserver.MediaServer;
import com.acme.videoserver.core.storage.Storage;

public class DefaultMediaServer implements MediaServer {

	private final Map<String, String> cache = new HashMap<>();
	
	private final Storage storage;
	
	public DefaultMediaServer(Storage storage) {
		this.storage = storage;
	}

	@Override
	public void add(Videoclip videoclip, String location) {
		cache.put(videoclip.uuid(), location);
	}
	
	@Override
	public Media stream(String mediaId) {
		return null;
	}


}
