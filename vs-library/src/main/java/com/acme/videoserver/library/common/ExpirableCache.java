package com.acme.videoserver.library.common;

import java.util.concurrent.TimeUnit;

public class ExpirableCache<K, V> implements Cache<K,V> {

	private final int duration;
	private final TimeUnit unit;
	
	public ExpirableCache(int duration, TimeUnit unit) {
		this.duration = duration;
		this.unit = unit;
	}
	
	@Override
	public boolean containsKey(K key) {
		return false;
	}

	@Override
	public void put(K key, V value) {
	}

	@Override
	public V get(K key) {
		return null;
	}

}
