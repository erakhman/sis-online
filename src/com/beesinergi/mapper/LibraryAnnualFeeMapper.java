package com.beesinergi.mapper;

import com.beesinergi.model.LibraryAnnualFee;

public interface LibraryAnnualFeeMapper extends CommonMapper<LibraryAnnualFee> {
	
	public LibraryAnnualFee selectByPrimaryKey(int id);
	
}
