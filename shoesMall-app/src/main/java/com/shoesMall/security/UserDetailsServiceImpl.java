package com.shoesMall.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.shoesMall.dao.UserDAO;
import com.shoesMall.domain.Role;
import com.shoesMall.domain.User;

@Component("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
		UserDetails userDetails = null;
		User user = userDAO.findByUnique("name", userName);
		if (user != null) {
			List<Role> roles = user.getRoles();
			String[] grantedAuthorities = new String[roles.size()];
			for (int i = 0; i < roles.size(); i++) {
				grantedAuthorities[i] = roles.get(i).getRole().toUpperCase();
			}
			userDetails = new org.springframework.security.core.userdetails.User(userName, user.getPassword(), user.getStatus() == 1, true, true, true, AuthorityUtils.createAuthorityList(grantedAuthorities));
		} else {
			throw new UsernameNotFoundException("User: " + userName + " not found.");
		}
		return userDetails;
	}
}
