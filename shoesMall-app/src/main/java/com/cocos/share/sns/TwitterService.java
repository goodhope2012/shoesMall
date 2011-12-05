package com.cocos.share.sns;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import com.cocos.share.domain.Blog;

public class TwitterService implements SnsInterface {
	private static final Logger LOGGER = Logger.getLogger(TwitterService.class);
	private static TwitterFactory twitterFactory = new TwitterFactory();
	private Twitter twitter;
	private RequestToken requestToken;
	private AccessToken accessToken;
	private String authorizationURL;

	public TwitterService(String consumerKey, String consumerSecret, String oauth_callback) {
		twitter = twitterFactory.getInstance();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		try {
			this.requestToken = twitter.getOAuthRequestToken(oauth_callback);
			this.authorizationURL = requestToken.getAuthorizationURL();
			LOGGER.info(authorizationURL);
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
	}

	public void initAccessToken(String accessPin) {
		try {
			if (StringUtils.isNotBlank(accessPin)) {
				accessToken = twitter.getOAuthAccessToken(requestToken, accessPin);
			} else {
				accessToken = twitter.getOAuthAccessToken(requestToken);
			}
		} catch (TwitterException te) {
			if (401 == te.getStatusCode()) {
				System.out.println("Unable to get the access token.");
			} else {
				te.printStackTrace();
			}
		}
	}

	public boolean share(Blog blog) throws TwitterException {
		if (blog == null) {
			return false;
		}
		System.out.println("Access token: " + accessToken.getToken() + " - Access token secret: " + accessToken.getTokenSecret());
		StatusUpdate latestStatus = new StatusUpdate(blog.getContent());
		latestStatus.media(blog.getFile());
		Status status = twitter.updateStatus(latestStatus);
		boolean delete = blog.getFile().delete();
		if (!delete) {
			LOGGER.error("Error when delete share image.");
		}
		// TODO
		return StringUtils.isNotBlank(status.getText());
	}

	public String getAuthorizationURL() {
		return authorizationURL;
	}
}