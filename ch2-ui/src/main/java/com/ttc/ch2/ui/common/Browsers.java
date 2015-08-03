package com.ttc.ch2.ui.common;


public enum Browsers {

	IE("ie"),CHROME("webkit"),FIREFOX("gecko"),OTHER("");
	
	private String name;	
	private Browsers(String name)
	{
		this.name=name;
	}
	
	public static Browsers getBrowserByName(String name)
	{
		for (int i = 0; i < values().length; i++) {
			
			if(values()[i].name==name)
				return values()[i]; 			
		}		
		return OTHER;
	}
}
