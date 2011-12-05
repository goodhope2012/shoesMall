package com.cocos.share.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cocos.share.constants.Constants;
import com.cocos.share.utils.AbsolutelyPathUtils;

//HandlerInterceptorAdapter是单例 所有变量会被共享
//you can implements ServletContextAware to get servletContext, the servletContext is application scope.
public class AbsolutelyPathInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute(Constants.ABSOLUTE_CONTEXT_PATH, AbsolutelyPathUtils.getAbsolutelypath(request));
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// absoluteContextPath in request may be missing if request redirected
		if (modelAndView != null && StringUtils.isNotBlank((String) request.getAttribute(Constants.ABSOLUTE_CONTEXT_PATH))) {
			modelAndView.addObject(Constants.ABSOLUTE_CONTEXT_PATH, request.getAttribute(Constants.ABSOLUTE_CONTEXT_PATH));
		}
		super.postHandle(request, response, handler, modelAndView);
	}
}