package com.acme.videoserver.webapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.takes.facets.fork.FkMethods;
import org.takes.facets.fork.FkRegex;
import org.takes.facets.fork.TkFork;
import org.takes.http.Exit;
import org.takes.http.FtBasic;

import com.acme.videoserver.library.sql.SQLLibrary;
import com.acme.videoserver.library.sql.h2.H2InMemoryDatasource;
import com.acme.videoserver.webapp.modules.TkVideoclips;

public class Main {

	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) throws Exception {
		
		LOGGER.info("Starting application....");

		new FtBasic(
				new TkFork(
						new FkRegex("/", "hello, world!"), 
						new FkRegex("/videoclips", new TkFork(
								new FkMethods("GET", new TkVideoclips(new SQLLibrary(new H2InMemoryDatasource())))))
						),
				8080).start(Exit.NEVER);
	}

}
