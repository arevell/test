package com.ttc.common.params;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.UnhandledException;

import com.ttc.ch2.ui.common.SessionHelper;

public class BlowfishCoder implements ParamCoder{

	private static final String CIPHER_TO_ENCODE = "CIPHER_TO_ENCODE";
	private static final String CIPHER_TO_DECODE = "CIPHER_TO_DECODE";
	
	private HttpServletRequest request;
	Cipher cipherToEncode;
	Cipher cipherToDecode;
	
	
	public BlowfishCoder() {
		request = null;
	}
	
	public BlowfishCoder(HttpServletRequest request) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
		this.request = request;
		String token = (String)request.getParameter("token");
		cipherToEncode = initEncoder(("66633"+token).getBytes());
		cipherToDecode = initDecoder(("66633"+token).getBytes());
	}
	
	private static Key getKey(byte[] seed) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
	    keyGenerator.init(128,new SecureRandom(seed));
	    Key key = keyGenerator.generateKey();
		return key;
	}
	
	public static void initCoder(HttpSession session, byte[] seed) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
		Cipher cipherToEncode = initEncoder(seed);
		Cipher cipherToDecode = initDecoder(seed);
		session.setAttribute(CIPHER_TO_ENCODE, cipherToEncode);
		session.setAttribute(CIPHER_TO_DECODE, cipherToDecode);
	}
	
	private static Cipher initEncoder(byte[] seed) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		Cipher cipherToEncode;
		cipherToEncode = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
		cipherToEncode.init(Cipher.ENCRYPT_MODE, getKey(seed));
		return cipherToEncode;
	}
	
	private static Cipher initDecoder(byte[] seed) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		Cipher cipherToDecode;
		cipherToDecode = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
	    cipherToDecode.init(Cipher.DECRYPT_MODE, getKey(seed));
	    return cipherToDecode; 
	}
	
	public String encode(String param) {		
		String encryptedString;
		HttpSession session;
		if(request == null) {
			session=SessionHelper.getSession();
			cipherToEncode = (Cipher)session.getAttribute(CIPHER_TO_ENCODE);
		}
		
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
		HttpSession session;
		if(request == null) { 
			session=SessionHelper.getSession();
			cipherToDecode = (Cipher)session.getAttribute(CIPHER_TO_DECODE);
		}
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
