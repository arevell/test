package com.ttc.ch2.bl.upload.common;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
 


public class ZipHelper {

 public static byte[] SPECIFIX_SINGS = { 'P', 'K', 0x3, 0x4 };
 
	public static boolean isZipStream(InputStream in) {
		if (!in.markSupported()) {
			in = new BufferedInputStream(in);
		}
		boolean isZip = true;
		try {
			in.mark(SPECIFIX_SINGS.length);
			for (int i = 0; i < SPECIFIX_SINGS.length; i++) {
				if (SPECIFIX_SINGS[i] != (byte) in.read()) {
					isZip = false;
					break;
				}
			}
			in.reset();
		} catch (IOException e) {
			isZip = false;
		}
		return isZip;
	}
	
	
	public static int getEntryCount(byte[] data) throws IOException{
		
		File temp=null;
		try{
			temp = File.createTempFile("upload_tmp_file", ".tmp");
				FileOutputStream output=new FileOutputStream(temp);
				try{		
					output.write(data);
				}
				finally{
					output.flush();
					output.close();
				}	 	   
			ZipFile zipFile=new ZipFile(temp);
			return zipFile.size();
		}
		finally{
			FileUtils.deleteQuietly(temp);
			}
		}
	}