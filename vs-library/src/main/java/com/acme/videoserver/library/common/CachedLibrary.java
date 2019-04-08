package com.acme.videoserver.library.common;

import java.util.List;

import com.acme.videoserver.core.library.Library;
import com.acme.videoserver.core.library.LibraryAccessException;
import com.acme.videoserver.core.library.Videoclip;

public class CachedLibrary implements Library {

	private final Library library;
	private final Cache<String, Videoclip> cache;

	// OOPS this makes me mutable
	private boolean allDataReceived = false;
	
	public CachedLibrary(Library library) {
		this.library = library;
		this.cache = new StandardCache<>();
	}

	@Override
	public void add(Videoclip clip) throws LibraryAccessException {
		library.add(clip);
		cache.put(clip.uuid(), clip);
	}

	@Override
	public Videoclip clip(String clipId) throws LibraryAccessException {

		if (!cache.containsKey(clipId)) {
			cache.put(clipId, library.clip(clipId));
		}

		return cache.get(clipId);
	}

	@Override
	public synchronized List<Videoclip> clips() throws LibraryAccessException {

		// how to remove the allDataReceived:
		//	create a function which returns a function
		//  this function will execute the library.clips().stream()
		//  and return a function which executes cache.values()
		//  then make it sticky
		
		if (!allDataReceived) {
			library.clips().stream().forEach(clip -> cache.put(clip.uuid(), clip));
			allDataReceived = true;
		}

		return cache.values();
	}

}
