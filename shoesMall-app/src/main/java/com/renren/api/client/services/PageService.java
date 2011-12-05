package com.renren.api.client.services;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-2-21
 */
public interface PageService {

    /**
     * 判断用户是否为Page的粉丝。
     * 
     * @param userId 可选，未登录时用户的ID。
     * @param pageId 可选，缺省当前应用对应的Page的ID。
     * @return
     */
    public boolean isFan(int userId, int pageId);
}
