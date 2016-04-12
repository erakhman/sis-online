package com.beesinergi.mapper;

import com.beesinergi.model.PoOutDetail;

public interface PoOutDetailMapper extends CommonMapper<PoOutDetail> {
	
	public void deleteByFkPoOut(int fkPoOut);
	
}
