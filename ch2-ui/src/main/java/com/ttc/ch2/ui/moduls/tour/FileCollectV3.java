package com.ttc.ch2.ui.moduls.tour;

import java.io.FileNotFoundException;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Messagebox;

import com.ttc.ch2.bl.filecollect.FileCollectService;
import com.ttc.ch2.bl.filecollect.FileCollectService.FileCollectVersion;
import com.ttc.ch2.bl.filecollect.FileCollectServiceException;
import com.ttc.ch2.bl.filecollect.FileCollectVO;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.ui.common.security.PathType;
import com.ttc.ch2.ui.moduls.tour.common.BaseFileCollect;
import com.ttc.ch2.ui.moduls.tour.services.FileCollectV3WeaveService;

@org.springframework.stereotype.Component("fileCollectV3")
@Scope("request")
public class FileCollectV3 extends BaseFileCollect {

	private static final Logger logger = LoggerFactory.getLogger(FileCollectV3.class);
	private static final Logger activityLogger = LoggerFactory.getLogger("ch2.activity.FileCollectV3");

	@Inject
	protected FileCollectService fileCollectService;
	
	@Inject
	private FileCollectV3WeaveService service;

	@Init
	public void init() {
		logger.trace("FileCollect:init-start");
		List<FileCollectVO> listFileCollectVOs;
		try {
			listFileCollectVOs = fileCollectService.getList(FileCollectVersion.V3);
			if (listFileCollectVOs == null || listFileCollectVOs.size() == 0) {
				Messagebox.show(Labels.getLabel("tour.file_collect.data.from.service.dont.exist"), Labels.getLabel("common.error.titlebox.name"), Messagebox.OK, Messagebox.ERROR);
			}
			baseInit(listFileCollectVOs);
			logger.trace("FileCollect:init-end");
		} catch (FileCollectServiceException e) {
			logger.error("FileCollect:init-error");
		}
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	
	public String generateLink(SellingCompany selected){		
		return "/"+PathType.O_ARCHIVES.getPartPath()+"/V3/"+selected.getCode()+".zip?token="+SecurityHelper.getToken();
	}
	
	

    @Command("download")
    public void download(@BindingParam("element") SellingCompany selected) throws FileNotFoundException {
        logger.trace("FileCollectV3:download-start");

        service.download(selected);
        
        activityLogger.info("USER: {}  collected file for the {}", SecurityHelper.getLoginSilent(), selected.getCode());
        logger.trace("FileCollectV3:download-end");
    }	
}
