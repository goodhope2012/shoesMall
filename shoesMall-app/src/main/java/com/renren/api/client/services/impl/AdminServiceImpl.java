package com.renren.api.client.services.impl;

import java.util.TreeMap;

import org.json.simple.JSONObject;

import com.renren.api.client.RenrenApiInvoker;
import com.renren.api.client.services.AdminService;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-2-17
 */
public class AdminServiceImpl extends AbstractService implements AdminService {

    public AdminServiceImpl(RenrenApiInvoker renrenApiInvoker) {
        super(renrenApiInvoker);
    }

    public JSONObject getAllocation() {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("method", "admin.getAllocation");
        return this.getResultJSONObject(params);
    }
}
