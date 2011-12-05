package com.cocos.share.action;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cocos.share.dao.UserBehaviorDAO;
import com.cocos.share.domain.UserBehavior;

@Controller
public class ReportAction {
	private static final Logger LOGGER = Logger.getLogger(ReportAction.class);
	@Autowired
	private UserBehaviorDAO userBehaviorDAO;

	@RequestMapping("/report")
	// @ResponseBody
	// @RequestMapping("/statistics")
	public String report() {
		String script = "";
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			List<UserBehavior> hehaviorList = userBehaviorDAO.list();
			LOGGER.info("hehaviorList.size = " + hehaviorList.size());
			script = objectMapper.writeValueAsString(hehaviorList);
		} catch (IOException e) {
			LOGGER.error(e, e);
		}
		LOGGER.info("JSON----" + script);
		return "/report/index";
	}
}