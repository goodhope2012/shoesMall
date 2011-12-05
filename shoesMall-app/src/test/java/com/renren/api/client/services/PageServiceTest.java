package com.renren.api.client.services;

import junit.framework.Assert;

import org.junit.Test;

import com.renren.api.client.AbstractServiceTest;
import com.renren.api.client.RenrenApiClient;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-2-21
 */
public class PageServiceTest extends AbstractServiceTest {

    @Test
    public void testIsFan() {
        RenrenApiClient client = new RenrenApiClient(null);
        int userId = 226009681;
        int pageId = 600006117;
        boolean b = client.getPageService().isFan(userId, pageId);
        Assert.assertTrue(b);
    }

}
