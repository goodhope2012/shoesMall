package com.cocos.share.sns;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import weibo4j.WeiboException;

import com.cocos.share.domain.Blog;
import com.face4j.facebook.Facebook;
import com.face4j.facebook.OAuthAccessToken;
import com.face4j.facebook.exception.FacebookException;
import com.face4j.facebook.factory.FacebookFactory;

public class FacebookService implements SnsInterface{
	private static final Logger LOGGER = Logger.getLogger(FacebookService.class);
	private FacebookFactory facebookFactory;
	private String accessToken;
	private String authorizationURL;

	public FacebookService(String consumerKey, String consumerSecret, String oauth_callback) {
		facebookFactory = new FacebookFactory(consumerKey, consumerSecret);
		this.authorizationURL = "https://www.facebook.com/dialog/oauth?client_id=" + consumerKey + "&display=touch&redirect_uri=" + oauth_callback + "&scope=publish_stream,read_stream,offline_access,publish_actions&response_type=token";
		LOGGER.info("=========================\n" + authorizationURL);
	}

	public void initAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public boolean share(Blog blog) throws WeiboException, IOException, FacebookException {
		if (StringUtils.isBlank(accessToken)) {
			LOGGER.error("Access token is blank.");
			return false;
		}
		Facebook facebook = facebookFactory.getInstance(new OAuthAccessToken(accessToken));
		String response = facebook.wallPost(blog.getContent(), "http://www.cocos2d-x.org/themes/cocos2dx/images/cocos2d-Logo-Landscape.png", "http://www.cocos2d-x.org/themes/cocos2dx/images/cocos2d-Logo-Landscape.png", null, null, null,
				"http://www.cocos2d-x.org/themes/cocos2dx/images/cocos2d-Logo-Landscape.png", facebook.getCurrentUser().getId());
		LOGGER.info("FACEBOOK RESPONSE = " + response);
		// TODO: refactor result
		return StringUtils.contains(response, "id");
	}

	public String getAuthorizationURL() {
		return authorizationURL;
	}
}