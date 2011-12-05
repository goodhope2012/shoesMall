package com.shoesMall.jcaptcha;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.FunkyBackgroundGenerator;
import com.octo.captcha.component.image.color.ColorGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.color.SingleColorGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

import java.awt.*;

public class IspeedImageCaptchaEngine extends ListImageCaptchaEngine {

	@Override
	protected void buildInitialFactories() {
		WordGenerator wgen = new RandomWordGenerator("123456789");
		RandomRangeColorGenerator cgen = new RandomRangeColorGenerator(new int[] { 0, 100 }, new int[] { 0, 100 }, new int[] { 0, 100 });
		// 文字显示4个数
		TextPaster textPaster = new RandomTextPaster(4, 4, cgen, true);
		// 图片的大小
		ColorGenerator colorGenerator = new SingleColorGenerator(Color.ORANGE);
		BackgroundGenerator backgroundGenerator = new FunkyBackgroundGenerator(125, 50, colorGenerator);
		Font[] fontsList = new Font[] { new Font("Arial", 0, 10), new Font("Tahoma", 0, 10), new Font("Verdana", 0, 10), };

		FontGenerator fontGenerator = new RandomFontGenerator(20, 30, fontsList);

		WordToImage wordToImage = new ComposedWordToImage(fontGenerator, backgroundGenerator, textPaster);
		this.addFactory(new GimpyFactory(wgen, wordToImage));
	}

}
