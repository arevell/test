package com.ttc.ch2.ui.moduls.tour.services;

import java.io.IOException;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ttc.ch2.bl.filecollect.FileCollectService;
import com.ttc.ch2.bl.filecollect.FileCollectService.FileCollectVersion;
import com.ttc.ch2.bl.filecollect.FileCollectServiceException;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.ui.common.exceptions.CH2Exception;
import com.ttc.ch2.ui.common.exceptions.NoFoundResourceException;
import com.ttc.ch2.ui.common.exceptions.PermissionException;

@Service
public class GetOutgoingArchivesWeaveService {

	private static final String ERROR_NO_VERSION = "Could not found outgoing archives for the version: [%s]";

	private static final Logger activityLogger = LoggerFactory.getLogger("ch2.activity.GetOutgoingArchivesController");

	@Inject
	private FileCollectService fileCollectService;

//	@Audit
	public void getOutgoingArchivesV3(HttpServletRequest request,
									  HttpServletResponse response,
									  String version,
									  String sellingCompanyCode,
									  String token) {
		checkAuthenticated();
		try {
			download(version, sellingCompanyCode, response);
			activityLogger.info("USER: {} called getOutgoingArchivesV3() for version: {}", SecurityHelper.getLoginSilent(), version);
		} catch (IllegalArgumentException e) {
			throw new CH2Exception(String.format(ERROR_NO_VERSION, version));
		} catch (FileCollectServiceException | IOException e) {
			throw new CH2Exception(e.getMessage(), e);
		}
	}

//	@Audit
	public void getOutgoingArchivesV1(HttpServletRequest request,
			HttpServletResponse response,
			String sellingCompanyCode,
			String token) {

		checkAuthenticated();
		try {
			download("V1", sellingCompanyCode, response);
			activityLogger.info("USER: {} called getOutgoingArchivesV1()", SecurityHelper.getLoginSilent());
		} catch (IllegalArgumentException e) {
			throw new CH2Exception(String.format(ERROR_NO_VERSION, "V1"));
		} catch (FileCollectServiceException | IOException e) {
			throw new CH2Exception(e.getMessage(), e);
		}
	}

	private void download(String version, String sellingCompanyCode, HttpServletResponse response) throws FileCollectServiceException, IOException {
		FileCollectVersion fileCollectVersion = FileCollectVersion.valueOf(version.toUpperCase());
		byte[] resultData = fileCollectService.getLatestVersion(sellingCompanyCode, fileCollectVersion);
		if(resultData==null){
			throw new NoFoundResourceException("Source not found for selling company code:"+sellingCompanyCode);
		}		
		int lenght=resultData.length;
		response.setHeader("Content-disposition", "attachment; filename = " + sellingCompanyCode + "_" + new Date().getTime() + ".zip");
		response.setContentType("application/zip");
		response.setContentLength(lenght);
		response.getOutputStream().write(resultData);
		response.getOutputStream().flush();
	}
	

    
    protected void checkAuthenticated(){        
        if(SecurityHelper.isAuthenticated()==false){
            throw new PermissionException("Authenticated not exist");
        }
    }
}
