package com.ttc.ch2.ui.moduls.details.common;

import com.ttc.ch2.domain.comment.Comment;

public class DefaultCommentDecorator extends BaseCommentDecorator{

	public static String name="default";
	
	@Override
	public String decorateContent(Comment comment) {		
		
		String content=super.decorateContent(comment);
		return content;
	}

}
