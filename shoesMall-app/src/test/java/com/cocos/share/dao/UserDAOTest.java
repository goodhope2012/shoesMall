package com.cocos.share.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.cocos.share.domain.User;

public class UserDAOTest extends BaseDAOTestCase {
	@Autowired(required = true)
	@Resource(name = "UserDAOHibernate")
	private UserDAO userDao;

	@Autowired(required = true)
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	@Test
	@Rollback(false)
	public void testSave() {
		User user = new User();
		userDao.saveOrUpdate(user);
	}
}