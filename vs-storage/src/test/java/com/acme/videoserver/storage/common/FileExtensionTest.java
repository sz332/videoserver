package com.acme.videoserver.storage.common;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FileExtensionTest {

	// https://stackoverflow.com/questions/3571223/how-do-i-get-the-file-extension-of-a-file-in-java
	@Test
	public void testGetExtension() {
		assertEquals("", getExtension("C"));
		assertEquals("ext", getExtension("C.ext"));
		assertEquals("ext", getExtension("A/B/C.ext"));
		assertEquals("", getExtension("A/B/C.ext/"));
		assertEquals("", getExtension("A/B/C.ext/.."));
		assertEquals("bin", getExtension("A/B/C.bin"));
		assertEquals("hidden", getExtension(".hidden"));
		assertEquals("dsstore", getExtension("/user/home/.dsstore"));
		assertEquals("", getExtension(".strange."));
		assertEquals("3", getExtension("1.2.3"));
		assertEquals("exe", getExtension("C:\\Program Files (x86)\\java\\bin\\javaw.exe"));
	}

	String getExtension(String fileName) {
		return new FileExtension(fileName).extension();
	}
}
