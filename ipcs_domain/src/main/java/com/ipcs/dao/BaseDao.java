package com.ipcs.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.ipcs.model.BasicObject;

public interface BaseDao<T extends BasicObject, PK extends Serializable> {
	
	public void save(T entity);
	
	public T get(PK id);
	
	public T load(PK id);
	
	public List<T> loadAll();
	
    public void update(T entity);
    
    public void saveOrUpdate(T entity);
    
    public void delete(T entity);
    
    public void deleteAll(Collection<T> entities);
    
    public List<T> find(String queryString);

}
