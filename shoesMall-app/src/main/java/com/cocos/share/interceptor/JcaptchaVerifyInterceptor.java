package com.cocos.share.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cocos.share.constants.Constants;
import com.cocos.share.jcaptcha.CaptchaServiceSingleton;

public class JcaptchaVerifyInterceptor extends HandlerInterceptorAdapter {
	private static final Logger LOGGER = Logger.getLogger(JcaptchaVerifyInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String userCaptchaResponse = request.getParameter("verifycode");
		String sessionId = request.getSession().getId();
		LOGGER.info("Verifycode: " + userCaptchaResponse + ", SessionId: " + sessionId);
		boolean captchaPassed = false;
		try {
			captchaPassed = CaptchaServiceSingleton.getInstance().validateResponseForID(sessionId, userCaptchaResponse);
		} catch (Exception e) {
		}
		if (!captchaPassed) {
			LOGGER.error("Jcaptcha verify error!");
			request.setAttribute(Constants.JCAPTCHA_VERIFY_ERROR_CODE, Constants.JCAPTCHA_VERIFY_ERROR_MSG);
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (modelAndView != null && StringUtils.isNotBlank((String) request.getAttribute(Constants.JCAPTCHA_VERIFY_ERROR_CODE))) {
			modelAndView.addObject(Constants.JCAPTCHA_VERIFY_ERROR_CODE, request.getAttribute(Constants.JCAPTCHA_VERIFY_ERROR_CODE));
		}
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}