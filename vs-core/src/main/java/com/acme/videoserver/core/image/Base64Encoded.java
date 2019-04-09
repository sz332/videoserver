package com.acme.videoserver.core.image;

import java.util.Base64;

import com.acme.videoserver.core.library.Image;

public class Base64Encoded {
	
	private final Image image;
	
	public Base64Encoded(Image image) {
		this.image = image;
	}

	public String asString() {
		return "data:image/" + image.mimeType() + ";base64," + Base64.getMimeEncoder().encode(image.data());
	}
	
}
