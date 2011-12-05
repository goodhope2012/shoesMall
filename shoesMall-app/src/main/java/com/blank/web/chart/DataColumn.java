package com.blank.web.chart;

import java.util.ArrayList;
import java.util.List;

/**
 * User: liuhm
 * Date: 11-9-29
 */
public class DataColumn {
    private String name;
    private String type;
    private List<Object> rows = new ArrayList<Object>();

    public DataColumn(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Object> getRows() {
        return rows;
    }

    public void setRows(List<Object> rows) {
        this.rows = rows;
    }

    public void addRow(Object obj) {
        rows.add(obj);
    }

    public int rows() {
        return rows.size();
    }
}
