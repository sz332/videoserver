package com.acme.videoserver.storage.filesystem;

import java.io.File;
import java.util.function.Consumer;

import org.junit.Assert;
import org.junit.Test;

import com.acme.videoserver.core.storage.RemoteLocation;
import com.acme.videoserver.core.storage.Storage;
import com.acme.videoserver.core.storage.StorageAccessException;
import com.acme.videoserver.core.storage.StorageConnection;
import com.acme.videoserver.storage.common.RemoteLocationWalker;

public class FileSystemStorageTest {

	private static final String USER = "";
	private static final String PASSWORD = "";

	@Test
	public void testListFiles() throws StorageAccessException {

		Storage storage = new FilesystemStorage(new File("E:/Temp/videos").toPath());
		Assert.assertNotNull(storage);

		StorageConnection connection = storage.connect(USER, PASSWORD);
		Assert.assertNotNull(connection);

		RemoteLocation root = connection.root();
		Assert.assertNotNull(root);

		RemoteLocationWalker walker = new RemoteLocationWalker(root);

		walker.walk(new Consumer<RemoteLocation>() {

			@Override
			public void accept(RemoteLocation t) {
				try {
					System.out.println(t.name());
					System.out.println(t.path());
				} catch (StorageAccessException e) {
					e.printStackTrace();
				}
			}
		});

	}

}
