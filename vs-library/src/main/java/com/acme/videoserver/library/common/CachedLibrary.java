package com.acme.videoserver.library.common;

import java.util.Collection;

import com.acme.videoserver.core.library.Library;
import com.acme.videoserver.core.library.LibraryAccessException;
import com.acme.videoserver.core.library.Videoclip;

public class CachedLibrary implements Library {

	private boolean allDataReceived = false;

	private final Library library;
	private final Cache<String, Videoclip> cache;

	public CachedLibrary(Library library) {
		this.library = library;
		this.cache = new StandardCache<String, Videoclip>();
	}

	@Override
	public void add(Videoclip clip) throws LibraryAccessException {
		library.add(clip);
		cache.put(clip.uuid(), new CachedVideoclip(clip));
	}

	@Override
	public Videoclip clip(String clipId) throws LibraryAccessException {

		if (!cache.containsKey(clipId)) {
			cache.put(clipId, new CachedVideoclip(library.clip(clipId)));
		}

		return cache.get(clipId);
	}

	@Override
	public synchronized Collection<Videoclip> clips() throws LibraryAccessException {

		if (!allDataReceived) {
			library.clips().stream().forEach(clip -> cache.put(clip.uuid(), new CachedVideoclip(clip)));
			allDataReceived = true;
		}

		return cache.values();
	}

}
