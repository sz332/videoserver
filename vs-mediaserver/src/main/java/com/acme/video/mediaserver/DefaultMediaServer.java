package com.acme.video.mediaserver;

import java.util.HashMap;
import java.util.Map;

import com.acme.videoserver.core.library.Videoclip;
import com.acme.videoserver.core.mediaserver.ConstantMedia;
import com.acme.videoserver.core.mediaserver.Media;
import com.acme.videoserver.core.mediaserver.MediaServer;
import com.acme.videoserver.core.mediaserver.MediaServerAccessException;
import com.acme.videoserver.core.storage.RemoteLocation;
import com.acme.videoserver.core.storage.Storage;
import com.acme.videoserver.core.storage.StorageAccessException;

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
	public Media stream(String mediaId) throws MediaServerAccessException {

		try {
			
			if (!cache.containsKey(mediaId)) {
				throw new MediaServerAccessException("Video not found in media server database");
			}
			
			RemoteLocation remoteLocation = storage.connect().resolve(cache.get(mediaId));

			return new ConstantMedia(mediaId, "video/mp4", remoteLocation.download());

		} catch (StorageAccessException e) {
			throw new MediaServerAccessException(e);
		}
	}

}
