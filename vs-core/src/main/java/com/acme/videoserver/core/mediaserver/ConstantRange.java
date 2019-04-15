package com.acme.videoserver.core.mediaserver;

import java.util.Objects;

public class ConstantRange implements Range {

	private final int start;
	private final int end;

	public ConstantRange(int start) {
		this.start = start;
		this.end = -1;
	}

	public ConstantRange(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public int start() {
		return start;
	}

	@Override
	public int end() {
		return end;
	}

	@Override
	public boolean openEnded() {
		return end == -1;
	}

	@Override
	public int hashCode() {
		return Objects.hash(end, start);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ConstantRange other = (ConstantRange) obj;
		return end == other.end && start == other.start;
	}

}
