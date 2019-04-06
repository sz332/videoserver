package com.acme.videoserver.core.library;

import java.time.LocalDateTime;
import java.util.List;

public interface Metadata {

	String uuid();
	
	String title();

	Image thumbnail();

	LocalDateTime recordingDateTime();
	
	String description();

	List<String> participants();

	List<String> tags();
	
}
