package com.renren.api.client.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.renren.api.client.RenrenApiInvoker;
import com.renren.api.client.services.NotificationsService;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-2-21
 */
public class NotificationsServiceImpl extends AbstractService implements NotificationsService {

    public NotificationsServiceImpl(RenrenApiInvoker renrenApiInvoker) {
        super(renrenApiInvoker);
    }

    public int send(String toIds, String notification) {
        String type = "user_to_user";// app_to_user , user_to_user(default) ,app_to_wap(mobile client)
        return this.send(toIds, notification, type);
    }

    public int send(String toIds, String notification, String type) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("method", "notifications.send");
        params.put("to_ids", toIds);
        params.put("type", type);
        params.put("notification", notification);
        return this.getResultInt(params);
    }

    public List<Integer> sendEmail(int templateId, String recipients, String templateData) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("method", "notifications.sendEmail");
        params.put("template_id", String.valueOf(templateId));
        params.put("recipients", recipients);
        params.put("template_data", templateData);
        String str = this.getResultValue(params, "uids");
        List<Integer> ids = new ArrayList<Integer>();
        if (str != null) {
            String[] strs = str.split(",");
            for (int i = 0; i < strs.length; i++) {
                if (strs[i].trim().length() < 1) continue;
                ids.add(Integer.parseInt(strs[i].trim()));
            }
        }
        return ids;
    }

}
