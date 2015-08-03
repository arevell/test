package com.ttc.ch2.scheduler.common;

import com.ttc.ch2.domain.jobs.QuartzJob;



public class DepartureExtendedSynchronizeStatusChecker extends BaseJobStatusChecker{

	public DepartureExtendedSynchronizeStatusChecker(String brandCode) {
		super(brandCode, QuartzJob.JobName.DepartureExtendedSynchronizeJob.toString());

	}

}
