package com.inmobi.casestudy.githit.cache.interfaces;

import java.util.HashMap;

public class CacheManager {

	static HashMap<String, Cache> cacheStore = new HashMap<String, Cache>();
	
	private static void createCache(String name, Class entryType){
		synchronized(cacheStore) {
			if(!cacheStore.containsKey(name)){
				cacheStore.put(name, new Cache(entryType));
			}
		}
	}
	
	public static boolean isCachePresent(String name) {
		return cacheStore.containsKey(name);
	}
	
	public static Cache getCache(String name) {
		return cacheStore.get(name);
	}

}
