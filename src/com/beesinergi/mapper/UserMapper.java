package com.beesinergi.mapper;

import java.util.List;

import com.beesinergi.model.AppUser;

public interface UserMapper extends CommonMapper<AppUser> {
	
	public void deleteByPrimaryKey(List<Integer> pkUserList);
	
	public AppUser selectByUserName(String userName);
	
	public AppUser selectByPrimaryKey(int pkUser);

    
}