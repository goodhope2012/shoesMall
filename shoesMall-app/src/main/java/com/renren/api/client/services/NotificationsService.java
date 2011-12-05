package com.renren.api.client.services;

import java.util.List;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-2-21
 */
public interface NotificationsService {

    /**
     * 给当前登录者的好友或也安装了同样应用的用户发通知。
     * 
     * @param toIds 这些用户必须是当前登录者的好友或也安装了此应用；多个可以是逗号分隔，最多20个。
     * @param notification 通知的内容，支持XNML<xn:name/>和<a/>。
     * @return
     */
    public int send(String toIds, String notification);
    
    /**
     * 给当前登录者的好友或也安装了同样应用的用户发通知。
     * 
     * @param toIds 这些用户必须是当前登录者的好友或也安装了此应用；多个可以是逗号分隔，最多20个。
     * @param notification 通知的内容，支持XNML<xn:name/>和<a/>。
     * @param 
     * @return
     */
    public int send(String toIds, String notification, String type);

    /**
     * 给你的用户发送Email；此接口需要扩展权限---Email。
     * 
     * @param templateId APP邮件模板的ID。
     * @param recipients 收件人的UID，多个ID用逗号隔开。
     * @param templateData
     *        JSON对象，渲染邮件模板所需要的数据。例如：{"who":"234234","static_uid":"23423423"
     *        }。
     * @return
     */
    public List<Integer> sendEmail(int templateId, String recipients, String templateData);
}
