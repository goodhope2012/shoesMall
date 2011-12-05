//package com.blank.util;
//
//import java.io.StringWriter;
//import java.util.Map;
//
//import org.apache.commons.lang.StringUtils;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jackson.node.ObjectNode;
//import org.springframework.ui.Model;
//import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
//
//import com.blank.web.context.WebContext;
//
//import freemarker.template.Template;
//
///**
// * User: liuhm
// * Date: 11-9-27
// */
//public class WebUtil {
//
//    public static ObjectNode renderTable(String name, Model model) throws Exception {
//        String lightTableId = WebContext.getRequest().getParameter("lightTableId");
//        if (StringUtils.isBlank("lightTableId")) {
//            return null;
//        }
//        //render html
//        ObjectMapper mapper = JsonUtil.mapper;
//        FreeMarkerConfigurer configurer =  WebContext.getBean(FreeMarkerConfigurer.class);
//        Template template =  configurer.getConfiguration().getTemplate( name + ".ftl");
//        StringWriter writer =new StringWriter();
//        template.process(model,writer);
//        String result = writer.toString();
//        String begin = "<light:table id=\"" + lightTableId + "\">";
//        String end = "</light:table>";
//        int beginIndex = result.indexOf(begin) + begin.length();
//        int lastIndex = result.indexOf(end, beginIndex);
//        String data = result.substring(beginIndex, lastIndex);
//
//        ObjectNode node = mapper.createObjectNode();
//        Map<String,Object> map = model.asMap();
//        node.put("search", (String) map.get("search"));
//        node.put("order",(String) map.get("order"));
//        node.put("orderBy",(String) map.get("orderBy"));
//        node.put("data",data);
//        node.put("lightTableId",lightTableId);
//        return node;
//    }
//}
