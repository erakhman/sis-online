package com.beesinergi.mapper;

import com.beesinergi.model.Po;

public interface PoMapper extends CommonMapper<Po> {
	
	public Po selectByPrimaryKey(int pkPo);
	
}
