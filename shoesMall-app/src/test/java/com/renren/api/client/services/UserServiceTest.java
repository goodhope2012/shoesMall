package com.renren.api.client.services;

import junit.framework.Assert;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import com.renren.api.client.AbstractServiceTest;
import com.renren.api.client.RenrenApiClient;
import com.renren.api.client.utils.JsonUtils;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-2-18
 */
public class UserServiceTest extends AbstractServiceTest {

    @Test
    public void testGetInfo() {
        String userIds = "226009681,252090984,252089313";
        String fields = "name,email_hash, sex,star,birthday,tinyurl,headurl,mainurl,hometown_location,hs_history,university_history,work_history,contact_info";
        JSONArray users = this.getRenrenApiClient().getUserService().getInfo(userIds, fields);
        JSONObject u = JsonUtils.getIndexJSONObject(users, 0);
        String name = JsonUtils.getValue(u, "name", String.class);
        Assert.assertNotNull(name);
        Assert.assertEquals(3, users.size());
    }

    @Test
    public void testHasAppPermission() {
        String extPerm = "emial";
        boolean b = this.getRenrenApiClient().getUserService().hasAppPermission(extPerm, 0);
        Assert.assertFalse(b);
    }

    @Test
    public void testGetLoggedInUser() {
        int userId = this.getRenrenApiClient().getUserService().getLoggedInUser();
        Assert.assertTrue(userId > 0);
    }

    @Test
    public void testIsAppUser() {
        boolean b = this.getRenrenApiClient().getUserService().isAppUser(700034618);
        Assert.assertTrue(b);

        RenrenApiClient client = new RenrenApiClient(null);
        int userId = 252090980;
        b = client.getUserService().isAppUser(userId);
        Assert.assertFalse(b);
    }

}
