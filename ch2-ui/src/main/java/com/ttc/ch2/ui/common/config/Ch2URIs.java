package com.ttc.ch2.ui.common.config;

public enum Ch2URIs {
	
	CHGPSWD("/modules/auth/chgPswd.zul"),	
	COMMENT_DETAILS("/modules/details/commentDetails.zul"),
	SCHEDULER("/modules/jobs/scheduler.zul"),
	JOB_DETAILS("/modules/jobs/jobDetails.zul"),
	AUDIT("/modules/audit/audit_panel.zul"),
	MESSAGE_LIST("/modules/messages/message_list.zul"),
	OPERATOR_LIST("/modules/users/usergui_list.zul"),
	OPERATOR_WIZ("/modules/users/usergui_wiz.zul"),
	OPERATOR_WIZ_DEL("/modules/users/usergui_wiz_del.zul"),
	OPERATOR_CCAPI_LIST("/modules/users/userccapi_list.zul"),
	OPERATOR_CCAPI_WIZ("/modules/users/userccapi_wiz.zul"),
	OPERATOR_CCAPI_WIZ_DEL("/modules/users/userccapi_wiz_del.zul"),
	TOUR_MATCH_STATUS("/modules/tour/tour_match_status.zul"),
	TOUR_MATCH_STATUS_HTML("/modules/tour/tour_match_status_html.zul"),
	TOUR_MATCH_STATUS_HTML_VIEW("/modules/tour/tour_match_status_view.htm"),
	TOUR_INF_DEP_VIEW("/modules/tour/tour_inf_dep_view.zul"),
	UPDATE_TOUR_INFO("/modules/tour/upload_tour_info.zul"),
	UPLOAD_FRAME("uploadFrame.zul"),
	TOUR_INFO_REPOSITORY("/modules/tour/tour_info_repository.zul"),
	TOUR_INFO_REPOSITORY_EXT("/modules/tour/tour_info_repository_ext.zul"),
	TOUR_INFO_REPOSITORY_OLDEXT("/modules/tour/tour_info_repository_oldext.zul"),
	TOUR_INFO_REPOSITORY_NEWEXT("/modules/tour/tour_info_repository_newext.zul"),
	TOUR_DEPARTURE_REPOSITORY("/modules/tour/tour_departure_repository.zul"),
	TOUR_DEPARTURE_REPOSITORY_EXT("/modules/tour/tour_departure_repository_ext.zul"),
	TOUR_DEPARTURE_REPOSITORY_OLDEXT("/modules/tour/tour_departure_repository_oldext.zul"),
	TOUR_DEPARTURE_REPOSITORY_NEWEXT("/modules/tour/tour_departure_repository_newext.zul"),
	FILE_COLLECT("/modules/tour/file_collect.zul"),
	FILE_COLLECT_EXT("/modules/tour/file_collect_ext.zul"),
	FILE_COLLECT_OLDEXT("/modules/tour/file_collect_oldext.zul"),
	FILE_COLLECT_NEWEXT("/modules/tour/file_collect_newext.zul"),
	BROCHURE_ENGINE("/modules/brochure/view.htm"),
	DIR_SWITCHER("/modules/processmgr/ch2_dir_switch.zul"),
	HEALTH_CHECKER("/modules/health/health_checker.zul"),
	AJAX("zkau");
	
	private String path = null;
	

	private Ch2URIs(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
	
	public String getPathWithAppName(String appName) {
		return appName+path;
	}
}
