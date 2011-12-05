package twitter4j;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TwitterTest {
	public static void main(String[] args) {
		try {
			Twitter twitter2 = new TwitterFactory().getInstance();
			twitter2.setOAuthConsumer("bddFOaAZvRrvh0sherkug", "zVKLiLGBdJEg0GHYIPtWwyQmFPsSmC6y8DuYWxJBEM4");
			// twitter.setOAuthConsumer.("USLG8ce1GgCOTzmxODYyg", "Wk3XdtBDyGRLq5dz20NMasrzQ4BfeRvbPSQ1iKaI");
			RequestToken requestToken2 = twitter2.getOAuthRequestToken();
			System.out.println("Request token: " + requestToken2.getToken() + " - Request token secret: " + requestToken2.getTokenSecret());
			AccessToken accessToken2 = null;
			BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
			while (null == accessToken2) {
				System.out.println(requestToken2.getAuthorizationURL());
				try {
					Desktop.getDesktop().browse(new URI(requestToken2.getAuthorizationURL()));
				} catch (IOException ignore) {
				} catch (URISyntaxException e) {
					throw new AssertionError(e);
				}
				System.out.print("Enter the PIN(if available) and hit enter after you granted access.[PIN]:");
				String pin2 = br2.readLine();
				try {
					if (pin2.length() > 0) {
						accessToken2 = twitter2.getOAuthAccessToken(requestToken2, pin2);
					} else {
						accessToken2 = twitter2.getOAuthAccessToken(requestToken2);
					}
				} catch (TwitterException te) {
					if (401 == te.getStatusCode()) {
						System.out.println("Unable to get the access token.");
					} else {
						te.printStackTrace();
					}
				}
			}
			// ===========
//			Twitter twitter = new TwitterFactory().getInstance();
//			twitter.setOAuthConsumer("USLG8ce1GgCOTzmxODYyg", "Wk3XdtBDyGRLq5dz20NMasrzQ4BfeRvbPSQ1iKaI");
//			RequestToken requestToken = twitter.getOAuthRequestToken();
//			System.out.println("Request token: " + requestToken.getToken() + " - Request token secret: " + requestToken.getTokenSecret());
//			AccessToken accessToken = null;
//			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//			while (null == accessToken) {
//				System.out.println(requestToken.getAuthorizationURL());
//				try {
//					Desktop.getDesktop().browse(new URI(requestToken.getAuthorizationURL()));
//				} catch (IOException ignore) {
//				} catch (URISyntaxException e) {
//					throw new AssertionError(e);
//				}
//				System.out.print("Enter the PIN(if available) and hit enter after you granted access.[PIN]:");
//				String pin = br.readLine();
//				try {
//					if (pin.length() > 0) {
//						accessToken = twitter.getOAuthAccessToken(requestToken, pin);
//					} else {
//						accessToken = twitter.getOAuthAccessToken(requestToken);
//					}
//				} catch (TwitterException te) {
//					if (401 == te.getStatusCode()) {
//						System.out.println("Unable to get the access token.");
//					} else {
//						te.printStackTrace();
//					}
//				}
//			}
//
//			System.out.println("Access token: " + accessToken.getToken() + " - Access token secret: " + accessToken.getTokenSecret());
//			StatusUpdate latestStatus = new StatusUpdate("这是第一条");
//			latestStatus.media(new File("f:/logo.png"));
//			twitter.updateStatus(latestStatus);

			// ===========
			System.out.println("Access token: " + accessToken2.getToken() + " - Access token secret: " + accessToken2.getTokenSecret());
			StatusUpdate latestStatus2 = new StatusUpdate("第二条也来啦，哈哈");
			latestStatus2.media(new File("f:/logo.png"));
			twitter2.updateStatus(latestStatus2);
			System.exit(0);
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to get accessToken: " + te.getMessage());
			System.exit(-1);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("Failed to read the system input.");
			System.exit(-1);
		}
	}
}