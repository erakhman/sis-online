package com.beesinergi.mapper;

import com.beesinergi.model.AppUserRole;

public interface UserRoleMapper extends CommonMapper<AppUserRole> {
	
	public void deleteByFkUser(int fkUser);
	
}
