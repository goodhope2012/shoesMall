package com.blank.web.action;

import java.io.Serializable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: liuhm Date: 11-9-21
 */
@Controller
public interface BaseAction<T, PK extends Serializable> {

	public static final String DEFAULT_VIEW_FORM = "form";
	public static final String DEFAULT_VIEW_LIST = "list";

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String get(@PathVariable("id") PK id, Model model);

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") PK id, Model model);

	@RequestMapping(value = "/new")
	public String _new(Model model);

	@RequestMapping(method = RequestMethod.POST)
	public String save(T t, BindingResult result, Model model);

	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable("id") PK id, Model model);

	@RequestMapping(method = RequestMethod.PUT)
	public String update(T t, BindingResult result, Model model);

	@RequestMapping(value = "/list")
	public String list(@RequestParam(required = false, defaultValue = "1") Integer pageNo, @RequestParam(required = false, defaultValue = "") String search, @RequestParam(required = false, defaultValue = "") String order, @RequestParam(required = false, defaultValue = "") String orderBy, Model model);

	@RequestMapping(value = "/listAjax")
	@ResponseBody
	public String ajaxList(@RequestParam(required = false, defaultValue = "1") Integer pageNo, @RequestParam(required = false, defaultValue = "") String search, @RequestParam(required = false, defaultValue = "") String order, @RequestParam(required = false, defaultValue = "") String orderBy,
			Model model);

	@RequestMapping("/listAll")
	public String listAll(@RequestParam(required = false, defaultValue = "") String order, @RequestParam(required = false, defaultValue = "") String orderBy, Model model);

	@RequestMapping("/listAllAjax")
	@ResponseBody
	public String ajaxListAll(@RequestParam(required = false, defaultValue = "") String order, @RequestParam(required = false, defaultValue = "") String orderBy, Model model);
}
