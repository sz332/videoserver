package com.acme.videoserver.update;

import java.io.File;

import org.junit.Test;

import com.acme.video.mediaserver.DefaultMediaServer;
import com.acme.videoserver.core.library.Library;
import com.acme.videoserver.core.mediaserver.MediaServer;
import com.acme.videoserver.core.storage.Storage;
import com.acme.videoserver.library.sql.SQLLibrary;
import com.acme.videoserver.library.sql.h2.H2InMemoryDatasource;
import com.acme.videoserver.storage.filesystem.FilesystemStorage;

public class PeriodicSynchronizationTest {
	
	@Test
	public void test() {
		
		Storage storage = new FilesystemStorage(new File("E:/temp/videos").toPath());
		Library library = new SQLLibrary(new H2InMemoryDatasource());
		MediaServer mediaServer = new DefaultMediaServer(storage);
		
		PeriodicSynchronization synchronization = new PeriodicSynchronization(storage, library, mediaServer);
		
		synchronization.synchronize();
	}

}
