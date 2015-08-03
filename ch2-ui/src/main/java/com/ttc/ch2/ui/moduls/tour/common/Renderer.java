package com.ttc.ch2.ui.moduls.tour.common;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;

public interface Renderer {

	public static final String thTextClass="thHtmlTabFont";
//	private static final String fontFamily=" titleHtmlPageFont";
	
	public String generate() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException;
}
