package com.renren.api.client;

/**
 * @author 李勇 2011-02-17
 */
public abstract class AbstractServiceTest {

	private RenrenApiClient client;

	static {
		// TODO 配置apiKey和apiSecret
		// RenrenApiConfig.renrenApiKey = null;
		// RenrenApiConfig.renrenApiSecret = null;
	}

	public AbstractServiceTest() {
		this.init();
	}

	protected void init() {
		String token = this.getAccessToken(RenrenApiConfig.renrenApiKey, RenrenApiConfig.renrenApiSecret);
		boolean isAccessToken = true;
		if (token == null || token.length() < 1) {
			token = this.getSessionKey(RenrenApiConfig.renrenApiKey, RenrenApiConfig.renrenApiSecret);
			isAccessToken = false;
		}
		this.client = new RenrenApiClient(token, isAccessToken);
	}

	protected String getSessionKey(String apiKey, String apiSecret) {
		// TODO 用OAuth2.0获取accessToken，再用accessToken换sessionKey
		return null;
	}

	protected String getAccessToken(String apiKey, String apiSecret) {
		// TODO 用OAuth2.0获取accessToken
		return "167883%7C6.df60edba1fa53ab2729d4fd04f702e25.2592000.1323511200-191519017";
	}

	protected RenrenApiClient getRenrenApiClient() {
		return this.client;
	}
}
