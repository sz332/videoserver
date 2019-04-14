package com.acme.videoserver.core.mediaserver;

import com.acme.videoserver.core.library.Videoclip;

public interface MediaServer {

	void add(Videoclip videoclip, String location);
	
    Media stream(String mediaId);

}
