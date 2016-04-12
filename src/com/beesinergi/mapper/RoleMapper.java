package com.beesinergi.mapper;

import com.beesinergi.model.AppRole;

public interface RoleMapper extends CommonMapper<AppRole> {
	
	public AppRole selectByPrimaryKey(int pkRole);
	
}
