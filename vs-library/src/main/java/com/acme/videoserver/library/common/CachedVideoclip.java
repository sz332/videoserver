package com.acme.videoserver.library.common;

import java.time.Instant;
import java.util.List;

import com.acme.videoserver.core.library.Image;
import com.acme.videoserver.core.library.Videoclip;

public class CachedVideoclip implements Videoclip {

	private static final String KEY_TITLE = "title";
	private static final String KEY_THUMBNAIL = "thumbnail";
	private static final String KEY_RECORDING_DATETIME = "recordingDateTime";
	private static final String KEY_DESCRIPTION = "description";
	private static final String KEY_PARTICIPANTS = "participants";
	private static final String KEY_TAGS = "tags";

	private final Videoclip clip;
	private final StandardCache<String, Object> cache;

	public CachedVideoclip(Videoclip clip) {
		this.clip = clip;
		this.cache = new StandardCache<>();
	}

	@Override
	public String uuid() {
		return clip.uuid();
	}

	@Override
	public String title() {
		if (!cache.containsKey(KEY_TITLE)) {
			cache.put(KEY_TITLE, clip.title());
		}
		return (String) cache.get(KEY_TITLE);
	}

	@Override
	public Image thumbnail() {
		if (!cache.containsKey(KEY_THUMBNAIL)) {
			cache.put(KEY_THUMBNAIL, clip.thumbnail());
		}
		return (Image) cache.get(KEY_THUMBNAIL);
	}

	@Override
	public Instant recordingDateTime() {
		if (!cache.containsKey(KEY_RECORDING_DATETIME)) {
			cache.put(KEY_RECORDING_DATETIME, clip.recordingDateTime());
		}
		return (Instant) cache.get(KEY_RECORDING_DATETIME);
	}

	@Override
	public String description() {
		if (!cache.containsKey(KEY_DESCRIPTION)) {
			cache.put(KEY_DESCRIPTION, clip.description());
		}
		return (String) cache.get(KEY_DESCRIPTION);
	}

	@Override
	public List<String> participants() {
		if (!cache.containsKey(KEY_PARTICIPANTS)) {
			cache.put(KEY_PARTICIPANTS, clip.participants());
		}
		return (List<String>) cache.get(KEY_PARTICIPANTS);
	}

	@Override
	public List<String> tags() {
		if (!cache.containsKey(KEY_TAGS)) {
			cache.put(KEY_TAGS, clip.tags());
		}
		return (List<String>) cache.get(KEY_TAGS);
	}

}
