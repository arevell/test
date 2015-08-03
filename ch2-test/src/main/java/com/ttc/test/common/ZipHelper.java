package com.ttc.test.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipHelper {
	
	public static void createZip(OutputStream out,InputStream entryInputStream,String zipEntryName)
	{
		byte[] buffer = new byte[1024];	 
		try{
	
			ZipOutputStream zos = new ZipOutputStream(out);			
			ZipEntry ze= new ZipEntry(zipEntryName);
			zos.putNextEntry(ze);			
			int len;
			while ((len = entryInputStream.read(buffer)) > 0) {
				zos.write(buffer, 0, len);
			}
	
			entryInputStream.close();
			zos.closeEntry();	
			//remember close it
			zos.close();	
		}catch(IOException ex){
		   ex.printStackTrace();
		}
	}
}
