package com.ttc.ch2.bl.outgoingarchives;

import java.util.List;

@Deprecated
public interface OutgoingArchivesService {

	@Deprecated
	public static enum OutgoingArchivesVersion { V1, V3 };

	@Deprecated
	public static enum Direction { TempToHistory, HistoryToTemp };

	@Deprecated
	public static enum DataType { TourInfo, TourDeparture, TourData };

	@Deprecated
	public void moveData(String brandCode, DataType dataType, Direction direction) throws OutgoingArchivesServiceException;

	@Deprecated
	public void createLatestVersion(String brandCode) throws OutgoingArchivesServiceException;

	@Deprecated
	public List<OutgoingArchivesVO> getList(OutgoingArchivesVersion outgoingArchivesVersion);

	@Deprecated
	public byte[] getLatestVersion(String sellingCompanyCode, OutgoingArchivesVersion outgoingArchivesVersion) throws OutgoingArchivesServiceException;
}
