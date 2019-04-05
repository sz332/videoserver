package com.acme.videoserver.storage.metadata;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDateTime;

import org.cactoos.text.TextOf;
import org.junit.Test;

import com.acme.videoserver.core.videoclip.DetailedMetadata;


public class XmlMetadataTest {

	@Test
	public void testDecode() throws IOException {
		
		String text = new TextOf(getClass().getResourceAsStream("/metadata.xml")).asString();

		DetailedMetadata metadata = new XmlMetadata(text);
		
		assertEquals("My title", metadata.title());
		assertEquals("My description", metadata.description());
		assertEquals(LocalDateTime.parse("2019-04-05T10:15"), metadata.recordingDateTime());
		assertEquals("image/jpeg", metadata.thumbnail().mimeType());
		
		assertTrue(metadata.participants().contains("John Doe"));
		assertTrue(metadata.participants().contains("Jack Doe"));
		
		assertTrue(metadata.tags().contains("fun"));
		assertTrue(metadata.tags().contains("java"));
		assertTrue(metadata.tags().contains("example"));
	}
	
	
}
