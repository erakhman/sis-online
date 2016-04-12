package com.beesinergi.mapper;

import java.util.HashMap;
import java.util.List;

public interface CommonMapper<T> {
	
	public List<T> selectAll(T param);
    
	public int selectCount(T param);
    
	public int insert(T record);
    
	public int updateByPrimaryKey(T record);
    
}