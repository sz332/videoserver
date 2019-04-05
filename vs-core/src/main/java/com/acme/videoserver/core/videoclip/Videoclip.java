package com.acme.videoserver.core.videoclip;

public interface Videoclip extends Metadata {

	DetailedMetadata metadata();
	void metadata(DetailedMetadata meta);

}
