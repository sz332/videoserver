package com.acme.videoserver.core.videoclip;

import java.util.UUID;

public interface Videoclip extends Metadata {

	UUID id();

	DetailedMetadata metadata();

	void metadata(DetailedMetadata meta);

}
