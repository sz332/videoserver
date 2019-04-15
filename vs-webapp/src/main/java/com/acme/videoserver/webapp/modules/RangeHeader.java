package com.acme.videoserver.webapp.modules;

import java.util.Optional;

import com.acme.videoserver.core.mediaserver.ConstantRange;
import com.acme.videoserver.core.mediaserver.Range;

public class RangeHeader {

	private final Iterable<String> header;

	public RangeHeader(Iterable<String> header) {
		this.header = header;
	}

	public Optional<Range> asRange() {
		for (String s : header) {
			if (s.trim().startsWith("Range:")) {
				String[] values = s.substring(s.indexOf("=") + 1).split("-");

				if (values.length == 1) {
					return Optional.of(new ConstantRange(Integer.parseInt(values[0])));
				} else if (values.length == 2) {
					return Optional.of(new ConstantRange(Integer.parseInt(values[0]), Integer.parseInt(values[1])));
				} else {
					return Optional.empty();
				}
			}
		}

		return Optional.empty();
	}

}
