package com.acme.videoserver.library.sql;

import java.util.Arrays;
import java.util.Objects;

import com.acme.videoserver.core.library.Image;

public class FakeImage implements Image {

	private final String mimeType = "image/jpeg";
	private final byte[] data = new byte[0];

	@Override
	public String mimeType() {
		return mimeType;
	}

	@Override
	public byte[] data() {
		return data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(data);
		result = prime * result + Objects.hash(mimeType);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (!(obj instanceof Image)) {
			return false;
		}
		
		Image other = Image.class.cast(obj);
		
		return Arrays.equals(data, other.data()) && Objects.equals(mimeType, other.mimeType());
	}

}
