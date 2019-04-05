package com.acme.videoserver.storage.common;

import java.util.List;
import java.util.function.Consumer;

import com.acme.videoserver.core.storage.RemoteLocation;
import com.acme.videoserver.core.storage.StorageAccessException;

public class RemoteLocationWalker {

	private final RemoteLocation root;

	public RemoteLocationWalker(RemoteLocation root) {
		this.root = root;
	}

	public void walk(Consumer<RemoteLocation> consumer) throws StorageAccessException {
		recursiveWalk(root, consumer);
	}

	private void recursiveWalk(RemoteLocation item, Consumer<RemoteLocation> consumer) throws StorageAccessException {
		List<RemoteLocation> children = item.children();

		for (RemoteLocation child : children) {

			if (!child.hasChildren()) {
				consumer.accept(child);
			} else {
				recursiveWalk(child, consumer);
			}
		}
	}

}
