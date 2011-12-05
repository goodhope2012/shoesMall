package com.cocos.share.action;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DevAction {
	private static final Logger LOGGER = Logger.getLogger(DevAction.class);

	@RequestMapping("/dev/doc")
	public String report() {
		LOGGER.info("Show sdk doc.");
		return "/dev/doc";
	}
}