package com.acme.videoserver.core.storage;

import java.util.function.BiConsumer;

public class Traversal {

	private final RemoteLocation root;

	public Traversal(RemoteLocation root) {
		this.root = root;
	}

	public void each(BiConsumer<RemoteLocation, RemoteLocation> consumer) throws StorageAccessException {
		recursiveTraverse(root, root, consumer);
	}

	private void recursiveTraverse(RemoteLocation parent, RemoteLocation item, BiConsumer<RemoteLocation,RemoteLocation> consumer) throws StorageAccessException {

		for (RemoteLocation child : item.children()) {

			if (!child.hasChildren()) {
				consumer.accept(item, child);
			} else {
				recursiveTraverse(item, child, consumer);
			}
		}
	}

}
