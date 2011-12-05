package facebook4j.test;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import com.face4j.facebook.Facebook;
import com.face4j.facebook.OAuthAccessToken;
import com.face4j.facebook.entity.User;
import com.face4j.facebook.exception.FacebookException;
import com.face4j.facebook.factory.FacebookFactory;

public class FacebookTest {
	public static void main(String[] args) throws IOException, URISyntaxException, FacebookException {
		Desktop.getDesktop().browse(new URI("https://www.facebook.com/dialog/oauth?client_id=271919379516751&redirect_uri=http://www.cocos2d-x.org&scope=publish_stream,read_stream,offline_access,publish_actions&response_type=token"));
		System.out.println("Input access token >");

		BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
		String accessTokenMsg = br2.readLine();
		System.out.println("accessToken ==== " + accessTokenMsg);

		FacebookFactory facebookFactory = new FacebookFactory("271919379516751", "8b729f86167300f478edef8b8a0fc440");
		Facebook facebook = facebookFactory.getInstance(new OAuthAccessToken(accessTokenMsg));
		User currentUser = facebook.getCurrentUser();
		facebook.wallPost("uuuddd2335", "http://www.cocos2d-x.org/themes/cocos2dx/images/cocos2d-Logo-Landscape.png", "http://www.cocos2d-x.org/themes/cocos2dx/images/cocos2d-Logo-Landscape.png", null, null, null, "http://www.cocos2d-x.org/themes/cocos2dx/images/cocos2d-Logo-Landscape.png",
				currentUser.getId());
	}
}