package com.ttc.test.helpservice;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ttc.ch2.dao.jobs.QuartzJobDAO;
import com.ttc.ch2.domain.jobs.QuartzJob;


@Component
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class QuartzJobServiceHelper {

	@Inject
	private QuartzJobDAO daoJob;
	

	public QuartzJob getQuartzJob(String brandCode)
	{
		QuartzJob job=new QuartzJob();
		job.setJobName(QuartzJob.JobName.DepartureSynchronizeJob.toString());
		job.setBrandCode(brandCode);
		QuartzJob srcJob=daoJob.findByExample(job);
		return srcJob;
	}
}
