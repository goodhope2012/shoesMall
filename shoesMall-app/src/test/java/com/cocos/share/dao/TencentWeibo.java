package com.cocos.share.dao;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import com.tencent.weibo.api.T_API;
import com.tencent.weibo.beans.OAuth;
import com.tencent.weibo.utils.OAuthClient;
import com.tencent.weibo.utils.WeiBoConst;

public class TencentWeibo {

	private static String verify = null;

	public static void main(String[] args) throws Exception {
		test_list_t();
	}

	private static void test_list_t() throws Exception {
		OAuth oauth = new OAuth("801059072", "02d05d9495ea98219990e7c7817947a4", "cocos2dx://weibo");
		OAuthClient auth = new OAuthClient();
		oauth = auth.requestToken(oauth);
		if (oauth.getStatus() == 1) {
			System.out.println("Get Request Token failed!");
			return;
		} else {
			String oauth_token = oauth.getOauth_token();
			String url = "http://open.t.qq.com/cgi-bin/authorize?oauth_token=" + oauth_token;
			System.out.println("Get verification code......");
			if (!java.awt.Desktop.isDesktopSupported()) {

				System.err.println("Desktop is not supported (fatal)");
				System.exit(1);
			}
			java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
			if (desktop == null || !desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {

				System.err.println("Desktop doesn't support the browse action (fatal)");
				System.exit(1);
			}
			try {
				desktop.browse(new URI(url));
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			} catch (URISyntaxException e) {
				e.printStackTrace();
				System.exit(1);
			}
			System.out.println("Input your verification code：");
			Scanner in = new Scanner(System.in);
			verify = in.nextLine();
			// 获取access token
			System.out.println("GetAccessToken......");
			oauth.setOauth_verifier(verify);
			oauth = auth.accessToken(oauth);
			System.out.println("Response from server：");
			if (oauth.getStatus() == 2) {
				System.out.println("Get Access Token failed!");
				return;
			} else {
				T_API tAPI = new T_API();
				String response = tAPI.add_pic(oauth, WeiBoConst.ResultType.ResultType_Json, "just a test from dev.", "127.0.0.1", "f:/logo.png");
				System.out.println("发布结果：" + response);
			}
			in.close();
		}
	}

}
