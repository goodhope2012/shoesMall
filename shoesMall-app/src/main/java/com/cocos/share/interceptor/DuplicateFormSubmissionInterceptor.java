package com.cocos.share.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cocos.share.constants.Constants;

public class DuplicateFormSubmissionInterceptor extends HandlerInterceptorAdapter {
	private static final Logger LOGGER = Logger.getLogger(DuplicateFormSubmissionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String formUUIDTokenRequest = (String) request.getParameter(Constants.FORM_UUID_TOKEN);
		if (StringUtils.isNotBlank(formUUIDTokenRequest)) {// if request contain session token
			HttpSession session = request.getSession();
			String formUUIDTokenSession = (String) session.getAttribute(formUUIDTokenRequest);
			LOGGER.info("formUUIDTokenRequest ======== " + formUUIDTokenRequest);
			LOGGER.info("formUUIDTokenSession ======== " + formUUIDTokenRequest);
			if (StringUtils.equalsIgnoreCase(formUUIDTokenSession, formUUIDTokenRequest)) {// valid request, and clear the token in session.
				session.removeAttribute(formUUIDTokenRequest);
			} else {// invalid request token.
				LOGGER.error("Duplicate form submission.");
				request.setAttribute(Constants.DUPLICATE_SUBMISSION_ERROR_CODE, Constants.DUPLICATE_SUBMISSION_ERROR_MSG);
			}
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (modelAndView != null && StringUtils.isNotBlank((String) request.getAttribute(Constants.DUPLICATE_SUBMISSION_ERROR_CODE))) {
			modelAndView.addObject(Constants.DUPLICATE_SUBMISSION_ERROR_CODE, request.getAttribute(Constants.DUPLICATE_SUBMISSION_ERROR_CODE));
		}
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}