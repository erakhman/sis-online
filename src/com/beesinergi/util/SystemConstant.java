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
	public static String YES = "Y";
	public static String NO = "N";
	
	public static class MenuCode {
		public static String PRODUCT = "product";
		public static String EMPLOYEE = "employee";
		public static String USER = "user";
		public static String LOOKUP = "lookup";	
		public static String ROLE = "role";
		public static String VENDOR = "vendor";
		public static String DRIVER = "driver";
		public static String PO = "po";
		public static String PO_APPROVAL = "poApproval";
		public static String PO_OUT = "poOut";
		public static String PURCHASE = "purchase";
		public static String DELIVERY = "delivery";
		public static String RESERVATION = "reservation";
		public static String STOCK = "stock";
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
	
	public static class ReservationType {
		public static int ROOM = 1;
		public static int CAR = 2;
	}
	
	public static Map<Integer,String> ReservationDescr() {
		Map<Integer,String> map = new HashMap<Integer, String>();
		map.put(ReservationType.ROOM, "label.room");
		map.put(ReservationType.CAR, "label.car");
		return map;
	}
	
	public static class TrxType {
		public static String PURCHASE = "1";
		public static String DELIVERY = "2";
		public static String ADJUSTMENT = "3";	
	}
	
	public static class AppStatusType {
		public static String PO = "PO";
		public static String PO_OUT = "PO_OUT";
		public static String RESERVATION = "RESERVATION";	
	}
	
	public static class PoStatus {
		public static String NEED_APPROVAL = "1";
		public static String APPROVED = "2";
		public static String REJECTED = "3";
		public static String DELIVERED = "4";	
		public static String OUTSTANDING = "5";	
		public static String PO_OUT = "6";	
	}
	
	public static class PoOutStatus {
		public static String NEW = "1";
		public static String PURCHASED = "2";	
	}
	
	public static class ReservationStatus {
		public static String RESERVED = "1";
		public static String COMPLETED = "2";	
	}
	
	
	
	
	
    
}
