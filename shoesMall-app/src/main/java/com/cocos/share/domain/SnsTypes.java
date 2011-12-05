package com.cocos.share.domain;

public enum SnsTypes {
	SINA("sina"), TENCENT("tencent"), RENREN("renren"), TWITTER("twitter"), FACEBOOK("facebook");

	private String briefName;

	private SnsTypes(String briefName) {
		this.briefName = briefName;
	}

	public static SnsTypes getByName(String briefName) {
		return valueOf(briefName.toUpperCase());
	}

	public String getBriefName() {
		return briefName;
	}

	public static boolean isSina(String briefName) {
		return SINA.getBriefName().equalsIgnoreCase(briefName);
	}

	public static boolean isTencent(String briefName) {
		return TENCENT.getBriefName().equalsIgnoreCase(briefName);
	}

	public static boolean isRenren(String briefName) {
		return RENREN.getBriefName().equalsIgnoreCase(briefName);
	}

	public static boolean isTwitter(String briefName) {
		return TWITTER.getBriefName().equalsIgnoreCase(briefName);
	}

	public static boolean isFacebook(String briefName) {
		return FACEBOOK.getBriefName().equalsIgnoreCase(briefName);
	}
}