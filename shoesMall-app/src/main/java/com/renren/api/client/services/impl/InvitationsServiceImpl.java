package com.renren.api.client.services.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.renren.api.client.RenrenApiInvoker;
import com.renren.api.client.services.InvitationsService;

public class InvitationsServiceImpl extends AbstractService implements InvitationsService {

    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public InvitationsServiceImpl(RenrenApiInvoker renrenApiInvoker) {
        super(renrenApiInvoker);
    }

    public String createLink(int domain) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("method", "invitations.createLink");
        params.put("domain", String.valueOf(domain));
        return this.getResultStringList(params).get(0);
    }

    public JSONObject getInfo(int inviteeId) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("method", "invitations.getInfo");
        params.put("invitee_uid", String.valueOf(inviteeId));
        JSONArray rets = this.getResultJSONArray(params);
        if (rets.size() < 1) return null;
        return (JSONObject) this.getResultJSONArray(params).get(0);
    }

    public JSONArray getInfos(Date begin, Date end) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("method", "invitations.getInfo");
        params.put("begin_time", dateFormat.format(begin));
        params.put("end_time", dateFormat.format(end));
        return this.getResultJSONArray(params);
    }
}
