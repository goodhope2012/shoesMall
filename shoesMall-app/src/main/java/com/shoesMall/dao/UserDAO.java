package com.shoesMall.dao;

import org.springframework.stereotype.Repository;

import com.shoesMall.domain.User;

@Repository
public class UserDAO extends GenericDAOImpl<User, Long> {
}