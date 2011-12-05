package com.renren.api.client.services;

import java.util.List;

import org.json.simple.JSONArray;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-2-21
 */
public interface ConnectService {

    /**
     * 此方法返回当前用户在此站点上，但还没有建立connect关系的好友数量.需要注意的是这个返回值依赖于通过connect.
     * regsiterUser接口的用户数量。如果之前没有调用过这个接口，则返回值将是0
     * 你可以根据这个接口的返回值来决定是否给用户显示一个列表来邀请他的朋友通过注册。
     * 
     * @return
     */
    public int getUnconnectedFriendsCount();

    /**
     * 用来建立站点用户和校内用户之间的映射关系。
     * 
     * @param accounts
     *        数组中每个元素是一个JSONObject，每个JSONObject有一个"email_hash"属性和两个可选的属性
     *        "account_id","account_url"。
     * @return
     */
    public List<String> registerUsers(JSONArray accounts);

    /**
     * 删除站点用户和校内用户之间的映射关系。
     * 
     * @param emailHashes email_hash值的数组。
     * @return
     */
    public List<String> unregisterUsers(JSONArray emailHashes);
}
