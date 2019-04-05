package com.acme.videoserver.core.videoclip;

import java.time.LocalDateTime;

public interface Metadata {

	String uuid();
	
	String title();

	Image thumbnail();

	LocalDateTime recordingDateTime();
}
