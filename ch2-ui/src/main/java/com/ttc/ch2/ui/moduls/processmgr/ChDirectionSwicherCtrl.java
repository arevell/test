package com.ttc.ch2.ui.moduls.processmgr;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.collections.comparators.NullComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zul.Messagebox;

import com.ttc.ch2.common.ordering.OrderingBrandByCode;
import com.ttc.ch2.common.ordering.OrderingTourInfoTransferByBrandCode;
import com.ttc.ch2.dao.transfer.TourInfoTransferDAO;
import com.ttc.ch2.domain.transfer.TourInfoTransfer;
import com.ttc.ch2.ui.moduls.processmgr.common.BrandStatus;
import com.ttc.ch2.ui.moduls.processmgr.common.FormData;
import com.ttc.ch2.ui.moduls.processmgr.common.Status;

@Component("ChDirectionSwicherCtrl")
@Scope("request")
public class ChDirectionSwicherCtrl {

	private static final Logger logger = LoggerFactory.getLogger(ChDirectionSwicherCtrl.class);

	@Inject
	TourInfoTransferDAO tourInfoTransferDAO;

	private FormData form;

	@Init
	public void init() {

		logger.trace("ChDirectionSwicherCtrl:init-start");
			List<TourInfoTransfer> list=tourInfoTransferDAO.findAll();
			Collections.sort(list, new OrderingTourInfoTransferByBrandCode());
			form = new FormData(list);
		logger.trace("ChDirectionSwicherCtrl:getFormData-end");
	}

	@Command
	@NotifyChange({ "msgStatusOperation" })
	public void onSave(@BindingParam("element") BrandStatus changedElement) {

		Status status = Status.valueOf(changedElement.getCbxValue().getValue());
		String brandCode = changedElement.getBrand().getCode();

		TourInfoTransfer tourInfoTransfer = tourInfoTransferDAO.getTourInfoTransfer(brandCode);

		if (tourInfoTransfer != null) {

			if (Status.CH1_CH2_DOWNLOAD.equals(status)) {

				tourInfoTransfer.setIsDownloadEnabled(true);
				tourInfoTransfer.setIsUploadEnabled(false);

			} else if (Status.CH2_CH1_UPLOAD.equals(status)) {

				tourInfoTransfer.setIsDownloadEnabled(false);
				tourInfoTransfer.setIsUploadEnabled(true);
			}

			tourInfoTransferDAO.save(tourInfoTransfer);
		}

		String msgStatusOperation = Labels.getLabel("processmgr.operation.status", new Object[] { changedElement.getBrand().getBrandName(), changedElement.getBrand().getCode() });
		String title = Labels.getLabel("common.operation.success");
		Messagebox.show(msgStatusOperation, title, Messagebox.OK, Messagebox.INFORMATION);
	}
	
	
	@Command
	@NotifyChange({ "form" })
	public void onSelect(@BindingParam("element") BrandStatus changedElement){
						
		Status status = Status.valueOf(changedElement.getCbxValue().getValue());
		changedElement.setShowMsg(Status.CH1_CH2_DOWNLOAD==status);		
	}

	public FormData getForm() {
		return form;
	}

	public void setForm(FormData form) {
		this.form = form;
	}

	
}
