package com.acme.videoserver.webapp;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.takes.facets.fork.FkRegex;
import org.takes.facets.fork.TkFork;
import org.takes.http.Exit;
import org.takes.http.FtBasic;

import com.acme.video.mediaserver.DefaultMediaServer;
import com.acme.videoserver.core.library.Library;
import com.acme.videoserver.core.mediaserver.MediaServer;
import com.acme.videoserver.core.storage.Storage;
import com.acme.videoserver.library.sql.SQLLibrary;
import com.acme.videoserver.library.sql.h2.H2InMemoryDatasource;
import com.acme.videoserver.storage.filesystem.FilesystemStorage;
import com.acme.videoserver.update.PeriodicSynchronization;
import com.acme.videoserver.webapp.modules.TkVideoclipMedia;
import com.acme.videoserver.webapp.modules.TkVideoclips;

public class Main {

	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) throws Exception {
		
		LOGGER.info("Starting application....");

		Storage storage = new FilesystemStorage(new File("E:/temp/videos").toPath());
		Library library = new SQLLibrary(new H2InMemoryDatasource());
		MediaServer mediaServer = new DefaultMediaServer(storage);
		
		PeriodicSynchronization synch = new PeriodicSynchronization(storage, library, mediaServer);
		
		synch.synchronize();
		
		new FtBasic(
				new TkFork(
						new FkRegex("/", "hello, world!"), 
						new FkRegex("/videoclips", new TkVideoclips(library)),
						new FkRegex("/videoclips/([a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}){1}/media", 
								new TkVideoclipMedia(mediaServer))
					)
					,
				8080).start(Exit.NEVER);
	}

}
