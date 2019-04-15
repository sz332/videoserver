package com.acme.videoserver.webapp.modules;

import java.util.Optional;

import com.acme.videoserver.core.mediaserver.ConstantRange;
import com.acme.videoserver.core.mediaserver.Range;

public class LimitedRangeHeader {

	private final RangeHeader header;
	private final int limit;

	public LimitedRangeHeader(RangeHeader header, int limit) {
		this.header = header;
		this.limit = limit;
	}

	public Optional<Range> asRange() {
		Optional<Range> range = header.asRange();

		if (range.isPresent()) {
			Range r = range.get();
			return Optional.of(new ConstantRange(r.start(), r.start() + limit));
		}

		return range;
	}

}
