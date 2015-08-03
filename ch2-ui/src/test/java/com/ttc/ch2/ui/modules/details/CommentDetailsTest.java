package com.ttc.ch2.ui.modules.details;

import java.lang.reflect.Field;
import java.util.Set;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.ttc.ch2.ui.moduls.details.CommentDetails;



public class CommentDetailsTest {

	@Test
	public void resizeContentTest() throws IllegalArgumentException, IllegalAccessException, Exception{
				
		String content=createContent();
		Assert.assertThat("Create invalid content - number of lines are incorrect", (long) Lists.newArrayList(Splitter.on("\n").split(content)).size(), Matchers.equalTo(getMaxLineInComment()+1));
		
		CommentDetails ctrl=new CommentDetails();		
		String result=Whitebox.invokeMethod(ctrl, "resizeContent", content );		
		long lineCount=Lists.newArrayList(Splitter.on("\n").split(result)).size();		
		Assert.assertThat("Invalid count lines after resizeing",lineCount, Matchers.equalTo(getMaxLineInComment()+1));
	}
	
	public String createContent() throws IllegalArgumentException, IllegalAccessException{
						
		StringBuilder sb=new StringBuilder();
		for (int i = 1; i < getMaxLineInComment()+1; i++) {
			sb.append("sample text\n");	
		}
		return sb.toString();
	}
	
	
	private long getMaxLineInComment() throws IllegalArgumentException, IllegalAccessException{
		long count=0;
		Set<Field> fields=Whitebox.getAllStaticFields(CommentDetails.class);		
		for (Field field : fields) {
			if(field.getName().equals("MAX_LINE_IN_COMMENTS")){
				count=field.getLong(new CommentDetails());				
			}
		}
		return count;
	}
	
}
