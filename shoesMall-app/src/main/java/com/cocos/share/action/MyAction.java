package com.cocos.share.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocos.share.dao.AppDAO;
import com.cocos.share.dao.SnsDAO;
import com.cocos.share.dao.UserBehaviorDAO;
import com.cocos.share.dao.UserDAO;
import com.cocos.share.domain.App;
import com.cocos.share.domain.AppSns;
import com.cocos.share.domain.Sns;
import com.cocos.share.domain.User;
import com.cocos.share.domain.UserBehavior;
import com.cocos.share.json.JsonAppSnsListList;
import com.cocos.share.json.JsonResponse;
import com.cocos.share.utils.AppKeyUtils;
import com.cocos.share.utils.PrincipalAwareUtils;

@Controller
public class MyAction {
	private static final Logger LOGGER = Logger.getLogger(MyAction.class);
	@Autowired
	private AppDAO appDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserBehaviorDAO userBehaviorDAO;
	@Autowired
	private SnsDAO snsDAO;

	@RequestMapping("/my")
	public String my(Model model) {
		String userName = PrincipalAwareUtils.getCurrentUserNameWithoutAnonymous();
		User user = userDAO.findByUnique("name", userName);
		model.addAttribute(user);
		return "/my/account";
	}

	@RequestMapping(value = "/my/apps/{shareKey}", method = RequestMethod.GET)
	public String myAppByShareKey(@PathVariable("shareKey") String shareKey, Model model) {
		List<Sns> snsList = snsDAO.list();
		model.addAttribute("snsList", snsList);

		App app = appDAO.findByUnique("shareKey", shareKey);
		model.addAttribute(app);
		LOGGER.info(app.getAppSnsList().size());

		String allVisits = "";
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			List<UserBehavior> hehaviorList = userBehaviorDAO.listByProperty("shareKey", shareKey);
			LOGGER.info("hehaviorList.size = " + hehaviorList.size());
			allVisits = objectMapper.writeValueAsString(hehaviorList);
		} catch (IOException e) {
			LOGGER.error(e, e);
		}
		allVisits = "[[1319200396480,193],[1319350396480,123],[1319450396480,153],[1319550396480,432],[1319650396480,234],[1319750396480,23],[1319950396480,355]]";
		LOGGER.info("JSON----" + allVisits);
		model.addAttribute("allVisits", allVisits);
		return "/my/appdetails";
	}

	@Transactional
	@ResponseBody
	@RequestMapping(value = "/my/apps/{shareKey}/modify/info", method = RequestMethod.POST)
	public JsonResponse modifyAppInfo(@PathVariable("shareKey") String shareKey, @RequestBody App app) {
		App dbApp = appDAO.findByUnique("shareKey", shareKey);
		if (dbApp == null) {
			LOGGER.error("App shareKey = " + app.getId() + " not exists.");
			return new JsonResponse(1, "修改的应用不存在!");
		}
		dbApp.setCategory(app.getCategory());
		dbApp.setDescription(app.getDescription());
		dbApp.setName(app.getName());
		dbApp.setPlatform(app.getPlatform());
		dbApp.setUrl(app.getUrl());
		appDAO.saveOrUpdate(dbApp);
		return new JsonResponse(1, "更新成功");
	}

	@Transactional
	@ResponseBody
	@RequestMapping(value = "/my/apps/{shareKey}/modify/sns", method = RequestMethod.POST)
	public JsonResponse modifyAppSns(@PathVariable("shareKey") String shareKey, @RequestBody JsonAppSnsListList jsonAppSnsListList) {
		App dbApp = appDAO.findByUnique("shareKey", shareKey);
		if (dbApp == null) {
			LOGGER.error("App shareKey = " + shareKey + " not exists.");
			return new JsonResponse(1, "修改的应用不存在!");
		}
		List<AppSns> newSnsList = new ArrayList<AppSns>();
		for (AppSns appSns : jsonAppSnsListList) {
			LOGGER.info(appSns.getSnsKey());
			if (appSns.getId() == null || appSns.getId() == 0) {
				appSns.setAppId(dbApp.getId());
				appSns.setCreatedDate(new Date());
				newSnsList.add(appSns);
			}
			for (AppSns mySns : dbApp.getAppSnsList()) {
				if (mySns.getId().equals(appSns.getId())) {
					mySns.setSnsAccount(appSns.getSnsAccount());
					mySns.setSnsKey(appSns.getSnsKey());
					mySns.setSnsSecret(appSns.getSnsSecret());
					mySns.setTemplet(appSns.getTemplet());
					newSnsList.add(mySns);
					break;
				}
			}
		}
		dbApp.getAppSnsList().clear();
		dbApp.setAppSnsList(newSnsList);
		appDAO.saveOrUpdate(dbApp);
		return new JsonResponse(1, "更新成功!");
	}

	@RequestMapping("/my/apps")
	public String myApps(Model model) {
		String userName = PrincipalAwareUtils.getCurrentUserNameWithoutAnonymous();
		User user = userDAO.findByUnique("name", userName);
		List<App> appList = appDAO.listByProperty("user", user);
		model.addAttribute(appList);
		LOGGER.info("app num  === === " + appList.size());
		return "/my/apps";
	}

	@RequestMapping("/my/apps/new")
	public String addApp() {
		return "/my/new";
	}

	@Transactional
	@RequestMapping("/my/apps/add")
	public String addApp(@Valid App app, BindingResult result) {
		LOGGER.info("app === " + app);
		if (app != null) {
			String userName = PrincipalAwareUtils.getCurrentUserNameWithoutAnonymous();
			User user = userDAO.findByUnique("name", userName);
			app.setUser(user);
			app.setCreatedDate(new Date());
			appDAO.saveOrUpdate(app);
			AppKeyUtils.generateShareKey(app);
			appDAO.saveOrUpdate(app);
		}
		return "redirect:/my/apps";
	}
}