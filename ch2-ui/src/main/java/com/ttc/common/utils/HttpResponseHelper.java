package com.ttc.common.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class HttpResponseHelper {

	
	public static void writeOutput(HttpServletResponse response,String content,String cType) throws IOException{		
		response.setContentType(cType);		
		PrintWriter out=response.getWriter();
		out.write(content);
		out.flush();
		out.close();
	}
	
	public static void writeOutput(int statusCode,HttpServletResponse response,String content,String cType) throws IOException{
		response.setStatus(statusCode);
		writeOutput(response, content, cType);
	}
}
