package com.beesinergi.mapper;

import com.beesinergi.model.PoOut;

public interface PoOutMapper extends CommonMapper<PoOut> {
	
	public PoOut selectByPrimaryKey(int pkPoOut);
	
}
