//package com.blank.web.action;
//
//import java.io.Serializable;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.codehaus.jackson.node.ObjectNode;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.BeanFactoryUtils;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
//
//import com.blank.dao.support.PropertyFilter;
//import com.blank.exception.WebContextException;
//import com.blank.service.BaseService;
//import com.blank.util.HibernateWebUtils;
//import com.blank.util.JsonUtil;
//import com.blank.util.Page;
//import com.blank.util.WebUtil;
//import com.blank.web.context.WebContext;
//import com.cocos.share.utils.GenericReflectionUtils;
//
//@Controller
//public abstract class BaseActionAdaptor<T, PK extends Serializable> implements BaseAction<T, PK>, ApplicationContextAware {
//
//	protected BaseService<T, PK> service;
//
//	protected String key;
//	protected String formView;
//	protected String listView;
//	protected Class<T> domainClass;
//
//	protected Logger logger = LoggerFactory.getLogger(getClass());
//
//	@ModelAttribute("contextPath")
//	public String contextPath(HttpServletRequest request) {
//		return request.getContextPath();
//	}
//
//	@ModelAttribute("list")
//	public String listView() {
//		return listView;
//	}
//
//	public String update(T t, BindingResult result, Model model) {
//		throw new UnsupportedOperationException("not support update()");
//	}
//
//	public String delete(@PathVariable PK id, Model model) {
//		throw new UnsupportedOperationException("not support delete()");
//	}
//
//	public String save(T t, BindingResult result, Model model) {
//		throw new UnsupportedOperationException("not support save()");
//	}
//
//	public String get(@PathVariable("id") PK id, Model model) {
//		T t = service.get(id, false);
//		model.addAttribute(key, t);
//		return formView;
//	}
//
//	public String list(@RequestParam(required = false, defaultValue = "1") Integer pageNo, @RequestParam(required = false, defaultValue = "") String search, @RequestParam(required = false, defaultValue = "") String order, @RequestParam(required = false, defaultValue = "") String orderBy, Model model) {
//		Page<T> page = new Page<T>(pageNo);
//		page.setOrder(order);
//		page.setOrderBy(orderBy);
//		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(search);
//		// service.list(page, filters);
//		model.addAttribute("page", page);
//		model.addAttribute("search", search);
//		model.addAttribute("order", order);
//		model.addAttribute("orderBy", orderBy);
//		return listView;
//	}
//
//	@ResponseBody
//	public String ajaxList(@RequestParam(required = false, defaultValue = "1") Integer pageNo, @RequestParam(required = false, defaultValue = "") String search, @RequestParam(required = false, defaultValue = "") String order, @RequestParam(required = false, defaultValue = "") String orderBy,
//			Model model) {
//		String view = list(pageNo, search, order, orderBy, model);
//		try {
//			ObjectNode node = WebUtil.renderTable(view, model);
//			return JsonUtil.requestSuccess(node);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return JsonUtil.requestError("error");
//	}
//
//	public String listAll(@RequestParam(required = false, defaultValue = "") String order, @RequestParam(required = false, defaultValue = "") String orderBy, Model model) {
//		Page<T> page = new Page<T>();
//		page.setOrder(order);
//		page.setOrderBy(orderBy);
//		// service.listAll(page);
//		model.addAttribute("page", page);
//		model.addAttribute("order", order);
//		model.addAttribute("orderBy", orderBy);
//		return listView;
//	}
//
//	public String ajaxListAll(@RequestParam(required = false, defaultValue = "") String order, @RequestParam(required = false, defaultValue = "") String orderBy, Model model) {
//		String view = listAll(order, orderBy, model);
//		try {
//			ObjectNode node = WebUtil.renderTable(view, model);
//			return JsonUtil.requestSuccess(node);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return JsonUtil.requestError("error");
//	}
//
//	public String _new(Model model) {
//		try {
//			T t = domainClass.newInstance();
//			model.addAttribute(key, t);
//		} catch (InstantiationException e) {
//			logger.error("the error seems will not occur,can not instance {}", domainClass.getName(), e);
//		} catch (IllegalAccessException e) {
//			logger.error("the error seems will not occur,not public class {}", domainClass.getName(), e);
//		}
//		return formView;
//	}
//
//	public String edit(@PathVariable("id") PK id, Model model) {
//		get(id, model);
//		return formView;
//	}
//
//	/**
//	 * 404
//	 * 
//	 * @param ex
//	 *            :
//	 * @param model
//	 *            :
//	 * @return :
//	 */
//	@ExceptionHandler(value = { NoSuchRequestHandlingMethodException.class })
//	@ResponseStatus(value = HttpStatus.NOT_FOUND)
//	protected String handler404(Exception ex, Model model) {
//		logger.error("{},404找不到请求", WebContext.getRequest().getRequestURL().toString(), ex);
//		model.addAttribute("message", "request not found");
//		return "exception/exception";
//	}
//
//	/**
//	 * 500
//	 * 
//	 * @param ex
//	 *            :
//	 * @param model
//	 *            :
//	 * @return :
//	 */
//	@ExceptionHandler
//	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//	protected String handler500(Exception ex, Model model) {
//		logger.error("{} : 500出错", WebContext.getRequest().getRequestURL(), ex);
//		model.addAttribute("message", "sorry,the serve is down for maintain，please wait for a moment");
//		return "exception/exception";
//	}
//
//	@ExceptionHandler(value = WebContextException.class)
//	protected String handlerWebException(Exception e, Model model) {
//		model.addAttribute("message", "this error seems will not occur.");
//		return "exception/exception";
//	}
//
//	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//		this.domainClass = GenericReflectionUtils.getSuperClassGenricType(getClass());
//		this.key = this.domainClass.getSimpleName().toLowerCase();
//		this.formView = key + "/" + BaseAction.DEFAULT_VIEW_FORM;
//		this.listView = key + "/" + BaseAction.DEFAULT_VIEW_LIST;
//		Map<String, ? extends BaseService> map = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, BaseService.class);
//		for (BaseService service : map.values()) {
//			Class<?> type = GenericReflectionUtils.getSuperClassGenricType(service.getClass().getSuperclass());
//			if (type == domainClass) {
//				// noinspection unchecked
//				this.service = service;
//				break;
//			}
//		}
//	}
//}
