package com.renren.api.client.services;

import junit.framework.Assert;

import org.json.simple.JSONObject;
import org.junit.Test;

import com.renren.api.client.AbstractServiceTest;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-2-18
 */
public class AdminServiceTest extends AbstractServiceTest {

    @Test
    public void getAllocation() {
        JSONObject obj = this.getRenrenApiClient().getAdminService().getAllocation();
        int i = Integer.parseInt(obj.get("notifications_per_day").toString());
        Assert.assertTrue(i > -1);
        i = Integer.parseInt(obj.get("requests_per_day").toString());
        Assert.assertTrue(i > -1);
    }
}
