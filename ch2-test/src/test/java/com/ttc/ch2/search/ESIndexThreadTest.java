package com.ttc.ch2.search;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.inject.Inject;

import org.apache.commons.lang.time.StopWatch;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.google.common.base.Throwables;
import com.ttc.ch2.bl.upload.UploadStatusService;
import com.ttc.ch2.common.DateHelper;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.search.export.IndexSynchronizerService;
import com.ttc.ch2.search.export.IndexSynchronizerServiceException;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/META-INF/spring/testForSearchCtx.xml", "classpath:/META-INF/spring/daoCtx.xml", "classpath:/META-INF/spring/blCtx.xml", "classpath:/META-INF/spring/searchCtx.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class ESIndexThreadTest {

	private static final Logger logger = LoggerFactory.getLogger(ESIndexThreadTest.class);
	
	@Inject
	private ApplicationContext ctx;
		
	@Test
	public void testESIndex() throws IOException, IndexSynchronizerServiceException, InterruptedException {

		System.out.println("testESIndex() start");
		
		try{		 
			saveMsg("Start");
			for (int i = 0; i < 5; i++) {
				StopWatch s=new StopWatch();
				s.start();
				Thread t1=new Thread(new Work(ctx, "BV", ProcessName.UPLOAD),"BV_Thread");
				Thread t2=new Thread(new Work(ctx, "CH", ProcessName.UPLOAD),"CH_Thread");
				Thread t3=new Thread(new Work(ctx, "IV", ProcessName.UPLOAD),"IV_Thread");
				Thread t4=new Thread(new Work(ctx, "TT", ProcessName.UPLOAD),"TT_Thread");
				
				t1.start();
				t2.start();
				t3.start();
				t4.start();
				
				t1.join();
				t2.join();
				t3.join();
				t4.join();
				
				s.stop();
				saveMsg("Iteration:"+(i+1)+" time:"+ DateHelper.calculateTime(s.getTime(), DateHelper.CalculateTimePattern.HMS)+"\n");
			}					
			saveMsg("End");
		}finally{
		}	
		System.out.println("testESIndex() end");
	}
	
	
	class Work implements Runnable{

		private String brandCode;
		
		private ProcessName processName;
					
		private IndexSynchronizerService indexSynchronizerService;
		
		private UploadStatusService uploadStatusService;

		
		public Work(ApplicationContext ctx,String brandCode,ProcessName processName) {
			super();
			this.brandCode=brandCode;
			this.processName=processName;
			this.uploadStatusService=ctx.getBean(UploadStatusService.class);
			this.indexSynchronizerService=ctx.getBean(IndexSynchronizerService.class);			
		}

		@Override
		public void run() {			
			try {
				uploadStatusService.initalizeNewProccess(brandCode);
				indexSynchronizerService.synchronize(processName, brandCode);
				uploadStatusService.clearProccess(brandCode);
			} catch (Exception e) {
				logger.error(String.format("Error for brand:%s and process name:%s",brandCode,processName),e);
				saveError(e);				
			}
			
		}		
	}
	
	private synchronized void saveError(Exception e){
		try{
				FileWriter writer=new FileWriter(new File("D:/testData.txt"),true);
				try{
				writer.append(Thread.currentThread().getName()+"\n"+Throwables.getStackTraceAsString(e));
				}
				finally{
				writer.flush();writer.close();
				}
		}
		catch(Exception ex){
			logger.error("",ex);
		}
	}
	
	private synchronized void saveMsg(String msg){
		try{
			FileWriter writer=new FileWriter(new File("D:/testData.txt"),true);
			try{
				writer.append(Thread.currentThread().getName()+":"+msg+"\n");
			}
			finally{
				writer.flush();writer.close();
			}
		}
		catch(Exception ex){
			logger.error("",ex);
		}
	}
	
}
