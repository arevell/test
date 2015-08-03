package com.ttc.ch2.bl.departure.common.reports;

import com.google.common.base.Predicate;
import com.ttc.ch2.bl.departure.common.TDMessage;

public class MessageFinder implements Predicate<TDMessage>
	{
		private String code;

		public MessageFinder(String code) {
			super();
			this.code = code;
		}

		@Override
		public boolean apply(TDMessage input) {
			return input.getMsgEnum().getCode().equals(code);
		}	
	}