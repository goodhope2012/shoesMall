package com.renren.api.client.services;

import java.util.Date;

import junit.framework.Assert;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import com.renren.api.client.AbstractServiceTest;
import com.renren.api.client.RenrenApiClient;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-2-21
 */
public class InvitationsServiceTest extends AbstractServiceTest {

    private RenrenApiClient client = new RenrenApiClient(null);

    @Test
    public void testCreateLink() {
        String link = this.getRenrenApiClient().getInvitationsService().createLink(
                InvitationsService.DOMAIN_RENREN);
        Assert.assertTrue(link.indexOf("renren") > -1);
    }

    @Test
    public void testGetInfo() {
        int inviteeId = 252090984;
        JSONObject obj = this.client.getInvitationsService().getInfo(inviteeId);
        if (obj == null) {
            System.out.println("Warning not found invitee!");
        }
    }

    @Test
    public void testGetInfos() {
        Date end = new Date();
        Date begin = new Date(end.getTime() - (1000 * 60 * 60 * 24 * 12));
        JSONArray rets = this.client.getInvitationsService().getInfos(begin, end);
        if (rets.size() < 1) {
            System.out.println("Warning not found invitees!");
        }
    }

}
