package com.ipcs.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ipcs.model.BasicObject;
import com.ipcs.model.Person;

public class GenericHibernateDao<T extends BasicObject, PK extends Serializable> extends HibernateDaoSupport implements BaseDao<T, PK>{

	  private Class<T> entityClass;

	    public GenericHibernateDao() {
	        this.entityClass = null;
	        Class c = getClass();
	        Type t = c.getGenericSuperclass();
	        if (t instanceof ParameterizedType) {
	            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
	            this.entityClass = (Class<T>) p[0];
	        }
	    }
	
	
	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}

	public T get(PK id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	public T load(PK id) {
		return getHibernateTemplate().load(entityClass, id);
	}

	public List<T> loadAll() {
		return getHibernateTemplate().loadAll(entityClass);
	}

	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	public void saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	public void deleteAll(Collection<T> entities) {
		getHibernateTemplate().delete(entityClass);
	}

	public List<T> find(String queryString) {
		return (List<T>)getHibernateTemplate().find(queryString);
	}

}
