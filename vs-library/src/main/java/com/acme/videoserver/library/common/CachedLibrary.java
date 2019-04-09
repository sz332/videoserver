package com.acme.videoserver.library.common;

import java.util.List;

import org.cactoos.func.StickyFunc;
import org.cactoos.func.UncheckedFunc;
import org.cactoos.scalar.UncheckedScalar;

import com.acme.videoserver.core.cache.Cache;
import com.acme.videoserver.core.cache.StandardCache;
import com.acme.videoserver.core.library.Library;
import com.acme.videoserver.core.library.LibraryAccessException;
import com.acme.videoserver.core.library.Videoclip;

public class CachedLibrary implements Library {

	private final Library library;
	private final Cache<String, Videoclip> cache;

	private UncheckedFunc<String, UncheckedScalar<List<Videoclip>>> output;

	public CachedLibrary(Library library) {
		this.library = library;
		this.cache = new StandardCache<>();

		output = new UncheckedFunc<>(new StickyFunc<>(input -> {

			library.clips()
					.stream()
					.forEach(clip -> cache.put(clip.uuid(), clip));

			return new UncheckedScalar<List<Videoclip>>(cache::values);
		}));
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
		return this.output.apply("").value();
	}

}
