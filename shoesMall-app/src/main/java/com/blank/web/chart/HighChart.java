package com.blank.web.chart;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.util.Assert;

import com.blank.util.JsonUtil;

/**
 * User: liuhm
 * Date: 11-9-28
 */
public class HighChart {

    public static String pieJson(DataTable table) throws IOException {
        Assert.state(table.getCols() == 2 && table.getRows() >= 1,"highChart pie must be two column and at least one row");
        List<DataColumn> columns = table.getColumns();
        ObjectNode objectNode = JsonUtil.mapper.createObjectNode();
        objectNode.put("name",columns.get(0).getName());
        ArrayNode node = JsonUtil.mapper.createArrayNode();
        for (int i = 0; i < table.getRows(); i++) {
            ArrayNode arrayNode = JsonUtil.mapper.createArrayNode();
            arrayNode.add((String) columns.get(0).getRows().get(i));
            arrayNode.add(((Number)columns.get(1).getRows().get(i)).doubleValue());
            node.add(arrayNode);
        }
        objectNode.put("data",node);
        return JsonUtil.mapper.writeValueAsString(objectNode);
    }

    public static String lineJson(DataTable table) throws IOException {
        Assert.state(table.getCols() >= 2 ,"highChart line must more than two cols");
        ObjectNode node = JsonUtil.mapper.createObjectNode();
        node.putPOJO("categories", table.getColumns().get(0).getRows());
        ArrayNode arrayNode = JsonUtil.mapper.createArrayNode();
        for (int i = 1; i < table.getCols(); i++) {
            ObjectNode objectNode = JsonUtil.mapper.createObjectNode();
            objectNode.put("name",table.getColumns().get(i).getName());
            objectNode.putPOJO("data", table.getColumns().get(i).getRows());
            arrayNode.add(objectNode);
        }
        node.put("data",arrayNode);
        return JsonUtil.mapper.writeValueAsString(node);
    }

    public static String barJson(DataTable table) throws IOException {
        return lineJson(table);
    }

    public static String columnJson(DataTable table) throws IOException {
        return lineJson(table);
    }

    /*public static HighChart create(List<Object[]> list,Converter... converters) {
        HighChart chart = new HighChart();
        String[] label = new String[list.size()];
        List<Number[]> data = new ArrayList<Number[]>(list.size());
        int index = 0;
        for (Object[] objects : list) {
            if (objects.length >= 2) {
                if (converters != null && converters[0] != null) {
                    label[index] = (String) converters[0].convert(objects[0]);
                }
            }
        }
    }*/
}
