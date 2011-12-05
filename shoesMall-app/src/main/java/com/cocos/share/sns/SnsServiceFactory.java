package com.cocos.share.sns;

import com.cocos.share.domain.SnsTypes;

public class SnsServiceFactory {

	public static SnsInterface createSns(String snsBriefName, String consumerKey, String consumerSecret, String oauth_callback, String appUrl) {
		switch (SnsTypes.getByName(snsBriefName)) {
		case SINA:
			return new WeiboService(consumerKey, consumerSecret, oauth_callback);
		case TENCENT:
			return new TencentService(consumerKey, consumerSecret, oauth_callback);
		case RENREN:
			return new RenrenService(consumerKey, consumerSecret, oauth_callback);
		case FACEBOOK:
			return new FacebookService(consumerKey, consumerSecret, appUrl);
		case TWITTER:
			return new TwitterService(consumerKey, consumerSecret, oauth_callback);
		default:
			return null;
		}
	}
}