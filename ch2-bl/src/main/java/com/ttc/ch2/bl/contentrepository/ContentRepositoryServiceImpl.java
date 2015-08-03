package com.ttc.ch2.bl.contentrepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.ttc.ch2.bl.departure.ImportStatusService;
import com.ttc.ch2.bl.upload.UploadStatusService;
import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.dao.SellingCompanyDAO;
import com.ttc.ch2.dao.tour.ContentRepositoryDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.common.SortCondition;
import com.ttc.ch2.domain.departure.ImportStatus.ProcessLevel;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.ContentRepository.RepositoryStatus;
import com.ttc.ch2.domain.tour.ContentRepository.Status;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class ContentRepositoryServiceImpl implements ContentRepositoryService  {
	
	private static final Logger logger = LoggerFactory.getLogger(ContentRepositoryServiceImpl.class);
	
	@Inject
	private ContentRepositoryDAO contentRepositoryDAO;
	
	@Inject
	private SellingCompanyDAO sellingCompanyDAO;
	
	@Inject
	private UploadStatusService uploadStatusService;
	
	@Inject
	private ImportStatusService importStatusService;
	

	@Override
	public int getCountentRepositoryTourInfoCount(ContentRepository filter,RepositoryStatus ... repositoryStatusTab) {
		return contentRepositoryDAO.getContentRepositoriesCount(null, filter, Lists.newArrayList(repositoryStatusTab));
	}
	
	@Override
	public int getCountentRepositoryTourDepartureCount(ContentRepository filter, RepositoryStatus... repositoryStatusTab) {
		
		return contentRepositoryDAO.getContentRepositoriesCount(null, filter, Lists.newArrayList(repositoryStatusTab));	
	}
	
	@Override
	public List<ContentRepository> getContentRepositoriesList(QueryCondition conditions, ContentRepository filter,RepositoryStatus... repositoryStatusTab) {	
		List<ContentRepository> list=contentRepositoryDAO.getContentRepositoriesList(conditions, filter,Lists.newArrayList(repositoryStatusTab));		
//		// remove lazy initialize on brands
//		for (ContentRepository contentRepository : list) {
//			for (Brand brand : contentRepository.getBrands()) {
//				brand.getId();
//			}			
//		}		
		return list;
	}
			
	@Override
	public List<ContentRepository> getContentRepositoriesList(List<SortCondition> sortConditionList, ContentRepository filter){	
		QueryCondition qc = new QueryCondition(-1, -1);
		qc.setSortConditions(sortConditionList);
		return contentRepositoryDAO.getContentRepositoriesList(qc, filter);
	}

	public List<Long> getContentRepositoryIDsList(List<String> tourCodes, String brandCode) {

		List<Long> contentRepositoryIDsList = new ArrayList<Long>();

		List<ContentRepository> contentRepositoriesList = findByTourCodes(tourCodes, brandCode);

		if (contentRepositoriesList != null && !contentRepositoriesList.isEmpty()) {

			for (ContentRepository contentRepository : contentRepositoriesList) {
				contentRepositoryIDsList.add(contentRepository.getId());
			}
		}

		return contentRepositoryIDsList;
	}

	public ContentRepository findByTourCode(String tourCode,String brandCode)
	{
		ContentRepository cr=contentRepositoryDAO.findByTourCode(tourCode,brandCode);		
		if(cr!=null)// remove lazy initialize
			for (Brand brand : cr.getBrands()) {
				brand.getId();
			}
		return cr;
	}

	public List<ContentRepository> findByTourCodes(List<String> tourCodes, String brandCode) {
		return contentRepositoryDAO.findByTourCodes(tourCodes, brandCode);
	}

	@Override
	public ContentRepository getContentRepositoryForTourcodeAndSC(String tourCode, String sellingCompanyCode) throws ContentRepositoryServiceException {
		ContentRepository cr;
		try {
			ContentRepository filter = new ContentRepository();
			SellingCompany selComp = sellingCompanyDAO.findBySellingCompanyCode(sellingCompanyCode);
			filter.setSellingCompanies(Sets.newHashSet(selComp));
			filter.setTourCode(tourCode);
			QueryCondition qc = new QueryCondition(-1, -1);
			qc.setEqualsPrefered(true);
			filter.setRepositoryStatus(ContentRepository.RepositoryStatus.TIandTD);
			
			List<ContentRepository> crl = contentRepositoryDAO.getContentRepositoriesList(qc, filter);
			if(crl.size() == 0)
				return null;
			cr = crl.get(0);
			cr.getXmlContentRepository().get(0).getTourInfoXML();
			cr.getXmlContentRepository().get(0).getTourDepartureXML();
			
		}catch(Exception e) {
			throw new ContentRepositoryServiceException(e);
		}
        return cr;
	}

	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public void clearTourInfo(Set<Long> crToSave,Brand b)
	{					
			List<Long> listCrToClear=contentRepositoryDAO.getContentRepositoriesIdsToClear(crToSave,b,RepositoryStatus.TIandTD,RepositoryStatus.TourInfoOnly);
			for (Long id: listCrToClear) {
				ContentRepository contentRepository=contentRepositoryDAO.find(id);
//				if(contentRepository.getRepositoryStatus()==RepositoryStatus.Empty || contentRepository.getRepositoryStatus()==RepositoryStatus.TourDepartureOnly){
//					// this contentRepository is clear on the tour info side
//					continue;
//				}					
						
				// silent add snapshot
				try{
					uploadStatusService.proccesingDescription(b.getCode(), "Clear data:"+contentRepository.getTourCode(),false);
				}
				catch(Exception e)
				{
					logger.error("",e);
				}
				
				if(contentRepository.getXmlContentRepository() != null && contentRepository.getXmlContentRepository().size() == 1) {
					contentRepository.getXmlContentRepository().get(0).setOldTourInfoXML(null);
					contentRepository.getXmlContentRepository().get(0).setTourInfoXML(null);
				}
				contentRepository.setOldTourInfoXMLSize(null);
				contentRepository.setTourInfoXMLMD5(null);
				contentRepository.setTourInfoXMLSize(null);
				contentRepository.setTourInfoModified(null);				
				contentRepository.setTiFileName(null);
				contentRepository.setIsTourInfoAvailable(false);
				
				RepositoryStatus calRepositoryStatus;
				if(contentRepository.getRepositoryStatus()==RepositoryStatus.TIandTD || contentRepository.getRepositoryStatus()==RepositoryStatus.TourDepartureOnly) 
					calRepositoryStatus=RepositoryStatus.TourDepartureOnly;
				else 
					calRepositoryStatus=RepositoryStatus.Empty;
				
				contentRepository.setStatus(Status.TourInfoUpload);								
				contentRepository.setRepositoryStatus(calRepositoryStatus);
				contentRepositoryDAO.save(contentRepository);
				contentRepositoryDAO.flush();
//				contentRepositoryDAO.evictEntity(contentRepository);
				contentRepositoryDAO.clearSession();
			}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public void clearTourDeparture(Set<Long> crToSave, Brand b) {

		logger.trace("ContentRepositoryServiceImpl:clearTourDeparture-start");
		List<Long> listCrToClear=contentRepositoryDAO.getContentRepositoriesIdsToClear(crToSave,b,RepositoryStatus.TIandTD,RepositoryStatus.TourDepartureOnly);
		for (Long id: listCrToClear) {
			ContentRepository contentRepository=contentRepositoryDAO.find(id);

		
//		List<ContentRepository> listCrToClear=contentRepositoryDAO.getContentRepositoriesElementsToClear(crToSave,b,RepositoryStatus.TIandTD,RepositoryStatus.TourDepartureOnly);	
//		for (ContentRepository contentRepository : listCrToClear) {
			
//			if(contentRepository.getRepositoryStatus()==RepositoryStatus.Empty || contentRepository.getRepositoryStatus()==RepositoryStatus.TourInfoOnly){
//				// this contentRepository is clear on the tour departure side
//				continue;
//			}
			
			logger.trace("Statr clear data:"+contentRepository.getTourCode()+" and brand code:"+b.getCode());
			
			// silent add snapshot
			try{
			importStatusService.setupMessage( "Clear data:"+contentRepository.getTourCode(), b.getCode(),TypeMsg.INF,ProcessLevel.DBLEVEL);
			}catch(Exception e)
			{
				logger.error("",e);
			}
			
			if(contentRepository.getXmlContentRepository() != null && contentRepository.getXmlContentRepository().size() == 1) {
				contentRepository.getXmlContentRepository().get(0).setOldTourDepartureXML(null);
				contentRepository.getXmlContentRepository().get(0).setTourDepartureXML(null);
			}
			contentRepository.setOldTourDepartureXMLSize(null);
			contentRepository.setTourDepartureXMLMD5(null);
			contentRepository.setTourDepartureXMLSize(null);
			contentRepository.setTourDepartureModified(null);				
			contentRepository.setTdFileName(null);
			contentRepository.setIsTourDepartureAvailable(false);
			contentRepository.setStatus(Status.TourDepartureUpload);

			RepositoryStatus calRepositoryStatus;
			if(contentRepository.getRepositoryStatus()==RepositoryStatus.TIandTD || contentRepository.getRepositoryStatus()==RepositoryStatus.TourInfoOnly) 
				calRepositoryStatus=RepositoryStatus.TourInfoOnly;
			else 
				calRepositoryStatus=RepositoryStatus.Empty;

			contentRepository.setStatus(Status.TourDepartureUpload);		
			contentRepository.setRepositoryStatus(calRepositoryStatus);
			contentRepositoryDAO.save(contentRepository);
			contentRepositoryDAO.flush();
			contentRepositoryDAO.clearSession();
			logger.trace("End clear data tour code:"+contentRepository.getTourCode()+" and brand code:"+b.getCode());
		}
		
		logger.trace("ContentRepositoryServiceImpl:clearTourDeparture-end");
	}
	
	public int deleteEmptyContentRepository(String brandCode)
	{
		return contentRepositoryDAO.deleteEmptyContentRepository(brandCode);
	}

	@Override
	public ContentRepository findByTourCodeWithXML(String tourCode,String brandCode) {
		ContentRepository cr = findByTourCode(tourCode,brandCode);
		if(cr!=null)
			cr.getXmlContentRepository().get(0);
		return cr;
	}
}
