package com.ttc.ch2.bl.upload.common;

import com.google.common.base.Predicate;


public class MessageFinder implements Predicate<TIMessage>
{
	private String code;

	public MessageFinder(String code) {
		super();
		this.code = code;
	}
	
	public MessageFinder(TourInfoMessages msg) {
		super();
		this.code = msg.getCode();
	}

	@Override
	public boolean apply(TIMessage input) {
		return input.getMsgEnum().getCode().equals(code);
	}
}
