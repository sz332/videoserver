package com.acme.videoserver.webapp.modules;

import java.io.IOException;
import java.util.stream.StreamSupport;

import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.misc.Href;
import org.takes.rq.RqHref;
import org.takes.rs.RsJson;

import com.acme.videoserver.core.library.ConstantQuery;
import com.acme.videoserver.core.library.Library;
import com.acme.videoserver.core.library.LibraryAccessException;
import com.acme.videoserver.core.library.Result;
import com.acme.videoserver.core.library.Videoclip;

public class TkVideoclips implements Take {

	private final Library library;

	public TkVideoclips(Library library) {
		this.library = library;
	}

	@Override
	public Response act(Request req) throws IOException {

		Href href = new RqHref.Base(req).href();

		String filter = StreamSupport.stream(href.param("filter").spliterator(), false)
				.findFirst()
				.orElse("");

		int limit = StreamSupport.stream(href.param("limit").spliterator(), false)
				.map(s -> Integer.parseInt(s))
				.findFirst()
				.orElse(Integer.MAX_VALUE);
		
		int offset = StreamSupport.stream(href.param("offset").spliterator(), false)
				.map(s -> Integer.parseInt(s))
				.findFirst()
				.orElse(0);

		try {
			Result<Videoclip> clips = library.clips(new ConstantQuery(filter, limit, offset));
			
			return new RsJson(new JsonVideoclipResult(clips));
			
		} catch (LibraryAccessException e) {
			throw new IOException(e);
		}
	}

}
