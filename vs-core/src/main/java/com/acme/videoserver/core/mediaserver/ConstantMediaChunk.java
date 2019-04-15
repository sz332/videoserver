package com.acme.videoserver.core.mediaserver;

public class ConstantMediaChunk implements MediaChunk {

	private final int startBytes;
	private final int endBytes;
	private final int totalBytes;
	private final String mimeType;
	private final byte[] data;

	public ConstantMediaChunk(int startBytes, int endBytes, int totalBytes, String mimeType, byte[] data) {
		this.startBytes = startBytes;
		this.endBytes = endBytes;
		this.totalBytes = totalBytes;
		this.mimeType = mimeType;
		this.data = data;
	}

	@Override
	public int startBytes() {
		return startBytes;
	}
	
	@Override
	public int endBytes() {
		return endBytes;
	}
	
	@Override
	public int totalBytes() {
		return totalBytes;
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
