package com.acme.videoserver.core.mediaserver;

public class ConstantMedia implements Media {

	private final String id;
	private final String mimeType;
	private final byte[] data;

	public ConstantMedia(String id, String mimeType, byte[] data) {
		this.id = id;
		this.mimeType = mimeType;
		this.data = data;
	}

	@Override
	public String id() {
		return id;
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
