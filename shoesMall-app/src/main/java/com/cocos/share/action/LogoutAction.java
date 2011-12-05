package com.cocos.share.action;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutAction {
	@RequestMapping("/cas/logout")
	public String logout() {
		SecurityContextHolder.clearContext();
		return "redirect:/";
	}
}