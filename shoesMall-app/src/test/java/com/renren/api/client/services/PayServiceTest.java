package com.renren.api.client.services;

import java.util.Random;

import junit.framework.Assert;

import org.junit.Test;

import com.renren.api.client.AbstractServiceTest;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-2-21
 */
public class PayServiceTest extends AbstractServiceTest {

    private Random random = new Random();

    @Test
    public void testIsCompleted() {
        long orderId = 1;
        boolean b = this.getRenrenApiClient().getPayService().isCompleted(orderId);
        Assert.assertEquals(false, b);
    }

    @Test
    public void testRegOrder() {
        long orderId = 1;
        int amount = 10;
        String desc = "Java client sdk test!";
        int type = 0;
        try {
            this.getRenrenApiClient().getPayService().regOrder(orderId, amount, desc, type);
            Assert.fail("error!");
        } catch (RenrenApiException e) {
            //right
        }
    }

    @Test
    public void testIsCompletedTest() {
        long orderId = 1;
        boolean b = this.getRenrenApiClient().getPayService().isCompletedTest(orderId);
        Assert.assertEquals(false, b);
    }

    @Test
    public void testRegOrderTest() {
        long orderId = Math.abs(this.random.nextLong());
        int amount = 10;
        String desc = "Java client sdk test!";
        int type = 0;
        String str = this.getRenrenApiClient().getPayService().regOrderTest(orderId, amount, desc,
                type);
        Assert.assertNotNull(str);
    }

}
