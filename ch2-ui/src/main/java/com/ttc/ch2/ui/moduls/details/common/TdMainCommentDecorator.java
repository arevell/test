package com.ttc.ch2.ui.moduls.details.common;

import com.ttc.ch2.domain.comment.Comment;

public class TdMainCommentDecorator extends DefaultCommentDecorator {

	public static String name="tdmaincomment";
		
	@Override
	public String decorateContent(Comment comment) {
	String content=super.decorateContent(comment);
	content=content.replace("Main information:", "<b>Main information:</b>");
	
	return content;
	}
}
