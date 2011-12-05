package com.shoesMall.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component("accessDeniedHandlerImpl")
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
	private static final Logger LOGGER = Logger.getLogger(AccessDeniedHandlerImpl.class);
	public static final String HTTP_PROTOCOL = "http://";
	public static final String HTTPS_PROTOCOL = "https://";
	private String accessDeniedUrl = "/accessdenied";

	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		String absoluteContextPath = "";
		if (request.getServerPort() == 80) {
			absoluteContextPath = HTTP_PROTOCOL + request.getServerName() + request.getContextPath();
		} else if (request.getServerPort() == 443) {
			absoluteContextPath = HTTPS_PROTOCOL + request.getServerName() + request.getContextPath();
		} else {
			absoluteContextPath = HTTP_PROTOCOL + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		}

		response.sendRedirect(absoluteContextPath + accessDeniedUrl);
		String deniedMessage = accessDeniedException.getMessage();
		String rp = request.getRequestURI();
		String deniedMsg = rp + " access denied, " + deniedMessage;
		request.getSession().setAttribute("ACCESS_DENIED_MSG", deniedMsg);
		LOGGER.error(deniedMsg);
	}
}