package com.ttc.ch2.ui.moduls.details.common;

import com.ttc.ch2.domain.comment.Comment;

public class TiMainCommentDecorator extends DefaultCommentDecorator {

	public static String name="timaincomment";
	
	
	@Override
	public String decorateContent(Comment comment) {
	String content=super.decorateContent(comment);
	content=content.replace("Errors:", "<b>Errors:</b>");
	content=content.replace("Additional information:", "<b>Additional information:</b>");
	return content;
	}
}
