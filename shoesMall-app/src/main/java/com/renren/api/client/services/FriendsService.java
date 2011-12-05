package com.renren.api.client.services;

import java.util.List;

import org.json.simple.JSONArray;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-2-16
 */
public interface FriendsService {

    /**
     * 判断两个List中对应的用户是否是好友。
     * 
     * @param users1 逗号分隔的用户ID
     * @param users2 逗号分隔的用户ID
     * @return
     */
    public JSONArray areFriends(String users1, String users2);

    /**
     * 得到当前登录用户的好友ID列表。
     * 
     * @param page
     * @param count
     * @return
     */
    public List<Integer> getFriendIds(int page, int count);

    /**
     * 得到当前登录用户的好友列表。
     * 
     * @param page
     * @param count
     * @return
     */
    public JSONArray getFriends(int page, int count);

    /**
     * 查询当前用户安装某个应用的好友ID列表。
     * 
     * @param fields 返回的好友包含的属性，用逗号分隔(如：name,tinyurl,headurl)。
     * @return
     */
    public JSONArray getAppFriends(String fields);
}
