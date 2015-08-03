package com.ttc.ch2.bl.departure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import javax.inject.Inject;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
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
import com.google.common.collect.Lists;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.TourDeparturesType;
import com.ttc.ch2.bl.contentrepository.ContentRepositoryService;
import com.ttc.ch2.bl.departure.common.LogOperationHelper;
import com.ttc.ch2.bl.departure.common.OperationStatus;
import com.ttc.ch2.bl.departure.common.TourDepartureData;
import com.ttc.ch2.bl.departure.common.TropicSynchronizeMessages;
import com.ttc.ch2.bl.departure.common.tourdeparturegen.ITropicsV1TourDepartureMapper;
import com.ttc.ch2.bl.departure.common.tourdeparturegen.ITropicsV3TourDepartureMapper;
import com.ttc.ch2.bl.departure.common.tourdeparturegen.TourDepartureAndSC;
import com.ttc.ch2.bl.departure.common.tourdeparturegen.TourDepartureDataConsumer;
import com.ttc.ch2.bl.download.ch1download.Ch1DownloadService;
import com.ttc.ch2.bl.filecollect.FileCollectService;
import com.ttc.ch2.bl.filecollect.FileCollectServiceException;
import com.ttc.ch2.bl.filecollect.FileCollectVO;
import com.ttc.ch2.bl.report.ReconciliationReportService;
import com.ttc.ch2.common.DateHelper;
import com.ttc.ch2.common.DateHelper.CalculateTimePattern;
import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.transfer.TourInfoTransferDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.departure.ImportStatus.ProcessLevel;
import com.ttc.ch2.domain.jobs.QuartzJob.JobStatus;
import com.ttc.ch2.search.export.IndexSynchronizerService;
import com.ttc.ch2.search.export.IndexSynchronizerServiceException;
import com.ttc.ch2.search.export.IndexSynchronizerVO;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.MarketVariationDepartureInfo;

import es.usc.citius.common.parallel.Parallel;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsDeparturesVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsOperatingProductVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsProductVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsTourBrandCompanyInfoVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsTourBrandInfoVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsTourOfBrandVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsToursOfBrandsVO;

@Service("TropicDepartureSynchronizeServiceImpl")
@Scope("prototype")
@Transactional(readOnly = false, propagation = Propagation.NEVER)
public class TropicDepartureSynchronizeServiceImpl implements TropicDepartureSynchronizeService {

	private static final Logger logger = LoggerFactory.getLogger(TropicDepartureSynchronizeServiceImpl.class);

	private static final CalculateTimePattern defaultTimePattern = CalculateTimePattern.HMS;

	@Value("${elastic.search.indexing}")
	private String elasticSearchIndexing;

	@Inject
	private TourDepartureDataConsumer tourDepartureDataConsumer;

	@Inject
	private TourDepartureService tourDepartureService;

	@Inject
	private BrandDAO brandDAO;

	@Inject
	private TourInfoTransferDAO tourInfoTransferDAO;

	@Inject
	private FileCollectService fileCollectService;

	@Inject
	private ContentRepositoryService contentRepositoryService;

	@Inject
	private TourContentRepositoryService tourContentRepositoryService;

	@Inject
	private ApplicationContext appCtx;

	@Inject
	private ReconciliationReportService reconciliationReportService;

	@Inject
	private ImportStatusService importStatusService;

	@Inject
	private IndexSynchronizerService indexSynchronizerService;

	@Inject
	private Ch1DownloadService ch1DownloadService;



	public OperationStatus departureSynchronize(OperationStatus opStatus) throws TropicSynchronizeServiceException {

		logger.trace("TropicDepartureSynchronizeServiceImpl:synchronize-start");

		Date startDateOutgoingArchives = null;

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

						LogOperationHelper.logMessageForBrand(logger, opStatusPerBrand, TropicSynchronizeMessages.OPERATION_FOR_BRAND_START, brand.getBrandName(), brand.getCode());

						// To make transaction annotation working properly, we cannot call the method directly. We have to do it that way.
						try{
						appCtx.getBean(TropicDepartureSynchronizeService.class).operationForBrand(opStatusPerBrand, brand); // new tx
						}
						catch(Exception e){
							// delete all rollbacked element from history 
							opStatus.getTourDepartureHistory().getContentRepositories().clear();
							opStatusPerBrand.getCrRejctedMd5ForBrand().clear();
							opStatusPerBrand.getCrSavedOrUpdateForBrand().clear();
							opStatusPerBrand.getCrUnsavedForBrand().clear();
							throw e;
						}

						// download TI from CH1 to CH2
						if (tourInfoTransferDAO.isDownloadEnabled(brand.getCode())) {
							try {
//								Date startDateTIDownload = new Date();
//								LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.TOUR_INFO_CH1_DOWNLOAD_START, brand.getCode());
//								Map<String, String> tourInfoV1CodeXmlMap = ch1DownloadService.downloadTIFromCH1(brand.getCode());
//								LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.TOUR_INFO_CH1_DOWNLOAD_END, brand.getCode(), DateHelper.calculateTime(startDateTIDownload, new Date(), defaultTimePattern));
//
								Date startDateTIUpload = new Date();
								LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.TOUR_INFO_CH1_UPLOAD_TO_CH2_START, brand.getCode());								
								ch1DownloadService.downloadTIFromCH1WithSaveToDB(brand.getCode());
								LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.TOUR_INFO_CH1_UPLOAD_TO_CH2_END, brand.getCode(), DateHelper.calculateTime(startDateTIUpload, new Date(), defaultTimePattern));

							} catch (Exception e) {
								LogOperationHelper.logDefaultException(logger, opStatus, new Date(), e, TropicSynchronizeMessages.UNEXPECTED_EXCEPTION);
							}

						} else {
							LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.TOUR_INFO_CH1_DOWNLOAD_DISABLED, brand.getCode());
						}

						// elasticsearch indexing
						IndexSynchronizerVO indexSynchronizerVO = null; 

						if (BooleanUtils.toBoolean(elasticSearchIndexing)) {
							try {
								setupMessageSilent(opStatusPerBrand,  TypeMsg.INF, TropicSynchronizeMessages.INDEXING_START.createMessage(brand.getCode()),ProcessLevel.INDEXING);								
								Date indexStart=new Date();
								LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.INDEXING_START, brand.getCode());								
								indexSynchronizerVO = indexSynchronizerService.synchronize(ProcessName.IMPORT, brand.getCode());
								LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.INDEX_SUCCESS);
								LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.INDEXING_END, brand.getCode(), DateHelper.calculateTime(indexStart, new Date(), defaultTimePattern));

							} catch (IndexSynchronizerServiceException e) {
								LogOperationHelper.logDefaultException(logger, opStatus, new Date(), e, TropicSynchronizeMessages.UNEXPECTED_EXCEPTION);
							}
						} else {
							LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.INDEXING_TOURN_OFF);
						}

						// outgoing archive operation
						startDateOutgoingArchives = new Date();
						LogOperationHelper.logMessageForBrand(logger, opStatusPerBrand, TropicSynchronizeMessages.OUTGOING_ARCHIVE_FOR_BRAND, brand.getCode());

						setupMessageSilent(opStatusPerBrand,  TypeMsg.INF, TropicSynchronizeMessages.OUTGOING_ARCHIVE_FOR_BRAND.createMessage(brand.getCode()),ProcessLevel.OUTGOING_ARCHIVE);

						final String brandCodeLocal = brand.getCode();

						FileCollectVO fileCollectVO = null;

						try {
							fileCollectVO = fileCollectService.createLatestVersion(ProcessName.IMPORT, brandCodeLocal);
						} catch (FileCollectServiceException e) {
							LogOperationHelper.logDefaultException(logger, opStatusPerBrand, new Date(), e, TropicSynchronizeMessages.OUTGOING_ARCHIVE_EXCEPTION, opStatusPerBrand.getCurrentBrand());
						}

						LogOperationHelper.logMessageForBrand(logger, opStatusPerBrand, TropicSynchronizeMessages.OUTGOING_ARCHIVE_FOR_BRAND_END, brand.getCode(), DateHelper.calculateTime(startDateOutgoingArchives, new Date(), defaultTimePattern));
						setupMessageSilent(opStatusPerBrand,  TypeMsg.INF, TropicSynchronizeMessages.RECONCILIATION_GEN.createMessage(brand.getCode()),ProcessLevel.RECONCILIATION);
						reconciliationReportService.createReconciliationReport(ProcessName.IMPORT, brand.getCode(), indexSynchronizerVO, fileCollectVO);

						// clear data for next brand
						startDateOutgoingArchives = null;
						opStatusPerBrand.getCrRejctedMd5ForBrand().clear();
					} finally {

						Date endDate = new Date();

						LogOperationHelper.logMessageForBrand(logger, opStatusPerBrand, TropicSynchronizeMessages.OPERATION_FOR_BRAND_END, brand.getBrandName(), brand.getCode(), DateHelper.calculateTime(startDateBrand, endDate, defaultTimePattern));

						String textDuration = DateHelper.calculateTime(startDateBrand, endDate, defaultTimePattern);
						String textStartTime = DateHelper.dateToString(startDateBrand);
						String textEndTime = DateHelper.dateToString(startDateBrand);

						LogOperationHelper.logMessageForBrand(logger, opStatusPerBrand, TropicSynchronizeMessages.BRAND_STATUS, brand.getCode(), textStartTime, textEndTime, textDuration);
					}
				}
			}
		} catch (TropicSynchronizeServiceException e) {

			e.setOpStatus(opStatus);
			throw e;

		} catch (TourDepartureServiceException e) {

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

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void operationForBrand(OperationStatus opStatus, Brand brand) throws TourDepartureServiceException, TropicSynchronizeServiceException {

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

		WsToursOfBrandsVO tours = invokeServiceGetToursOfBrands(opStatus, brandsCodesList);

		if (tours != null && tours.getTour() != null && tours.getTour().size() > 0) {

			Collection<WsTourOfBrandVO> toursList = Collections2.filter(tours.getTour(), new BrandFilter(brandCode));
			
			if (toursList != null && toursList.size() > 0) {

				int index = 0;
				
				// to remove				
//				List<WsTourOfBrandVO>toursListCpy=Lists.newArrayList(Iterables.partition(toursList, 5).iterator().next());
//				toursList=Lists.newArrayList();
//				for (Iterator iterator = toursListCpy.iterator(); iterator.hasNext(); ) {
//					WsTourOfBrandVO wsTourOfBrandVO = (WsTourOfBrandVO) iterator.next();
//					if(wsTourOfBrandVO.getTourCode().equals("EGBOUS09")){
//						toursList.add(wsTourOfBrandVO);
//						break;
//					}
//				}
				
				for (WsTourOfBrandVO tour : toursList) {

					index++;

					Date startDateProduct = new Date();

					LogOperationHelper.logMessageForProduct(logger, opStatus, tour.getTourCode(), TropicSynchronizeMessages.OPERATION_FOR_PRODUCT_START, tour.getTourCode(), index, toursList.size());

					setupMessageSilent(opStatus, TypeMsg.INF, TropicSynchronizeMessages.OPERATION_FOR_PRODUCT_START.createMessage(tour.getTourCode(), index, toursList.size()),ProcessLevel.IMPORT);

					try {
						operationForTour(opStatus, brandCode, brandSellingCompanies, tour);
					} catch (TourDepartureServiceException e) {
						tourCodesUnsaved.add(tour.getTourCode());
						LogOperationHelper.logExceptionForProduct(logger, tour.getTourCode(), opStatus, e, TropicSynchronizeMessages.OPERATION_FOR_PRODUCT_IMPORT_WARNING, tour.getTourCode(), index, toursList.size());
					} catch (TropicSynchronizeServiceException e) {

						if (TropicSynchronizeMessages.CANCEL_PROCESS.getMessage().equals(e.getMessage()) || TropicSynchronizeMessages.INACTIVE_PROCESS.getMessage().equals(e.getMessage())) {
							throw e;
						} else {
							tourCodesUnsaved.add(tour.getTourCode());
							LogOperationHelper.logExceptionForProduct(logger, tour.getTourCode(), opStatus, e, TropicSynchronizeMessages.OPERATION_FOR_PRODUCT_MAPPING_WARNING, tour.getTourCode(), index, toursList.size());
						}
					}

					LogOperationHelper.logMessageForProduct(logger, opStatus, tour.getTourCode(), TropicSynchronizeMessages.OPERATION_FOR_PRODUCT_END, tour.getTourCode(), index, toursList.size(),DateHelper.calculateTime(startDateProduct, new Date(), defaultTimePattern));
				}
			}
		}

		if (!tourCodesUnsaved.isEmpty()) {
			List<Long> contentRepositoryIDsList = contentRepositoryService.getContentRepositoryIDsList(tourCodesUnsaved, brandCode);
			opStatus.getCrUnsavedForBrand().addAll(contentRepositoryIDsList);
		}

		Date startClean=new Date();
		LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.CLEAN_CR_START,brandCode);
		contentRepositoryService.clearTourDeparture(opStatus.getCrNotClearedPerBrand(), brand);
		LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.CLEAN_CR_END,brandCode,DateHelper.calculateTime(startClean, new Date(), defaultTimePattern));
		
		logger.trace("TropicDepartureSynchronizeServiceImpl:operationForBrand-end");
	}

	private void operationForTour(OperationStatus opStatus, String brandCode, final List<String> brandSellingCompanies, WsTourOfBrandVO tour) throws TourDepartureServiceException, TropicSynchronizeServiceException {

		logger.trace("TropicDepartureSynchronizeServiceImpl:operationForTour-start");

		WsTourBrandInfoVO brandInfo = null;

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

		final String tourCode = tour.getTourCode();

		TourDepartureAndSC tourDepartureAndSCParent = null;

		Collection<TourDataVO> tourDataList = Parallel.ForEach(brandInfo.getCompanyInfo(), new Parallel.F<WsTourBrandCompanyInfoVO, TourDataVO>() {

			@Override
			public TourDataVO apply(WsTourBrandCompanyInfoVO companyInfo) {

				try {

					if (brandSellingCompanies.contains(companyInfo.getCompanyCode())) {
						return new TourDataVO(companyInfo, invokeServiceGetDatesAndRates(null, tourCode, companyInfo.getCompanyCode(), StringUtils.EMPTY), null);
					}

					return new TourDataVO(null, null, null);

				} catch (TourDepartureServiceException e) {

					return new TourDataVO(companyInfo, null, e);
				}
			}
		});

		for (TourDataVO tourData : tourDataList) {

			WsTourBrandCompanyInfoVO companyInfo = tourData.getCompanyInfoVO();
			WsDeparturesVO departures = tourData.getDeparturesVO();

			if (companyInfo == null) {
				continue;
			}

			if (tourData.getException() != null) {
				throw tourData.getException();
			}

			if (departures == null || departures.getDeparture() == null || departures.getDeparture().size() == 0) {

				opStatus.addRejectedCountDeparture();

				LogOperationHelper.logMessageForSellingCompany(logger, companyInfo.getCompanyCode(), opStatus, TropicSynchronizeMessages.REJECT_DATA_FOR_SELLING_COMPANY_PRODUCT_CODE, tour.getTourCode(), companyInfo.getCompanyCode(), "Product does not have departures");

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

					tourDepartureAndSCParent = new TourDepartureAndSC();
					tourDepartureAndSCParent.setProduct(product);
					tourDepartureAndSCParent.setDepartures(departures);
					tourDepartureAndSCParent.setSellingCompany(sellingCompany);
					tourDepartureAndSCParent.setIsUsed(false);

				} else {

					TourDepartureAndSC tourDepartureAndSCChild = new TourDepartureAndSC();
					tourDepartureAndSCChild.setProduct(product);
					tourDepartureAndSCChild.setDepartures(departures);
					tourDepartureAndSCChild.setSellingCompany(sellingCompany);
					tourDepartureAndSCChild.setIsUsed(false);

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

	private WsToursOfBrandsVO invokeServiceGetToursOfBrands(OperationStatus opStatus, List<String> brandsCodesList) throws TourDepartureServiceException {

		logger.trace("TropicDepartureSynchronizeServiceImpl:getToursOfBrands-start");

		WsToursOfBrandsVO tours = null;

		try {

			tours = tourDepartureService.getToursOfBrands(brandsCodesList);

		} catch (TourDepartureServiceException e) {

			LogOperationHelper.logExceptionForBrand(logger, opStatus, e, TropicSynchronizeMessages.GET_TOURS_FROM_TROPIC_EXCEPTION, opStatus.getCurrentBrand(),e.getMessage());
			throw e;
		}

		logger.trace("TropicDepartureSynchronizeServiceImpl:getToursOfBrands-end");
		return tours;
	}

	private WsDeparturesVO invokeServiceGetDatesAndRates(OperationStatus opStatus, String tourCode, String sellingCompanyCode, String apiKey) throws TourDepartureServiceException {

		logger.trace("TropicDepartureSynchronizeServiceImpl:invokeServiceGetDatesAndRates-start");

		WsDeparturesVO departures = tourDepartureService.getTourDatesAndRates(tourCode, sellingCompanyCode, apiKey);

		logger.trace("TropicDepartureSynchronizeServiceImpl:invokeServiceGetDatesAndRates-end");

		return departures;
	}

	private TourDepartureData genrateTourDepartureXmlContent(OperationStatus opStatus, TourDepartureAndSC tourDepartureAndSC) throws TropicSynchronizeServiceException {

		logger.trace("TropicDepartureSynchronizeServiceImpl:genrateTourDepartureXmlContent-start");

		TourDepartureData tourDepartureData = new TourDepartureData(null, null, null, null);

		Date startDate = new Date();
		String tourCode = tourDepartureAndSC.getProduct().getCode();
		String tourFileName = tourCode + ".xml";

		try {

			LogOperationHelper.logMessageForProduct(logger, opStatus,tourCode, TropicSynchronizeMessages.START_GENERATING_FILES_FOR_TOUR, tourCode);

			MarketVariationDepartureInfo tourDeparturesV1 = ITropicsV1TourDepartureMapper.map(opStatus, tourDepartureAndSC);

			String xmlContentV1 = tourDepartureDataConsumer.processTourDepartureV1(tourDeparturesV1, opStatus);

			if (xmlContentV1 == null) {
				LogOperationHelper.logMessageForProduct(logger, opStatus, tourCode, TropicSynchronizeMessages.SCHEMA_VALIDATION_WARNING, tourCode, tourFileName);
				throw new TropicSynchronizeServiceException(TropicSynchronizeMessages.getMessage(TropicSynchronizeMessages.SCHEMA_VALIDATION_WARNING, tourCode, tourFileName));
			}

			TourDeparturesType tourDeparturesV3 = ITropicsV3TourDepartureMapper.map(opStatus, tourDepartureAndSC);

			String xmlContentV3 = tourDepartureDataConsumer.processTourDepartureV3(tourDeparturesV3, opStatus);

			if (xmlContentV3 == null) {
				LogOperationHelper.logMessageForProduct(logger, opStatus, tourCode, TropicSynchronizeMessages.SCHEMA_VALIDATION_WARNING, tourCode, tourFileName);
				throw new TropicSynchronizeServiceException(TropicSynchronizeMessages.getMessage(TropicSynchronizeMessages.SCHEMA_VALIDATION_WARNING, tourCode, tourFileName));
			}

			tourDepartureData.setFileName(tourFileName);
			tourDepartureData.setXmlContentV1(xmlContentV1);
			tourDepartureData.setXmlContentV3(xmlContentV3);
			tourDepartureData.setCheckSum(DigestUtils.md5DigestAsHex(tourDepartureData.getXmlContentV3().getBytes("UTF-8")));
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

	private class TourDataVO {

		private WsTourBrandCompanyInfoVO companyInfoVO;
		private WsDeparturesVO departuresVO;
		private TourDepartureServiceException exception;

		TourDataVO(WsTourBrandCompanyInfoVO companyInfoVO, WsDeparturesVO departuresVO, TourDepartureServiceException exception) {

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

		public TourDepartureServiceException getException() {
			return exception;
		}
	}
}
