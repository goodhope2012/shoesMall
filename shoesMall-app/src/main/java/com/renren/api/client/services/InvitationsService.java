package com.renren.api.client.services;

import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-2-21
 */
public interface InvitationsService {

    public final static int DOMAIN_RENREN = 0;

    public final static int DOMAIN_KAIXIN = 1;

    /**
     * 生成用户站外邀请用户注册的链接地址,应用可以引导用户通过QQ或者msn等渠道邀请好友加入应用。
     * 
     * @param domain
     * @return
     */
    public String createLink(int domain);

    /**
     * 获得某个用户被谁邀请进来的。
     * 
     * @param inviteeId
     * @return
     */
    public JSONObject getInfo(int inviteeId);

    /**
     * 获得一段时间内所有用户的邀请信息。
     * 
     * @param begin
     * @param end
     * @return
     */
    public JSONArray getInfos(Date begin, Date end);

}
