package com.blank.service;

import java.io.Serializable;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocos.share.dao.GenericDAOImpl;

/**
 * User: liuhm
 * Date: 11-9-20
 */
@Service
public abstract class BaseService<T,PK extends Serializable> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Transactional
    public T get(PK id, boolean initial) {
        T t = getDAO().get(id);
        if (initial) {
            Hibernate.initialize(t);
        }
        return t;
    }

    @Transactional
    public void saveOrUpdate(T t) {
        getDAO().saveOrUpdate(t);
    }

//    @Transactional
//    public void delete(PK id) {
//        getDAO().delete(id);
//    }
//
//    @Transactional
//    public void merge(T t) {
//        getDAO().merge(t);
//    }
//
//    @Transactional
//    public void persist(T t) {
//        getDAO().persist(t);
//    }
//
//    @Transactional
//    public void list(final Page<T> page,List<PropertyFilter> propertyFilters) {
//        getDAO().find(page, propertyFilters);
//    }

//    @Transactional
//    public void listAll(final Page<T> page) {
//        getDAO().getAll(page);
//    }

    protected abstract GenericDAOImpl<T, PK> getDAO();
}
