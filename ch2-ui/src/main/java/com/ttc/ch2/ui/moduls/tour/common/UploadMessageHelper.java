package com.ttc.ch2.ui.moduls.tour.common;

import com.ttc.ch2.bl.upload.common.UploadMessage;
import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.security.UserContext;

public class UploadMessageHelper {
	
	
	public void setupProperty()
	{
		String loc_class="";
		String loc_message="";
		UploadMessage localUploadMessage=(UploadMessage) SessionHelper.getAttributeFromUserContext(UserContext.UserContextStaticName.UPLOADED_FILE_STATUS_MSG);
		if(localUploadMessage!=null){					
			if(localUploadMessage.getTypeMsg()==TypeMsg.INF){
				loc_class="hightlight";
			}
			else{
				loc_class="error_bold";
			}			
			loc_message=localUploadMessage.getMessage();
		}						
	}

}
