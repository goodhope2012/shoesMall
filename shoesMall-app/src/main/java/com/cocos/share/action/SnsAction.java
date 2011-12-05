package com.cocos.share.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cocos.share.constants.Constants;
import com.cocos.share.dao.AppDAO;
import com.cocos.share.dao.UserBehaviorDAO;
import com.cocos.share.domain.App;
import com.cocos.share.domain.AppSns;
import com.cocos.share.domain.Blog;
import com.cocos.share.domain.UserBehavior;
import com.cocos.share.json.JsonResponse;
import com.cocos.share.sns.BlogPool;
import com.cocos.share.sns.SnsInterface;
import com.cocos.share.sns.SnsServiceFactory;
import com.cocos.share.sns.SnsServicePool;

@Controller
public class SnsAction {
	private static final String COCOS_SHARE_CALLBACK = "cocos2dx://weibo";
	private static final Logger LOGGER = Logger.getLogger(SnsAction.class);
	@Autowired
	private UserBehaviorDAO userBehaviorDAO;
	@Autowired
	private AppDAO appDAO;
	private ObjectMapper objectMapper = new ObjectMapper();

	@ResponseBody
	@RequestMapping("/sns/share/{snsBriefName}")
	public JsonResponse shareSNS(@PathVariable("snsBriefName") String snsBriefName, @RequestParam("switchUser") int switchUser, @RequestParam("jsonBehavior") String jsonBehavior, @RequestParam("shareContent") String shareContent, @RequestParam("shareImage") MultipartFile shareImage)
			throws Exception, JsonMappingException, UnsupportedEncodingException, IOException {
		UserBehavior behavior = objectMapper.readValue(URLDecoder.decode(jsonBehavior, "utf-8").getBytes(), UserBehavior.class);
		shareContent = URLDecoder.decode(shareContent, "utf-8");
		File shareImageFile = saveMultipartFile(shareImage);
		App app = appDAO.findByUnique("shareKey", behavior.getShareKey());
		if (app == null) {
			LOGGER.info("Share to SNS: " + snsBriefName + " fail, app not exists.");
			behavior.setResult("ERROR");
			userBehaviorDAO.saveOrUpdate(behavior);
			return new JsonResponse(-2, "应用未注册!");
		}
		String userKey = behavior.getShareKey() + behavior.getImei() + snsBriefName;
		if (switchUser == 1) {
			BlogPool.push(userKey, new Blog(shareContent, shareImageFile));
			for (AppSns appSns : app.getAppSnsList()) {
				if (appSns.getSns().getBriefName().equals(snsBriefName)) {
					SnsInterface weiboService = SnsServiceFactory.createSns(snsBriefName, appSns.getSnsKey(), appSns.getSnsSecret(), COCOS_SHARE_CALLBACK, app.getUrl());
					SnsServicePool.put(userKey, weiboService);
					return new JsonResponse(-1, weiboService.getAuthorizationURL(), "需要登陆!");
				}
			}
		} else {
			for (AppSns appSns : app.getAppSnsList()) {
				if (appSns.getSns().getBriefName().equals(snsBriefName)) {
					SnsInterface weiboService = SnsServicePool.get(userKey);
					if (weiboService == null) {
						BlogPool.push(userKey, new Blog(shareContent, shareImageFile));
						weiboService = SnsServiceFactory.createSns(snsBriefName, appSns.getSnsKey(), appSns.getSnsSecret(), COCOS_SHARE_CALLBACK, app.getUrl());
						SnsServicePool.put(userKey, weiboService);
						return new JsonResponse(-1, weiboService.getAuthorizationURL(), "需要登陆!");
					}
					boolean success = weiboService.share(new Blog(shareContent, shareImageFile));
					if (!success) {
						BlogPool.push(userKey, new Blog(shareContent, shareImageFile));
						weiboService = SnsServiceFactory.createSns(snsBriefName, appSns.getSnsKey(), appSns.getSnsSecret(), COCOS_SHARE_CALLBACK, app.getUrl());
						SnsServicePool.put(userKey, weiboService);
						return new JsonResponse(-1, weiboService.getAuthorizationURL(), "需要登陆!");
					}
					userBehaviorDAO.saveOrUpdate(behavior);
					return new JsonResponse(1, "分享成功!");
				}
			}
		}
		return new JsonResponse(-3, "SDK配置错误或SNS未接入!");
	}

	private File saveMultipartFile(MultipartFile shareImage) throws IOException {
		File shareImageFile = null;
		if (!shareImage.isEmpty()) {
			shareImageFile = new File(Constants.USER_DIR + File.separator + UUID.randomUUID() + shareImage.getOriginalFilename());
			shareImage.transferTo(shareImageFile);
		}
		return shareImageFile;
	}

	@ResponseBody
	@RequestMapping("/sns/login/{snsBriefName}")
	public JsonResponse loginSNS(@PathVariable("snsBriefName") String snsBriefName, @RequestParam("accessPin") String accessPin, @RequestParam String jsonBehavior) throws Exception {
		UserBehavior behavior = objectMapper.readValue(URLDecoder.decode(jsonBehavior, "utf-8").getBytes(), UserBehavior.class);
		LOGGER.info("Login via SNS: " + snsBriefName + ", access pin: " + accessPin);
		App app = appDAO.findByUnique("shareKey", behavior.getShareKey());
		if (app == null) {
			behavior.setResult("ERROR");
			userBehaviorDAO.saveOrUpdate(behavior);
			return new JsonResponse(-2, "应用未注册");
		}
		String userKey = behavior.getShareKey() + behavior.getImei() + snsBriefName;
		for (AppSns appSns : app.getAppSnsList()) {
			if (appSns.getSns().getBriefName().equals(snsBriefName)) {
				SnsInterface weiboService = SnsServicePool.get(userKey);
				weiboService.initAccessToken(accessPin);
				boolean success = weiboService.share(BlogPool.pop(userKey));
				return success ? new JsonResponse(1, "发布成功") : new JsonResponse(-1, "发布失败");
			}
		}
		userBehaviorDAO.saveOrUpdate(behavior);
		LOGGER.error("配置错误或SNS未接入!");
		return new JsonResponse(-3, "配置错误或SNS未接入!");
	}
}