package com.ttc.ch2.ui.moduls.tour.common;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.ttc.ch2.domain.upload.UploadTourInfoFileSource;
import com.ttc.ch2.ui.common.EnumDecorator;

public class UploadTourInfoSourceDec extends EnumDecorator<UploadTourInfoFileSource> {

	@Override
	public void fillValues() {
		values.addAll(Lists.newArrayList(Iterables.transform(Lists.newArrayList(UploadTourInfoFileSource.values()),new EnumToString())));
	}

	@Override
	public UploadTourInfoFileSource getValueByString(String v) {

		try{
		if(StringUtils.isNotEmpty(v))
			return UploadTourInfoFileSource.valueOf(v);
		}
		catch(IllegalArgumentException illegalArgumentException)
		{		
		return null;
		}
		return null;
	}
}
