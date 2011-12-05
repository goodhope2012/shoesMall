package com.cocos.share.utils;

import org.apache.commons.codec.digest.DigestUtils;

import com.cocos.share.domain.App;

public class AppKeyUtils {
	public static void generateShareKey(App app) {
		app.setShareKey(DigestUtils.shaHex(app.getId().toString()));
	}
}