package com.ttc.ch2.ui.moduls.tour.common;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.util.resource.Labels;

import com.ttc.ch2.bl.filecollect.FileCollectVO;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.ui.common.zkcontrolers.AbstractEncryptedParamHandelComp;

public abstract class BaseFileCollect extends AbstractEncryptedParamHandelComp {

	private static final Logger logger = LoggerFactory.getLogger(BaseFileCollect.class);

	protected boolean dataExist;
	
	private String user;
	private String titleBrand;
	private List<FileCollectVO> listFileCollectVOs;
	
	public abstract String generateLink(SellingCompany selected);
	
	
	protected void baseInit(List<FileCollectVO> listFileCollectVOs) {
		logger.trace("BaseFileCollect:baseInit-start");
		
		this.listFileCollectVOs=listFileCollectVOs;
		this.titleBrand=Labels.getLabel("tour.file_collect.group_box_title") + ":";
		this.dataExist=listFileCollectVOs.size()>0;
		
		if(SecurityHelper.isUserCcapi())
			this.user=SecurityHelper.getUserCCAPIPrincipal().getUserDb().getUsername();
		
		logger.trace("BaseFileCollect:baseInit-end");
	}

	public boolean isDataExist() {
		return dataExist;
	}

	public void setDataExist(boolean dataExist) {
		this.dataExist = dataExist;
	}

	public List<FileCollectVO> getListFileCollectVOs() {
		return listFileCollectVOs;
	}

	public void setListFileCollectVOs(List<FileCollectVO> listFileCollectVOs) {
		this.listFileCollectVOs = listFileCollectVOs;
	}

	public String getTitleBrand() {
		return titleBrand;
	}

	public void setTitleBrand(String titleBrand) {
		this.titleBrand = titleBrand;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}
}
