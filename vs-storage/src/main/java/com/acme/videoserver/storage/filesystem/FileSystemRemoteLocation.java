package com.acme.videoserver.storage.filesystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.acme.videoserver.core.storage.RemoteLocation;
import com.acme.videoserver.core.storage.StorageAccessException;
import com.acme.videoserver.storage.common.FileExtension;

public class FileSystemRemoteLocation implements RemoteLocation {

	private final Path path;

	public FileSystemRemoteLocation(Path path) {
		this.path = path;
	}

	@Override
	public String name() throws StorageAccessException {
		return path.getFileName().toString();
	}
	
	@Override
	public String extension() throws StorageAccessException {
		return new FileExtension(name()).extension();
	}

	@Override
	public String path() throws StorageAccessException {
		return path.toAbsolutePath().toString();
	}

	@Override
	public byte[] download() throws StorageAccessException {

		try {

			if (!path.toFile().isDirectory()) {
				return Files.readAllBytes(path);
			}

			return new byte[0];

		} catch (IOException e) {
			throw new StorageAccessException(e);
		}

	}

	@Override
	public boolean hasChildren() throws StorageAccessException {
		return path.toFile().isDirectory();
	}

	@Override
	public List<RemoteLocation> children() throws StorageAccessException {

		File file = path.toFile();

		if (file.isDirectory()) {
			return Arrays.asList(file.listFiles())
					.stream()
					.map(f -> new FileSystemRemoteLocation(f.toPath()))
					.collect(Collectors.toList());
		}

		return Collections.emptyList();
	}

}
