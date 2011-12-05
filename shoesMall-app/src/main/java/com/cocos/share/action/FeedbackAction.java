package com.cocos.share.action;

import java.util.Date;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocos.share.dao.FeedbackDAO;
import com.cocos.share.domain.Feedback;
import com.cocos.share.json.JsonResponse;

@Controller
public class FeedbackAction {
	private static final Logger LOGGER = Logger.getLogger(FeedbackAction.class);
	@Autowired
	private FeedbackDAO feedbackDAO;

	@ResponseBody
	@RequestMapping("/feedback")
	public JsonResponse feedback(@Valid Feedback feedback, BindingResult result) {
		LOGGER.info("============feedback==========" + feedback.getTopic());
		feedback.setDate(new Date());
		feedbackDAO.saveOrUpdate(feedback);
		return new JsonResponse(1, "反馈成功!");
	}
}