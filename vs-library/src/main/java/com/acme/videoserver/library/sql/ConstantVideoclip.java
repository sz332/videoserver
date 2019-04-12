package com.acme.videoserver.library.sql;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import com.acme.videoserver.core.library.Image;
import com.acme.videoserver.core.library.Videoclip;

public class ConstantVideoclip implements Videoclip {

	private final String uuid;
	private final String title;
	private final String description;
	private final Image thumbnail;
	private final Instant recordingDateTime;
	private final List<String> participants;
	private final List<String> tags;

	public ConstantVideoclip(String uuid, String title, String description, Image thumbnail, Instant recordingDateTime,
			List<String> participants, List<String> tags) {
		this.uuid = uuid;
		this.title = title;
		this.description = description;
		this.thumbnail = thumbnail;
		this.recordingDateTime = recordingDateTime;
		this.participants = participants;
		this.tags = tags;
	}

	@Override
	public String uuid() {
		return uuid;
	}

	@Override
	public String title() {
		return title;
	}

	@Override
	public Image thumbnail() {
		return thumbnail;
	}

	@Override
	public Instant recordingDateTime() {
		return recordingDateTime;
	}

	@Override
	public String description() {
		return description;
	}

	@Override
	public List<String> participants() {
		return participants;
	}

	@Override
	public List<String> tags() {
		return tags;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, participants, recordingDateTime, tags, thumbnail, title, uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Videoclip)) {
			return false;
		}

		Videoclip other = Videoclip.class.cast(obj);

		return Objects.equals(uuid, other.uuid()) 
				&& Objects.equals(title, other.title())
				&& Objects.equals(description, other.description()) 
				&& Objects.equals(recordingDateTime, other.recordingDateTime()) 
				&& new HashSet<>( participants ).equals( new HashSet<>( other.participants() ))
				&& new HashSet<>( tags ).equals( new HashSet<>( other.tags() ))
				&& thumbnailEquals(thumbnail, other.thumbnail());
	}
	
	private boolean thumbnailEquals(Image image1, Image image2) {
		if ((image1 == null && image2 != null) || (image1 != null && image2 == null) || (image1 == null && image2 == null)) {
			return false;
		}
		
		return image1.equals(image2);
	}

}
