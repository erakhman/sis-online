package com.beesinergi.util;

import com.beesinergi.util.ReflectionFunction;

public final class SystemParameter {
	
	public static String DEFAULT_PASSWORD;
	public static String REPORT_TEMPLATE;
	public static String TAHUN_AJARAN_BERJALAN;
	
	
	public static int PAGE_LIMIT;
	public static int MAX_WRONG_PASSWORD;
	public static int RUNNING_NUMBER_PO_NO;
	public static int RUNNING_NUMBER_PO_OUT_NO;
	public static int RUNNING_NUMBER_PURCHASE_NO;
	public static int RUNNING_NUMBER_DELIVERY_NO;
	public static int RUNNING_NUMBER_ADJ_NO;
	
	
	public static synchronized void updateSystemEnvironment(String name, String value) {
		ReflectionFunction.setProperties(SystemParameter.class, name, value);
	}

}
