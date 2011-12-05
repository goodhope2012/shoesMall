package com.cocos.share.domain;

public enum BehaviorResultTypes {
	FAIL("fail"), SUCCESS("success");

	private String briefName;

	private BehaviorResultTypes(String briefName) {
		this.briefName = briefName;
	}

	public static BehaviorResultTypes getByName(String briefName) {
		return valueOf(briefName.toUpperCase());
	}

	public String getBriefName() {
		return briefName;
	}
}