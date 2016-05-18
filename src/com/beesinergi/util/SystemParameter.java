package com.beesinergi.util;

import com.beesinergi.util.ReflectionFunction;

public final class SystemParameter {
	
	public static String DEFAULT_PASSWORD;
	public static String REPORT_TEMPLATE;
	
	public static int PAGE_LIMIT;
	public static int MAX_WRONG_PASSWORD;
	public static int DURASI_UJIAN;
	public static int REGISTRATION_FEE;
	public static int RUNNING_NO_REGISTRATION_CODE;
	public static int RUNNING_NO_MEMBER_CODE;
	
	
	public static synchronized void updateSystemEnvironment(String name, String value) {
		ReflectionFunction.setProperties(SystemParameter.class, name, value);
	}

}
