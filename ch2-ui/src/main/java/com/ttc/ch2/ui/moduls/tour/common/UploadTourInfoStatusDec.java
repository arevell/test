package com.ttc.ch2.ui.moduls.tour.common;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.ttc.ch2.domain.upload.UploadTourInfoFileStatus;
import com.ttc.ch2.ui.common.EnumDecorator;

public class UploadTourInfoStatusDec extends EnumDecorator<UploadTourInfoFileStatus> {

	@Override
	public void fillValues() {
		values.addAll(Lists.newArrayList(Iterables.transform(Lists.newArrayList(UploadTourInfoFileStatus.values()),new EnumToString())));
	}

	@Override
	public UploadTourInfoFileStatus getValueByString(String v) {

		try{
		if(StringUtils.isNotEmpty(v))
			return UploadTourInfoFileStatus.valueOf(v);
		}
		catch(IllegalArgumentException illegalArgumentException)
		{		
		return null;
		}
		return null;
	}
}
