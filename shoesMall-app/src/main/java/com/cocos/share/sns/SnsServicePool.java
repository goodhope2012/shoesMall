package com.cocos.share.sns;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SnsServicePool {
	private static ConcurrentMap<String, SnsInterface> facebookServicePool = new ConcurrentHashMap<String, SnsInterface>();

	// TODO 清理超時的数据，防止无限增长

	public static void put(String shareKey, SnsInterface weibo) {
		facebookServicePool.put(shareKey, weibo);
	}

	public static SnsInterface get(String shareKey) {
		return facebookServicePool.get(shareKey);
	}
}