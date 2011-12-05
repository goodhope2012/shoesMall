package com.renren.api.client;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.renren.api.client.services.AdminServiceTest;
import com.renren.api.client.services.ConnectServiceTest;
import com.renren.api.client.services.FriendsServiceTest;
import com.renren.api.client.services.InvitationsServiceTest;
import com.renren.api.client.services.PageServiceTest;
import com.renren.api.client.services.PayServiceTest;
import com.renren.api.client.services.RenrenPhotoServiceTest;
import com.renren.api.client.services.UserServiceTest;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-2-18
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { AdminServiceTest.class, ConnectServiceTest.class, FriendsServiceTest.class,
        InvitationsServiceTest.class, PageServiceTest.class, PayServiceTest.class,
        UserServiceTest.class, RenrenPhotoServiceTest.class })
public class TestAll {

}
