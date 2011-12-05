package com.cocos.share.action;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginAction {
	private static final Logger LOGGER = Logger.getLogger(LoginAction.class);

	@RequestMapping("/cas/login/{failed}")
	public String login(@PathVariable("failed") String failed, HttpSession session) {
		if (StringUtils.equalsIgnoreCase("failed", failed)) {
			String userName = (String) session.getAttribute("SPRING_SECURITY_LAST_USERNAME");
			Exception e = (Exception) session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
			if (e instanceof BadCredentialsException) {
				LOGGER.error("登陆失败,用户名或密码错误,请重新输入正确的账号密码.");
			} else {
				LOGGER.error(userName + ": login failed! " + e);
			}
			session.setAttribute("SPRING_SECURITY_LAST_EXCEPTION", null);
			LOGGER.error(userName + ": login failed! " + e);
		}
		return "/cas/login";
	}

	@RequestMapping("/cas/login")
	public String login() {
		return "/cas/login";
	}
}