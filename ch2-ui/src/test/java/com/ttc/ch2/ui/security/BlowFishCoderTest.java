package com.ttc.ch2.ui.security;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import com.ttc.common.params.BlowfishCoder;

public class BlowFishCoderTest {

	
	@Test
	public void blowFishCoderTest() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException{
		
		HttpServletRequest request = PowerMockito.mock(HttpServletRequest.class);
		Random generator = new Random();
		PowerMockito.when(request.getParameter("token")).thenReturn("fmulder"+generator.nextInt(100000));			
		BlowfishCoder bfc = new BlowfishCoder(request);
		try {
			String out = bfc.encode("test");
			bfc.decode(out.substring(0,out.length()-3)+"AAA");
			fail("Test failed");
		}catch(Exception e) {
			//e.printStackTrace();
			assertTrue(e.getMessage().contains(javax.crypto.BadPaddingException.class.getName()));
		}
		
	}
	
}
