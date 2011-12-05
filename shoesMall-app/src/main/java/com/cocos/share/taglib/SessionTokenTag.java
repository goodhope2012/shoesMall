package com.cocos.share.taglib;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import com.cocos.share.constants.Constants;

public class SessionTokenTag extends TagSupport {
	private static final long serialVersionUID = -653418153910154032L;
	private static final Logger LOGGER = Logger.getLogger(SessionTokenTag.class);

	public int doEndTag() {
		String formUUIDToken = UUID.randomUUID().toString();
		HttpSession session = ((HttpServletRequest) pageContext.getRequest()).getSession(true);
		session.setAttribute(formUUIDToken, formUUIDToken);
		try {
			JspWriter out = pageContext.getOut();
			out.println("<input type='hidden' name='" + Constants.FORM_UUID_TOKEN + "' value='" + formUUIDToken + "' />");
		} catch (IOException e) {
			LOGGER.error("Error in HelloWorld taglib: ", e);
		}
		return SKIP_BODY;
	}
}