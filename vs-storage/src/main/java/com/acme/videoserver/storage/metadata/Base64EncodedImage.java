package com.acme.videoserver.storage.metadata;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.acme.videoserver.core.videoclip.Image;

public class Base64EncodedImage implements Image {

	private static final String IMAGE_REGEXP = "data:image\\/([a-zA-Z]*);base64,([^\"]*)";
	private static final Pattern PATTERN = Pattern.compile(IMAGE_REGEXP);

	private final byte[] data;
	private final String mimeType;

	// FIXME make it code free
	public Base64EncodedImage(String text) {
		Matcher matcher = PATTERN.matcher(text);

		if (matcher.matches()) {
			this.mimeType = "image/" + matcher.group(1);
			this.data = Base64.getMimeDecoder().decode(matcher.group(2));
		} else {
			this.mimeType = "";
			this.data = Base64.getMimeDecoder().decode(text);
		}
	}

	@Override
	public String mimeType() {
		return mimeType;
	}

	@Override
	public byte[] data() {
		return data;
	}

}
