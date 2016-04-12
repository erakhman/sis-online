package com.beesinergi.mapper;

import com.beesinergi.model.AppRoleMenu;

public interface RoleMenuMapper extends CommonMapper<AppRoleMenu> {
	
	public void deleteByFkRole(int fkRole);
	
}
