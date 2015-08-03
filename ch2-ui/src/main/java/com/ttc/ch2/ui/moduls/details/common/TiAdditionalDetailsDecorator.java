package com.ttc.ch2.ui.moduls.details.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.apache.ecs.html.Span;

import com.google.common.base.Preconditions;
import com.ttc.ch2.domain.comment.Comment;
import com.ttc.ch2.ui.common.exceptions.CH2Exception;

public class TiAdditionalDetailsDecorator extends BaseCommentDecorator{
		
	public static final String name="tiAdditionaldetails";
		
	@Override
	public String decorateContent(Comment comment) {
		Preconditions.checkArgument(comment!=null,"TiAdditionalDetailsDecorator->decorateContent comment is null");		
		return this.decorateContent(comment.getContent());		
	}
		
	public String decorateContent(String txt){		
		String content=super.decorateContent(txt);	
		content=content.replace("Additional information:", "<b>Additional information:</b>");
		StringBuilder sb=new StringBuilder();
		BufferedReader reader = new BufferedReader(new StringReader(content));
		String line="";
		try {
			while((line=reader.readLine())!=null){
				String result=null;				
				if(line.contains(" File Name:")){
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
