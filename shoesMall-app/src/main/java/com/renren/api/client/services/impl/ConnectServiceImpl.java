package com.renren.api.client.services.impl;

import java.util.List;
import java.util.TreeMap;

import org.json.simple.JSONArray;

import com.renren.api.client.RenrenApiInvoker;
import com.renren.api.client.services.ConnectService;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-2-16
 */
public class ConnectServiceImpl extends AbstractService implements ConnectService {

    public ConnectServiceImpl(RenrenApiInvoker renrenApiInvoker) {
        super(renrenApiInvoker);
    }

    public int getUnconnectedFriendsCount() {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("method", "connect.getUnconnectedFriendsCount");
        List<Integer> ints = this.getResultIntList(params);
        return ints.get(0);
    }

    public List<String> registerUsers(JSONArray accounts) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("method", "connect.registerUsers");
        params.put("accounts", accounts.toJSONString());

        return this.getResultStringList(params);
    }

    public List<String> unregisterUsers(JSONArray emailHashes) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("method", "connect.unregisterUsers");
        params.put("email_hashes", emailHashes.toJSONString());

        return this.getResultStringList(params);
    }
}
