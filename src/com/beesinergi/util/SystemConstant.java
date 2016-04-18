package com.beesinergi.util;

import java.util.HashMap;
import java.util.Map;

public class SystemConstant {

	public static String DATE_FORMAT = "dd-MM-yyyy";
	public static String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm";
	public static String JQUERY_DATE_FORMAT = "DD-MMM-YYYY";
	public static String JQUERY_DATE_TIME_FORMAT = "DD-MMM-YYYY HH:mm";
	public static String DATE_PICKER_FORMAT = "dd-mm-yy";
	public static String SESSION_USER = "_userInfo";
	public static String SESSION_WAKTU_UJIAN = "_waktuUjian";
	public static String YES = "Y";
	public static String NO = "N";
	
	public static Integer PK_USER_ROLE_CALON_SISWA = 2;
	
	public static class MenuCode {
		public static String USER = "user";
		public static String LOOKUP = "lookup";	
		public static String ROLE = "role";
		public static String PENDAFTARAN = "pendaftaran";
		public static String UJIAN_MASUK = "ujianMasuk";
	}	
	
	public static class LookupType {
		public static int CATEGORY = 1;
		public static int UNIT = 2;
		public static int DEPARTMENT = 3;
		public static int ROOM = 4;	
		public static int CAR = 5;	
	}
	
	public static Map<Integer,String> LookupTypeDescr() {
		Map<Integer,String> map = new HashMap<Integer, String>();
		map.put(LookupType.CATEGORY, "label.category");
		map.put(LookupType.UNIT, "label.unit");
		map.put(LookupType.DEPARTMENT, "label.department");
		map.put(LookupType.ROOM, "label.room");
		map.put(LookupType.CAR, "label.car");
		return map;
	}
	
	
	
    
}
