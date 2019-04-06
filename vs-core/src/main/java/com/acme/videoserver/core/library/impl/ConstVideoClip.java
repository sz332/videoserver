package com.acme.videoserver.core.library.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.acme.videoserver.core.library.Image;
import com.acme.videoserver.core.library.Videoclip;

public class ConstVideoClip implements Videoclip {

	private final String uuid;
	private final String title;
	private final String description;
	private final Image thumbnail;
	private final LocalDateTime recordingDateTime;
	private final List<String> participants;
	private final List<String> tags;

	public ConstVideoClip(String uuid, String title, String description, Image thumbnail, LocalDateTime recordingDateTime, List<String> participants, List<String> tags) {
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
	public String description() {
		return description;
	}
	
	@Override
	public Image thumbnail() {
		return thumbnail;
	}

	@Override
	public LocalDateTime recordingDateTime() {
		return recordingDateTime;
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
