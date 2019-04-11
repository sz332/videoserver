package com.acme.videoserver.storage.metadata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.LocalDateTime;

import org.cactoos.text.TextOf;
import org.junit.Test;

import com.acme.videoserver.core.library.Videoclip;


public class XmlMetadataTest {

	@Test
	public void testDecode() throws IOException {
		
		String text = new TextOf(getClass().getResourceAsStream("/metadata.xml")).asString();

		Videoclip videoclip = new XmlVideoclip(text);
		
		assertEquals("af935b53-2877-4454-93a9-524650d6787b", videoclip.uuid());
		assertEquals("My title", videoclip.title());
		assertEquals("My description", videoclip.description());
		assertEquals(LocalDateTime.parse("2019-04-05T10:15"), videoclip.recordingDateTime());
		assertEquals("image/jpeg", videoclip.thumbnail().mimeType());
		
		assertTrue(videoclip.participants().contains("John Doe"));
		assertTrue(videoclip.participants().contains("Jack Doe"));
		
		assertTrue(videoclip.tags().contains("fun"));
		assertTrue(videoclip.tags().contains("java"));
		assertTrue(videoclip.tags().contains("example"));
	}
	
	
}
