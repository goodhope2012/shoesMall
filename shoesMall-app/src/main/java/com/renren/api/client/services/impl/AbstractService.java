package com.renren.api.client.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.renren.api.client.RenrenApiInvoker;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-2-17
 */
public abstract class AbstractService {

    protected RenrenApiInvoker renrenApiInvoker;

    public AbstractService(RenrenApiInvoker renrenApiInvoker) {
        this.renrenApiInvoker = renrenApiInvoker;
    }

    protected String getResultValue(TreeMap<String, String> params, String propertyName) {
        String ret = this.renrenApiInvoker.sendPostRestRequest(params);
        JSONObject obj = (JSONObject) JSONValue.parse(ret);
        Object o = obj.get(propertyName);
        if (o == null) return "";
        return o.toString();
    }

    protected int getResultInt(TreeMap<String, String> params) {
        String str = this.getResultValue(params, "result");
        return Integer.parseInt(str.trim());
    }

    protected boolean getResultBoolean(TreeMap<String, String> params) {
        int r = this.getResultInt(params);
        if (r == 1) {
            return true;
        }
        return false;
    }

    protected JSONObject getResultJSONObject(TreeMap<String, String> params) {
        String ret = this.renrenApiInvoker.sendPostRestRequest(params);
        return (JSONObject) JSONValue.parse(ret);
    }

    protected JSONArray getResultJSONArray(TreeMap<String, String> params) {
        String ret = this.renrenApiInvoker.sendPostRestRequest(params);
        return (JSONArray) JSONValue.parse(ret);
    }

    protected List<Integer> getResultIntList(TreeMap<String, String> params) {
        JSONArray array = this.getResultJSONArray(params);
        List<Integer> ints = new ArrayList<Integer>();
        for (int i = 0; i < array.size(); i++) {
            ints.add(Integer.parseInt(array.get(i).toString()));
        }
        return ints;
    }

    protected List<String> getResultStringList(TreeMap<String, String> params) {
        JSONArray array = this.getResultJSONArray(params);
        List<String> strs = new ArrayList<String>();
        for (int i = 0; i < array.size(); i++) {
            strs.add(array.get(i).toString());
        }
        return strs;
    }
}
