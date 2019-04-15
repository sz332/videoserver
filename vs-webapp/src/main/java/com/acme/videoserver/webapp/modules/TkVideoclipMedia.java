package com.acme.videoserver.webapp.modules;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.misc.Href;
import org.takes.rq.RqHref;
import org.takes.rs.RsFluent;

import com.acme.videoserver.core.mediaserver.MediaChunk;
import com.acme.videoserver.core.mediaserver.MediaServer;
import com.acme.videoserver.core.mediaserver.MediaServerAccessException;
import com.acme.videoserver.core.mediaserver.Range;

// https://tutorial-academy.com/rest-jersey2-resume-video-streaming/
public class TkVideoclipMedia implements Take {

	private static final String URL_REGEX = "/videoclips/([a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}){1}/media";

	private final Pattern pattern = Pattern.compile(URL_REGEX);

	private final MediaServer mediaServer;

	public TkVideoclipMedia(MediaServer mediaServer) {
		this.mediaServer = mediaServer;
	}

	@Override
	public Response act(Request req) throws IOException {

		try {
			Href href = new RqHref.Base(req).href();

			Matcher matcher = pattern.matcher(href.path());

			if (matcher.matches()) {
				String uuid = matcher.group(1);

				Optional<Range> range = new LimitedRangeHeader(new RangeHeader(req.head()), 1024 * 1024 ).asRange();

				if (range.isPresent()) {

					MediaChunk chunk = mediaServer.stream(uuid, range.get());

					return new RsFluent().withStatus(206)
							.withHeader("Content-Range", String.format("bytes %d-%d/%d", chunk.startBytes(), chunk.endBytes(), chunk.totalBytes()))
							.withHeader("Accept-Ranges", "bytes")
							.withHeader("Content-Length", String.valueOf(chunk.data().length))
							.withHeader("Content-Type", chunk.mimeType())
							.withBody(chunk.data());
				} else {

					MediaChunk media = mediaServer.stream(uuid);

					return new RsFluent().withStatus(200)
							.withHeader("Content-Type", media.mimeType())
							.withHeader("Content-Disposition", "inline; filename=\"output.mp4\"")
							.withBody(media.data());
				}

			}

			return new RsFluent().withStatus(404);

		} catch (MediaServerAccessException e) {
			throw new IOException(e);
		}
	}

}
