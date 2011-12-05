package com.shoesMall.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.shoesMall.utils.PrincipalAwareUtils;

public class PrincipalAwareInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute("userName", PrincipalAwareUtils.getCurrentUserNameWithoutAnonymous());
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (modelAndView != null && StringUtils.isNotBlank((String) request.getAttribute("userName"))) {
			modelAndView.addObject("userName", request.getAttribute("userName"));
		}
		super.postHandle(request, response, handler, modelAndView);
	}
}