package com.cocos.share.action;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SKDCenterAction {
	private static final Logger LOGGER = Logger.getLogger(SKDCenterAction.class);

	@RequestMapping("/sdkcenter")
	public String listSdk() {
		LOGGER.info(" get sdk....");
		return "/sdkcenter/index";
	}
	
	@RequestMapping("/sdkcenter/getsdk")
	public String getSdk() {
		LOGGER.info(" get sdk....");
		return "/sdkcenter/getsdk";
	}
}