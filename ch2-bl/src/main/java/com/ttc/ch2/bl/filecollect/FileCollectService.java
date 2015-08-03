package com.ttc.ch2.bl.filecollect;

import java.util.List;
import java.util.Map;

import com.ttc.ch2.bl.filecollect.FileCollectServiceImpl.ZipStreamVO;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.domain.Brand;

public interface FileCollectService {

	public static enum FileCollectVersion {
		V1, V3
	};

	public List<FileCollectVO> getList(FileCollectVersion fileCollectVersion) throws FileCollectServiceException;

	public byte[] getLatestVersion(String sellingCompanyCode, FileCollectVersion fileCollectVersion) throws FileCollectServiceException;

	public FileCollectVO createLatestVersion(ProcessName processName, String brandCode) throws FileCollectServiceException;

	public void saveLatestVersion(ProcessName processName, Brand brand, Map<String, ZipStreamVO> brandZipMapV1, Map<String, ZipStreamVO> brandZipMapV3, FileCollectVO fileCollectVO);
}
