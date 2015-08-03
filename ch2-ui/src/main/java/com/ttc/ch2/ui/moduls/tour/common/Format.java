package com.ttc.ch2.ui.moduls.tour.common;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Preconditions;
import com.ttc.ch2.ui.common.exceptions.CH2Exception;


public enum  Format{
	 UI_PLAIN("ui-plain","text/html"),
	 XML("xml","text/xml");		 
	 private String paramName=null;
	 private String contentType=null;
	 private Format(String paramName,String contentType) {
			this.paramName = paramName;
			this.contentType=contentType;
		}

	 public String getParamName() {
			return paramName;
		} 
	 
	 public static Format getFormatByParamName(String paramName){
		 			 
		 Preconditions.checkArgument(StringUtils.isNotBlank(paramName),"Enum Format-> getFormatByParamName paramaName is null");
		 
		 for (int i = 0; i < values().length; i++) {
			 if(values()[i].getParamName().equals(paramName))
				 return values()[i];				
		 }			 
		 throw new CH2Exception("Not found enum Format for ParamName:"+paramName);
	 }

	public String getContentType() {
		return contentType;
	}
	}