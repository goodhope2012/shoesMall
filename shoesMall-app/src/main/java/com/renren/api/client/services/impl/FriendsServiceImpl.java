package com.renren.api.client.services.impl;

import java.util.List;
import java.util.TreeMap;

import org.json.simple.JSONArray;

import com.renren.api.client.RenrenApiInvoker;
import com.renren.api.client.services.FriendsService;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-2-16
 */
public class FriendsServiceImpl extends AbstractService implements FriendsService {

    public FriendsServiceImpl(RenrenApiInvoker renrenApiInvoker) {
        super(renrenApiInvoker);
    }

    public JSONArray areFriends(String users1, String users2) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("method", "friends.areFriends");
        params.put("uids1", users1);
        params.put("uids2", users2);
        JSONArray friendsInfos = this.getResultJSONArray(params);
        return friendsInfos;
    }

    public JSONArray getAppFriends(String fields) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("method", "friends.getAppFriends");
        params.put("fields", fields);
        JSONArray friends = this.getResultJSONArray(params);
        return friends;
    }

    public List<Integer> getFriendIds(int page, int count) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("method", "friends.get");
        params.put("page", String.valueOf(page));
        params.put("count", String.valueOf(count));
        return this.getResultIntList(params);
    }

    public JSONArray getFriends(int page, int count) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("method", "friends.getFriends");
        params.put("page", String.valueOf(page));
        params.put("count", String.valueOf(count));
        return this.getResultJSONArray(params);
    }
}
