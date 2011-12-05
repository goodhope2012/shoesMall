package com.cocos.share.sns;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.cocos.share.domain.Blog;

public class BlogPool {
	private static ConcurrentMap<String, Blog> blogPool = new ConcurrentHashMap<String, Blog>();

	public static void push(String shareKey, Blog blog) {
		blogPool.put(shareKey, blog);
	}

	public static Blog pop(String shareKey) {
		return blogPool.remove(shareKey);
	}
}