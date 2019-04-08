package com.acme.videoserver.library.sql;

import java.time.Instant;
import java.util.List;

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

}
