package com.acme.videoserver.webapp.modules;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.misc.Href;
import org.takes.rq.RqHref;

import com.acme.videoserver.core.mediaserver.Media;
import com.acme.videoserver.core.mediaserver.MediaServer;

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
		
		Href href = new RqHref.Base(req).href();

		Matcher matcher = pattern.matcher(href.path());
		
		if (matcher.matches()) {
			String uuid = matcher.group(1);
			Media media = mediaServer.stream(uuid);
			
			
		}
		
		System.out.println("href = " + href);
		
		return null;
	}

}
