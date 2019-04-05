package com.acme.videoserver.core.library;

import java.util.List;

public interface DetailedMetadata extends Metadata {

	String description();

	List<String> participants();

	List<String> tags();

}
