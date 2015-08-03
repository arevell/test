package com.ttc.ch2.ui.common.security;

import javax.servlet.http.HttpServletRequest;

public enum PathType {

	O_ARCHIVES("outgoing_archives"),
	TOUR_INFO("tour_info"), 
	TOUR_DEPARTURE("tour_departure"),
	BROUCHURE("brochure_engine/Brochure"),
	OTHER("other");

	String partPath=null;
	
	private PathType(String path){
		this.partPath=path;
	}
	
	
	private static final String DEFAULT_TOURINFO_NAME = "tour_info";
	private static final String DEFAULT_TOURDEPARTURE_NAME = "tour_departure";
	private static final String DEFAULT_OUTGOING_ARCHIVES = "outgoing_archives";
	private static final String DEFAULT_BROUCHURE= "brochure_engine/brochure";

	public static PathType getPathType(HttpServletRequest request) {
		String uri = request.getRequestURI().toLowerCase();
		PathType type = PathType.OTHER;
		if (uri.contains(DEFAULT_TOURINFO_NAME)) {
			type = PathType.TOUR_INFO;
		} else if (uri.contains(DEFAULT_TOURDEPARTURE_NAME)) {
			type = PathType.TOUR_DEPARTURE;
		} else if (uri.contains(DEFAULT_OUTGOING_ARCHIVES)) {
		type = PathType.O_ARCHIVES;
		} else if (uri.contains(DEFAULT_BROUCHURE)) {
			type = PathType.BROUCHURE;
		}		
		return type;
	}

	public String getPartPath() {
		return partPath;
	}

};