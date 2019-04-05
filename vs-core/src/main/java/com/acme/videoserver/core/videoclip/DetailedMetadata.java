package com.acme.videoserver.core.videoclip;

import java.util.List;

public interface DetailedMetadata extends Metadata {

	String description();

	List<String> participants();

	List<String> tags();

}
