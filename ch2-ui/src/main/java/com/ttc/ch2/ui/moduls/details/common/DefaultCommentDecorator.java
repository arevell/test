package com.ttc.ch2.ui.moduls.details.common;

import com.google.common.base.Preconditions;
import com.ttc.ch2.domain.comment.Comment;

public class DefaultCommentDecorator extends BaseCommentDecorator{

	public static String name="default";
	
	@Override
	public String decorateContent(Comment comment) {				
		Preconditions.checkArgument(comment!=null,"DefaultCommentDecorator->decorateContent comment is null");
		return this.decorateContent(comment.getContent());
	}

	@Override
	public String decorateContent(String txt) {
		return super.decorateContent(txt);
	}

}
