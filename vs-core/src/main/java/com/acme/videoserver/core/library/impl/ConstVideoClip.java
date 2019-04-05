package com.acme.videoserver.core.library.impl;

import java.time.LocalDateTime;

import com.acme.videoserver.core.library.DetailedMetadata;
import com.acme.videoserver.core.library.Image;
import com.acme.videoserver.core.library.Videoclip;

public class ConstVideoClip implements Videoclip {

	private final String uuid;
	private final String title;
	private final Image thumbnail;
	private final LocalDateTime recordingDateTime;
	private final DetailedMetadata metadata;

	public ConstVideoClip(String uuid, String title, Image thumbnail, LocalDateTime recordingDateTime, DetailedMetadata metadata) {
		this.uuid = uuid;
		this.title = title;
		this.thumbnail = thumbnail;
		this.recordingDateTime = recordingDateTime;
		this.metadata = metadata;
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
	public LocalDateTime recordingDateTime() {
		return recordingDateTime;
	}

	@Override
	public DetailedMetadata metadata() {
		return metadata;
	}

}
