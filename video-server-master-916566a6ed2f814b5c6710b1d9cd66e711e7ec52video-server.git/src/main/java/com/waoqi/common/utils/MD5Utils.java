package com.waoqi.common.utils;

import com.waoqi.common.config.BootdoConfig;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;



public class MD5Utils {



	private static final String SALT = "1qazxsw2";

	private static final String ALGORITH_NAME = "md5";

	private static final int HASH_ITERATIONS = 2;

	//前台用户校验规则
	private static final String USER_PASSWORD_SALT = "fpC0baWr1OZCukcv";


	public static String encrypt(String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
		return newPassword;
	}

	public static String encrypt(String username, String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(username + SALT),
				HASH_ITERATIONS).toHex();
		return newPassword;
	}



	public static String encryptUser(String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(USER_PASSWORD_SALT), HASH_ITERATIONS).toHex();
		return newPassword;
	}

	public static String encryptUser(String username, String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(username + USER_PASSWORD_SALT),
				HASH_ITERATIONS).toHex();
		return newPassword;
	}


	public static void main(String[] args) {
		System.out.println(MD5Utils.encryptUser("123456"));
		System.out.println(MD5Utils.encryptUser("admin" , "123456"));
	}

}
