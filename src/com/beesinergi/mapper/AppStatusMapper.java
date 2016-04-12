package com.beesinergi.mapper;

import java.util.List;

import com.beesinergi.model.AppStatus;

public interface AppStatusMapper extends CommonMapper<AppStatus> {
	
	public List<AppStatus> selectByType(String statusType);
	
}
