package com.inmobi.casestudy.githit.cache.interfaces;

import java.util.HashMap;

public class Cache {

	HashMap<Object, Object> objectCache = new HashMap<Object, Object>();
	Class<?> entryType;
	
	Cache(Class<?> entryType) {
		this.entryType = entryType;
	}
	
	public boolean reloadCache() {
		return false;
	}
	
	public Object getEntry(Object id) {
		if(objectCache.containsKey(id))
			return objectCache.get(id);
		else
			return null;
	}
	
	public boolean putEntry(Object id, Object entry) {
		if(entry.getClass() != entryType ) {
			return false;
		}
		if(!this.isFull()) {
			objectCache.put(id, entry);
		}
		else {
			return false;
		}
		return true;
	}
	
	private void evictEntry(Object id) {
		objectCache.remove(id);
		return;
	}
	
	public boolean isFull() {
		return false;
	}
	
}
