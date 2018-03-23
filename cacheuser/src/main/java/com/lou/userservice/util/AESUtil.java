/**
 * 
 */
package com.lou.userservice.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * @author louxiaobo
 *
 */
public class AESUtil {


    public static String encrypt(String cleartext, String key) throws Exception
	{  
    
	    	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    	byte[] keyBytes = new byte[16];
	    	byte[] b = key.getBytes("UTF-8");
	    	int len = b.length;
	
	    	if (len > keyBytes.length) {
	    		len = keyBytes.length;
	        }
	    	
	    	System.arraycopy(b, 0, keyBytes, 0, len);
	    	SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
	    	
	    	IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
	    	cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
	
	    	byte[] results = cipher.doFinal(cleartext.getBytes("UTF-8"));
	    	return toHex(results);
    	
	}  
   
	public static String decrypt(String encrypted, String key) throws Exception
	{

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			
			byte[] keyBytes = new byte[16];
			byte[] b = key.getBytes("UTF-8");
			int len = b.length;
			
			if (len > keyBytes.length) { 
				len = keyBytes.length;
	        }
			
			System.arraycopy(b, 0, keyBytes, 0, len);
			SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
			IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
			cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
			
			byte [] results = cipher.doFinal(toByte(encrypted));
			
			return new String(results, "UTF-8");


	}

	public static String toHex(String txt)
	{
		return toHex(txt.getBytes());
	}

	public static String toHex(byte[] buf)
	{
		if (buf == null) {
			return "";
        }
		StringBuffer result = new StringBuffer(2*buf.length);
		for (int i = 0; i < buf.length; i++) {
			appendHex(result, buf[i]);
		}
		return result.toString();
	}

	private static final String HEX = "0123456789ABCDEF";
	private static void appendHex(StringBuffer sb, byte b)
	{
		sb.append(HEX.charAt((b>>4)&0x0f)).append(HEX.charAt(b&0x0f));
	}

	public static byte[] toByte(String hexString)
	{
		int len = hexString.length()/2;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++) {
			result[i] = Integer.valueOf(hexString.substring(2*i, 2*i+2), 16).byteValue();
        }
		return result;
	}
}
