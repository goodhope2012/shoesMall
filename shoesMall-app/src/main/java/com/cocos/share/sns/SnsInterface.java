package com.cocos.share.sns;

import com.cocos.share.domain.Blog;

public interface SnsInterface {

	public void initAccessToken(String accessToken) throws Exception;

	public boolean share(Blog blog) throws Exception;

	public String getAuthorizationURL() throws Exception;
}