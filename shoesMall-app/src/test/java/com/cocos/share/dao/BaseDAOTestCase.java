package com.cocos.share.dao;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:*.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class BaseDAOTestCase {
}