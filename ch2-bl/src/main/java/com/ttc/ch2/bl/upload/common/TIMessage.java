package com.ttc.ch2.bl.upload.common;

import java.util.Date;

import com.google.common.base.Preconditions;

public class TIMessage 
{
	private TourInfoMessages msgEnum;	
	private String message;
	private String content;
	private Throwable e;
	private Long duration;
	private Date time;
		
	public TIMessage(TourInfoMessages msgEnum, String message, Throwable e,String content) {
		super();
		Preconditions.checkArgument(message!=null,"Message is null");		
		this.msgEnum = msgEnum;
		this.message = message;
		this.e = e;
		this.content=content;
		this.time=new Date();		
	}	

	public TourInfoMessages getMsgEnum() {
		return msgEnum;
	}

	public String getMessage() {
		return message;
	}

	public Throwable getE() {
		return e;
	}

	public String getContent() {
		return content;
	}

	public Date getTime() {
		return time;
	}
	
	@Override
	public String toString() {		
		return message;
	}

	public Long getDuration() {
		return duration;
	}
}
