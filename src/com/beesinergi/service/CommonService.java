package com.beesinergi.service;

import java.util.List;

import com.beesinergi.util.Paging;

public interface CommonService<T> {
	
	public T findById(int id);
	
	public Paging findAllByPaging(Paging paging, T param);
	
	public void save(T object) throws Exception;
	
	public List<T> findAll(T param);
	
	public Integer getCount(T param);

}
