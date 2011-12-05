package com.shoesMall.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.shoesMall.constants.Constants;
import com.shoesMall.utils.PrincipalAwareUtils;

public class AuditTrailInterceptor extends HandlerInterceptorAdapter {
	private static final Logger LOGGER = Logger.getLogger("AuditTrail");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Long startTime = System.currentTimeMillis();
		String clientIp = request.getRemoteAddr();
		request.setAttribute("startTime", startTime);
		request.setAttribute("clientIp", clientIp);
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		StringBuilder auditTrailInfo = new StringBuilder();
		long endTime = System.currentTimeMillis();
		String viewName = modelAndView == null ? "unkown" : modelAndView.getViewName();
		auditTrailInfo.append(PrincipalAwareUtils.getCurrentUserNameWithAnonymous()).append(Constants.COMMA_SEPARATOR);
		auditTrailInfo.append(request.getAttribute("clientIp")).append(Constants.COMMA_SEPARATOR);
		auditTrailInfo.append(request.getRequestedSessionId()).append(Constants.COMMA_SEPARATOR);
		auditTrailInfo.append(request.getProtocol()).append(Constants.COMMA_SEPARATOR);
		auditTrailInfo.append(request.getRequestURI()).append(Constants.COMMA_SEPARATOR);
		auditTrailInfo.append(viewName).append(Constants.COMMA_SEPARATOR);
		auditTrailInfo.append(endTime - (Long) request.getAttribute("startTime")).append("ms");
		LOGGER.info(auditTrailInfo.toString());
		super.postHandle(request, response, handler, modelAndView);
	}
}