package com.ttc.common.params;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.lang.UnhandledException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlowfishCoderOriginal implements ParamCoder{

	private static Logger logger = LoggerFactory.getLogger(BlowfishCoderOriginal.class);
	
	private static Cipher cipherToEncode;
	private static Cipher cipherToDecode;
	
	static
	{
		try {			
		    KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
		    Random gen = new Random();
		    byte[] randomBytes = new byte[8];
		    gen.nextBytes(randomBytes);
		    keyGenerator.init(128,new SecureRandom(randomBytes));
		    Key key = keyGenerator.generateKey();
		    logger.info("Generated BlowfishKey for current application context is equal: "+bytesToHex(randomBytes));
		    
		    cipherToEncode = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
		    cipherToEncode.init(Cipher.ENCRYPT_MODE, key);
		    cipherToDecode = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
		    cipherToDecode.init(Cipher.DECRYPT_MODE, key);
		} catch (NoSuchAlgorithmException e) {
			throw new UnhandledException(e);
		} catch (NoSuchPaddingException e) {
			throw new UnhandledException(e);
		} catch (InvalidKeyException e) {
			throw new UnhandledException(e);
		}
	}
	
	
	public String encode(String param) {		
		String encryptedString;
		try {			
			byte[]	encryptedParam = cipherToEncode.doFinal(param.getBytes("UTF8"));			
			encryptedString = bytesToHex(encryptedParam);
		} catch (IllegalBlockSizeException e) {
			throw new UnhandledException(e);
		} catch (BadPaddingException e) {
			throw new UnhandledException(e);
		} catch (UnsupportedEncodingException e) {
			throw new UnhandledException(e);
		}		
		return	encryptedString;
	}

	public String decode(String param) {
			
		String result;
		try {					
			result = new String(cipherToDecode.doFinal(hexToBytes(param)));
		} catch (IllegalBlockSizeException e) {
			throw new UnhandledException(e);
		} catch (BadPaddingException e) {
			throw new UnhandledException(e);
		} 		
		return	result;
	}
	
	
	
	private static byte[] hexToBytes(String str) {
		if (str == null) {
			return null;
		} else if (str.length() < 2) {
			return null;
		} else {
			int len = str.length() / 2;
			byte[] buffer = new byte[len];
			for (int i = 0; i < len; i++) {
				buffer[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
			}
			return buffer;
		}

	}

	private static String bytesToHex(byte[] data) {
		if (data == null) {
			return null;
		} else {
			int len = data.length;
			String str = "";
			for (int i = 0; i < len; i++) {
				if ((data[i] & 0xFF) < 16)
					str = str + "0" + java.lang.Integer.toHexString(data[i] & 0xFF);
				else
					str = str + java.lang.Integer.toHexString(data[i] & 0xFF);
			}
			return str.toUpperCase();
		}
	}

}
