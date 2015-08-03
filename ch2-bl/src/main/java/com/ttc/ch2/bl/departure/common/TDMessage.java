package com.ttc.ch2.bl.departure.common;

import java.io.Serializable;
import java.util.Date;

public class TDMessage implements Serializable
{
	private static final long serialVersionUID = -587843840714250664L;
	
	private TropicSynchronizeMessages msgEnum;
	private String brandCode;
	private String sellingCompanyCode;
	private String productCode;
	
	private String message;
	private String content;
	private Throwable e;
	private Date time;
	private Long duration;
	
	public TDMessage(String brandCode,String sellingCompanyCode,String productCode,TropicSynchronizeMessages msgEnum, String message, Throwable e,Long duration,Date time) {
		super();
		this.brandCode=brandCode;
		this.sellingCompanyCode=sellingCompanyCode;
		this.productCode=productCode;
		this.msgEnum = msgEnum;
		this.message = message;		
		this.e = e;
		this.duration=duration;
		this.time=time;
	}	

	public TropicSynchronizeMessages getMsgEnum() {
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

	public void setContent(String content) {
		this.content = content;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public String getSellingCompanyCode() {
		return sellingCompanyCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public Date getTime() {
		return time;
	}

	public long getDuration() {
		return duration;
	}
}
