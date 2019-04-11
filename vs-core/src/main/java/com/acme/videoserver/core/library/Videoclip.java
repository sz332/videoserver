package com.acme.videoserver.core.library;

import java.time.Instant;
import java.util.List;

public interface Videoclip {

	String uuid();
	
	String title();

	Image thumbnail();

	Instant recordingDateTime();
	
	String description();

	List<String> participants();

	List<String> tags();
	
}
