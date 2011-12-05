package com.blank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cocos.share.dao.GenericDAOImpl;
import com.cocos.share.domain.User;

@Service
public class UserService extends BaseService<User, Long> {
	@Autowired
	GenericDAOImpl userDAO;

	@Override
	protected GenericDAOImpl<User, Long> getDAO() {
		return userDAO;
	}
}
