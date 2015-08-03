package com.ttc.ch2.ui.moduls.tour;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelArray;
import org.zkoss.zul.event.PagingEvent;

import com.ttc.ch2.bl.upload.UploadService;
import com.ttc.ch2.bl.upload.common.TourInfoMessages;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.domain.TIBlobData;
import com.ttc.ch2.domain.auth.Role;
import com.ttc.ch2.domain.comment.TIComment;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.common.SortCondition;
import com.ttc.ch2.domain.common.SortCondition.Direction;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.exceptions.CH2Exception;
import com.ttc.ch2.ui.common.security.UserContext.UserContextStaticName;
import com.ttc.ch2.ui.common.zkcontrolers.AbstractEncryptedParamHandelComp;
import com.ttc.ch2.ui.moduls.details.common.DefaultCommentDecorator;
import com.ttc.ch2.ui.moduls.details.common.TiAdditionalDetailsDecorator;
import com.ttc.ch2.ui.moduls.details.common.TiErrorsDetailsDecorator;
import com.ttc.ch2.ui.moduls.details.common.TiMainCommentDecorator;
import com.ttc.ch2.ui.moduls.tour.common.PagingUploadFileCommentsModel;
import com.ttc.ch2.ui.moduls.tour.common.PagingUploadFileListModel;
import com.ttc.ch2.ui.moduls.tour.common.UploadTourInfoSourceDec;
import com.ttc.ch2.ui.moduls.tour.common.UploadTourInfoStatusDec;



@org.springframework.stereotype.Component("UploadTourInfo")
@Scope("request")
public class UploadTourInfo extends AbstractEncryptedParamHandelComp {
	
	private static final Logger logger=LoggerFactory.getLogger(UploadTourInfo.class);
	
	@Inject
	private UploadService uploadService;
	
	private UploadTourInfoFile filter;
	private UploadTourInfoFile selectedValue;
	private ListModel<String> chooseStatus;
	private ListModel<String> chooseSource;
	private PagingUploadFileListModel listModel;
	private PagingUploadFileCommentsModel commentsListModel;
		
	private UploadTourInfoStatusDec uploadTourInfoStatusDec;
	private UploadTourInfoSourceDec uploadTourInfoSourceDec;
	
	private boolean brandRole;
	private boolean showDetail=false;
	private String groupBoxTitle=null;
	private String choosenFile=null;
			
	@Init
	public void init() {
		logger.trace("UploadTourInfo:init-start");
		this.brandRole=SecurityHelper.hasAuthority(Role.BRAND);
		uploadTourInfoStatusDec=new UploadTourInfoStatusDec();
		uploadTourInfoSourceDec=new UploadTourInfoSourceDec();
		chooseStatus=new ListModelArray<String>(uploadTourInfoStatusDec.getValues());		
		chooseSource=new ListModelArray<String>(uploadTourInfoSourceDec.getValues());
		
		if(SessionHelper.getAttributeFromUserContext(UserContextStaticName.FORM_LIST_UPLOAD)==null){
		filter=new UploadTourInfoFile();
		filter.setDateUpload(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
		SessionHelper.putAttributeToUserContext(UserContextStaticName.FORM_LIST_UPLOAD, filter);
		}
		else{
			filter=(UploadTourInfoFile) SessionHelper.getAttributeFromUserContext("FORM_LIST_UPLOAD");
		}
		
		if(SessionHelper.getAttributeFromUserContext(UserContextStaticName.SELECTED_VALUE_UPLOAD)!=null){
			onShowDetail((UploadTourInfoFile) SessionHelper.getAttributeFromUserContext(UserContextStaticName.SELECTED_VALUE_UPLOAD));			
		}
		else{
			commentsListModel=new PagingUploadFileCommentsModel(uploadService,new UploadTourInfoFile());	
		}	
		QueryCondition condition=new QueryCondition(0, PagingUploadFileCommentsModel.PAGE_SIZE,new SortCondition("dateUpload", Direction.DESC));		
		SessionHelper.putAttributeToUserContext(UserContextStaticName.SORT_CONDITION_UPLOAD, condition);					
		listModel=new PagingUploadFileListModel(uploadService,condition,filter);			
		logger.trace("UploadTourInfo:init-end");
	}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);	
	}
	
////	@Command("upload")
//	@NotifyChange({"listModel"})
//	public void upload(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {		
//		logger.trace("UploadTourInfo:upload-start");
//		
//		Object objUploadEvent = ctx.getTriggerEvent();		
//		if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
//			UploadEvent upEvent  = (UploadEvent) objUploadEvent;
//		    Media media=upEvent.getMedia();		
//			fileOperation(media);			
//		}
//		logger.trace("UploadTourInfo:upload-end");
//	}
	
//	@Command("upload")
//	@NotifyChange({"listModel","choosenFile"})
//	public void chooseFile(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {		
//		logger.trace("UploadTourInfo:chooseFile-start");		
//		Object objUploadEvent = ctx.getTriggerEvent();		
//		if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
//			UploadEvent upEvent  = (UploadEvent) objUploadEvent;
//			Media media=upEvent.getMedia();					
//			if (validateUploadFile(media)) {
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();   
//				try {
//					ByteStreams.copy(media.getStreamData(), baos);
//					choosenFile=media.getName();
//				} catch (IOException e) {
//					Messagebox.show(e.getMessage(), Labels.getLabel("contenthub.upload_tour_info.validation_title_box"), Messagebox.OK, Messagebox.ERROR);
//					return;
//				}	
//				SessionHelper.putAttributeToUserContext(UserContextStaticName.UPLOADED_FILE, baos.toByteArray());
//			}					
//		}
//		logger.trace("UploadTourInfo:chooseFile-end");
//	}
	
	@Command("download")
	public void download(@BindingParam("element") UploadTourInfoFile selected)
	{
		logger.trace("UploadTourInfo:download-start");
	    try {
	    	TIBlobData zipData=uploadService.getFullData(selected.getId()).getZipData();
	    	Filedownload.save(zipData.getData(), "application/zip",selected.getName());		
		} catch (Exception e) {
			throw new CH2Exception(e);
		}
		logger.trace("UploadTourInfo:download-end");
	}
	
	@Command("onPagingList")
	@NotifyChange("listModel")
	public void onPagingList(BindContext ctx)
	{		
		logger.trace("UploadTourInfo:onPagingList-start");
		PagingEvent event = (PagingEvent) ctx.getTriggerEvent();	
		QueryCondition condition=(QueryCondition) SessionHelper.getAttributeFromUserContext(UserContextStaticName.SORT_CONDITION_UPLOAD);
		listModel=new PagingUploadFileListModel(uploadService,condition,filter,event.getActivePage());
		logger.trace("UploadTourInfo:onPagingList-end");
	}
	
	@Command
    @NotifyChange({"listModel"})
    public void changeFilter() {
    	logger.trace("OperatorList:changeFilter-start");
		filter.setStatus(uploadTourInfoStatusDec.getValueByString());	
		filter.setSourceUploadFile(uploadTourInfoSourceDec.getValueByString());
		SessionHelper.putAttributeToUserContext(UserContextStaticName.FORM_LIST_UPLOAD, filter);
				
		QueryCondition condition=(QueryCondition) SessionHelper.getAttributeFromUserContext(UserContextStaticName.SORT_CONDITION_UPLOAD);
    	listModel=new PagingUploadFileListModel(uploadService,condition,filter,0);
    	logger.trace("OperatorList:changeFilter-end");
    }
	
	@Command
	@NotifyChange({"listModel","showDetail","selectedValue","commentsListModel","groupBoxTitle"})
	public void onShowDetail(@BindingParam("element") UploadTourInfoFile selected)
	{
		showDetail=true;		
		selectedValue=selected;		
		SessionHelper.putAttributeToUserContext(UserContextStaticName.SELECTED_VALUE_UPLOAD, selectedValue);
		groupBoxTitle=Labels.getLabel("contenthub.upload_tour_info.title_details_prefix")+" "+selected.getName();
		commentsListModel=new PagingUploadFileCommentsModel(uploadService,selectedValue);
	}
	
	@Command("onPagingListComments")
	@NotifyChange("commentsListModel")
	public void onPagingListComments(BindContext ctx)
	{		
		logger.trace("UploadTourInfo:onPagingList-start");
		PagingEvent event = (PagingEvent) ctx.getTriggerEvent();				
		commentsListModel=new PagingUploadFileCommentsModel(uploadService,selectedValue,event.getActivePage());
		logger.trace("UploadTourInfo:onPagingList-end");
	}
	
	public boolean canDownloadFile(UploadTourInfoFile uploadTourInfoFile){
		return SecurityHelper.hasAuthority(Role.ADMINISTRATOR) && uploadTourInfoFile.getZipAttached() !=null && uploadTourInfoFile.getZipAttached();
	}
	
	public String calculateDecorator(TIComment comment){

		String result=DefaultCommentDecorator.name;
		if(TourInfoMessages.UPLOAD_STATUS==TourInfoMessages.getByCode(comment.getMessageCode())){
			result=TiMainCommentDecorator.name;
		}
		if(TourInfoMessages.UPLOAD_STATUS_INFO==TourInfoMessages.getByCode(comment.getMessageCode())){
			result=TiAdditionalDetailsDecorator.name;
		}		
		if(TourInfoMessages.UPLOAD_STATUS_ERROR==TourInfoMessages.getByCode(comment.getMessageCode())){
			result=TiErrorsDetailsDecorator.name;
		}		
		return result;
	}

//	// ---------------------- private methods ----------------------
//	private void fileOperation(Media media) {
//		logger.trace("UploadTourInfo:fileOperation-start");
//		if (validateUploadFile(media)) {
//			UploadTourInfoFile u = new UploadTourInfoFile();
//			u.setName(choosenFile);
////			u.setPath(UploadTourInfoDAO.PATH + media.getName());
////			u.setDateUpload(new Date());
//			InputStream stream=null;
//			try {
//				stream=new ByteArrayInputStream((byte[])SessionHelper.getAttributeFromUserContext(UserContextStaticName.UPLOADED_FILE));
//				uploadTourInfoService.addManualUploadTourInfoFile(u ,stream,SecurityHelper.getUserGuiPrincipal().getUserDb());				
//				Messagebox.show(Labels.getLabel("contenthub.upload_tour_info.msg_box"), Labels.getLabel("contenthub.upload_tour_info.msg_title_box"), Messagebox.OK, Messagebox.INFORMATION);
//			} catch (UploadServiceException e) {
//				logger.error("",e);
//				Messagebox.show(e.getMessage(), Labels.getLabel("contenthub.upload_tour_info.validation_title_box"), Messagebox.OK, Messagebox.ERROR);
//			}
//			finally {
//				if(stream!=null)
//					IOUtils.closeQuietly(stream);
//			}
//			QueryCondition condition=(QueryCondition) SessionHelper.getAttributeFromUserContext(UserContextStaticName.SORT_CONDITION_UPLOAD);
//			listModel=new PagingUploadFileListModel(uploadService,condition,filter,listModel.getActivePage());	
//		}
//		else {
//			Messagebox.show(Labels.getLabel("contenthub.upload_tour_info.validation_msg_box"), Labels.getLabel("contenthub.upload_tour_info.validation_title_box"), Messagebox.OK, Messagebox.ERROR);
//		}
//		logger.trace("UploadTourInfo:fileOperation-end");
//	}
//		
//	private boolean validateUploadFile(Media media) {
//		logger.trace("UploadTourInfo:validate-start");
//		boolean result=true;				
//		result&="zip".equals(media.getFormat());
//		result&=media.isBinary();
//		logger.trace("UploadTourInfo:validate-end");
//		return result;		
//	}
	

	// geter seters 
	public UploadTourInfoFile getFilter() {
		return filter;
	}

	public void setFilter(UploadTourInfoFile filter) {
		this.filter = filter;
	}
	
	public PagingUploadFileListModel getListModel() {
		return listModel;
	}

	public void setListModel(PagingUploadFileListModel listModel) {
		this.listModel = listModel;
	}

	public UploadTourInfoStatusDec getUploadTourInfoStatusDec() {
		return uploadTourInfoStatusDec;
	}

	public void setUploadTourInfoStatusDec(UploadTourInfoStatusDec uploadTourInfoStatusDec) {
		this.uploadTourInfoStatusDec = uploadTourInfoStatusDec;
	}

	public UploadTourInfoSourceDec getUploadTourInfoSourceDec() {
		return uploadTourInfoSourceDec;
	}

	public void setUploadTourInfoSourceDec(UploadTourInfoSourceDec uploadTourInfoSourceDec) {
		this.uploadTourInfoSourceDec = uploadTourInfoSourceDec;
	}
	

	public ListModel<String> getChooseStatus() {
		return chooseStatus;
	}

	public ListModel<String> getChooseSource() {
		return chooseSource;
	}

	public boolean isShowDetail() {
		return showDetail;
	}

	public void setShowDetail(boolean showDetail) {
		this.showDetail = showDetail;
	}

	public UploadTourInfoFile getSelectedValue() {
		return selectedValue;
	}

	public void setSelectedValue(UploadTourInfoFile selectedValue) {
		this.selectedValue = selectedValue;
	}

	public String getGroupBoxTitle() {
		return groupBoxTitle;
	}

	public void setGroupBoxTitle(String groupBoxTitle) {
		this.groupBoxTitle = groupBoxTitle;
	}

	public PagingUploadFileCommentsModel getCommentsListModel() {
		return commentsListModel;
	}

	public void setCommentsListModel(PagingUploadFileCommentsModel commentsListModel) {
		this.commentsListModel = commentsListModel;
	}

	public String getChoosenFile() {
		return choosenFile;
	}

	public void setChoosenFile(String choosenFile) {
		this.choosenFile = choosenFile;
	}

	public boolean isBrandRole() {
		return brandRole;
	}

	public void setBrandRole(boolean brandRole) {
		this.brandRole = brandRole;
	}
}
