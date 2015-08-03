package com.ttc.ch2.ui.moduls.processmgr.common;

import java.util.List;

import com.google.common.collect.Lists;
import com.ttc.ch2.domain.transfer.TourInfoTransfer;

public class FormData {

	private List<BrandStatus> list = Lists.newArrayList();
	private ComboValue[] statusOptionsTab;

	public FormData(List<TourInfoTransfer> tourInfoTransferList) {

		if (tourInfoTransferList == null) {
			return;
		}

		statusOptionsTab = new ComboValue[tourInfoTransferList.size()];

		for (int i = 0; i < tourInfoTransferList.size(); i++) {

			ComboValue comboValue = tourInfoTransferList.get(i).getIsDownloadEnabled()
					? new ComboValue(Status.CH1_CH2_DOWNLOAD.toString(), Status.CH1_CH2_DOWNLOAD.getDesc())
					: new ComboValue(Status.CH2_CH1_UPLOAD.toString(), Status.CH2_CH1_UPLOAD.getDesc());

			BrandStatus brandStatus=new BrandStatus(tourInfoTransferList.get(i).getBrand(), comboValue);
			Status status = Status.valueOf(brandStatus.getCbxValue().getValue());
			brandStatus.setShowMsg(Status.CH1_CH2_DOWNLOAD==status);
			list.add(brandStatus);
			statusOptionsTab[i] = comboValue;
		}
	}

	public List<BrandStatus> getList() {
		return list;
	}

	public void setList(List<BrandStatus> list) {
		this.list = list;
	}

	public ComboValue[] getStatusOptionsTab() {
		return statusOptionsTab;
	}

	public void setStatusOptionsTab(ComboValue[] statusOptionsTab) {
		this.statusOptionsTab = statusOptionsTab;
	}
}
