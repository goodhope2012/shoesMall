package com.cocos.share.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import weibo4j.Status;
import weibo4j.Weibo;
import weibo4j.WeiboException;
import weibo4j.http.AccessToken;
import weibo4j.http.RequestToken;
import weibo4j.util.BareBonesBrowserLaunch;

public class WeiboService {

	public static void main(String[] args) {
		try {
			// Weibo weibo2 = new Weibo("2241811079", "f2f22d18a7abb1896c3e3a356c412e4a");
			// RequestToken requestToken2 = weibo2.getOAuthRequestToken();
			// AccessToken accessToken2 = null;
			// BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
			// while (null == accessToken2) {
			// System.out.println("==============");
			// System.out.println(requestToken2.getAuthorizationURL());
			// System.out.println("==============");
			// BareBonesBrowserLaunch.openURL(requestToken2.getAuthorizationURL());
			// System.out.print("Hit enter when it's done.[Enter]:");
			//
			// String pin = br2.readLine();
			// System.out.println("pin: " + br2.toString());
			// try {
			// accessToken2 = requestToken2.getAccessToken(pin);
			// } catch (WeiboException te) {
			// if (401 == te.getStatusCode()) {
			// System.out.println("Unable to get the access token.");
			// } else {
			// te.printStackTrace();
			// }
			// }
			// }
			// System.out.println("Got access token.");
			// System.out.println("Access token: " + accessToken2.getToken());
			// System.out.println("Access token secret: " + accessToken2.getTokenSecret());
			//
			// weibo2.setToken(accessToken2.getToken(), accessToken2.getTokenSecret());
			//
			// // Status status2 = weibo2.updateStatus("1Q84!");
			// Status status2 = weibo2.uploadStatus("发克咪", new File("E:/cocos-webapps/cocos-share/src/main/webapp/images/add.png"));
			// System.out.println("Successfully updated the status to [" + status2.getText() + "].");

			Weibo weibo = new Weibo("3199617148", "442e7b8b81364dec77a47a5e32870218");
			RequestToken requestToken = weibo.getOAuthRequestToken();
			System.out.println("Request token: " + requestToken.getToken());
			System.out.println("Request token secret: " + requestToken.getTokenSecret());
			AccessToken accessToken = null;

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while (null == accessToken) {
				System.out.println("Open the following URL and grant access to your account:");
				System.out.println(requestToken.getAuthorizationURL());
				BareBonesBrowserLaunch.openURL(requestToken.getAuthorizationURL());
				System.out.print("Hit enter when it's done.[Enter]:");

				String pin = br.readLine();
				System.out.println("pin: " + br.toString());
				try {
					accessToken = requestToken.getAccessToken(pin);
				} catch (WeiboException te) {
					if (401 == te.getStatusCode()) {
						System.out.println("Unable to get the access token.");
					} else {
						te.printStackTrace();
					}
				}
			}
			System.out.println("Got access token.");
			System.out.println("Access token: " + accessToken.getToken());
			System.out.println("Access token secret: " + accessToken.getTokenSecret());

			weibo.setToken(accessToken.getToken(), accessToken.getTokenSecret());

			// Status status = weibo.updateStatus("1Q84!");
			Status status = weibo.uploadStatus("---", new File("d:/bg.jpg"));
			System.out.println("Successfully updated the status to [" + status.getText() + "].");

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(0);
		} catch (WeiboException te) {
			System.out.println("Failed to get timeline: " + te.getMessage());
			System.exit(-1);
		} catch (IOException ioe) {
			System.out.println("Failed to read the system input.");
			System.exit(-1);
		}
	}
}
