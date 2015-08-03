package com.ttc.ch2.dao.comment;

import java.util.List;

import com.google.common.collect.Lists;

public enum TypeComment {
	TIComment ("Tour Info - upload"),TDComment("Tour departure - import"),QHComment("Schedule - import"),CREComment("Indexing - import");
	
	
	private String desc;

	private TypeComment(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
	
	
	public static List<TypeComment> getValues(){
		List<TypeComment> l=Lists.newArrayList();
		
		for (int i = 0; i < values().length; i++) {
			if(values()[i]==CREComment)
				continue;
			
			l.add(values()[i]);
		}
		
		return l;
		
	}
	
}
