package com.ttc.ch2.ui.moduls.details.common;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Preconditions;


public abstract class BaseCommentDecorator implements CommentContentDecorator{

	protected static final String END_LINE="\n";

	@Override
	public String decorateContent(String content) {
		Preconditions.checkArgument(StringUtils.isNotBlank(content),"BaseCommentDecorator->decorateContent content is null");
		return content.replace("\n", "<br/>\n");
	}

}
