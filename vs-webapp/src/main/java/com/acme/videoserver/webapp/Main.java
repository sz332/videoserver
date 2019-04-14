package com.acme.videoserver.webapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.takes.facets.fork.FkRegex;
import org.takes.facets.fork.TkFork;
import org.takes.http.Exit;
import org.takes.http.FtBasic;

import com.acme.video.mediaserver.DefaultMediaServer;
import com.acme.videoserver.library.sql.SQLLibrary;
import com.acme.videoserver.library.sql.h2.H2InMemoryDatasource;
import com.acme.videoserver.webapp.modules.TkVideoclipMedia;
import com.acme.videoserver.webapp.modules.TkVideoclips;

public class Main {

	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) throws Exception {
		
		LOGGER.info("Starting application....");

		new FtBasic(
				new TkFork(
						new FkRegex("/", "hello, world!"), 
						new FkRegex("/videoclips", new TkVideoclips(new SQLLibrary(new H2InMemoryDatasource()))),
						new FkRegex("/videoclips/([a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}){1}/media", 
								new TkVideoclipMedia(new DefaultMediaServer()))
					)
					,
				8080).start(Exit.NEVER);
	}

}
