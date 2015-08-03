package com.ttc.ch2.search;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ttc.ch2.bl.upload.UploadStatusService;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.dao.tour.ContentRepositoryDAO;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.search.export.IndexSynchronizerService;
import com.ttc.ch2.search.export.IndexSynchronizerServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/META-INF/spring/testForSearchCtx.xml", "classpath:/META-INF/spring/daoCtx.xml", "classpath:/META-INF/spring/blCtx.xml", "classpath:/META-INF/spring/searchCtx.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class ESIndexTest {

	@Inject
	private IndexSynchronizerService indexSynchronizerService;

	@Inject
	private ContentRepositoryDAO contentRepositoryDAO;
	
	@Inject
	private UploadStatusService uploadStatusService;
	
//	@Test
	@Transactional(propagation = Propagation.NEVER)
	public void testESIndex() throws IOException, IndexSynchronizerServiceException {

		System.out.println("testESIndex() start");
		uploadStatusService.initalizeNewProccess("BV");

		indexSynchronizerService.synchronize(ProcessName.UPLOAD, "BV");

		System.out.println("testESIndex() end");
	}
	
	@Ignore
	@Test
	@Transactional(propagation = Propagation.NEVER)
	public void testTTselect() throws IOException, IndexSynchronizerServiceException {

		System.out.println("testTTselect() start");

		List<ContentRepository> contentRepositoryList = contentRepositoryDAO.getContentRepositoriesForExport("TT");

		System.out.println("testTTselect() end");
	}
}
