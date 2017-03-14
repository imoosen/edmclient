package com.fiveone.edm.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

public final class Encrypter {
	private static Cipher ecipher;
	private static Cipher dcipher;
	private static final String key = "*:@6$0!t*:@9$7!t*:@8$7!t";
	private static final String alg = "DESede";
	private static final Logger logger = Logger.getLogger(Encrypter.class);

	static {
		try {
			SecretKey skey = new SecretKeySpec("*:@6$0!t*:@9$7!t*:@8$7!t".getBytes(), "DESede");

			ecipher = Cipher.getInstance("DESede");

			dcipher = Cipher.getInstance("DESede");

			ecipher.init(1, skey);

			dcipher.init(2, skey);
			
		} catch (Exception e) {
			logger.error("初始化安全加密类异常：", e);
		}
	}

	/**
	 * 对字符串加密
	 * @param str
	 * @return
	 */
	public static String encrypt(String str) {
		if (str == null)
			return "";
		try {
			byte[] utf8 = str.getBytes("UTF8");

			byte[] enc = ecipher.doFinal(utf8);

			return Base64Support.toStr(enc);
			
			//Base64Support.encodeStr(str);
			
		} catch (Exception e) {
			logger.error("加密类异常：", e);
		}
		return "";
	}

	/**
	 * 对字符串解密
	 * @param str
	 * @return
	 */
	public static String decrypt(String str) {
		if (str == null)
			return "";
		try {
			byte[] dec = Base64Support.fromStr(str);

			byte[] utf8 = dcipher.doFinal(dec);

			return new String(utf8, "UTF8");
		} catch (Exception e) {
			logger.error("解密类异常：", e);
		}
		return "";
	}
/*
	public static void main(String[] args) {
		System.out.println(decrypt("Xch1NgWuUJ2bpZrr7fPoF0LJbmxcY1bX5G9//PrWyy4="));
		System.out.println(encrypt("G0001#j#0000110j#08-05 09:28:48"));
	}*/
}
