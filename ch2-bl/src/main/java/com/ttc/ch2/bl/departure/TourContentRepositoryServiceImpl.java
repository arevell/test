package com.ttc.ch2.bl.departure;

import java.util.Date;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.SellingCompanyType;
import com.ttc.ch2.bl.departure.common.LogOperationHelper;
import com.ttc.ch2.bl.departure.common.OperationStatus;
import com.ttc.ch2.bl.departure.common.TourDepartureData;
import com.ttc.ch2.bl.departure.common.TropicSynchronizeMessages;
import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.common.enums.SystemDirection;
import com.ttc.ch2.common.predicates.FindEntityByIdPredicate;
import com.ttc.ch2.common.predicates.FindeSellingCompanyByCodePredicate;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.SellingCompanyDAO;
import com.ttc.ch2.dao.tour.ContentRepositoryDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.departure.ImportStatus.ProcessLevel;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.ContentRepository.RepositoryStatus;
import com.ttc.ch2.domain.tour.ContentRepository.Status;
import com.ttc.ch2.domain.tour.XMLContentRepository;

@Service
@Scope("prototype")
@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
public class TourContentRepositoryServiceImpl implements TourContentRepositoryService {

	private static final Logger logger = LoggerFactory.getLogger(TourContentRepositoryServiceImpl.class);

	@Inject
	private ContentRepositoryDAO contentRepositoryDAO;

	@Inject
	private SellingCompanyDAO sellingCompanyDAO;

	@Inject
	private BrandDAO brandDAO;
	
	@Inject
	private ImportStatusService importStatusService;

	@Override
	public boolean persistData(OperationStatus opStatus, TourDepartureData tourDepartureData, String brandCode) {

		String tourCode = tourDepartureData.getTourCode();
		try {
			logger.trace("TourContentRepositoryServiceImpl:persistData-start");			
			boolean savedEntity = false;
			Date now = new Date();
			ContentRepository cr = contentRepositoryDAO.findByTourCode(tourDepartureData.getTourCode(),opStatus.getCurrentBrand());
			Brand localBrand = brandDAO.findByBrandCode(brandCode);

			if (cr == null) {				
				LogOperationHelper.logMessageForProduct(logger, opStatus,  tourDepartureData.getTourCode(),TropicSynchronizeMessages.SAVE_CR_FOR_TOUR_CODE, tourDepartureData.getTourCode());
				cr = new ContentRepository();
				cr.setBrands(Sets.newHashSet(localBrand));
				cr.setTourCode(tourDepartureData.getTourCode());
				cr.setRepositoryStatus(RepositoryStatus.TourDepartureOnly);				
				savedEntity = true;

			} else if (opStatus.getSystemDirection()==SystemDirection.TROPICS && tourDepartureData.getCheckSum().equals(cr.getTourDepartureXMLMD5())) {

				opStatus.getCrRejctedMd5ForBrand().add(cr.getId());
				opStatus.addRejectMd5();
				LogOperationHelper.logMessageForProduct(logger,opStatus,tourDepartureData.getTourCode(), TropicSynchronizeMessages.TD_CHECK_SUM_EXIST_IN_CR, tourDepartureData.getTourCode());
				logger.trace("TourContentRepositoryServiceImpl:persistData-end");
				return false;

			} else {

				cr.setRepositoryStatus(setupRepositoryStatus(cr));
				LogOperationHelper.logMessageForProduct(logger, opStatus,  tourDepartureData.getTourCode(),TropicSynchronizeMessages.UPDATE_CR_FOR_TOUR_CODE, tourDepartureData.getTourCode());
				if (!Iterables.tryFind(cr.getBrands(), new FindEntityByIdPredicate(localBrand.getId())).isPresent()) {
					cr.getBrands().add(localBrand);
				}
				savedEntity = false;
			}

			for (SellingCompanyType sellingCompany : tourDepartureData.getTourDeparturesType().getSellingCompanies().getSellingCompany()) {

				Optional<SellingCompany> result = Iterables.tryFind(cr.getSellingCompanies(), new FindeSellingCompanyByCodePredicate(sellingCompany.getCode()));

				if (!result.isPresent()) {

					SellingCompany sc = sellingCompanyDAO.findBySellingCompanyCode(sellingCompany.getCode());
					cr.getSellingCompanies().add(sc);
				}
			}

			cr.setTdFileName(tourDepartureData.getFileName());

			if (cr.getXmlContentRepository() == null || cr.getXmlContentRepository().size() != 1) {

				XMLContentRepository xmlContent = new XMLContentRepository();
				xmlContent.setContentRepository(cr);
				cr.setXmlContentRepository(Lists.newArrayList(xmlContent));
			}

			cr.getXmlContentRepository().get(0).setOldTourDepartureXML(tourDepartureData.getXmlContentV1());
			cr.getXmlContentRepository().get(0).setTourDepartureXML(tourDepartureData.getXmlContentV3());
			cr.setOldTourDepartureXMLSize(new Long(tourDepartureData.getXmlContentV1().getBytes().length));
			cr.setTourDepartureXMLSize(new Long(tourDepartureData.getXmlContentV3().getBytes().length));
			cr.setTourDepartureXMLMD5(tourDepartureData.getCheckSum());
			cr.setTourDepartureModified(now);
			cr.setStatus(Status.TourDepartureUpload);

			
			importStatusService.setupMessage("Add/Edt data:"+cr.getTourCode(),brandCode,TypeMsg.INF,ProcessLevel.IMPORT);
			contentRepositoryDAO.save(cr);
			contentRepositoryDAO.flush();
			contentRepositoryDAO.evictEntity(cr);

			if (savedEntity)
				opStatus.addInsert();
			else
				opStatus.addUpdate();

			opStatus.getCrSavedOrUpdateForBrand().add(cr.getId());

			logger.trace("TourContentRepositoryServiceImpl:persistData-end");
			return savedEntity;

		} catch (Exception e) {
			logger.error("Problem with :" + tourCode, e);
			throw new TourContentRepositoryServiceException(e);
		}
	}

	private RepositoryStatus setupRepositoryStatus(ContentRepository cr) {

		switch (cr.getRepositoryStatus()) {

		case Initial:
			return RepositoryStatus.TourDepartureOnly;
		case TIandTD:
			return RepositoryStatus.TIandTD;
		case TourInfoOnly:
			return RepositoryStatus.TIandTD;
		case TourDepartureOnly:
			return RepositoryStatus.TourDepartureOnly;
		default:
			return RepositoryStatus.TourDepartureOnly;
		}
	}
}
