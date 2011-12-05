package com.renren.api.client.services;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.renren.api.client.AbstractServiceTest;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-2-21
 */
public class NotificationsServiceTest extends AbstractServiceTest {

    @Test
    public void testSend() {
        String toIds = "226009681,252090984";
        String notification = "hello,<xn:name uid=\"252089313\" linked=\"true\"/>";
        int i = this.getRenrenApiClient().getNotificationsService().send(toIds, notification);
        Assert.assertEquals(1, i);
    }

    @Test
    public void testSendEmail() {
        int templateId = 1;
        String recipients = "226009681,252090984";
        String templateData = "{\"who\":252089313}";
        List<Integer> ids = this.getRenrenApiClient().getNotificationsService().sendEmail(
                templateId, recipients, templateData);
        if (ids.size() < 1) {
            System.out.println("Warning send email error!");
        } else {
            Assert.assertEquals(2, ids.size());
        }
    }

}
