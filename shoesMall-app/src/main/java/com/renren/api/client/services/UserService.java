package com.renren.api.client.services;

import org.json.simple.JSONArray;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-2-17
 */
public interface UserService {

    /**
     * 获取一批用户的信息
     * 
     * @param userIds 逗号分隔的用户ID
     * @param fields 逗号分隔的用户属性，如：name,email_hash,
     *        sex,star,birthday,tinyurl,
     *        headurl,mainurl,hometown_location,hs_history
     *        ,university_history,work_history,contact_info
     * @return
     */
    public JSONArray getInfo(String userIds, String fields);

    /**
     * 检查用户是否授予应用扩展权限。
     * 
     * @param extPerm 用户可操作的扩展授权，例如email。
     * @param userId 未登录时检测该值。
     * @return
     */
    public boolean hasAppPermission(String extPerm, int userId);

    /**
     * 获取当前登录用户的ID
     * 
     * @return
     */
    public int getLoggedInUser();

    /**
     * 判断用户是否是app的用户。
     * 
     * @param userId 未登录时判断该用户。
     * @return
     */
    public boolean isAppUser(int userId);
}
