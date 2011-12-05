package com.renren.api.client.services;

import org.json.simple.JSONObject;

import com.renren.api.client.RenrenApiClient;
import com.renren.api.client.utils.HttpURLUtils;

public class RenrenPhotoServiceTest {

	public static void main(String[] args) throws Exception {
		RenrenApiClient client;
		// String consumerKey = "510238aca2af4f3c9ac60e22c6b7d518";
		// Desktop.getDesktop().browse(new URI("https://graph.renren.com/oauth/authorize?scope=photo_upload%20publish_feed%20publish_share%20status_update&client_id=" + consumerKey + "&response_type=token&display=mobile&redirect_uri=http://graph.renren.com/oauth/login_success.html"));
		// String accessToken2 = null;
		// BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
		// String pin2 = br2.readLine();
//		String token = "167883|6.ca6e6504a970a3e2c765860bfa8dfb58.2592000.1323612000-191519017";
		String token = "167883|6.d34dcc68c3bfccfd4e1524b8dabaa14a.2592000.1323622800-191519017";
		System.out.println(token);
		client = new RenrenApiClient(token, true);
		long albumId = 1;
		String fileName = "http://www.baidu.com/img/baidu_sylogo1.gif";
		String desc = "你妹oioioioioii";
		byte[] photo = HttpURLUtils.getBytes(fileName, null);
		JSONObject ret = client.getPhotoService().upload(albumId, photo, fileName, desc);
		System.out.println("photo:" + ret);
	}
}
