package com.cocos.share.jcaptcha;

import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

public class CaptchaServiceSingleton {
	private static final CaptchaEngine CAPTCHA_ENGINE = new IspeedImageCaptchaEngine();
	private static ImageCaptchaService instance = new DefaultManageableImageCaptchaService(new FastHashMapCaptchaStore(), CAPTCHA_ENGINE, 180, 100000, 75000);

	private CaptchaServiceSingleton() {
	}

	public static ImageCaptchaService getInstance() {
		return instance;
	}
}