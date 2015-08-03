package com.ttc.ch2.ui.moduls.details.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Pattern;

import org.apache.ecs.html.Span;

import com.google.common.base.Preconditions;
import com.ttc.ch2.domain.comment.Comment;
import com.ttc.ch2.ui.common.exceptions.CH2Exception;

public class TiErrorsDetailsDecorator extends BaseCommentDecorator{
	
	Pattern ELEMENT_PATERN = Pattern.compile("^\\d.*File Name:*.$");
	

	
	public static final String name="tierrorsdetails";
	
//	1. File Name:ATLPUS09
//	INF-4004 Operation on file: ATLPUS09.xml, size:9,512, file date:23/05/14 11:12
//	INF-4012 Validating file:ATLPUS09.xml
//	INF-4013 Maping file:ATLPUS09
//	INF-4014 Creating tour info file version 1:ATLPUS09
//	INF-4021 MD5 for TourInfo_XML is the same, ignoring tour code:ATLPUS09
//	2. File Name:AGIHUS09
//	INF-4004 Operation on file: AGIHUS09.xml, size:13,532, file date:23/05/14 11:12
//	INF-4012 Validating file:AGIHUS09.xml
//	INF-4013 Maping file:AGIHUS09
//	INF-4014 Creating tour info file version 1:AGIHUS09
//	INF-4021 MD5 for TourInfo_XML is the same, ignoring tour code:AGIHUS09
//	3. File Name:ATAMUS09
//	INF-4004 Operation on file: ATAMUS09.xml, size:9,302, file date:23/05/14 11:12
//	INF-4012 Validating file:ATAMUS09.xml
//	INF-4013 Maping file:ATAMUS09
//	INF-4014 Creating tour info file version 1:ATAMUS09
//	INF-4021 MD5 for TourInfo_XML is the same, ignoring tour code:ATAMUS09
	
	
	
	
	@Override
	public String decorateContent(Comment comment) {
		Preconditions.checkArgument(comment!=null,"TiErrorsDetailsDecorator->decorateContent comment is null");
		return this.decorateContent(comment.getContent()); 
	}

	@Override
	public String decorateContent(String txt) {
		String content=super.decorateContent(txt);
		content=content.replace("Error information:", "<b>Error information:</b>");
		
		StringBuilder sb=new StringBuilder();
		BufferedReader reader = new BufferedReader(new StringReader(content));
		String line="";
		boolean startError=false;
		try {
			while((line=reader.readLine())!=null){
				String result=null;
				
				if(line.trim().startsWith("ERR-")){
					startError=true;					
				}else if(line.trim().startsWith("INF-") || line.trim().startsWith("WRN-")  || line.trim().contains("File Name:")){
					startError=false;					
				}
				else if (line.trim().contains("Error information:")) {
					startError = false;
				}
				
								
				if(startError==true){
					Span span=new Span();
					span.setTagText(line);
					span.addAttribute("class", "error_bold");
					result=span.toString();
				}
				else if(line.contains(" File Name:")){
					Span span=new Span();
					span.setTagText(line);
					span.addAttribute("class", "hightlight");
					result=span.toString();
				}
				else{
					result=line;
				}
				sb.append(result).append(END_LINE);
			}
		} catch (IOException e) {
			throw new CH2Exception(e);
		}
		
		return sb.toString();
	}
	
	
}
