package com.cocos.share.sns;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.cocos.share.domain.Blog;
import com.tencent.weibo.api.T_API;
import com.tencent.weibo.beans.OAuth;
import com.tencent.weibo.utils.OAuthClient;
import com.tencent.weibo.utils.WeiBoConst;

public class TencentService implements SnsInterface {
	private static final Logger LOGGER = Logger.getLogger(TencentService.class);
	private OAuth oauth;// WEIBO
	private OAuthClient auth;
	private String authorizationURL;

	public TencentService(String consumerKey, String consumerSecret, String oauth_callback) {
		this.oauth = new OAuth(consumerKey, consumerSecret, oauth_callback);
		this.auth = new OAuthClient();
		try {
			oauth = auth.requestToken(oauth);
			if (oauth.getStatus() == 1) {
				LOGGER.info("Get Request Token failed!");
			}
			String oauth_token = oauth.getOauth_token();
			authorizationURL = "http://open.t.qq.com/cgi-bin/authorize?oauth_token=" + oauth_token;
			LOGGER.info("authorizationURL ===  " + authorizationURL);
		} catch (Exception e) {
			LOGGER.error("Exception, when get Request Token failed!", e);
		}
	}

	public void initAccessToken(String accessPin) throws Exception {
		oauth.setOauth_verifier(accessPin);
		oauth = auth.accessToken(oauth);
		LOGGER.info("Response from server：");
		if (oauth.getStatus() == 2) {
			LOGGER.error("Get Access Token failed!");
		}
	}

	public boolean share(Blog blog) throws Exception {
		T_API tAPI = new T_API();
		String response = tAPI.add_pic(oauth, WeiBoConst.ResultType.ResultType_Json, blog.getContent(), "127.0.0.1", blog.getFile().getAbsolutePath());
		LOGGER.info("腾讯发布结果：" + response);
		boolean delete = blog.getFile().delete();
		if (!delete) {
			LOGGER.error("Error when delete share image.");
		}
		return StringUtils.contains(response, "\"errcode\":0,\"msg\":\"ok\",\"ret\":0");
	}

	public String getAuthorizationURL() {
		return authorizationURL;
	}
}