package com.chrisom.sisinv.dao;

public interface DAOInterface <T> {
	public void insert(T element);
	
	public Long countIds(String id); 
}