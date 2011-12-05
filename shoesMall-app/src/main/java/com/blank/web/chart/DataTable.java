package com.blank.web.chart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

/**
 * User: liuhm
 * Date: 11-9-29
 */
public class DataTable {
    private int cols;
    private int rows;
    private List<DataColumn> columns = new ArrayList<DataColumn>();

    public int getCols() {
        return columns.size();
    }

    public int getRows() {
        return rows;
    }

    public List<DataColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<DataColumn> columns) {
        this.columns = columns;
    }

    public void addDataColumn(DataColumn dataColumn) {
        if (columns.size() < 1) {
            rows = dataColumn.rows();
            columns.add(dataColumn);
        } else {
            Assert.state(dataColumn.rows() == rows, "google column rows invalid");
            columns.add(dataColumn);
        }
    }

}
