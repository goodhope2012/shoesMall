package com.shoesMall.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.shoesMall.utils.GenericReflectionUtils;

@SuppressWarnings("unchecked")
public class GenericDAOImpl<T, PK extends Serializable> implements GenericDAO<T, PK> {
	protected Logger LOGGER = Logger.getLogger(getClass());
	private Class<T> entityClass;
	@Autowired(required = true)
	SessionFactory sessionFactory;

	public GenericDAOImpl() {
		// 自动获取范型类型
		this.entityClass = GenericReflectionUtils.getSuperClassGenricType(getClass());
	}

	public GenericDAOImpl(Class<T> entityClass) {
		// 外部直接注入类型
		this.entityClass = entityClass;
	}

	@Override
	public void saveOrUpdate(final T entity) {
		Assert.notNull(entity, "can't save or update null entity");
		getSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(final T entity) {
		Assert.notNull(entity, "can't delete null entity");
		getSession().delete(entity);
	}

	@Override
	public T get(final PK id) {
		Assert.notNull(id, "Id can't be null");
		return (T) getSession().load(entityClass, id);
	}

	@Override
	public T findByUnique(final String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		Criteria createCriteria = createCriteria(criterion);
		Object uniqueResult = createCriteria.uniqueResult();
		return (T) uniqueResult;
	}

	public Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		LOGGER.info("criteria==" + criteria);
		for (Criterion c : criterions) {
			criteria.add(c);
			LOGGER.info("c==" + c);
		}
		LOGGER.info("criteria==" + criteria);
		return criteria;
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public List<T> listByProperty(final String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		LOGGER.info("criterion==" + criterion);
		return find(criterion);
	}

	public List<T> find(final Criterion... criterions) {
		return createCriteria(criterions).list();
	}

	public List<T> list() {
		return getSession().createCriteria(entityClass).list();
	}

	@Transactional
	@Override
	public void saveOrUpdateAll(List<T> behaviorList) {
		for (T t : behaviorList) {
			getSession().save(t);
		}
	}
}