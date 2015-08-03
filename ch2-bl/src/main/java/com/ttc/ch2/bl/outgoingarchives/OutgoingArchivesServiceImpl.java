package com.ttc.ch2.bl.outgoingarchives;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.ttc.ch2.bl.upload.PermissionDeniedException;
import com.ttc.ch2.common.AuthorityHelper;
import com.ttc.ch2.common.FunctionType;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.auth.CCAPIAuthority;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.domain.user.UserGui;

@Service
public class OutgoingArchivesServiceImpl implements OutgoingArchivesService {

	private static final String SEPARATOR_PATH = "/";
	private static final String SEPARATOR_FILE_NAME = "-";
	private static final String SEPARATOR_FILE_EXTENSION = ".";
	private static final String PATTERN_KEY = SEPARATOR_PATH + "%s" + SEPARATOR_PATH + "%s";
	private static final String PATTERN_FILE = "%s" + SEPARATOR_PATH + "%s" + SEPARATOR_FILE_NAME + "%d" + SEPARATOR_FILE_EXTENSION + "zip";
	private static final String PATTERN_DIRECTORY = "%s" + SEPARATOR_PATH + "%s" + SEPARATOR_PATH + "%s";
	private static final String ENTRY_TOUR_INFO = "tour_info/";
	private static final String ENTRY_TOUR_DEPARTURE = "tour_departure/";
	private static final String ERROR_INTERNAL = "An internal error has occurred during execution of %s: [%s]";
	private static final String ERROR_NO_DATA_FOUND = "No data for sellingCompanyCode: [%s]";
	private static final String PERMISION_DANIED_CCAPI = "Permission denied for sellingCompanyCode: [%s] and function [%s]";
	private static final String PERMISION_DANIED_GUI = "Permission denied for sellingCompanyCode: [%s] and function [%s]";
	private static final String INVALID_USER = "Unexpected type of user";


	@Value("${content.repository.path}")
	private String contentRepositoryDirectory;

	@Value("${outgoing.archives.tour.info.v1.temp.directory}")
	private String outgoingArchivesTITempDirectoryV1;

	@Value("${outgoing.archives.tour.info.v3.temp.directory}")
	private String outgoingArchivesTITempDirectoryV3;

	@Value("${outgoing.archives.tour.departure.v1.temp.directory}")
	private String outgoingArchivesTDTempDirectoryV1;

	@Value("${outgoing.archives.tour.departure.v3.temp.directory}")
	private String outgoingArchivesTDTempDirectoryV3;

	@Value("${outgoing.archives.tour.data.v1.latest.directory}")
	private String outgoingArchivesTDLatestDirectoryV1;

	@Value("${outgoing.archives.tour.data.v3.latest.directory}")
	private String outgoingArchivesTDLatestDirectoryV3;

	@Value("${outgoing.archives.tour.info.v1.history.temp.directory}")
	private String outgoingArchivesTIHistoryTempDirectoryV1;

	@Value("${outgoing.archives.tour.info.v3.history.temp.directory}")
	private String outgoingArchivesTIHistoryTempDirectoryV3;

	@Value("${outgoing.archives.tour.departure.v1.history.temp.directory}")
	private String outgoingArchivesTDHistoryTempDirectoryV1;

	@Value("${outgoing.archives.tour.departure.v3.history.temp.directory}")
	private String outgoingArchivesTDHistoryTempDirectoryV3;

	@Value("${outgoing.archives.tour.data.v1.history.latest.directory}")
	private String outgoingArchivesTDHistoryLatestDirectoryV1;

	@Value("${outgoing.archives.tour.data.v3.history.latest.directory}")
	private String outgoingArchivesTDHistoryLatestDirectoryV3;


	private class SellingCompanyFinder implements Predicate<File> {

		private Set<String> companies = null;

		SellingCompanyFinder(UserCCAPI userCCAPI, FunctionType functionType) {

			companies = Sets.newHashSet();

			for (CCAPIAuthority authority : userCCAPI.getCcapiAuthorities()) {

				if (authority.getFunction().getName().equals(functionType.getSimpleName())) {
					companies.add(authority.getSellingCompany().getCode());
				}
			}
		}

		@Override
		public boolean apply(File input) {
			return companies.contains(input.getName());
		}
	}

	private class DirectoryFinder implements Predicate<File> {

		private Set<String> brands = null;

		DirectoryFinder(UserGui userGui) {

			brands = Sets.newHashSet();

			for (Brand brand : userGui.getBrands()) {
				brands.add(brand.getCode());
			}
		}

		DirectoryFinder(UserCCAPI userCCAPI, FunctionType function) {

			brands = Sets.newHashSet();

			for (CCAPIAuthority authority : userCCAPI.getCcapiAuthorities()) {

				if (authority.getFunction().getName().equals(function.getSimpleName())) {
					brands.add(authority.getSellingCompany().getBrand().getCode());
				}
			}
		}

		@Override
		public boolean apply(File input) {
			return brands.contains(input.getName());
		}
	}


	@Override
	public void moveData(String brandCode, DataType dataType, Direction direction) throws OutgoingArchivesServiceException {

		String srcDirectoryPathV1 = null;
		String dstDirectoryPathV1 = null;
		String srcDirectoryPathV3 = null;
		String dstDirectoryPathV3 = null;

		switch (dataType) {

			case TourData:

				srcDirectoryPathV1 = Direction.TempToHistory.equals(direction) ? outgoingArchivesTDLatestDirectoryV1 : outgoingArchivesTDHistoryLatestDirectoryV1;
				dstDirectoryPathV1 = Direction.TempToHistory.equals(direction) ? outgoingArchivesTDHistoryLatestDirectoryV1 : outgoingArchivesTDLatestDirectoryV1;
				srcDirectoryPathV3 = Direction.TempToHistory.equals(direction) ? outgoingArchivesTDLatestDirectoryV3 : outgoingArchivesTDHistoryLatestDirectoryV3;
				dstDirectoryPathV3 = Direction.TempToHistory.equals(direction) ? outgoingArchivesTDHistoryLatestDirectoryV3 : outgoingArchivesTDLatestDirectoryV3;

				break;

			case TourDeparture:

				srcDirectoryPathV1 = Direction.TempToHistory.equals(direction) ? outgoingArchivesTDTempDirectoryV1 : outgoingArchivesTDHistoryTempDirectoryV1;
				dstDirectoryPathV1 = Direction.TempToHistory.equals(direction) ? outgoingArchivesTDHistoryTempDirectoryV1 : outgoingArchivesTDTempDirectoryV1;
				srcDirectoryPathV3 = Direction.TempToHistory.equals(direction) ? outgoingArchivesTDTempDirectoryV3 : outgoingArchivesTDHistoryTempDirectoryV3;
				dstDirectoryPathV3 = Direction.TempToHistory.equals(direction) ? outgoingArchivesTDHistoryTempDirectoryV3 : outgoingArchivesTDTempDirectoryV3;

				break;

			case TourInfo:

				srcDirectoryPathV1 = Direction.TempToHistory.equals(direction) ? outgoingArchivesTITempDirectoryV1 : outgoingArchivesTIHistoryTempDirectoryV1;
				dstDirectoryPathV1 = Direction.TempToHistory.equals(direction) ? outgoingArchivesTIHistoryTempDirectoryV1 : outgoingArchivesTITempDirectoryV1;
				srcDirectoryPathV3 = Direction.TempToHistory.equals(direction) ? outgoingArchivesTITempDirectoryV3 : outgoingArchivesTIHistoryTempDirectoryV3;
				dstDirectoryPathV3 = Direction.TempToHistory.equals(direction) ? outgoingArchivesTIHistoryTempDirectoryV3 : outgoingArchivesTITempDirectoryV3;

				break;

			default:

				break;
		}

		srcDirectoryPathV1 = combinePath(contentRepositoryDirectory, combinePath(srcDirectoryPathV1, brandCode));
		dstDirectoryPathV1 = combinePath(contentRepositoryDirectory, combinePath(dstDirectoryPathV1, brandCode));
		srcDirectoryPathV3 = combinePath(contentRepositoryDirectory, combinePath(srcDirectoryPathV3, brandCode));
		dstDirectoryPathV3 = combinePath(contentRepositoryDirectory, combinePath(dstDirectoryPathV3, brandCode));

		File srcDirectoryV1 = new File(srcDirectoryPathV1);
		File dstDirectoryV1 = new File(dstDirectoryPathV1);
		File srcDirectoryV3 = new File(srcDirectoryPathV3);
		File dstDirectoryV3 = new File(dstDirectoryPathV3);

		try {

			if (srcDirectoryV1.exists()) {
				FileUtils.deleteDirectory(dstDirectoryV1);
				FileUtils.moveDirectory(srcDirectoryV1, dstDirectoryV1);
			}

			if (srcDirectoryV3.exists()) {
				FileUtils.deleteDirectory(dstDirectoryV3);
				FileUtils.moveDirectory(srcDirectoryV3, dstDirectoryV3);
			}

		} catch (IOException e) {
			throw new OutgoingArchivesServiceException(String.format(ERROR_INTERNAL, "moveData", e.getMessage()));
		}
	}


	@Override
	public void createLatestVersion(String brandCode) throws OutgoingArchivesServiceException {

		try {

			moveData(brandCode, DataType.TourData, Direction.TempToHistory);

			createLatestVersion(combinePath(contentRepositoryDirectory, combinePath(outgoingArchivesTITempDirectoryV1, brandCode)),
								combinePath(contentRepositoryDirectory, combinePath(outgoingArchivesTDTempDirectoryV1, brandCode)),
								combinePath(contentRepositoryDirectory, combinePath(outgoingArchivesTDLatestDirectoryV1, brandCode)));

			createLatestVersion(combinePath(contentRepositoryDirectory, combinePath(outgoingArchivesTITempDirectoryV3, brandCode)),
								combinePath(contentRepositoryDirectory, combinePath(outgoingArchivesTDTempDirectoryV3, brandCode)),
								combinePath(contentRepositoryDirectory, combinePath(outgoingArchivesTDLatestDirectoryV3, brandCode)));

		} catch (IOException e) {
			throw new OutgoingArchivesServiceException(String.format(ERROR_INTERNAL, "createLatestVersion", e.getMessage()));
		}
	}

	@Override
	public List<OutgoingArchivesVO> getList(OutgoingArchivesVersion outgoingArchivesVersion) {

		String pathVersion = OutgoingArchivesVersion.V1.equals(outgoingArchivesVersion) ? outgoingArchivesTDLatestDirectoryV1 : outgoingArchivesTDLatestDirectoryV3;
		String pathArchive = combinePath(contentRepositoryDirectory, pathVersion);

		File[] brandDirectories = new File(pathArchive).listFiles();

		if (brandDirectories == null) {
			return null;
		}

		DirectoryFinder finder = getDirectoryFinder(outgoingArchivesVersion);

		if (finder == null) {
			return null;
		}

		Iterable<File> brandDirectoriesData = Iterables.filter(Lists.newArrayList(brandDirectories), finder);

		if (brandDirectoriesData.iterator().hasNext() == false) {
			return null;
		}

		List<OutgoingArchivesVO> outgoingArchivesList = new ArrayList<OutgoingArchivesVO>();

		for (File brandDirectory : brandDirectoriesData) {

			File[] companyDirectories = brandDirectory.listFiles();

			if (companyDirectories == null) {
				continue;
			}

			List<SellingCompany> sellingCompanyList = new ArrayList<SellingCompany>();

			Iterable<File> companyDirectoriesData = Iterables.filter(Lists.newArrayList(companyDirectories), getSellingCompanyFinder(outgoingArchivesVersion));

			for (File companyDirectory : companyDirectoriesData) {

				SellingCompany sellingCompany = new SellingCompany();
				sellingCompany.setCode(companyDirectory.getName());

				sellingCompanyList.add(sellingCompany);
			}

			Brand brand = new Brand();
			brand.setCode(brandDirectory.getName());

			OutgoingArchivesVO outgoingArchives = new OutgoingArchivesVO();
			outgoingArchives.setBrand(brand);
			outgoingArchives.setSellingCompanies(sellingCompanyList);

			outgoingArchivesList.add(outgoingArchives);
		}

		return outgoingArchivesList;
	}

	@Override
	public byte[] getLatestVersion(String sellingCompanyCode, OutgoingArchivesVersion outgoingArchivesVersion) throws OutgoingArchivesServiceException {

		if (SecurityHelper.isUserCcapi()) {

			FunctionType function = OutgoingArchivesVersion.V1.equals(outgoingArchivesVersion) ? FunctionType.OA_VIEW_V1 : FunctionType.OA_VIEW_V3;

			if (AuthorityHelper.hasAuthorityUserCcapi(function, sellingCompanyCode) == false) {
				throw new PermissionDeniedException(String.format(PERMISION_DANIED_CCAPI, sellingCompanyCode, function.getSimpleName()));
			}

		} else if (SecurityHelper.isUserGui()) {

			if (AuthorityHelper.hasAuthorityForUserGui(sellingCompanyCode) == false) {
				throw new PermissionDeniedException(String.format(PERMISION_DANIED_GUI, sellingCompanyCode));
			}

		} else {
			throw new OutgoingArchivesServiceException(INVALID_USER);
		}

		String pathVersion = OutgoingArchivesVersion.V1.equals(outgoingArchivesVersion) ? outgoingArchivesTDLatestDirectoryV1 : outgoingArchivesTDLatestDirectoryV3;
		String pathArchive = String.format(PATTERN_DIRECTORY, combinePath(contentRepositoryDirectory, pathVersion), sellingCompanyCode.substring(0, 2), sellingCompanyCode);

		File directoryArchive = new File(pathArchive);

		if (!directoryArchive.exists() || directoryArchive.listFiles() == null || directoryArchive.listFiles().length == 0) {
			throw new OutgoingArchivesServiceException(String.format(ERROR_NO_DATA_FOUND, sellingCompanyCode));
		}

		long timeStampCur = 0l;
		File fileResult = null;

		for (File fileArchive : directoryArchive.listFiles()) {

			long timeStampNew = Long.parseLong(StringUtils.substringBetween(fileArchive.getName(), SEPARATOR_FILE_NAME, SEPARATOR_FILE_EXTENSION));

			if (timeStampCur < timeStampNew) {

				timeStampCur = timeStampNew;
				fileResult = fileArchive;
			}
		}

		byte[] resultData = null;

		try {
			resultData = FileUtils.readFileToByteArray(fileResult);
		} catch (IOException e) {
			throw new OutgoingArchivesServiceException(String.format(ERROR_INTERNAL, "getLatestVersion", e.getMessage()));
		}

		return resultData;
	}


	private void createLatestVersion(String pathTourInfo, String pathTourDeparture, String pathTourData) throws IOException {

		Map<String, File> filesMapTI = prepareFilesMap(pathTourInfo);
		Map<String, File> filesMapTD = prepareFilesMap(pathTourDeparture);

		if (filesMapTI == null || filesMapTD == null) {
			return;
		}

		String entryCur = StringUtils.EMPTY;
		ZipOutputStream outputStream = null;

		for (Entry<String, File> entryTI : filesMapTI.entrySet()) {

			for (Entry<String, File> entryTD : filesMapTD.entrySet()) {

				if (entryTI.getKey().equals(entryTD.getKey())) {

					String entryNew = entryTI.getKey().replace(entryTI.getValue().getName(), StringUtils.EMPTY);

					if (!entryCur.equals(entryNew)) {

						entryCur = entryNew;

						if (outputStream != null) {
							outputStream.close();
						}

						String pathDirectory = combinePath(pathTourData, entryCur);
						String pathFile = String.format(PATTERN_FILE, pathDirectory, StringUtils.substringBetween(entryCur, SEPARATOR_PATH), new Date().getTime());

						new File(pathDirectory).mkdirs();

						outputStream = new ZipOutputStream(new FileOutputStream(pathFile));
					}

					FileInputStream inputStream = new FileInputStream(entryTI.getValue());
					outputStream.putNextEntry(new ZipEntry(ENTRY_TOUR_INFO + entryTI.getValue().getName()));

					IOUtils.copy(inputStream, outputStream);

					inputStream.close();
					outputStream.closeEntry();

					inputStream = new FileInputStream(entryTD.getValue());
					outputStream.putNextEntry(new ZipEntry(ENTRY_TOUR_DEPARTURE + entryTD.getValue().getName()));

					IOUtils.copy(inputStream, outputStream);

					inputStream.close();
					outputStream.closeEntry();
				}
			}
		}

		if (outputStream != null) {
			outputStream.close();
		}
	}

	private Map<String, File> prepareFilesMap(String pathBase) {

		Map<String, File> filesMap = new HashMap<String, File>();

		File[] companyDirectories = new File(pathBase).listFiles();

		if (companyDirectories == null) {
			return null;
		}

		for (File companyDirectory : companyDirectories) {

			File[] tourFiles = companyDirectory.listFiles();

			if (tourFiles == null) {
				continue;
			}

			for (File tourFile : tourFiles) {
				filesMap.put(String.format(PATTERN_KEY, companyDirectory.getName(), tourFile.getName()), tourFile);
			}
		}

		return new TreeMap<String, File>(filesMap);
	}

	private String combinePath(String path1, String path2) {
		return new File(path1, path2).toString();
	}

	private DirectoryFinder getDirectoryFinder(OutgoingArchivesVersion outgoingArchivesVersion) {

		UserGui userGui = null;

		try {
			userGui = SecurityHelper.getUserGuiPrincipal().getUserDb();
		} catch (UnsupportedOperationException u) { }

		UserCCAPI userCCAPI = null;

		if (userGui == null) {

			try {
				userCCAPI = SecurityHelper.getUserCCAPIPrincipal().getUserDb();
			} catch (UnsupportedOperationException u) { }
		}

		if (userGui == null && userCCAPI == null) {
			return null;
		}

		return (userGui != null ? new DirectoryFinder(userGui) : new DirectoryFinder(userCCAPI, outgoingArchivesVersion == OutgoingArchivesVersion.V1 ? FunctionType.OA_VIEW_V1 : FunctionType.OA_VIEW_V3));
	}

	@SuppressWarnings("unchecked")
	private Predicate<File> getSellingCompanyFinder(OutgoingArchivesVersion outgoingArchivesVersion) {

		UserCCAPI userCCAPI = null;

		try {
			userCCAPI = SecurityHelper.getUserCCAPIPrincipal().getUserDb();
		} catch (UnsupportedOperationException u) { }

		return (Predicate<File>) (userCCAPI != null ? new SellingCompanyFinder(userCCAPI, outgoingArchivesVersion == OutgoingArchivesVersion.V1 ? FunctionType.OA_VIEW_V1 : FunctionType.OA_VIEW_V3) : Predicates.alwaysTrue());
	}
}
