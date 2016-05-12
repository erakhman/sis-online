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
	public static Integer PK_USER_ROLE_SISWA = 3;
	
	public static class MenuCode {
		public static String USER = "user";
		public static String LOOKUP = "lookup";	
		public static String ROLE = "role";
		public static String PENDAFTARAN = "pendaftaran";
		public static String UJIAN_MASUK = "ujianMasuk";
		public static String HASIL_UJIAN_MASUK = "hasilUjianMasuk";
		public static String TAHUN_AJARAN = "tahunAjaran";
		public static String COA_TYPE = "coaType";
		public static String PENALTY_TYPE = "penaltyType";
		public static String BOOK_SHELF = "bookShelf";
		public static String BOOK_LOCATION = "bookLocation";
		public static String RETURN_AND_BORROW_BOOK = "returnAndBorrowBook";
		public static String PELAJARAN = "pelajaran";
		public static String KELAS = "kelas";
		public static String PELAJARAN_UJIAN = "pelajaranUjian";
		public static String JADWAL_UJIAN = "jadwalUjian";
		public static String SISWA = "siswa";
		public static String SOAL = "soal";
		public static String COA = "coa";
		public static String STAFF = "staff";
		public static String CLASS_HISTORY = "classHistory";
		public static String LIBRARY_ANNUAL_FEE = "libraryAnnualFee";
		public static String BOOK = "book";
		public static String BOOK_CATEGORY = "bookCategory";
		public static String BOOK_PUBLISHER = "bookPublisher";
		public static String LIBRARY_MEMBER = "libraryMember";
	}	
	
	public static class LookupGroup {
		public static int JADWAL_UJIAN = 1;
	}
	
	/*
	 *  The value is primary key from lookup table where lookup group = jadwal ujian
	 */
	public static class JadwalUjianType {
		public static int UJIAN_MASUK = 1;
		public static int ULANGAN_HARIAN = 2;
		public static int UTS = 3;
		public static int UAS = 4;
	}
	
	public static Map<Integer,String> LookupGroupDescr() {
		Map<Integer,String> map = new HashMap<Integer, String>();
		map.put(LookupGroup.JADWAL_UJIAN, "lookupGroup.jadwalUjian");
		return map;
	}
	
	public static class RegistrationStatus {
		public static int NEW = 1;
		public static int PASSED = 2;
		public static int REGISTERED = 3;
	}
	
	public static Map<Integer,String> RegistrationStatus() {
		Map<Integer,String> map = new HashMap<Integer, String>();
		map.put(RegistrationStatus.NEW, "New");
		map.put(RegistrationStatus.PASSED, "Passed");
		map.put(RegistrationStatus.REGISTERED, "Registered");
		return map;
	}
	
	public static class StaffType {
		public static int TEACHER = 1;
		public static int ADMIN = 2;
	}
	
	public static Map<Integer,String> StaffType() {
		Map<Integer,String> map = new HashMap<Integer, String>();
		map.put(StaffType.TEACHER, "Guru");
		map.put(StaffType.ADMIN, "TU");
		return map;
	}
	
	public static class StaffStatus {
		public static int EMPLOYEE = 1;
		public static int HONORER = 2;
	}
	
	public static Map<Integer,String> StaffStatus() {
		Map<Integer,String> map = new HashMap<Integer, String>();
		map.put(StaffStatus.EMPLOYEE, "PNS");
		map.put(StaffStatus.HONORER, "Honorer");
		return map;
	}
	
    
}
