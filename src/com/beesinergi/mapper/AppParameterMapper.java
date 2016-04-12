package com.beesinergi.mapper;

import com.beesinergi.model.AppParameter;

public interface AppParameterMapper extends CommonMapper<AppParameter> {
	
	void updateByName(AppParameter appParameter);

}