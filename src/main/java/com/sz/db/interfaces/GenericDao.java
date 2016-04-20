package com.sz.db.interfaces;

import java.io.Serializable;
import java.util.List;

import com.sz.db.services.PersistException;

public interface GenericDao <T extends Serializable>{
	public T get(int id) throws PersistException;	
	
	public T save(T entity) throws Exception;
	
	public void delete(T entity);
	
	public T update(T entity);
	
	public List<T> getAll() throws PersistException;

}
