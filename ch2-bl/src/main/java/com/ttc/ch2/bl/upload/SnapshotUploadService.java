package com.ttc.ch2.bl.upload;

import com.ttc.ch2.api.ccapi.v3.GetTourDataUploadStatusRequest;
import com.ttc.ch2.api.ccapi.v3.GetTourDataUploadStatusResponse;

public interface SnapshotUploadService {

	GetTourDataUploadStatusResponse getSnapshotUploadTour(GetTourDataUploadStatusRequest request);

}
