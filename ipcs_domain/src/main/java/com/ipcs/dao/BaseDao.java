package com.ipcs.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import com.ipcs.model.BasicObject;

/**
 * @author Chen Chao
 *
 */
public interface BaseDao<T extends BasicObject, PK extends Serializable> {

    public PK save(T entity);

    public T get(PK id);

    public T load(PK id);

    public void update(T entity);

    public void saveOrUpdate(T entity);

    public void merge(T entity);

    public void delete(T entity);

    public void deleteAll(Collection<T> entities);

    public List<T> find(String queryString);
    
    public List<T> findAll();

    public List<T> createCriteria(Criterion[] Criterions);

}
