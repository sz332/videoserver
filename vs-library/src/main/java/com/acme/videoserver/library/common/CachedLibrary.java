package com.acme.videoserver.library.common;

import com.acme.videoserver.core.cache.Cache;
import com.acme.videoserver.core.cache.StandardCache;
import com.acme.videoserver.core.library.*;
import org.cactoos.func.SolidFunc;
import org.cactoos.func.UncheckedFunc;
import org.cactoos.scalar.UncheckedScalar;

import java.util.List;

public class CachedLibrary implements Library {

	private final Library library;
	private final Cache<String, Videoclip> cache;

	private UncheckedFunc<String, UncheckedScalar<List<Videoclip>>> output;

	public CachedLibrary(Library library) {
		this.library = library;
		this.cache = new StandardCache<>();

		output = new UncheckedFunc<>(new SolidFunc<>(input -> {

			library.clips()
					.stream()
					.forEach(clip -> cache.put(clip.uuid(), clip));

			return new UncheckedScalar<>(cache::values);
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
	public List<Videoclip> clips() throws LibraryAccessException {
		return this.output.apply("").value();
	}

	@Override
	public Result<Videoclip> clips(Query query) throws LibraryAccessException {
		// FIXME implement cache
		throw new UnsupportedOperationException("clips query not implemented yet");
	}

}
