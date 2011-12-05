package com.renren.api.client.services;

import java.util.List;

import junit.framework.Assert;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import com.renren.api.client.AbstractServiceTest;
import com.renren.api.client.utils.EmailHashUtil;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-2-21
 */
public class ConnectServiceTest extends AbstractServiceTest {

    @Test
    public void testGetUnconnectedFriendsCount() {
        int i = this.getRenrenApiClient().getConnectService().getUnconnectedFriendsCount();
        Assert.assertTrue(i > -1);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testRegisterUsers() {
        JSONArray accounts = new JSONArray();
        String eh = EmailHashUtil.getEmailHash("liyongibm@tom.com");
        JSONObject acc = new JSONObject();
        acc.put("email_hash", eh);
        //acc.put("account_id", 252090984);//可选
        //acc.put("account_url", "http://www.renren.com/profile.do?id=252090984");//可选
        accounts.add(acc);
        List<String> rs = this.getRenrenApiClient().getConnectService().registerUsers(accounts);
        Assert.assertEquals(eh, rs.get(0));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testUnregisterUsers() {
        JSONArray emailHashes = new JSONArray();
        String eh = EmailHashUtil.getEmailHash("liyongibm@tom.com");
        emailHashes.add(eh);
        List<String> rs = this.getRenrenApiClient().getConnectService()
                .unregisterUsers(emailHashes);
        Assert.assertEquals(eh, rs.get(0));
    }

}
