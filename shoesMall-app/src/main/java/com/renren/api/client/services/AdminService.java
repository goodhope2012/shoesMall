package com.renren.api.client.services;

import org.json.simple.JSONObject;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-2-21
 */
public interface AdminService {

    /**
     * 获取一个应用当天可以发送的通知和应用邀请的配额。
     * 
     * @return
     */
    public JSONObject getAllocation();
}
