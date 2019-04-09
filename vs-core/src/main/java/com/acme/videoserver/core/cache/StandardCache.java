package com.acme.videoserver.core.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StandardCache<K, V> implements Cache<K, V> {

	private final Map<K,V> map;
	
	public StandardCache() {
		this.map = new HashMap<>();
	}

	@Override
	public boolean containsKey(K key) {
		return map.containsKey(key);
	}

	@Override
	public void put(K key, V value) {
		map.put(key, value);
	}

	@Override
	public V get(K key) {
		return map.get(key);
	}

	@Override
	public List<V> values() {
		return new ArrayList<>(map.values());
	}

}
