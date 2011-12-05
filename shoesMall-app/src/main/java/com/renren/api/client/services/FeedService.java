package com.renren.api.client.services;

import org.json.simple.JSONObject;

/**
 * @author 李勇(yong.li@renren-inc.com) 2011-6-8
 */
public interface FeedService {

    /**
     * 上传照片
     * 
     * @param albumId
     * @param photo
     * @param fileName
     * @param description
     * @return
     */
    public JSONObject upload(long albumId, byte[] photo, String fileName, String description);

}
