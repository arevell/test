package com.ttc.ch2.brox.service;

import com.ttc.ch2.api.ccapi.v3.GetBrochureRequest;
import com.ttc.ch2.api.ccapi.v3.GetBrochureResponse;

public interface BrochureService {

	public static final String ANY_CODE = "any";

	GetBrochureResponse getBrochure(GetBrochureRequest brochureParameters);

	byte[] getBrochureData(GetBrochureRequest brochureParameters) throws BrochureServiceException;
}
