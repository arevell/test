package com.ttc.ch2.ui.moduls.tour.services;

import java.io.FileNotFoundException;
import java.util.Date;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.zkoss.zul.Filedownload;

import com.ttc.ch2.bl.filecollect.FileCollectService;
import com.ttc.ch2.bl.filecollect.FileCollectService.FileCollectVersion;
import com.ttc.ch2.bl.filecollect.FileCollectServiceException;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.ui.common.exceptions.CH2Exception;

@Service
public class FileCollectV1WeaveService {

	private static final Logger logger = LoggerFactory.getLogger(FileCollectV1WeaveService.class);

	@Inject
	protected FileCollectService fileCollectService;

	public void download(SellingCompany selected) throws FileNotFoundException {
		try {
			byte[] data = fileCollectService.getLatestVersion(selected.getCode(), FileCollectVersion.V1);
			Filedownload.save(data, "application/zip", selected.getCode()+ "_" + new Date().getTime() + ".zip");

		} catch (FileCollectServiceException e) {
			throw new CH2Exception(e);
		}
	}
}
