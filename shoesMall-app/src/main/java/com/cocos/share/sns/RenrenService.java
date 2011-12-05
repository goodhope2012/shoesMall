package com.cocos.share.sns;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import weibo4j.WeiboException;
import weibo4j.util.URLEncodeUtils;

import com.cocos.share.domain.Blog;
import com.renren.api.client.RenrenApiClient;

public class RenrenService implements SnsInterface {
	private static final Logger LOGGER = Logger.getLogger(RenrenService.class);
	private RenrenApiClient client;
	private String accessToken;
	private String authorizationURL;

	public RenrenService(String consumerKey, String consumerSecret, String oauth_callback) {
		this.authorizationURL = "https://graph.renren.com/oauth/authorize?scope=photo_upload publish_feed publish_share status_update&client_id=" + consumerKey + "&response_type=token&display=mobile&redirect_uri=http://graph.renren.com/oauth/login_success.html";
	}

	public void initAccessToken(String accessToken) {
		this.accessToken = URLEncodeUtils.decodeURL(accessToken);
	}

	public boolean share(Blog blog) throws WeiboException, IOException {
		if (StringUtils.isBlank(accessToken)) {
			LOGGER.error("Access token is blank.");
			return false;
		}
		boolean isAccessToken = StringUtils.isNotBlank(accessToken);
		this.client = new RenrenApiClient(accessToken, isAccessToken);
		long albumId = 1;// 相册id
		byte[] photo = IOUtils.toByteArray(new FileInputStream(blog.getFile()));
		JSONObject ret = this.client.getPhotoService().upload(albumId, photo, blog.getFile().getName(), blog.getContent());
		LOGGER.info("Renren share result:  " + ret.toJSONString());
		boolean delete = blog.getFile().delete();
		if (!delete) {
			LOGGER.error("Error when delete share image.");
		}
		// TODO: refactor result
		return ret.containsKey("src_small");
	}

	public String getAuthorizationURL() {
		return authorizationURL;
	}
}