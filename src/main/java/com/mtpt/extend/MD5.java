package com.mtpt.extend;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.apache.commons.codec.binary.Base32;

import sun.misc.BASE64Encoder;
/**
 * 
 * @author lvgordon
 * 密码加密：通过MD5的加密方式。加密规则为：
 * 原密码重复一次得到新的密码字符串；
 * 使用MD5加密方式加密新密码字符串得到加密后的字符串；
 * 使用BASE64加密上一步加密后的字符串
 *
 */
public class MD5 {
	private static String TYPE = "MD5";
	public static String encrypt(String password) {
		BigInteger bigInteger = null;
		password = password + password;//将原来密码的值重复一次
		try {
			MessageDigest md5 = MessageDigest.getInstance(TYPE);
			byte[] pass = password.getBytes();
			md5.update(pass);
			bigInteger = new BigInteger(md5.digest());//使用md5将重复的密码加密一次
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String md5str = bigInteger.toString(32);
		BASE64Encoder encoder = new BASE64Encoder();
		String resultstr = encoder.encode(md5str.getBytes());//使用base64将加密后所得的字符串在进行加密一次
		return resultstr;
	}
	
	
	public static void main(String[] args) {
		System.out.println(Integer.parseInt("adb"));
	}
}
