package com.acme.videoserver.library.common;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.acme.videoserver.core.library.Library;
import com.acme.videoserver.core.library.LibraryAccessException;
import com.acme.videoserver.core.library.Videoclip;

public class CachedLibrary implements Library {

	private final Library library;
	private final Cache<String, Videoclip> cache;

	public CachedLibrary(Library library, int expirationDuration, TimeUnit expirationUnit) {
		this.library = library;
		this.cache = new ExpirableCache<String, Videoclip>(expirationDuration, expirationUnit);
	}

	public CachedLibrary(Library library) {
		this(library, 0, TimeUnit.SECONDS);
	}

	@Override
	public void add(Videoclip clip) throws LibraryAccessException {
		library.add(clip);
	}

	@Override
	public List<Videoclip> clips() throws LibraryAccessException {
		return library.clips();
	}

	@Override
	public Videoclip clip(String clipId) throws LibraryAccessException {
		
		if (!cache.containsKey(clipId)) {
			cache.put(clipId, new CachedVideoclip(library.clip(clipId)));
		}
		
		return cache.get(clipId);
	}

}
