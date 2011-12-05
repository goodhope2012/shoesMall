package com.shoesMall.dao;

import java.io.Serializable;
import java.util.List;

public abstract interface GenericDAO<T, PK extends Serializable> {
	void saveOrUpdate(T entity);

	void delete(final T entity);

	T get(PK id);

	T findByUnique(final String propertyName, final Object value);

	void saveOrUpdateAll(List<T> behaviorList);
}