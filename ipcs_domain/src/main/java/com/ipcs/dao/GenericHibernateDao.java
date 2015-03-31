package com.ipcs.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ipcs.model.BasicObject;

/**
 * @author Chen Chao
 *
 */
public abstract class GenericHibernateDao<T extends BasicObject, PK extends Serializable>
		implements BaseDao<T, PK> {

	private Class<T> entityClass;
	
	private SessionFactory sessionFactory = null;
	
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public GenericHibernateDao() {
		this.entityClass = null;
		Class c = getClass();
		Type t = c.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			this.entityClass = (Class<T>) p[0];
		}
	}

	public PK save(Session session, T entity) {
		return (PK) session.save(entity);
	}

	public T get(Session session, PK id) {
		return (T) session.get(entityClass, id);
	}

	public T load(Session session, PK id) {
		System.out.println("Class name"+entityClass.getName());
		return (T)session.load(entityClass, id);
	}

	public void update(Session session, T entity) {
		session.update(entity);
	}

	public void saveOrUpdate(Session session, T entity) {
		session.saveOrUpdate(entity);
	}

	public void delete(Session session, T entity) {
		session.delete(entity);
	}
	

	public void deleteAll(Session session, Collection<T> entities) {
		session.delete(entityClass);
	}

	public List<T> find(Session session, String queryString) {
		return (List<T>) session.createQuery(queryString);
	}

}
