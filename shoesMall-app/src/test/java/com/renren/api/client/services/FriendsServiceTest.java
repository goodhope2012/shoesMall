package com.renren.api.client.services;

import java.util.List;

import junit.framework.Assert;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import com.renren.api.client.AbstractServiceTest;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-2-17
 */
public class FriendsServiceTest extends AbstractServiceTest {

    @Test
    public void testAreFriends() {
        String users1 = "226009681,252090984";
        String users2 = "252089313,80178";
        JSONArray rets = this.getRenrenApiClient().getFriendsService().areFriends(users1, users2);
        int i = Integer.parseInt(((JSONObject) rets.get(0)).get("are_friends").toString());
        Assert.assertEquals(1, i);
        i = Integer.parseInt(((JSONObject) rets.get(1)).get("are_friends").toString());
        Assert.assertEquals(0, i);
    }

    @Test
    public void testGetFriendIds() {
        int page = 1;
        int count = 10;
        List<Integer> friendIds = this.getRenrenApiClient().getFriendsService().getFriendIds(page,
                count);
        Assert.assertTrue(friendIds.size() > 0);
    }

    @Test
    public void testGetFriends() {
        int page = 1;
        int count = 10;
        JSONArray friends = this.getRenrenApiClient().getFriendsService().getFriends(page, count);
        Assert.assertTrue(friends.size() > 0);
    }

    @Test
    public void testGetAppFriends() {
        String fields = "name,tinyurl";
        JSONArray friends = this.getRenrenApiClient().getFriendsService().getAppFriends(fields);
        Assert.assertTrue(friends.size() > 0);
    }

}
