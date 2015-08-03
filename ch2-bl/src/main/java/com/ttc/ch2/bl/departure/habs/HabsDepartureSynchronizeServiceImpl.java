package com.ttc.ch2.bl.departure.habs;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.common.base.Joiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.TourDeparturesType;
import com.ttc.ch2.bl.constraints.ConstraintService;
import com.ttc.ch2.bl.constraints.ConstraintServiceException;
import com.ttc.ch2.bl.contentrepository.ContentRepositoryService;
import com.ttc.ch2.bl.departure.ImportStatusService;
import com.ttc.ch2.bl.departure.TourContentRepositoryService;
import com.ttc.ch2.bl.departure.TropicSynchronizeServiceException;
import com.ttc.ch2.bl.departure.common.LogOperationHelper;
import com.ttc.ch2.bl.departure.common.OperationStatus;
import com.ttc.ch2.bl.departure.common.TourDepartureData;
import com.ttc.ch2.bl.departure.common.TropicSynchronizeMessages;
import com.ttc.ch2.bl.departure.common.tourdeparturegen.TourDepartureDataConsumer;
import com.ttc.ch2.bl.departure.habs.tourdeparturegen.HabsITropicsV1TourDepartureMapper;
import com.ttc.ch2.bl.departure.habs.tourdeparturegen.HabsITropicsV3TourDepartureMapper;
import com.ttc.ch2.bl.departure.habs.tourdeparturegen.HabsTourDepartureAndSC;
import com.ttc.ch2.bl.filecollect.FileCollectService;
import com.ttc.ch2.bl.filecollect.FileCollectServiceException;
import com.ttc.ch2.bl.filecollect.FileCollectVO;
import com.ttc.ch2.bl.report.ReconciliationReportService;
import com.ttc.ch2.bl.report.ReconciliationReportServiceException;
import com.ttc.ch2.common.DateHelper;
import com.ttc.ch2.common.DateHelper.CalculateTimePattern;
import com.ttc.ch2.common.JobCoordinator;
import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.common.enums.SystemDirection;
import com.ttc.ch2.common.predicates.Ch2ConfigValueFinder;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.Ch2ConfigValueDAO;
import com.ttc.ch2.dao.tour.ContentRepositoryDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.Ch2ConfigValue;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.departure.ImportStatus.ProcessLevel;
import com.ttc.ch2.domain.jobs.QuartzJob.JobStatus;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.search.export.IndexSynchronizerService;
import com.ttc.ch2.search.export.IndexSynchronizerServiceException;
import com.ttc.ch2.search.export.IndexSynchronizerVO;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.MarketVariationDepartureInfo;
import com.wsout.habs.itropicsbuildws.WsDeparturesVO;
import com.wsout.habs.itropicsbuildws.WsTourBrandCompanyInfoVO;
import com.wsout.habs.itropicsbuildws.WsTourBrandInfoVO;
import com.wsout.habs.itropicsbuildws.WsTourOfBrandVO;
import com.wsout.habs.itropicsbuildws.WsToursOfBrandsVO;

import es.usc.citius.common.parallel.Parallel;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsOperatingProductVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsProductVO;





@Service("HabsDepartureSynchronizeServiceImpl")
@Scope("prototype")
@Transactional(readOnly = false, propagation = Propagation.NEVER)
public class HabsDepartureSynchronizeServiceImpl implements HabsDepartureSynchronizeService {

	private static final Logger logger = LoggerFactory.getLogger(HabsDepartureSynchronizeServiceImpl.class);

	private static final CalculateTimePattern defaultTimePattern = CalculateTimePattern.HMS;

	@Value("${elastic.search.indexing}")
	private String elasticSearchIndexing;

	@Inject
	private TourDepartureDataConsumer tourDepartureDataConsumer;

	@Inject
	private HabsTourDepartureService tourDepartureService;

	@Inject
	private BrandDAO brandDAO;

	@Inject
	private FileCollectService fileCollectService;

	@Inject
	private ContentRepositoryService contentRepositoryService;	
	
	@Inject
	private ContentRepositoryDAO contentRepositoryDAO;
	
	@Inject
	private TourContentRepositoryService tourContentRepositoryService;

	@Inject
	private ApplicationContext appCtx;

	@Inject
	@Named("HabsReconciliationReportServiceImpl")
	private ReconciliationReportService reconciliationReportService;

	@Inject
	private ImportStatusService importStatusService;

	@Inject
	private IndexSynchronizerService indexSynchronizerService;
	
	@Inject
	private Ch2ConfigValueDAO ch2ConfigValueDAO;
	
	@Inject
	private ConstraintService constraintService;

	public OperationStatus departureSynchronize(OperationStatus opStatus) throws TropicSynchronizeServiceException {

		logger.trace("TropicDepartureSynchronizeServiceImpl:synchronize-start");

		try {

			List<Brand> brands = getBrands(opStatus);

			if (brands.isEmpty()) {

				LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.NO_BRANDS);

			} else {

				for (Brand brand : brands) {

					Date startDateBrand = new Date();

					// TODO when multithread will be ready use creator for opStatus
					OperationStatus opStatusPerBrand = opStatus;

					try {

						opStatusPerBrand.setCurrentBrand(brand.getCode());
						opStatusPerBrand.setCrRejctedMd5ForBrand(new TreeSet<Long>());
						opStatusPerBrand.setCrSavedOrUpdateForBrand(new TreeSet<Long>());
						opStatusPerBrand.setCrUnsavedForBrand(new TreeSet<Long>());
						
						List<Ch2ConfigValue> cfgs=ch2ConfigValueDAO.findAll();
						Ch2ConfigValue value=Iterables.find(cfgs, new Ch2ConfigValueFinder(Ch2ConfigValue.PropName.SystemDirection));
						opStatusPerBrand.setSystemDirection(SystemDirection.valueOf(value.getPropertyValue()));
						

						LogOperationHelper.logDefaultMessage(logger, opStatusPerBrand, TropicSynchronizeMessages.OPERATION_FOR_BRAND_START, brand.getBrandName(), brand.getCode());

						// To make transaction annotation working properly, we cannot call the method directly. We have to do it that way.
						try{
						appCtx.getBean(HabsDepartureSynchronizeService.class).operationForBrand(opStatusPerBrand, brand); // new tx					
						}
						catch(Exception e){
							// delete all rollbacked element from history 
							opStatus.getTourDepartureHistory().getContentRepositories().clear();
							opStatusPerBrand.getCrRejctedMd5ForBrand().clear();
							opStatusPerBrand.getCrSavedOrUpdateForBrand().clear();
							opStatusPerBrand.getCrUnsavedForBrand().clear();
							throw e;
						}
						finally{
							try{
								opStatus.getTropicsComunicationWatch().stop();
								LogOperationHelper.logDefaultMessage(logger, opStatusPerBrand, TropicSynchronizeMessages.COMMUNICATION_TIME, DateHelper.calculateTime(opStatus.getTropicsComunicationWatch().getTime(), defaultTimePattern));
							}catch(Exception e){
								logger.error("",e);
							}
						}
					
						// elasticsearch indexing	
						 IndexSynchronizerVO indexSynchronizerVO = executeElasticSearchIndexing(opStatus, brand.getCode());
						
						// outgoing archive operation
						FileCollectVO fileCollectVO = executeOutgoingArchives(opStatus, brand.getCode());							
						
						generateReconciliationReport(opStatus, brand.getCode(), indexSynchronizerVO, fileCollectVO);
						
							EnumMap<JobCoordinator.Params,Object> params=new EnumMap<>(JobCoordinator.Params.class);
							params.put(JobCoordinator.Params.FILE_COLLECT_VO, fileCollectVO);
							params.put(JobCoordinator.Params.INDEX_VO, indexSynchronizerVO);
							opStatus.getJobCoordinator().setup(params);
						
						
						// clear data for next brand
						opStatusPerBrand.getCrRejctedMd5ForBrand().clear();
					} finally {

						Date endDate = new Date();

						LogOperationHelper.logDefaultMessage(logger, opStatusPerBrand, TropicSynchronizeMessages.OPERATION_FOR_BRAND_END, brand.getBrandName(), brand.getCode(), DateHelper.calculateTime(startDateBrand, endDate, defaultTimePattern));

						String textDuration = DateHelper.calculateTime(startDateBrand, endDate, defaultTimePattern);
						String textStartTime = DateHelper.dateToString(startDateBrand);
						String textEndTime = DateHelper.dateToString(startDateBrand);

						LogOperationHelper.logMessageForBrand(logger, opStatusPerBrand, TropicSynchronizeMessages.BRAND_STATUS, brand.getCode(), textStartTime, textEndTime, textDuration);
						setupMessageSilent(opStatusPerBrand,  TypeMsg.INF, TropicSynchronizeMessages.BRAND_STATUS.createMessage(brand.getCode(), textStartTime, textEndTime, textDuration),ProcessLevel.FINALIZE);
					}
				}
			}
		} catch (TropicSynchronizeServiceException e) {

			e.setOpStatus(opStatus);
			throw e;

		} catch (HabsTourDepartureServiceException e) {

			TropicSynchronizeServiceException ex = new TropicSynchronizeServiceException(e);
			ex.setOpStatus(opStatus);
			throw ex;

		} catch (Exception e) {

			LogOperationHelper.logDefaultException(logger, opStatus, new Date(), e, TropicSynchronizeMessages.UNEXPECTED_EXCEPTION);

			TropicSynchronizeServiceException ex = new TropicSynchronizeServiceException(e);
			ex.setOpStatus(opStatus);
			throw ex;
		}

		logger.trace("TropicDepartureSynchronizeServiceImpl:synchronize-end");

		return opStatus;
	}

	private void generateReconciliationReport(OperationStatus opStatus, String brandCode, IndexSynchronizerVO indexSynchronizerVO, FileCollectVO fileCollectVO) throws ReconciliationReportServiceException {
		if( (indexSynchronizerVO != null && indexSynchronizerVO.hasErrors()) || (fileCollectVO!=null && fileCollectVO.hasErrors())){
			Date start=new Date();
			try{							
			LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.RECONCILIATION_GEN, brandCode);
			setupMessageSilent(opStatus,  TypeMsg.INF, TropicSynchronizeMessages.RECONCILIATION_GEN.createMessage(brandCode),ProcessLevel.RECONCILIATION);
			reconciliationReportService.createReconciliationReport(ProcessName.IMPORT, brandCode, indexSynchronizerVO, fileCollectVO);
			}
			finally{
				LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.RECONCILIATION_GEN_END, brandCode,DateHelper.calculateTime(start, new Date(), defaultTimePattern));	
			}
		}
	}
	
	private FileCollectVO executeOutgoingArchives(OperationStatus opStatus, String brandCode){
		Date startDateOutgoingArchives = new Date();
		LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.OUTGOING_ARCHIVE_FOR_BRAND, brandCode);

		setupMessageSilent(opStatus,  TypeMsg.INF, TropicSynchronizeMessages.OUTGOING_ARCHIVE_FOR_BRAND.createMessage(brandCode),ProcessLevel.OUTGOING_ARCHIVE);

		final String brandCodeLocal = brandCode;

		FileCollectVO fileCollectVO = new FileCollectVO();

		try {
				fileCollectService.createLatestVersion(ProcessName.IMPORT, brandCodeLocal,fileCollectVO);					
		} catch (FileCollectServiceException e) {
			LogOperationHelper.logDefaultException(logger, opStatus, new Date(), e, TropicSynchronizeMessages.OUTGOING_ARCHIVE_EXCEPTION, opStatus.getCurrentBrand());
		}

		LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.OUTGOING_ARCHIVE_FOR_BRAND_END, brandCode, DateHelper.calculateTime(startDateOutgoingArchives, new Date(), defaultTimePattern));
		
		return fileCollectVO;
	}
	
	private IndexSynchronizerVO executeElasticSearchIndexing(OperationStatus opStatus, String brandCode){
		IndexSynchronizerVO result=null;		
		if (BooleanUtils.toBoolean(elasticSearchIndexing)) {
			Date indexStart=new Date();
			IndexSynchronizerVO indexSynchronizerVO=new IndexSynchronizerVO(false);
				try {
					setupMessageSilent(opStatus,  TypeMsg.INF, TropicSynchronizeMessages.INDEXING_START.createMessage(brandCode),ProcessLevel.INDEXING);												
					LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.INDEXING_START, brandCode);				
					List<Long> ids = new ArrayList<Long>(opStatus.getCrSavedOrUpdateForBrand());
					indexSynchronizerService.synchronize(ProcessName.IMPORT, brandCode, ids,indexSynchronizerVO);					
					if(indexSynchronizerVO.hasErrors()) {
							throw new IndexSynchronizerServiceException(IndexSynchronizerService.ES_EXCEPTION_MSG); 
						}										
					if(!ids.isEmpty()){
						LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.SHORT_INDEX_SUCCESS,ids.size());
					}
					else {
						LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.SHORT_INDEX_IGNORED);
					}
				} catch (IndexSynchronizerServiceException e) {
					LogOperationHelper.logDefaultException(logger, opStatus, new Date(), e, TropicSynchronizeMessages.UNEXPECTED_EXCEPTION);
				} finally {
					opStatus.getIndexMap().put(new Date(), indexSynchronizerVO);
						try{
						result=indexSynchronizerService.getStateOfIndex(opStatus.getIndexMap(), brandCode);
						}
						catch(Exception e){
							LogOperationHelper.logDefaultException(logger, opStatus, new Date(), e, TropicSynchronizeMessages.UNEXPECTED_EXCEPTION);	
						}
					LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.INDEXING_END, brandCode, DateHelper.calculateTime(indexStart, new Date(), defaultTimePattern));
				}
		} else {									
			LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.INDEXING_TOURN_OFF);
		}		
		return result;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void operationForBrand(OperationStatus opStatus, Brand brand) throws HabsTourDepartureServiceException, TropicSynchronizeServiceException {

		logger.trace("TropicDepartureSynchronizeServiceImpl:operationForBrand-start");

		String brandCode = brand.getCode() != null ? brand.getCode() : StringUtils.EMPTY;

		List<String> brandsCodesList = new ArrayList<String>();
		List<String> brandSellingCompanies = new ArrayList<String>();
		List<String> tourCodesUnsaved = new ArrayList<String>();

		for (Brand brandItem : brandDAO.findAll()) {
			brandsCodesList.add(brandItem.getCode());
		}

		for (SellingCompany sellingCompany : brand.getSellingCompanies()) {
			brandSellingCompanies.add(sellingCompany.getCode());
		}
		
		opStatus.getTropicsComunicationWatch().start();
		WsToursOfBrandsVO tours = invokeServiceGetToursOfBrands(opStatus, brandsCodesList);
		opStatus.getTropicsComunicationWatch().suspend();
		int tourIndex = 0;	
		if (tours != null && tours.getTour() != null && tours.getTour().size() > 0) {
			Collection<WsTourOfBrandVO> toursList = Collections2.filter(tours.getTour(), new BrandFilter(brandCode));			
			if (toursList != null && toursList.size() > 0) {			
				for (WsTourOfBrandVO tour : toursList) {				
					tourIndex++;
					Date startDateProduct = new Date();

					LogOperationHelper.logMessageForProduct(logger, opStatus, tour.getTourCode(), TropicSynchronizeMessages.OPERATION_FOR_PRODUCT_START, tour.getTourCode(), tourIndex, toursList.size());

					setupMessageSilent(opStatus, TypeMsg.INF, TropicSynchronizeMessages.OPERATION_FOR_PRODUCT_START.createMessage(tour.getTourCode(), tourIndex, toursList.size()),ProcessLevel.IMPORT);

					try {
						operationForTour(opStatus, brandCode, brandSellingCompanies, tour);
					} catch (HabsTourDepartureServiceException e) {
						tourCodesUnsaved.add(tour.getTourCode());
						LogOperationHelper.logExceptionForProduct(logger, tour.getTourCode(), opStatus, e, TropicSynchronizeMessages.OPERATION_FOR_PRODUCT_IMPORT_WARNING, tour.getTourCode(), tourIndex, toursList.size());
					} catch (TropicSynchronizeServiceException e) {

						if (TropicSynchronizeMessages.CANCEL_PROCESS.getMessage().equals(e.getMessage()) || TropicSynchronizeMessages.INACTIVE_PROCESS.getMessage().equals(e.getMessage())) {
							throw e;
						} else {
							tourCodesUnsaved.add(tour.getTourCode());
							LogOperationHelper.logExceptionForProduct(logger, tour.getTourCode(), opStatus, e, TropicSynchronizeMessages.OPERATION_FOR_PRODUCT_MAPPING_WARNING, tour.getTourCode(), tourIndex, toursList.size());
						}
					}

					LogOperationHelper.logMessageForProduct(logger, opStatus, tour.getTourCode(), TropicSynchronizeMessages.OPERATION_FOR_PRODUCT_END, tour.getTourCode(), tourIndex, toursList.size(),DateHelper.calculateTime(startDateProduct, new Date(), defaultTimePattern));
				}
			}
		}

		elasticSearchOperation(opStatus, brand);

		if(tourIndex>0){
			tourDepartureClearOperation(opStatus, brand, tourCodesUnsaved);
		}
			
		logToursWithoutBrands(opStatus);
		
		logger.trace("TropicDepartureSynchronizeServiceImpl:operationForBrand-end");
	}
	
	private void operationForTour(OperationStatus opStatus, String brandCode, final List<String> brandSellingCompanies, WsTourOfBrandVO tour) throws HabsTourDepartureServiceException, TropicSynchronizeServiceException {
		logger.trace("TropicDepartureSynchronizeServiceImpl:operationForTour-start");

		WsTourBrandInfoVO brandInfo = null;
		// TO - DO analized this part of code with part of code  in class BrandFilter probably it that same code
		for (WsTourBrandInfoVO brandItem : tour.getBrandInfo()) {

			if (brandCode.equals(brandItem.getBrandCode())) {

				brandInfo = brandItem;
				break;
			}
		}

		if (brandInfo == null || brandInfo.getCompanyInfo() == null) {

			logger.trace("TropicDepartureSynchronizeServiceImpl:operationForTour-end");
			return;
		}
		
//		Set<String> acceptedTour=Sets.newHashSet("EXSDA1510");
//		if(acceptedTour.contains(tour.getTourCode())==false)
//			return;
				
		InvalidBrandSellingCompaniesRemover scRemover=new InvalidBrandSellingCompaniesRemover(brandSellingCompanies);			
		List<WsTourBrandCompanyInfoVO> companyInfoList=Lists.newArrayList(Iterables.filter(brandInfo.getCompanyInfo(), scRemover));
		
		String calculatedCheckSum=generateCheckSum(companyInfoList,opStatus);	
		if(checkSum(tour.getTourCode(), brandCode, calculatedCheckSum, opStatus)==false){									
			return;
		}
				
		opStatus.getTropicsComunicationWatch().resume();
		Collection<TourDataVO> tourDataList = Parallel.ForEach(companyInfoList,new ParrallelFuncion(tour.getTourCode()));
//		Collection<TourDataVO> tourDataList = Parallel.ForEach(companyInfoList,new ParrallelFuncion(brandCode,  tour.getTourCode(), brandSellingCompanies,opStatus.getSystemDirection()));
		opStatus.getTropicsComunicationWatch().suspend();
		
		HabsTourDepartureAndSC tourDepartureAndSCParent = null;
		for (TourDataVO tourData : Lists.newArrayList(tourDataList)) {
			
//			if(tourData.isHasCheckSum()==false){
//				opStatus.addTourWithOutCheckSum(checkSumRemover.getMd5NotExitCount());
//			}
//			
//			if(tourData.getRejectedCrId()!=null){
//				opStatus.getCrRejctedMd5ForBrand().add(tourData.getRejectedCrId());
//				opStatus.addRejectMd5();
//				LogOperationHelper.logMessageForProduct(logger,opStatus,tour.getTourCode(), TropicSynchronizeMessages.TD_CHECK_SUM_EXIST_IN_CR, tour.getTourCode());
//				continue;
//			}			
			if (tourData.isEmptyObject()) {
				continue;
			}
			
			if (tourData.getException() != null) {
				throw tourData.getException();
			}
			
			WsTourBrandCompanyInfoVO companyInfo = tourData.getCompanyInfoVO();
			WsDeparturesVO departures = tourData.getDeparturesVO();


			if (departures == null || departures.getDeparture() == null || departures.getDeparture().size() == 0) {

				opStatus.addRejectedCountDeparture();

				LogOperationHelper.logMessageForSellingCompany(logger, companyInfo.getCompanyCode(), opStatus, TropicSynchronizeMessages.REJECT_DATA_FOR_SELLING_COMPANY_PRODUCT_CODE, tour.getTourCode(), companyInfo.getCompanyCode(), "Product does not have departures");
				LogOperationHelper.logMessageForProduct(logger,opStatus,tour.getTourCode(), TropicSynchronizeMessages.REJECT_DATA_FOR_SELLING_COMPANY_PRODUCT_CODE, tour.getTourCode(),companyInfo.getCompanyCode(), "Product does not have departures");

			} else {

				// TODO : instead of creating and transferring VO objects this should be passed as fields (requires modification in TourDepartureAndSC)
				WsOperatingProductVO operatingProduct = new WsOperatingProductVO();
				operatingProduct.setCode(tour.getOperatingProductCode());

				WsProductVO product = new WsProductVO();
				product.setCode(tour.getTourCode());
				product.setBrochureCode(companyInfo.getBrochureCode());
				product.setOperatingProduct(operatingProduct);
				product.setOnlineBookable(tour.isOnlineBookable());

				SellingCompany sellingCompany = new SellingCompany();
				sellingCompany.setCode(companyInfo.getCompanyCode());

				if (tourDepartureAndSCParent == null) {
					tourDepartureAndSCParent = createHabsTourDepartureAndSC(calculatedCheckSum,product,departures,sellingCompany,companyInfo);
				} else {
					HabsTourDepartureAndSC tourDepartureAndSCChild = createHabsTourDepartureAndSC(calculatedCheckSum,product,departures,sellingCompany,companyInfo);					
					tourDepartureAndSCParent.getOtherReferences().add(tourDepartureAndSCChild);
				}
				opStatus.addImport();
				LogOperationHelper.logMessageForSellingCompany(logger, companyInfo.getCompanyCode(), opStatus, TropicSynchronizeMessages.IMPORT_DATA_FOR_SELLING_COMPANY_PRODUCT_CODE,  tour.getTourCode(), companyInfo.getCompanyCode());
			}

			checkCancelOperation(opStatus,"TropicDepartureSynchronizeServiceImpl:operationForTour-> mapping to TourDepartureAndSC");
		}

		if (tourDepartureAndSCParent != null) {

			final TourDepartureData tourDepartureData = genrateTourDepartureXmlContent(opStatus, tourDepartureAndSCParent);
			final String brandCodeLocal = brandCode;
			final OperationStatus opStatusLocal = opStatus;

			tourContentRepositoryService.persistData(opStatusLocal, tourDepartureData, brandCodeLocal);
		}

		logger.trace("TropicDepartureSynchronizeServiceImpl:operationForTour-end");
	}

	
	private void tourDepartureClearOperation(OperationStatus opStatus,Brand brand,List<String> tourCodesUnsaved){
		
		if (!tourCodesUnsaved.isEmpty()) {
			List<Long> contentRepositoryIDsList = contentRepositoryService.getContentRepositoryIDsList(tourCodesUnsaved, brand.getCode());
			opStatus.getCrUnsavedForBrand().addAll(contentRepositoryIDsList);
		}
		
		Date startClean=new Date();
		LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.CLEAN_CR_START,brand.getCode());
		contentRepositoryService.clearTourDeparture(opStatus.getCrNotClearedPerBrand(), brand);
		LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.CLEAN_CR_END,brand.getCode(),DateHelper.calculateTime(startClean, new Date(), defaultTimePattern));
		
	}
	
	private void elasticSearchOperation(OperationStatus opStatus,Brand brand){

		if (BooleanUtils.toBoolean(elasticSearchIndexing)) {
			LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.CLEANING_ES_START);
			IndexSynchronizerVO indexSynchronizerVO=new IndexSynchronizerVO(false);
			try {
				indexSynchronizerService.synchronize_delete(ProcessName.IMPORT, brand, opStatus.getCrNotClearedPerBrand(),indexSynchronizerVO);
				if(indexSynchronizerVO.hasErrors()) {
						throw new IndexSynchronizerServiceException(IndexSynchronizerService.ES_EXCEPTION_MSG);
				}				
			}catch(IndexSynchronizerServiceException e) {
				LogOperationHelper.logDefaultException(logger, opStatus, new Date(), e, TropicSynchronizeMessages.UNEXPECTED_EXCEPTION);
			}
			finally{
				opStatus.getIndexMap().put(new Date(), indexSynchronizerVO);
			}
			LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.CLEANING_ES_STOP);
		}
	}

	private void logToursWithoutBrands(OperationStatus opStatus){	
		try{
			Set<String> tourCodes=constraintService.getToursWithoutBrand();
			if(tourCodes.isEmpty()==false)
				LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.TOURS_WITHOUT_BRAND,Joiner.on(",").join(tourCodes));
			
		}catch(ConstraintServiceException e){
			logger.error("",e);
		}
	}
	
	
	
	private List<Brand> getBrands(OperationStatus opStatus) throws Exception {

		logger.trace("TropicDepartureSynchronizeServiceImpl:getAllBrands-start");

		List<Brand> brands = Lists.newArrayList();

		try {

			brands = StringUtils.isNotBlank(opStatus.getCurrentBrand()) ? Arrays.asList(brandDAO.findByBrandCode(opStatus.getCurrentBrand())) : brandDAO.findAll();

		} catch (Exception e) {

			LogOperationHelper.logDefaultException(logger, opStatus, e, TropicSynchronizeMessages.READ_BRANDS_FROM_DB_EXCEPTION);
			throw e;
		}

		logger.trace("TropicDepartureSynchronizeServiceImpl:getAllBrands-end");

		return brands;
	}

	private WsToursOfBrandsVO invokeServiceGetToursOfBrands(OperationStatus opStatus, List<String> brandsCodesList) throws HabsTourDepartureServiceException {

		logger.trace("TropicDepartureSynchronizeServiceImpl:getToursOfBrands-start");

		WsToursOfBrandsVO tours = null;

		try {

			tours = tourDepartureService.getToursOfBrands(brandsCodesList);

		} catch (HabsTourDepartureServiceException e) {

			LogOperationHelper.logExceptionForBrand(logger, opStatus, e, TropicSynchronizeMessages.GET_TOURS_FROM_TROPIC_EXCEPTION, opStatus.getCurrentBrand(),e.getMessage());
			throw e;
		}

		logger.trace("TropicDepartureSynchronizeServiceImpl:getToursOfBrands-end");
		return tours;
	}

	private WsDeparturesVO invokeServiceGetDatesAndRates(OperationStatus opStatus, String tourCode, String sellingCompanyCode, String apiKey) throws HabsTourDepartureServiceException {

		logger.trace("TropicDepartureSynchronizeServiceImpl:invokeServiceGetDatesAndRates-start");

		WsDeparturesVO departures = tourDepartureService.getTourDatesAndRates(tourCode, sellingCompanyCode, apiKey);

		logger.trace("TropicDepartureSynchronizeServiceImpl:invokeServiceGetDatesAndRates-end");

		return departures;
	}

	private TourDepartureData genrateTourDepartureXmlContent(OperationStatus opStatus, HabsTourDepartureAndSC tourDepartureAndSC) throws TropicSynchronizeServiceException {

		logger.trace("TropicDepartureSynchronizeServiceImpl:genrateTourDepartureXmlContent-start");

		TourDepartureData tourDepartureData = new TourDepartureData(null, null, null, null);

		Date startDate = new Date();
		String tourCode = tourDepartureAndSC.getProduct().getCode();
		String tourFileName = tourCode + ".xml";

		try {

			LogOperationHelper.logMessageForProduct(logger, opStatus,tourCode, TropicSynchronizeMessages.START_GENERATING_FILES_FOR_TOUR, tourCode);

			MarketVariationDepartureInfo tourDeparturesV1 = HabsITropicsV1TourDepartureMapper.map(opStatus, tourDepartureAndSC);


			String xmlContentV1 = tourDepartureDataConsumer.processTourDepartureV1(tourDeparturesV1, opStatus);
			if (xmlContentV1 == null) {
				
				if(tourDepartureAndSC.getSellingCompany()!=null && StringUtils.isNotBlank(tourDepartureAndSC.getSellingCompany().getCode())){
					LogOperationHelper.logMessageForSellingCompany(logger, tourDepartureAndSC.getSellingCompany().getCode(), opStatus, TropicSynchronizeMessages.REJECT_DATA_FOR_SELLING_COMPANY_PRODUCT_CODE, tourCode, tourDepartureAndSC.getSellingCompany().getCode(), "Product has xml validation error for TourDeparture version 1");
					LogOperationHelper.logMessageForProduct(logger,  opStatus,tourCode, TropicSynchronizeMessages.REJECT_DATA_FOR_SELLING_COMPANY_PRODUCT_CODE, tourCode, tourDepartureAndSC.getSellingCompany().getCode(), "Product has xml validation error for TourDeparture version 1");					
					opStatus.addRejectedInvalidSchema();
				}
				LogOperationHelper.logMessageForProduct(logger, opStatus, tourCode, TropicSynchronizeMessages.SCHEMA_VALIDATION_WARNING, tourCode, tourFileName);
				throw new TropicSynchronizeServiceException(TropicSynchronizeMessages.getMessage(TropicSynchronizeMessages.SCHEMA_VALIDATION_WARNING, tourCode, tourFileName));
			}

			
			TourDeparturesType tourDeparturesV3 = HabsITropicsV3TourDepartureMapper.map(opStatus, tourDepartureAndSC);

			String xmlContentV3 = tourDepartureDataConsumer.processTourDepartureV3(tourDeparturesV3, opStatus);

			if (xmlContentV3 == null) {
				if(tourDepartureAndSC.getSellingCompany()!=null && StringUtils.isNotBlank(tourDepartureAndSC.getSellingCompany().getCode())){
					LogOperationHelper.logMessageForSellingCompany(logger, tourDepartureAndSC.getSellingCompany().getCode(), opStatus, TropicSynchronizeMessages.REJECT_DATA_FOR_SELLING_COMPANY_PRODUCT_CODE, tourCode, tourDepartureAndSC.getSellingCompany().getCode(), "Product has xml validation error for TourDeparture version 3");
					LogOperationHelper.logMessageForProduct(logger,  opStatus,tourCode, TropicSynchronizeMessages.REJECT_DATA_FOR_SELLING_COMPANY_PRODUCT_CODE, tourCode, tourDepartureAndSC.getSellingCompany().getCode(), "Product has xml validation error for TourDeparture version 3");
					opStatus.addRejectedInvalidSchema();
				}
				LogOperationHelper.logMessageForProduct(logger, opStatus, tourCode, TropicSynchronizeMessages.SCHEMA_VALIDATION_WARNING, tourCode, tourFileName);
				throw new TropicSynchronizeServiceException(TropicSynchronizeMessages.getMessage(TropicSynchronizeMessages.SCHEMA_VALIDATION_WARNING, tourCode, tourFileName));
			}

			tourDepartureData.setFileName(tourFileName);
			tourDepartureData.setXmlContentV1(xmlContentV1);
			tourDepartureData.setXmlContentV3(xmlContentV3);
						
			if(SystemDirection.TROPICS==opStatus.getSystemDirection() || StringUtils.isBlank(tourDepartureAndSC.getMd5FromHabs())){
				tourDepartureData.setCheckSum(DigestUtils.md5DigestAsHex(tourDepartureData.getXmlContentV3().getBytes("UTF-8")));
			}
			else{
				tourDepartureData.setCheckSum(tourDepartureAndSC.getMd5FromHabs());
			}
			
			tourDepartureData.setTourDeparturesType(tourDeparturesV3);

			LogOperationHelper.logMessageForProduct(logger, opStatus,tourCode,TropicSynchronizeMessages.END_GENERATING_FILES_FOR_TOUR, tourCode, DateHelper.calculateTime(startDate, new Date(), defaultTimePattern));

		} catch (Exception e) {
			throw new TropicSynchronizeServiceException(e);
		}

		logger.trace("TropicDepartureSynchronizeServiceImpl:genrateTourDepartureXmlContent-end");

		return tourDepartureData;
	}

	private void checkCancelOperation(OperationStatus opStatus,String extraMsg) throws TropicSynchronizeServiceException {

		if (JobStatus.Cancelled==opStatus.getJobStatus()) {
			LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.CANCEL_PROCESS,extraMsg);
			TropicSynchronizeServiceException e = new TropicSynchronizeServiceException(TropicSynchronizeMessages.CANCEL_PROCESS.getMessage());
			throw e;
		}
		else if (JobStatus.Inactive==opStatus.getJobStatus()) {
			LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.INACTIVE_PROCESS,extraMsg);
			TropicSynchronizeServiceException e = new TropicSynchronizeServiceException(TropicSynchronizeMessages.INACTIVE_PROCESS.getMessage());
			throw e;
		}
	}

	private void setupMessageSilent(OperationStatus opStatus, TypeMsg msgType, String message, ProcessLevel processLevel) {

		try {
			importStatusService.setupMessage(message, opStatus.getCurrentBrand(), msgType, processLevel);
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	
	
	private HabsTourDepartureAndSC createHabsTourDepartureAndSC(String calculatedCheckSum,WsProductVO product, WsDeparturesVO departures, SellingCompany sellingCompany, WsTourBrandCompanyInfoVO companyInfo){
		
		HabsTourDepartureAndSC tourDeparture = new HabsTourDepartureAndSC();
		tourDeparture.setProduct(product);
		tourDeparture.setDepartures(departures);
		tourDeparture.setSellingCompany(sellingCompany);
		tourDeparture.setIsUsed(false);
		tourDeparture.setMd5FromHabs(calculatedCheckSum);		
		return tourDeparture;
	}
	
	
   public  String generateCheckSum(List<WsTourBrandCompanyInfoVO> companyInfoList, OperationStatus opStatus){
	String result=null;
	try{
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		
		boolean updateed=false;
		int countElementWithoutMd5=0;
		for (WsTourBrandCompanyInfoVO wsTourBrandCompanyInfoVO : companyInfoList) {				
			if(StringUtils.isNotBlank(wsTourBrandCompanyInfoVO.getMd5Checksum())){
				messageDigest.update(Hex.decodeHex(wsTourBrandCompanyInfoVO.getMd5Checksum().toCharArray()));
				updateed=true;
			}
			else{
				countElementWithoutMd5++;
			}
		}				
		opStatus.addTourWithOutCheckSum(countElementWithoutMd5);

		if(updateed){
			result=Hex.encodeHexString(messageDigest.digest());
		}
	}
	catch(Exception e){
		logger.error("",e);
	}			
	return result;
}
   
   private boolean checkSum(String tourCode,String brandCode,String calculatedCheckSum,OperationStatus opStatus){
	   ContentRepository cr=null;
	   boolean checkSumResult=false;
	   try{
		   cr=contentRepositoryService.findByTourCode(tourCode, brandCode);
		   checkSumResult=SystemDirection.TROPICS==opStatus.getSystemDirection() || cr==null || StringUtils.isBlank(calculatedCheckSum) ||	calculatedCheckSum.equals(StringUtils.defaultIfBlank(cr.getTourDepartureXMLMD5(), ""))==false;
		   
		   if(checkSumResult==false){
			   opStatus.getCrRejctedMd5ForBrand().add(cr.getId());
				LogOperationHelper.logMessageForProduct(logger,opStatus,tourCode, TropicSynchronizeMessages.TD_CHECK_SUM_EXIST_IN_CR, tourCode);
				opStatus.addRejectMd5();
		   }
	   }
	   finally{
		   if(cr!=null)
			   contentRepositoryDAO.evictEntity(cr);
	   }
	   return checkSumResult;
   }
	
	

	private class BrandFilter implements Predicate<WsTourOfBrandVO> {

		private String brandCode;

		public BrandFilter(String brandCode) {

			super();
			this.brandCode = brandCode != null ? brandCode : StringUtils.EMPTY;
		}

		@Override
		public boolean apply(WsTourOfBrandVO input) {

			if (input == null || input.getBrandInfo() == null || input.getBrandInfo().size() == 0) {
				return false;
			}

			for (WsTourBrandInfoVO brandInfo : input.getBrandInfo()) {

				if (brandCode.equals(brandInfo.getBrandCode())) {
					return true;
				}
			}

			return false;
		}
	}

	private static class TourDataVO {

		static TourDataVO EMPTY_OBJECT=new TourDataVO(null,null,null); 
		 				
		private WsTourBrandCompanyInfoVO companyInfoVO;
		private WsDeparturesVO departuresVO;
		private HabsTourDepartureServiceException exception;
		

		TourDataVO(WsTourBrandCompanyInfoVO companyInfoVO, WsDeparturesVO departuresVO, HabsTourDepartureServiceException exception) {

			this.companyInfoVO = companyInfoVO;
			this.departuresVO = departuresVO;
			this.exception = exception;
		}

		public WsTourBrandCompanyInfoVO getCompanyInfoVO() {
			return companyInfoVO;
		}

		public WsDeparturesVO getDeparturesVO() {
			return departuresVO;
		}

		public HabsTourDepartureServiceException getException() {
			return exception;
		}
		
		public boolean isEmptyObject(){
			return companyInfoVO==null;
		}
	}
	
	
	
	
	private class InvalidBrandSellingCompaniesRemover extends BaseWsTourBrandCompanyInfoVOPredicate{

	
		private List<String> brandSellingCompanies;
		
		public InvalidBrandSellingCompaniesRemover(List<String> brandSellingCompanies) {
			super();
			this.brandSellingCompanies = brandSellingCompanies;
		}

		@Override
		public boolean apply(WsTourBrandCompanyInfoVO companyInfo) {
			
			boolean result=(brandSellingCompanies.contains(companyInfo.getCompanyCode()));
			
			if(result)
				acceptedCount++;
			else
				rejectedCount++;
			
			return result;
		}	
	}
		
	private abstract class BaseWsTourBrandCompanyInfoVOPredicate implements Predicate<WsTourBrandCompanyInfoVO>{
		
		protected long rejectedCount;
		protected long acceptedCount;
		public long getRejectedCount() {
			return rejectedCount;
		}
		public long getAcceptedCount() {
			return acceptedCount;
		}
	}
	
	private class ParrallelFuncion implements Parallel.F<WsTourBrandCompanyInfoVO, TourDataVO>{
		
		private String tourCode;
		
		public ParrallelFuncion(String tourCode) {
			super();
			this.tourCode = tourCode;
		}

		public TourDataVO apply(WsTourBrandCompanyInfoVO companyInfo) {	
			try{
				return new TourDataVO(companyInfo, invokeServiceGetDatesAndRates(null, tourCode, companyInfo.getCompanyCode(), StringUtils.EMPTY), null);
			} catch (HabsTourDepartureServiceException e){
				logger.error("",e);
				return new TourDataVO(companyInfo, null, e);
			}
		}
	}

	
	
//	private class ParrallelFuncion implements Parallel.F<WsTourBrandCompanyInfoVO, TourDataVO>{
//
//		private String brandCode; 
//		private String tourCode;
//		private List<String> brandSellingCompanies;
//		private SystemDirection systemDirection;
//		
//		public ParrallelFuncion(String brandCode, String tourCode,List<String> brandSellingCompanies,SystemDirection systemDirection) {
//			super();
//			this.brandCode = brandCode;
//			this.tourCode = tourCode;
//			this.brandSellingCompanies = brandSellingCompanies;
//			this.systemDirection=systemDirection;
//		}
//
//		@Override
//		public TourDataVO apply(WsTourBrandCompanyInfoVO companyInfo) {			
//			try {								
//				if(brandSellingCompanies.contains(companyInfo.getCompanyCode())){
//					if(StringUtils.isBlank(companyInfo.getMd5Checksum())){
//						TourDataVO t=new TourDataVO(companyInfo, invokeServiceGetDatesAndRates(null, tourCode, companyInfo.getCompanyCode(), StringUtils.EMPTY), null);
//						t.setHasCheckSum(false);
//						return t;					
//					}
//					else{
//						ContentRepository cr=contentRepositoryService.findByTourCode(tourCode, brandCode);
//						if(SystemDirection.TROPICS==systemDirection || cr==null || companyInfo.getMd5Checksum().equals(StringUtils.defaultIfBlank(cr.getTourDepartureXMLMD5(), ""))==false){
//								return new TourDataVO(companyInfo, invokeServiceGetDatesAndRates(null, tourCode, companyInfo.getCompanyCode(), StringUtils.EMPTY), null);
//						}
//						else{
//							TourDataVO rejectedVO=new TourDataVO(null,null,null);
//							rejectedVO.setRejectedCrId(cr.getId());
//							return rejectedVO;							
//						}
//					}
//				}
//				return TourDataVO.EMPTY_OBJECT;
//			} catch (HabsTourDepartureServiceException e){
//				logger.error("",e);
//				return new TourDataVO(companyInfo, null, e);
//			}
//		}		
//	}
	
}
