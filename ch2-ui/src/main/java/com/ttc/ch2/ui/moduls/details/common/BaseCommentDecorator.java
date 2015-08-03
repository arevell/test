package com.ttc.ch2.ui.moduls.details.common;

import com.google.common.base.Preconditions;
import com.ttc.ch2.domain.comment.Comment;

public class BaseCommentDecorator implements CommentContentDecorator{

	protected static final String END_LINE="\n";
	
	@Override
	public String decorateContent(Comment comment) {
		Preconditions.checkArgument(comment!=null,"DefaultCommentDecorator->decorateContent is null");
		return comment.getContent().replace("\n", "<br/>\n");
	}

}
