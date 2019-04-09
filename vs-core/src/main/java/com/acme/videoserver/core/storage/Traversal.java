package com.acme.videoserver.core.storage;

import java.util.List;
import java.util.function.Consumer;

import com.acme.videoserver.core.storage.RemoteLocation;
import com.acme.videoserver.core.storage.StorageAccessException;

public class Traversal {

	private final RemoteLocation root;

	public Traversal(RemoteLocation root) {
		this.root = root;
	}

	public void each(Consumer<RemoteLocation> consumer) throws StorageAccessException {
		recursiveTraverse(root, consumer);
	}

	private void recursiveTraverse(RemoteLocation item, Consumer<RemoteLocation> consumer) throws StorageAccessException {
		List<RemoteLocation> children = item.children();

		for (RemoteLocation child : children) {

			if (!child.hasChildren()) {
				consumer.accept(child);
			} else {
				recursiveTraverse(child, consumer);
			}
		}
	}

}
