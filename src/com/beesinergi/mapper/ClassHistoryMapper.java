package com.beesinergi.mapper;

import java.util.List;

import com.beesinergi.model.ClassHistory;

public interface ClassHistoryMapper extends CommonMapper<ClassHistory> {

	public void deleteByPrimaryKey(int pkClassHistory);
	
}
