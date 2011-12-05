package com.cocos.share.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocos.share.utils.AbsolutelyPathUtils;

@Controller
public class StatisticsScriptAction {
	private static final Logger LOGGER = Logger.getLogger(StatisticsScriptAction.class);

	@ResponseBody
	@RequestMapping("/script/statistics")
	public String save(@RequestParam String appId, HttpServletRequest request) {
		LOGGER.info("appId=" + appId);
		StringBuilder script = new StringBuilder();
		script.append("document.write(\"<a href='#'>\");");
		script.append("document.write(\"<img border=0 src='" + AbsolutelyPathUtils.getAbsolutelypath(request) + "/images/favicon.ico'/>\");");
		script.append("document.write(\"</a>\");");
		return script.toString();
	}
}