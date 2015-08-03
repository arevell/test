package com.ttc.common.params;

public interface ParamCoder {
	String encode(String param);
	String decode(String param);
}
