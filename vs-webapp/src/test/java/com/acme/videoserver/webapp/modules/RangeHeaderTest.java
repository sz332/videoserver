package com.acme.videoserver.webapp.modules;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.acme.videoserver.core.mediaserver.ConstantRange;

public class RangeHeaderTest {
	
	@Test
	public void testOnlyStart() {
		RangeHeader rh = new RangeHeader(Arrays.asList("Range: bytes=500-"));
		Assert.assertEquals(new ConstantRange(500), rh.asRange().get());
	}
	
	@Test
	public void testStartAndEnd() {
		RangeHeader rh = new RangeHeader(Arrays.asList("Range: bytes=500-1000"));
		Assert.assertEquals(new ConstantRange(500, 1000), rh.asRange().get());
	}

	@Test
	public void testNoRange() {
		RangeHeader rh = new RangeHeader(Arrays.asList(""));
		Assert.assertFalse(rh.asRange().isPresent());
	}	
	
}
