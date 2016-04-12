package com.beesinergi.util;

import java.util.List;

import com.beesinergi.model.AppParameter;
import com.beesinergi.service.AppParameterService;

public class LoaderFactory {

	public static void initializeAppParameter(AppParameterService appParameterService) {
		List<AppParameter> appParams = appParameterService.findAll(null);
		for (AppParameter appParam : appParams) {
			if (appParam.getDescription() != null && appParam.getValue() != null) {
				SystemParameter.updateSystemEnvironment(appParam.getName(),appParam.getValue().toString());
			}
		}
	}

}
