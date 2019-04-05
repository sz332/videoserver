package com.acme.videoserver.core.library;

import java.time.LocalDateTime;

public interface Metadata {

	String uuid();
	
	String title();

	Image thumbnail();

	LocalDateTime recordingDateTime();
	
}
