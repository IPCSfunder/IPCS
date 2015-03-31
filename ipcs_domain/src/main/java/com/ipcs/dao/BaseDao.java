package com.ipcs.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;

import com.ipcs.model.BasicObject;

/**
 * @author Chen Chao
 *
 */
public interface BaseDao<T extends BasicObject, PK extends Serializable> {
	
	public PK save(Session session, T entity);
	
	public T get(Session session, PK id);
	
	public T load(Session session, PK id);
	
    public void update(Session session, T entity);
    
    public void saveOrUpdate(Session session, T entity);
    
    public void delete(Session session, T entity);
    
    public void deleteAll(Session session, Collection<T> entities);
    
    public List<T> find(Session session, String queryString);

}
