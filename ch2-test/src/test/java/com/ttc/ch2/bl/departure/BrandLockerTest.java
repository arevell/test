package com.ttc.ch2.bl.departure;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.task.TaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.ttc.ch2.bl.lock.LockBrandService;
import com.ttc.ch2.common.BaseTest;
import com.ttc.ch2.common.enums.ProcessName;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/blCtx.xml", "classpath:/META-INF/spring/testCtx.xml", "classpath:/META-INF/spring/testThreadsCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class BrandLockerTest extends BaseTest{
	
	public class LockerThread implements Runnable {

		public static final String BRAND_CODE = "CH";
		
		@Override
		public void run() {
			
			do {
				boolean locked = lockBrandService.lockBrand(BRAND_CODE,  ProcessName.IMPORT);
				try {
					Thread.sleep(10);
				}catch(InterruptedException e) {
					System.out.println("Thread interrupted!");
				}
				if(locked)
					lockBrandService.releaseLockBrand(BRAND_CODE,  ProcessName.IMPORT);
			}while(workMutex);
		}
		
	}
	

	@Inject 
	private TaskExecutor taskExecutor;
	
	@Inject 
	private LockBrandService lockBrandService;
	
	private boolean workMutex=true;
	

	@Test
	public void generateLocksTest() throws InterruptedException 
	{
		taskExecutor.execute(new LockerThread());
		taskExecutor.execute(new LockerThread());
		taskExecutor.execute(new LockerThread());
		taskExecutor.execute(new LockerThread());
		
		Thread.sleep(100000);
		workMutex = false;
	}
		
	
}
