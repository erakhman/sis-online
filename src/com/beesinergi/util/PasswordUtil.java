package com.beesinergi.util;

import org.jasypt.util.password.BasicPasswordEncryptor;

public class PasswordUtil {

	public static boolean checkPassword(String inputPassword, String dbPassword) {
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		if (passwordEncryptor.checkPassword(inputPassword, dbPassword)) {
			return true;
		} 
		return false;
	}
	
	public static String getEncryptPassword(String password) {
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		return passwordEncryptor.encryptPassword(password);
	}
	
	public static void main(String[] args) {
		System.out.println(getEncryptPassword("password"));
	}
}
