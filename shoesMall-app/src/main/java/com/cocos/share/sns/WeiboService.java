package com.cocos.share.sns;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import weibo4j.Status;
import weibo4j.Weibo;
import weibo4j.WeiboException;
import weibo4j.http.AccessToken;
import weibo4j.http.RequestToken;

import com.cocos.share.domain.Blog;

public class WeiboService implements SnsInterface {
	private static final Logger LOGGER = Logger.getLogger(WeiboService.class);
	private Weibo weibo;
	private RequestToken requestToken;
	private AccessToken accessToken;
	private String authorizationURL;

	public WeiboService(String consumerKey, String consumerSecret, String oauth_callback) {
		this.weibo = new Weibo(consumerKey, consumerSecret);
		try {
			this.requestToken = weibo.getOAuthRequestToken(oauth_callback);
			this.authorizationURL = requestToken.getAuthorizationURL();
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
	}

	public void initAccessToken(String accessPin) {
		try {
			accessToken = requestToken.getAccessToken(accessPin);
			weibo.setToken(accessToken.getToken(), accessToken.getTokenSecret());
		} catch (WeiboException e) {
			if (401 == e.getStatusCode()) {
				LOGGER.error("Unable to get the access token.", e);
			} else {
				LOGGER.error(e, e);
			}
		}
	}

	public boolean share(Blog blog) throws WeiboException {
		if (blog == null) {
			return false;
		}
		Status status = weibo.uploadStatus(blog.getContent(), blog.getFile());
		boolean delete = blog.getFile().delete();
		if (!delete) {
			LOGGER.error("Error when delete share image.");
		}
		return StringUtils.isNotBlank(status.getText());
	}

	public Weibo getWeibo() {
		return weibo;
	}

	public void setWeibo(Weibo weibo) {
		this.weibo = weibo;
	}

	public String getAuthorizationURL() {
		return authorizationURL;
	}
}