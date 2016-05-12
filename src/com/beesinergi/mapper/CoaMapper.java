package com.beesinergi.mapper;

import java.util.List;

import com.beesinergi.model.Coa;

public interface CoaMapper extends CommonMapper<Coa> {

	public List<Coa> selectAllParent();
}
