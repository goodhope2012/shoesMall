package com.cocos.share.constants;

import java.io.File;

public class Constants {
	private static final String CATALINA_HOME = System.getProperty("catalina.home");
	public static final String USER_DIR = ((CATALINA_HOME == null) ? System.getProperty("user.dir") : CATALINA_HOME) + File.separator;
	public static final String COMMA_SEPARATOR = ",";
	public static final String HTTP_PROTOCOL = "http://";
	public static final String HTTPS_PROTOCOL = "https://";
	public static final String FORM_UUID_TOKEN = "form_uuid_token";
	public static final String ABSOLUTE_CONTEXT_PATH = "absoluteContextPath";

	public static final String DUPLICATE_SUBMISSION_ERROR_CODE = "duplicate_submission_error_code";
	public static final String DUPLICATE_SUBMISSION_ERROR_MSG = "请勿重复提交表单!";
	public static final String JCAPTCHA_VERIFY_ERROR_CODE = "jcaptcha_verify_error_code";
	public static final String JCAPTCHA_VERIFY_ERROR_MSG = "识别码输入错误!";
}