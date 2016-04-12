package com.beesinergi.mapper;

import com.beesinergi.model.Po;
import com.beesinergi.model.PoDetail;

public interface PoDetailMapper extends CommonMapper<PoDetail> {
	
	public void deleteByFkPo(int fkPo);
	
}
