package com.mtpt.extend;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

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
	
	/**
	 * 对用户的密码进行加密
	 * @param password
	 * @return
	 */
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


	/**
	 * md5的加密方式
	 * @param mdstr 加密的明文文本
	 * @return
	 */
	public static String md(String mdstr) {
		BigInteger bigInteger = null;
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] pass = mdstr.getBytes("utf-8");
			md5.update(pass);
			byte b[] = md5.digest();
			int i;
			for(int offset=0; offset<b.length; offset++){
				i = b[offset];
				if(i<0){
					i+=256;
				}
				if(i<16){
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buf.toString().toUpperCase();
	}
}
