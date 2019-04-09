package com.acme.videoserver.core.cache;

import java.util.List;

public interface Cache<K, V> {

	boolean containsKey(K key);

	void put(K key, V value);

	V get(K key);
	
	List<V> values();

}
