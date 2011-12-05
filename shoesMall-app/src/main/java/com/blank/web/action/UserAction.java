//package com.blank.web.action;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.validation.Valid;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.blank.util.JsonUtil;
//import com.blank.util.Page;
//import com.blank.web.chart.DataColumn;
//import com.blank.web.chart.DataTable;
//import com.blank.web.chart.HighChart;
//import com.cocos.share.domain.User;
//
///**
// * User: liuhm Date: 11-9-20
// */
//@Controller
//@RequestMapping("/user")
//public class UserAction extends BaseActionAdaptor<User, Long> {
//
//	@Override
//	@ResponseBody
//	public String save(@Valid User user, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			List<ObjectError> errors = result.getAllErrors();
//			try {
//				return JsonUtil.requestError(errors);
//			} catch (IOException e) {
//				logger.error("the error seems will not occur . parse json error", e);
//				return JsonUtil.DEFAULT_ERROR;
//			}
//		} else {
//			return JsonUtil.requestSuccess("it is valid");
//		}
//	}
//
//	@RequestMapping("/statistics")
//	@ResponseBody
//	public String statistics() throws IOException {
//		Page<User> page = new Page<User>();
//		// service.listAll(page);
//		List<User> users = page.getResult();
//		Map<String, Integer> map = new HashMap<String, Integer>();
//		for (User user : users) {
//			String c = user.getName().substring(0, 1);
//			Integer count = map.get(c);
//			if (count == null) {
//				map.put(c, 1);
//			} else {
//				map.put(c, ++count);
//			}
//		}
//		DataColumn column1 = new DataColumn("char");
//		DataColumn column2 = new DataColumn("count");
//		for (Map.Entry<String, Integer> entry : map.entrySet()) {
//			column1.addRow(entry.getKey());
//			column2.addRow(entry.getValue());
//		}
//		DataTable table = new DataTable();
//		table.addDataColumn(column1);
//		table.addDataColumn(column2);
//		return HighChart.pieJson(table);
//	}
//
//}
