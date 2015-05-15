package com.ipcs.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

import com.ipcs.model.Base.BasicObject;

/**
 * @author Chen Chao
 *
 */

public abstract class GenericHibernateDao<T extends BasicObject, PK extends Serializable>
	implements BaseDao<T, PK> {

    private Class<T> entityClass;

    @Autowired
    private SessionFactory sessionFactory = null;

    public Session getCurrentSession() {
        Session newsession = getSessionFactory().getCurrentSession();
	return newsession  ==null? getSessionFactory().openSession():newsession;
    }

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }


    @SuppressWarnings("unchecked")
    public GenericHibernateDao() {
	this.entityClass = null;
	Class c = getClass();
	Type t = c.getGenericSuperclass();
	if (t instanceof ParameterizedType) {
	    Type[] p = ((ParameterizedType) t).getActualTypeArguments();
	    this.entityClass = (Class<T>) p[0];
	}
    }

    @SuppressWarnings("unchecked")
    public PK save(T entity) {
	return (PK) getCurrentSession().save(entity);
    }

    @SuppressWarnings("unchecked")
    public void batchSave(List<T> entities) {
	for (int i = 0; i < entities.size(); i++) {
	    getCurrentSession().save(entities.get(i));
	    if (i % 20 == 0) { // 20, same as the JDBC batch size
		// flush a batch of inserts and release memory:
		getCurrentSession().flush();
		getCurrentSession().clear();
	    }
	}
    }

    @SuppressWarnings("unchecked")
    public T get(PK id) {
	return (T) getCurrentSession().get(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    public T load(PK id) {
	return (T) getCurrentSession().load(entityClass, id);
    }

    public void update(T entity) {
	getCurrentSession().update(entity);
    }

    public void saveOrUpdate(T entity) {
	getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(T entity) {
	getCurrentSession().delete(entity);
    }

    public void merge(T entity){getCurrentSession().merge(entity);}

    public void deleteAll(Collection<T> entities) {
	Iterator iterator = entities.iterator();
	int i =0; 
	while (iterator.hasNext()) {
	    getCurrentSession().delete(iterator.next());
	    i++;
	    if (i % 20 == 0) { // 20, same as the JDBC batch size
		// flush a batch of inserts and release memory:
		getCurrentSession().flush();
		getCurrentSession().clear();
	    }
	}

    }

    @SuppressWarnings("unchecked")
    public List<T> find(String queryString) {
	return (List<T>) getCurrentSession().createQuery(queryString).list();
    }

    @SuppressWarnings("unchecked")
    public List<T> createCriteria(Criterion[] Criterions) {
	Criteria cr = getCurrentSession().createCriteria(entityClass);
	for (Criterion criterion : Criterions) {
	    cr.add(criterion);
	}
	return (List<T>) cr.list();
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
	return (List<T>) getCurrentSession().createCriteria(entityClass).list();
    }

}
