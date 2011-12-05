package com.renren.api.client;

import com.renren.api.client.services.AdminService;
import com.renren.api.client.services.ConnectService;
import com.renren.api.client.services.FriendsService;
import com.renren.api.client.services.InvitationsService;
import com.renren.api.client.services.NotificationsService;
import com.renren.api.client.services.PageService;
import com.renren.api.client.services.PayService;
import com.renren.api.client.services.PhotoService;
import com.renren.api.client.services.UserService;
import com.renren.api.client.services.impl.AdminServiceImpl;
import com.renren.api.client.services.impl.ConnectServiceImpl;
import com.renren.api.client.services.impl.FriendsServiceImpl;
import com.renren.api.client.services.impl.InvitationsServiceImpl;
import com.renren.api.client.services.impl.NotificationsServiceImpl;
import com.renren.api.client.services.impl.PageServiceImpl;
import com.renren.api.client.services.impl.PayServiceImpl;
import com.renren.api.client.services.impl.PhotoServiceImpl;
import com.renren.api.client.services.impl.UserServiceImpl;

/**
 * 注意：在构造第一个RenrenApiClient实例之前，
 * 必须先初始化RenrenApiConfig的renrenApiKey和renrenApiSecret静态属性。
 * 
 * @author 李勇(yong.li@opi-corp.com) 2011-2-16
 */
public class RenrenApiClient {

    private RenrenApiInvoker renrenApiInvoker;

    private FriendsService friendsService;

    private UserService userService;

    private AdminService adminService;

    private ConnectService connectService;

    private InvitationsService invitationsService;

    private NotificationsService notificationsService;

    private PageService pageService;

    private PayService payService;

    private PhotoService photoService;

    /**
     * 如果sessionKey为空，那么只能调用不需要sessionKey的接口。
     * 
     * @param sessionKey
     */
    public RenrenApiClient(String sessionKey) {
        this(sessionKey, false);
    }

    /**
     * 
     * @param token 访问标识
     * @param isAccessToken ture:token为accessToken, false:sessionKey
     */
    public RenrenApiClient(String token, boolean isAccessToken) {
        this.renrenApiInvoker = new RenrenApiInvoker(token, isAccessToken);
        this.checkConfig();
        this.initService();
    }

    private void checkConfig() {
        if (RenrenApiConfig.renrenApiKey == null || RenrenApiConfig.renrenApiKey.length() < 1
                || RenrenApiConfig.renrenApiSecret == null
                || RenrenApiConfig.renrenApiSecret.length() < 1) {
            throw new RuntimeException(
                    "Please init com.renren.api.client.RenrenApiConfig.renrenApiKey and com.renren.api.client.RenrenApiConfig.renrenApiSecret!");
        }
    }

    private void initService() {
        this.friendsService = new FriendsServiceImpl(this.renrenApiInvoker);
        this.userService = new UserServiceImpl(this.renrenApiInvoker);
        this.adminService = new AdminServiceImpl(this.renrenApiInvoker);
        this.connectService = new ConnectServiceImpl(this.renrenApiInvoker);
        this.invitationsService = new InvitationsServiceImpl(this.renrenApiInvoker);
        this.notificationsService = new NotificationsServiceImpl(this.renrenApiInvoker);
        this.pageService = new PageServiceImpl(this.renrenApiInvoker);
        this.payService = new PayServiceImpl(this.renrenApiInvoker);
        this.photoService = new PhotoServiceImpl(this.renrenApiInvoker);
    }

    public RenrenApiInvoker getRenrenApiInvoker() {
        return renrenApiInvoker;
    }

    public AdminService getAdminService() {
        return adminService;
    }

    public FriendsService getFriendsService() {
        return friendsService;
    }

    public UserService getUserService() {
        return userService;
    }

    public ConnectService getConnectService() {
        return connectService;
    }

    public InvitationsService getInvitationsService() {
        return invitationsService;
    }

    public NotificationsService getNotificationsService() {
        return notificationsService;
    }

    public PageService getPageService() {
        return pageService;
    }

    public PayService getPayService() {
        return payService;
    }

    public PhotoService getPhotoService() {
        return photoService;
    }
}
