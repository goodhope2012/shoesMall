package com.cocos.share.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class PrincipalAwareUtils {
	private static final String ANONYMOUS_USER = "anonymousUser";

	public static String getCurrentUserNameWithoutAnonymous() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) (authentication.getPrincipal());
			String userName = userDetails.getUsername();
			if (StringUtils.isNotBlank(userName) && !StringUtils.equalsIgnoreCase(ANONYMOUS_USER, userName)) {
				return userName;
			}
		}
		return null;
	}

	public static String getCurrentUserNameWithAnonymous() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) (authentication.getPrincipal());
			String userName = userDetails.getUsername();
			if (StringUtils.isNotBlank(userName)) {
				return userName;
			}
		}
		return null;
	}
}