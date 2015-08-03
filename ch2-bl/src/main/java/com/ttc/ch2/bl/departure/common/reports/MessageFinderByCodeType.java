package com.ttc.ch2.bl.departure.common.reports;

import com.google.common.base.Predicate;
import com.ttc.ch2.bl.departure.common.TDMessage;

public class MessageFinderByCodeType implements Predicate<TDMessage>
{
	private String codeType;
	
	public MessageFinderByCodeType(String code) {
		super();
		this.codeType = code;
	}
	
	@Override
	public boolean apply(TDMessage input) {
		return input.getMsgEnum().getCode().contains(codeType);
}
	
}	