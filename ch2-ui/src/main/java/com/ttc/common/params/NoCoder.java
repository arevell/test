package com.ttc.common.params;

public class NoCoder implements ParamCoder{

	@Override
	public String encode(String param) {
		return param;
	}

	@Override
	public String decode(String param) {
		return param;
	}

}
