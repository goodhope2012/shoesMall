package com.blank.util;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * User: liuhm
 * Date: 11-9-22
 */
public class JsonUtil {

    public static final ObjectMapper mapper = new ObjectMapper();
    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    public static final String DEFAULT_ERROR = "{\"error\":\"error\"}";
    public static final String DEFAULT_SUCCESS = "{\"success\":\"success\"}";

    public static String requestError(List<ObjectError> errors) throws IOException {
        ArrayNode array = mapper.createArrayNode();
        for (ObjectError error : errors) {
            ObjectNode node = mapper.createObjectNode();
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                node.put(fieldError.getField(), error.getDefaultMessage());
            } else {
                node.put(error.getObjectName(), error.getDefaultMessage());
            }
            array.add(node);
        }
        ObjectNode node = mapper.createObjectNode();
        node.put("error",array);
        return mapper.writeValueAsString(node);
    }

    public static String requestError(String message) {
        if (StringUtils.isBlank(message)) {
            return DEFAULT_ERROR;
        } else {
            return "{\"error\":\""+message+"\"}";
        }
    }

    public static String requestSuccess(String message) {
        if (StringUtils.isBlank(message)) {
            return DEFAULT_SUCCESS;
        } else {
            return "{\"success\":\""+message+"\"}";
        }
    }

    public static String requestSuccess(ObjectNode node) throws IOException {
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("success",node);
        return mapper.writeValueAsString(objectNode);
    }
}
