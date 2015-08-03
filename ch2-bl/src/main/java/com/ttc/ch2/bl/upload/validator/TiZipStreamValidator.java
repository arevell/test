package com.ttc.ch2.bl.upload.validator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.base.Splitter;
import com.ttc.ch2.bl.upload.UploadService;
import com.ttc.ch2.bl.upload.UploadServiceException;
import com.ttc.ch2.bl.upload.common.LogOperationHelper;
import com.ttc.ch2.bl.upload.common.OperationStatus;
import com.ttc.ch2.bl.upload.common.TourInfoMessages;
import com.ttc.ch2.bl.upload.common.ZipHelper;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.upload.UploadTourInfoDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;
import com.ttc.ch2.domain.upload.UploadTourInfoFileStatus;

@Component
public class TiZipStreamValidator {

	private static final Logger logger = LoggerFactory.getLogger(TiZipStreamValidator.class);
		
	public static final String UPLOAD_FILENAME_PATTERN="\\A[A-Z_0-9]{2}-[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}-[0-9]{2}-[0-9]{2}Z\\.zip$";
	
	@Inject
	private UploadService uploadService;
		
	@Inject
	private BrandDAO brandDAO; 
	
	@Inject
	private UploadTourInfoDAO uploadTourInfoDAO;
	
	@Value("${ch2.ti.maxsize}")
	private long zipFileMaxSize;
	
	@Value("${ch2.tizip.maxfiles}")
	private long zipMaxFiles;
	
	@Value("${ch2.tizip.maxzipsforbrand}")
	private int maxZipsForBrand;
	
	public void validZipStream(OperationStatus opStatus,UploadTourInfoFile uploadTourInfoFile, InputStream inputStream)
	{
		// calculate brand code
		String brandCode="[not finded]";
		if(uploadTourInfoFile.getName().charAt(2)=='-'){
			brandCode=Splitter.on("-").split(uploadTourInfoFile.getName()).iterator().next();	
			Brand brand=brandDAO.findByBrandCode(brandCode);
			if(brand == null) {
				LogOperationHelper.logMessage(logger, opStatus, TourInfoMessages.BRAND_DONT_EXIST,brandCode,uploadTourInfoFile.getName());	
				throw new UploadServiceException(TourInfoMessages.getMessage(TourInfoMessages.BRAND_DONT_EXIST, uploadTourInfoFile.getName()));
			}
			else{
				uploadTourInfoFile.setBrand(brand);		
				opStatus.setBrandCode(brandCode);
			}
		}
		
		if(uploadTourInfoFile.getName().length()!=27)
		{
			LogOperationHelper.logMessage(logger, opStatus, TourInfoMessages.INCORRECT_FILE_NAME_LENGHT, uploadTourInfoFile.getName());
        	throw new UploadServiceException(TourInfoMessages.getMessage(TourInfoMessages.INCORRECT_FILE_NAME_LENGHT, uploadTourInfoFile.getName()));
		}
		
		if(!ZipHelper.isZipStream(inputStream))
		{
        	LogOperationHelper.logMessage(logger, opStatus, TourInfoMessages.INCORRECT_ZIP, uploadTourInfoFile.getName());
        	throw new UploadServiceException(TourInfoMessages.getMessage(TourInfoMessages.INCORRECT_ZIP, uploadTourInfoFile.getName()));        	
		}
						
		if(uploadTourInfoFile.getName().matches(UPLOAD_FILENAME_PATTERN)==false)
		{
			LogOperationHelper.logMessage(logger, opStatus, TourInfoMessages.INCORRECT_FILE_NAME, uploadTourInfoFile.getName(),"not according to an agreed naming convention ‘[BrandCode]-[ISO DateTime Format].zip’");
			throw new UploadServiceException(TourInfoMessages.getMessage(TourInfoMessages.INCORRECT_FILE_NAME, uploadTourInfoFile.getName()));
		}
		else
		{				
			if(opStatus.getExtraPermissionChecker().checkBrandPermission()==false)
			{
				LogOperationHelper.logMessage(logger, opStatus, TourInfoMessages.PERMISSION_DENIED_INVALID_BRANDIN_ZIP_FILE,brandCode, uploadTourInfoFile.getName());
				throw new UploadServiceException(TourInfoMessages.getMessage(TourInfoMessages.PERMISSION_DENIED_INVALID_BRANDIN_ZIP_FILE,brandCode, uploadTourInfoFile.getName()));
			}
		}	
	}
	
	public void searchDuplicateUploadFileName(UploadTourInfoFile uploadTourInfoFile,OperationStatus opStatus){
		
		UploadTourInfoFile f=new UploadTourInfoFile();
		f.setName(uploadTourInfoFile.getName());
		List<UploadTourInfoFile> duplicateUploadFileName= uploadService.getUploadTourInfoList(null,f);
		if(duplicateUploadFileName.size()>0)
		{
			LogOperationHelper.logMessage(logger, opStatus,  TourInfoMessages.DUPLICATE_FILE_NAME, uploadTourInfoFile.getName());
			throw new UploadServiceException(TourInfoMessages.getMessage(TourInfoMessages.DUPLICATE_FILE_NAME, uploadTourInfoFile.getName()));
		}
	}
	
	public void validZipExcludeZipBomb(InputStream zipStream) {
	
        long filesInZip=0l; 
        try(ZipInputStream stream = new ZipInputStream(zipStream)) {
            ZipEntry entry;
            while((entry = stream.getNextEntry())!=null)
            {
            	filesInZip++;
                long xmlSize = entry.getSize();
                if(filesInZip > zipMaxFiles) {
                	throw new UploadServiceException(TourInfoMessages.getMessage(TourInfoMessages.ZIP_MAX_FILES_EXCEEDED, zipMaxFiles));
                }
                if(xmlSize > zipFileMaxSize || xmlSize < 0) {
                	throw new UploadServiceException(TourInfoMessages.getMessage(TourInfoMessages.ZIP_XML_SIZE_EXCEEDED, zipFileMaxSize));
                }
 
            }
        } catch (IOException e) {
			throw new UploadServiceException(e);
		}
    
	}
	
	public void validateZipUplodPermission(UploadTourInfoFile uploadTourInfoFile) {
		Brand brand;
		if(uploadTourInfoFile.getName().charAt(2)=='-'){
			String brandCode=Splitter.on("-").split(uploadTourInfoFile.getName()).iterator().next();	
			brand=brandDAO.findByBrandCode(brandCode);
			if(brand == null) {	
				throw new UploadServiceException(TourInfoMessages.getMessage(TourInfoMessages.BRAND_DONT_EXIST, uploadTourInfoFile.getName()));
			}
			UploadTourInfoFile search = new UploadTourInfoFile();
			search.setBrand(brand);
			search.setStatus(UploadTourInfoFileStatus.REJECTED);
			Date now = new Date(); 
			search.setDateUpload(new Date(now.getYear(),now.getMonth(),now.getDate()));
			int zipForBrandCount = uploadTourInfoDAO.getUploadTourInfoCount(search);
			if(zipForBrandCount > maxZipsForBrand) {
				throw new UploadServiceException(TourInfoMessages.getMessage(TourInfoMessages.MAX_ZIPS_EXCEEDED, maxZipsForBrand));
			}
		}
		else {
			throw new UploadServiceException(TourInfoMessages.getMessage(TourInfoMessages.INCORRECT_FILE_NAME, uploadTourInfoFile.getName()));
		}
		
	}
}
