package com.cocos.share.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocos.share.constants.Constants;
import com.cocos.share.dao.UserDAO;
import com.cocos.share.domain.Role;
import com.cocos.share.domain.User;
import com.cocos.share.json.JsonResponse;

@Controller
public class RegisterAction {
	private static final Logger LOGGER = Logger.getLogger(RegisterAction.class);
	@Autowired
	private UserDAO userDAO;

	@RequestMapping("/cas/register")
	public String register() {
		return "/cas/register";
	}

	// @Valid标识的domain自动回显
	@ResponseBody
	@Transactional
	@RequestMapping(value = "/cas/register/add", method = RequestMethod.POST)
	public JsonResponse addUser(HttpServletRequest request, @Valid User user, Model model) throws JsonGenerationException, JsonMappingException, IOException {
		String jcaptchaVerifyError = (String) request.getAttribute(Constants.JCAPTCHA_VERIFY_ERROR_CODE);
		String duplicateSubmissionError = (String) request.getAttribute(Constants.DUPLICATE_SUBMISSION_ERROR_CODE);
		if (StringUtils.isNotBlank(jcaptchaVerifyError) || StringUtils.isNotBlank(duplicateSubmissionError)) {
			List<String> errors = new ArrayList<String>();
			errors.add(jcaptchaVerifyError);
			errors.add(duplicateSubmissionError);
			model.addAttribute("errors", errors);
			LOGGER.info("verifyError==" + jcaptchaVerifyError);
			LOGGER.info("duplicateSubmission==" + duplicateSubmissionError);
			return new JsonResponse(1, new ObjectMapper().writeValueAsString(errors));
		}

		user.setPassword(DigestUtils.shaHex(user.getPassword()));
		user.setStatus(1);// TODO set default value
		List<Role> roles = new ArrayList<Role>();
		Role role = new Role(1L, "ROLE_USER");// TODO refactor to enum
		roles.add(role);
		user.setRoles(roles);
		userDAO.saveOrUpdate(user);
		LOGGER.info("Add user: " + user);// TODO toString
		List<String> msgs = new ArrayList<String>();
		msgs.add("Add user successfully!");
		model.addAttribute("msgs", msgs);
		return new JsonResponse(1, "恭喜你, 注册成功!");
	}
}