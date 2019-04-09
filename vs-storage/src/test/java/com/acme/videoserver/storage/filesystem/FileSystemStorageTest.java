package com.acme.videoserver.storage.filesystem;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.Test;

import com.acme.videoserver.core.storage.RemoteLocation;
import com.acme.videoserver.core.storage.Traversal;
import com.acme.videoserver.core.storage.Storage;
import com.acme.videoserver.core.storage.StorageAccessException;
import com.acme.videoserver.core.storage.StorageConnection;

public class FileSystemStorageTest {

	private static final String USER = "";
	private static final String PASSWORD = "";

	@Test
	public void testListFiles() throws StorageAccessException {

		File fsRoot = getResourceRoot();

		Storage storage = new FilesystemStorage(fsRoot.toPath());
		Assert.assertNotNull(storage);

		StorageConnection connection = storage.connect(USER, PASSWORD);
		Assert.assertNotNull(connection);

		RemoteLocation root = connection.root();
		Assert.assertNotNull(root);

		Traversal traversal = new Traversal(root);

		traversal.each(remoteLocation -> {
			try {
				System.out.println(remoteLocation.name());
				System.out.println(remoteLocation.path());

				if ("xml".equals(remoteLocation.extension())) {
					String s = new String(remoteLocation.download(), StandardCharsets.UTF_8);
					System.out.println(s);
				}

			} catch (StorageAccessException e) {
				e.printStackTrace();
			}
		});

	}

	private File getResourceRoot() {
		URL url = getClass().getResource("/videos");

		File f;

		try {
			f = new File(url.toURI());
		} catch (URISyntaxException e) {
			f = new File(url.getPath());
		}

		return f;
	}

}
