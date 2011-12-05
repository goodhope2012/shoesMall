package com.renren.api.client.services.impl;

import java.util.TreeMap;

import org.json.simple.JSONArray;

import com.renren.api.client.RenrenApiInvoker;
import com.renren.api.client.services.UserService;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-2-17
 */
public class UserServiceImpl extends AbstractService implements UserService {

    public UserServiceImpl(RenrenApiInvoker renrenApiInvoker) {
        super(renrenApiInvoker);
    }

    public JSONArray getInfo(String userIds, String fields) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("method", "users.getInfo");
        params.put("uids", userIds);
        params.put("fields", fields);
        return this.getResultJSONArray(params);
    }

    public boolean hasAppPermission(String extPerm, int userId) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("method", "users.hasAppPermission");
        params.put("ext_perm", extPerm);
        params.put("uid", String.valueOf(userId));
        return this.getResultBoolean(params);
    }

    public int getLoggedInUser() {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("method", "users.getLoggedInUser");
        String uid = this.getResultValue(params, "uid");
        return Integer.parseInt(uid);
    }

    public boolean isAppUser(int userId) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("method", "users.isAppUser");
        params.put("uid", String.valueOf(userId));

        return this.getResultBoolean(params);
    }

}
