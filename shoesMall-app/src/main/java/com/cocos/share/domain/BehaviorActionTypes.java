package com.cocos.share.domain;

public enum BehaviorActionTypes {
	SHARE_SINA("share_sina"), 
	SHARE_TENCENT("share_tencent"), 
	SHARE_RENREN("share_renren"), 
	SHARE_TWITTER("share_twitter"), 
	SHARE_FACEBOOK("share_facebook"),
	LOGIN_SINA("LOGIN_sina"), 
	LOGIN_TENCENT("LOGIN_tencent"), 
	LOGIN_RENREN("LOGIN_renren"), 
	LOGIN_TWITTER("LOGIN_twitter"), 
	LOGIN_FACEBOOK("LOGIN_facebook");

	private String briefName;

	private BehaviorActionTypes(String briefName) {
		this.briefName = briefName;
	}

	public static BehaviorActionTypes getByName(String briefName) {
		return valueOf(briefName.toUpperCase());
	}

	public String getBriefName() {
		return briefName;
	}
}