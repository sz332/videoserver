package com.acme.videoserver.storage.metadata;

import java.time.LocalDateTime;
import java.util.List;

import com.acme.videoserver.core.image.Base64EncodedImage;
import com.acme.videoserver.core.library.Image;
import com.acme.videoserver.core.library.Metadata;
import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;

public class XmlMetadata implements Metadata {

	private final XML xml;

	public XmlMetadata(String text) {
		this.xml = new XMLDocument(text);
	}
	
	@Override
	public String uuid() {
		return xml.xpath("//metadata/uuid/text()").get(0);
	}

	@Override
	public String title() {
		return xml.xpath("//metadata/title/text()").get(0);
	}

	@Override
	public LocalDateTime recordingDateTime() {
		return LocalDateTime.parse(xml.xpath("//metadata/recordingDateTime/text()").get(0));
	}

	@Override
	public Image thumbnail() {
		try {
			return new Base64EncodedImage(xml.xpath("//metadata/thumbnail/text()").get(0).trim());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String description() {
		return xml.xpath("//metadata/description/text()").get(0);
	}
	
	@Override
	public List<String> participants() {
		return xml.xpath("//metadata//participants//participant/text()");
	}

	@Override
	public List<String> tags() {
		return xml.xpath("//metadata//tags//tag/text()");
	}

}
