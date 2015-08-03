package com.ttc.ch2.bl.filecollect;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ttc.ch2.bl.contentrepository.ContentRepositoryService;
import com.ttc.ch2.bl.departure.ImportStatusService;
import com.ttc.ch2.bl.upload.PermissionDeniedException;
import com.ttc.ch2.bl.upload.UploadStatusService;
import com.ttc.ch2.common.AuthorityHelper;
import com.ttc.ch2.common.FunctionType;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.filecollect.FileCollectDAO;
import com.ttc.ch2.dao.tour.ContentRepositoryDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.auth.CCAPIAuthority;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.departure.ImportStatus.ProcessLevel;
import com.ttc.ch2.domain.filecollect.FileCollect;
import com.ttc.ch2.domain.filecollect.ZIPFileCollect;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.ContentRepository.RepositoryStatus;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.domain.user.UserGui;

@Service
public class FileCollectServiceImpl implements FileCollectService {

	private static Logger logger = LoggerFactory.getLogger(FileCollectServiceImpl.class);

	private static final int BUFFER_SIZE = 1024;

	private static final String PERMISSION_DENIED_GUI = "Permission denied for sellingCompanyCode: [%s]";
	private static final String PERMISSION_DENIED_CCAPI = "Permission denied for sellingCompanyCode: [%s] and function [%s]";

	private static final String CUSTOM_TAG_BEGIN = "<CustomTag>";
	private static final String CUSTOM_TAG_END = "</CustomTag>";

	private static final String ENTRY_TEMPLATE_TOUR_INFO = "tour_info/%s.xml";
	private static final String ENTRY_TEMPLATE_TOUR_DEPARTURE = "tour_departure/%s.xml";

	private static final String FILE_NAME_TEMPLATE = "%s.zip";

	private static final String INFO_TOUR_PROCESSING = "File Collect - processing tour: [%s] being: [%s/%s]";
	private static final String INFO_ZIP_SAVING = "File Collect - saving zip file for selling company: [%s] being: [%s/%s]";

	private static final String ERROR_UNEXPECTED_USER_TYPE = "Unexpected type of user";
	private static final String ERROR_CREATE_ZIP_TOUR = "Error creating zip file - brand code: [%s], tour code: [%s]";
	private static final String ERROR_CREATE_ZIP_SC = "Error creating zip file - selling company: [%s]";
	private static final String ERROR_DELETE_ZIP_SC = "Error deleting zip file - selling company: [%s]";

	private enum TransformerType {
		TI_V1, TI_V3, TD_V1, TD_V3
	};

	private FileCollectService fileCollectService;

	private Map<TransformerType, Transformer> transformerMap;

	@Value("${synchronize.tour.xsl.file.v1}")
	private String xslFileTISingleCompanyV1;

	@Value("${synchronize.tour.xsl.file.v3}")
	private String xslFileTISingleCompanyV3;

	@Value("${synchronize.departure.xsl.file.v1}")
	private String xslFileTDSingleCompanyV1;

	@Value("${synchronize.departure.xsl.file.v3}")
	private String xslFileTDSingleCompanyV3;

	@Inject
	private BrandDAO brandDAO;

	@Inject
	private FileCollectDAO fileCollectDAO;

	@Inject
	private ContentRepositoryService contentRepositoryService;

	@Inject
	private ContentRepositoryDAO contentRepositoryDAO;
	
	@Inject
	private ImportStatusService importStatusService;

	@Inject
	private UploadStatusService uploadStatusService;

	@Inject
	private ApplicationContext applicationContext;

	class ZipStreamVO {

		private ByteArrayOutputStream outputStreamRegular;
		private ZipOutputStream outputStreamZip;
		private MessageDigest outputStreamMD5;
		private String checksumMD5;

		public ZipStreamVO() throws NoSuchAlgorithmException {

			outputStreamRegular = new ByteArrayOutputStream(BUFFER_SIZE);
			outputStreamZip = new ZipOutputStream(outputStreamRegular);
			outputStreamMD5 = MessageDigest.getInstance("MD5");
			checksumMD5 = null;
		}

		public ByteArrayOutputStream getOutputStreamRegular() {
			return outputStreamRegular;
		}

		public ZipOutputStream getOutputStreamZip() {
			return outputStreamZip;
		}

		public MessageDigest getOutputStreamMD5() {
			return outputStreamMD5;
		}

		public String getChecksumMD5() {

			if (checksumMD5 == null) {
				checksumMD5 = Hex.encodeHexString(outputStreamMD5.digest());
			}

			return checksumMD5;
		}
	}


	@PostConstruct
	public void init() throws TransformerConfigurationException, TransformerFactoryConfigurationError {

		fileCollectService = applicationContext.getBean(FileCollectService.class);

		transformerMap = new HashMap<TransformerType, Transformer>();

		transformerMap.put(TransformerType.TI_V1, TransformerFactory.newInstance().newTransformer(new StreamSource(getClass().getResourceAsStream(xslFileTISingleCompanyV1))));
		transformerMap.put(TransformerType.TI_V3, TransformerFactory.newInstance().newTransformer(new StreamSource(getClass().getResourceAsStream(xslFileTISingleCompanyV3))));
		transformerMap.put(TransformerType.TD_V1, TransformerFactory.newInstance().newTransformer(new StreamSource(getClass().getResourceAsStream(xslFileTDSingleCompanyV1))));
		transformerMap.put(TransformerType.TD_V3, TransformerFactory.newInstance().newTransformer(new StreamSource(getClass().getResourceAsStream(xslFileTDSingleCompanyV3))));
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<FileCollectVO> getList(FileCollectVersion fileCollectVersion) throws FileCollectServiceException {

		Map<String, List<String>> userPermissionMap = getUserPermissionMap(fileCollectVersion);

		if (userPermissionMap == null || userPermissionMap.isEmpty()) {
			return null;
		}

		List<FileCollectVO> fileCollectListVO = new ArrayList<FileCollectVO>();
		List<FileCollect> fileCollectListDO = fileCollectDAO.findAll();

		if (fileCollectListDO == null || fileCollectListDO.size() == 0) {
			return null;
		}

		for (FileCollect fileCollectDO : fileCollectListDO) {

			List<String> userPermissionSCList = userPermissionMap.get(fileCollectDO.getBrand().getCode());

			if (userPermissionSCList == null || userPermissionSCList.size() == 0 || !userPermissionSCList.contains(fileCollectDO.getSellingCompany().getCode())) {
				continue;
			}

			FileCollectVO fileCollectVO = null;

			for (FileCollectVO fileCollectItem : fileCollectListVO) {

				if (fileCollectItem.getBrand().getCode().equals(fileCollectDO.getBrand().getCode())) {

					fileCollectVO = fileCollectItem;
					break;
				}
			}

			if (fileCollectVO == null) {

				Brand brand = new Brand();
				brand.setCode(fileCollectDO.getBrand().getCode());

				fileCollectVO = new FileCollectVO();
				fileCollectVO.setBrand(brand);
				fileCollectVO.setSellingCompanies(new ArrayList<SellingCompany>());

				fileCollectListVO.add(fileCollectVO);
			}

			SellingCompany sellingCompany = new SellingCompany();
			sellingCompany.setCode(fileCollectDO.getSellingCompany().getCode());

			fileCollectVO.getSellingCompanies().add(sellingCompany);
		}

		return fileCollectListVO;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public byte[] getLatestVersion(String sellingCompanyCode, FileCollectVersion fileCollectVersion) throws FileCollectServiceException {

		checkUserPermission(sellingCompanyCode, fileCollectVersion);

		FileCollect fileCollectDO = fileCollectDAO.getFileCollect(sellingCompanyCode);

		if (fileCollectDO == null) {
			return null;
		}

		return FileCollectVersion.V1.equals(fileCollectVersion) ? fileCollectDO.getFileZip().getFileCollectZIPV1() : fileCollectDO.getFileZip().getFileCollectZIPV3();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public FileCollectVO createLatestVersion(ProcessName processName, String brandCode) throws FileCollectServiceException {

		FileCollectVO fileCollectVO = new FileCollectVO();

		ContentRepository filter = new ContentRepository();

		Brand brand = brandDAO.findByBrandCode(brandCode);

		filter.getBrands().add(brand);

//		List<ContentRepository> contentRepositoryList = contentRepositoryService.getContentRepositoriesList(new QueryCondition(), filter, RepositoryStatus.TIandTD);
		List<Long> ids=contentRepositoryDAO.getContentRepositoriesIdsList(new QueryCondition(), filter,Lists.newArrayList(RepositoryStatus.TIandTD));
		
		Map<String, ZipStreamVO> brandZipMapV1 = new HashMap<String, ZipStreamVO>();
		Map<String, ZipStreamVO> brandZipMapV3 = new HashMap<String, ZipStreamVO>();

		if (ids == null || ids.size() == 0) {

			fileCollectService.saveLatestVersion(processName, brand, brandZipMapV1, brandZipMapV3, fileCollectVO);

			return null;
		}

		int index = 0;

		for (Long id : ids) {
			ContentRepository contentRepository = contentRepositoryDAO.find(id);
			try {

				if (ProcessName.IMPORT.equals(processName)) {
					importStatusService.setupMessage(String.format(INFO_TOUR_PROCESSING, contentRepository.getTourCode(), ++index, ids.size()), brandCode, TypeMsg.INF, ProcessLevel.OUTGOING_ARCHIVE);
				} else if (ProcessName.UPLOAD.equals(processName)) {
					uploadStatusService.proccesingDescription(brandCode, String.format(INFO_TOUR_PROCESSING, contentRepository.getTourCode(), ++index, ids.size()), false);
				}

				createTourZipData(contentRepository, brandZipMapV1, FileCollectVersion.V1);				
				createTourZipData(contentRepository, brandZipMapV3, FileCollectVersion.V3);
				
				contentRepositoryDAO.flush();
//				contentRepositoryDAO.evictEntity(contentRepository);
//				contentRepository.getXmlContentRepository().clear();
				contentRepositoryDAO.clearSession();

			} catch (Exception e) {

				fileCollectVO.getToursListNotAdded().add(contentRepository.getTourCode());

				logger.error(String.format(ERROR_CREATE_ZIP_TOUR, brandCode, contentRepository.getTourCode()), e);
				throw new FileCollectServiceException(e);
			}
		}

		try {

			fileCollectService.saveLatestVersion(processName, brand, brandZipMapV1, brandZipMapV3, fileCollectVO);

		} catch (Exception e) {

			throw new FileCollectServiceException(e);
		}

		return fileCollectVO;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void saveLatestVersion(ProcessName processName, Brand brand, Map<String, ZipStreamVO> brandZipMapV1, Map<String, ZipStreamVO> brandZipMapV3, FileCollectVO fileCollectVO) {

		if (fileCollectVO == null) {
			fileCollectVO = new FileCollectVO();
		}

		int index = 0;

		for (SellingCompany sellingCompany : brand.getSellingCompanies()) {

			try {

				if (ProcessName.IMPORT.equals(processName)) {
					importStatusService.setupMessage(String.format(INFO_ZIP_SAVING, sellingCompany.getCode(), ++index, brand.getSellingCompanies().size()), brand.getCode(), TypeMsg.INF, ProcessLevel.OUTGOING_ARCHIVE);
				} else if (ProcessName.UPLOAD.equals(processName)) {
					uploadStatusService.proccesingDescription(brand.getCode(), String.format(INFO_ZIP_SAVING, sellingCompany.getCode(), ++index, brand.getSellingCompanies().size()), false);
				}

				ZipStreamVO zipStreamVOV1 = brandZipMapV1.get(sellingCompany.getCode());
				ZipStreamVO zipStreamVOV3 = brandZipMapV3.get(sellingCompany.getCode());

				FileCollect fileCollect = fileCollectDAO.getFileCollect(brand.getCode(), sellingCompany.getCode());

				if (zipStreamVOV1 == null || zipStreamVOV3 == null) {

					if (fileCollect != null) {

						try {

							fileCollectDAO.remove(fileCollect);

						} catch (Exception e) {

							fileCollectVO.getZipListNotDeleted().add(sellingCompany.getCode());

							logger.error(String.format(ERROR_DELETE_ZIP_SC, sellingCompany.getCode()), e);
						}
					}

				} else {

					zipStreamVOV1.getOutputStreamZip().close();
					zipStreamVOV3.getOutputStreamZip().close();

					byte[] zipFileDataV1 = zipStreamVOV1.getOutputStreamRegular().toByteArray();
					byte[] zipFileDataV3 = zipStreamVOV3.getOutputStreamRegular().toByteArray();

					if (fileCollect == null) {

						fileCollect = new FileCollect();

						fileCollect.setBrand(brand);
						fileCollect.setSellingCompany(sellingCompany);
						fileCollect.setFileName(String.format(FILE_NAME_TEMPLATE, sellingCompany.getCode()));
						fileCollect.setFileZip(new ZIPFileCollect());
					}

					ZIPFileCollect zipFileCollect = fileCollect.getFileZip();

					if (zipStreamVOV1.getChecksumMD5().equals(fileCollect.getFileMD5V1()) && zipStreamVOV3.getChecksumMD5().equals(fileCollect.getFileMD5V3())) {
						continue;
					}

					zipFileCollect.setFileCollectZIPV1(zipFileDataV1);
					zipFileCollect.setFileCollectZIPV3(zipFileDataV3);

					fileCollect.setFileMD5V1(zipStreamVOV1.getChecksumMD5());
					fileCollect.setFileMD5V3(zipStreamVOV3.getChecksumMD5());
					fileCollect.setFileModified(new Date());

					fileCollectDAO.save(fileCollect);
				}

			} catch (Exception e) {

				fileCollectVO.getZipListNotAdded().add(sellingCompany.getCode());

				logger.error(String.format(ERROR_CREATE_ZIP_SC, sellingCompany.getCode()), e);
			}
		}
	}


	private void checkUserPermission(String sellingCompanyCode, FileCollectVersion fileCollectVersion) throws FileCollectServiceException {

		if (SecurityHelper.isUserCcapi()) {

			FunctionType function = FileCollectVersion.V1.equals(fileCollectVersion) ? FunctionType.OA_VIEW_V1 : FunctionType.OA_VIEW_V3;

			if (AuthorityHelper.hasAuthorityUserCcapi(function, sellingCompanyCode) == false) {
				throw new PermissionDeniedException(String.format(PERMISSION_DENIED_CCAPI, sellingCompanyCode, function.getSimpleName()));
			}

		} else if (SecurityHelper.isUserGui()) {

			if (AuthorityHelper.hasAuthorityForUserGui(sellingCompanyCode) == false) {
				throw new PermissionDeniedException(String.format(PERMISSION_DENIED_GUI, sellingCompanyCode));
			}

		} else {
			throw new FileCollectServiceException(ERROR_UNEXPECTED_USER_TYPE);
		}
	}

	private Map<String, List<String>> getUserPermissionMap(FileCollectVersion fileCollectVersion) throws FileCollectServiceException {

		Map<String, List<String>> userPermissionMap = new HashMap<String, List<String>>();

		if (SecurityHelper.isUserCcapi()) {

			UserCCAPI userCCAPI = SecurityHelper.getUserCCAPIPrincipal().getUserDb();

			if (userCCAPI == null || userCCAPI.getCcapiAuthorities() == null) {
				return null;
			}

			String functionName = FileCollectVersion.V1.equals(fileCollectVersion) ? FunctionType.OA_VIEW_V1.getSimpleName() : FunctionType.OA_VIEW_V3.getSimpleName();

			for (CCAPIAuthority authority : userCCAPI.getCcapiAuthorities()) {

				if (authority.getFunction().getName().equals(functionName)) {

					List<String> userPermissionSCList = userPermissionMap.get(authority.getSellingCompany().getBrand().getCode());

					if (userPermissionSCList == null) {

						userPermissionSCList = new ArrayList<String>();
						userPermissionMap.put(authority.getSellingCompany().getBrand().getCode(), userPermissionSCList);
					}

					userPermissionSCList.add(authority.getSellingCompany().getCode());
				}
			}

		} else if (SecurityHelper.isUserGui()) {

			UserGui userGui = SecurityHelper.getUserGuiPrincipal().getUserDb();

			if (userGui == null || userGui.getBrands() == null) {
				return null;
			}

			for (Brand brand : userGui.getBrands()) {

				List<String> userPermissionSCList = new ArrayList<String>();

				for (SellingCompany sellingCompany : brand.getSellingCompanies()) {
					userPermissionSCList.add(sellingCompany.getCode());
				}

				userPermissionMap.put(brand.getCode(), userPermissionSCList);
			}

		} else {
			throw new FileCollectServiceException(ERROR_UNEXPECTED_USER_TYPE);
		}

		return userPermissionMap;
	}

	private void createTourZipData(ContentRepository contentRepository, Map<String, ZipStreamVO> brandZipMap, FileCollectVersion fileCollectVersion) throws TransformerException, IOException, NoSuchAlgorithmException {

		if (contentRepository == null || contentRepository.getXmlContentRepository() == null) {
			return;
		}

		String[] dataArrayTI = null;
		String[] dataArrayTD = null;

		if (FileCollectVersion.V1.equals(fileCollectVersion)) {

			dataArrayTI = StringUtils.splitByWholeSeparator(createSingleSellingCompanyData(TransformerType.TI_V1, contentRepository.getXmlContentRepository().get(0).getOldTourInfoXML()), CUSTOM_TAG_BEGIN);
			dataArrayTD = StringUtils.splitByWholeSeparator(createSingleSellingCompanyData(TransformerType.TD_V1, contentRepository.getXmlContentRepository().get(0).getOldTourDepartureXML()), CUSTOM_TAG_BEGIN);

		} else if (FileCollectVersion.V3.equals(fileCollectVersion)) {

			dataArrayTI = StringUtils.splitByWholeSeparator(createSingleSellingCompanyData(TransformerType.TI_V3, contentRepository.getXmlContentRepository().get(0).getTourInfoXML()), CUSTOM_TAG_BEGIN);
			dataArrayTD = StringUtils.splitByWholeSeparator(createSingleSellingCompanyData(TransformerType.TD_V3, contentRepository.getXmlContentRepository().get(0).getTourDepartureXML()), CUSTOM_TAG_BEGIN);
		}

		if (dataArrayTI != null && dataArrayTI.length > 1 && dataArrayTD != null && dataArrayTD.length > 1) {

			for (int indexTI = 1; indexTI < dataArrayTI.length; indexTI++) {

				String sellingCompanyTI = StringUtils.defaultIfBlank(StringUtils.substringBefore(dataArrayTI[indexTI], CUSTOM_TAG_END), "TI");

				for (int indexTD = 1; indexTD < dataArrayTD.length; indexTD++) {

					String sellingCompanyTD = StringUtils.defaultIfBlank(StringUtils.substringBefore(dataArrayTD[indexTD], CUSTOM_TAG_END), "TD");

					if (sellingCompanyTI.equals(sellingCompanyTD)) {

						byte[] xmlDataTI = StringUtils.replaceOnce(dataArrayTI[indexTI], sellingCompanyTI + CUSTOM_TAG_END, dataArrayTI[0]).getBytes(StandardCharsets.UTF_8);
						byte[] xmlDataTD = StringUtils.replaceOnce(dataArrayTD[indexTD], sellingCompanyTD + CUSTOM_TAG_END, dataArrayTD[0]).getBytes(StandardCharsets.UTF_8);

						ZipStreamVO zipStreamVO = brandZipMap.get(sellingCompanyTI);

						if (zipStreamVO == null) {

							zipStreamVO = new ZipStreamVO();
							brandZipMap.put(sellingCompanyTI, zipStreamVO);
						}

						ZipOutputStream zipOutputStream = zipStreamVO.getOutputStreamZip();

						zipOutputStream.putNextEntry(new ZipEntry(String.format(ENTRY_TEMPLATE_TOUR_INFO, contentRepository.getTourCode())));
						zipOutputStream.write(xmlDataTI);
						zipOutputStream.closeEntry();

						zipOutputStream.putNextEntry(new ZipEntry(String.format(ENTRY_TEMPLATE_TOUR_DEPARTURE, contentRepository.getTourCode())));
						zipOutputStream.write(xmlDataTD);
						zipOutputStream.closeEntry();

						zipStreamVO.getOutputStreamMD5().update(xmlDataTI);
						zipStreamVO.getOutputStreamMD5().update(xmlDataTD);
					}
				}
			}
		}
	}

	private String createSingleSellingCompanyData(TransformerType transformerType, String tourInfoXML) throws TransformerException {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(BUFFER_SIZE);

		Source source = new StreamSource(new StringReader(tourInfoXML));
		Result result = new StreamResult(outputStream);

		transformerMap.get(transformerType).transform(source, result);

		return new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
	}
}
