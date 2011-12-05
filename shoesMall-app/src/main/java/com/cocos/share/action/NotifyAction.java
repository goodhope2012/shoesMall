package com.cocos.share.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocos.share.dao.AppDAO;
import com.cocos.share.dao.UserBehaviorDAO;
import com.cocos.share.domain.App;
import com.cocos.share.domain.UserBehavior;
import com.cocos.share.json.JsonResponse;
import com.cocos.share.json.JsonUserBehaviorList;

@Controller
public class NotifyAction {
	private static final Logger LOGGER = Logger.getLogger(NotifyAction.class);
	@Autowired
	private UserBehaviorDAO userBehaviorDAO;
	@Autowired
	private AppDAO appDAO;

	@Transactional
	@ResponseBody
	@RequestMapping("/init")
	public JsonResponse init(@RequestBody UserBehavior behavior) {
		LOGGER.info("init app " + behavior.getShareKey());
		App app = appDAO.findByUnique("shareKey", behavior.getShareKey());
		if (app == null) {
			behavior.setResult("ERROR");
			userBehaviorDAO.saveOrUpdate(behavior);
			return new JsonResponse(1, "App not exists.");
		}
		userBehaviorDAO.saveOrUpdate(behavior);
		return new JsonResponse(1, "", app.getAppSnsList());
	}

	@Transactional
	@ResponseBody
	@RequestMapping("/notify")
	public JsonResponse notify(@RequestBody UserBehavior behavior) {
		LOGGER.info("behavior----" + behavior.getAction());
		userBehaviorDAO.saveOrUpdate(behavior);
		return new JsonResponse(1, "GOOD ACTION...");
	}

	@Transactional
	@ResponseBody
	@RequestMapping("/notify/batch")
	public JsonResponse notifyBatch(@RequestBody JsonUserBehaviorList behaviorList) {
		LOGGER.info("behavior----" + behaviorList.get(0).getAction());
		userBehaviorDAO.saveOrUpdateAll(behaviorList);
		return new JsonResponse(1, "GOOD ACTION...");
	}
}