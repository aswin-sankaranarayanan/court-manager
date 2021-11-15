package com.court.manager.core.services;

public interface DBAdapter<E,D,R> {

	public void init(Class<E> entityClass,Class<D> dtoClass, R repository);
}
