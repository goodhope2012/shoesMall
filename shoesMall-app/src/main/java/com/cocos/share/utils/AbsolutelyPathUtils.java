package com.cocos.share.utils;

import javax.servlet.http.HttpServletRequest;

import com.cocos.share.constants.Constants;

public class AbsolutelyPathUtils {

	public static String getAbsolutelypath(HttpServletRequest request) {
		String absoluteContextPath;
		if (request.getServerPort() == 80) {
			absoluteContextPath = Constants.HTTP_PROTOCOL + request.getServerName() + request.getContextPath();
		} else if (request.getServerPort() == 443) {
			absoluteContextPath = Constants.HTTPS_PROTOCOL + request.getServerName() + request.getContextPath();
		} else {
			absoluteContextPath = Constants.HTTP_PROTOCOL + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		}
		return absoluteContextPath;
	}
}