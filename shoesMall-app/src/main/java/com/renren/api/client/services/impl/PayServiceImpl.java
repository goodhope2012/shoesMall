package com.renren.api.client.services.impl;

import java.util.TreeMap;

import com.renren.api.client.RenrenApiInvoker;
import com.renren.api.client.services.PayService;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-2-17
 */
public class PayServiceImpl extends AbstractService implements PayService {

    public PayServiceImpl(RenrenApiInvoker renrenApiInvoker) {
        super(renrenApiInvoker);
    }

    public boolean isCompleted(long orderId) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("method", "pay.isCompleted");
        params.put("order_id", String.valueOf(orderId));
        return this.getResultBoolean(params);
    }

    public boolean isCompletedTest(long orderId) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("method", "pay4Test.isCompleted");
        params.put("order_id", String.valueOf(orderId));
        return this.getResultBoolean(params);
    }

    public String regOrder(long orderId, int amount, String desc, int type) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("method", "pay.regOrder");
        params.put("order_id", String.valueOf(orderId));
        params.put("amount", String.valueOf(amount));
        params.put("type", String.valueOf(type));
        params.put("desc", desc);
        return this.getResultValue(params, "token");
    }

    public String regOrderTest(long orderId, int amount, String desc, int type) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("method", "pay4Test.regOrder");
        params.put("order_id", String.valueOf(orderId));
        params.put("amount", String.valueOf(amount));
        params.put("type", String.valueOf(type));
        params.put("desc", desc);
        return this.getResultValue(params, "token");
    }
}
