package com.ttc.ch2.scheduler.service.departureextend;


public interface DepartureExtendedSynchronizeService {

	public void interrupt();

	public void processing();

	public String getBrandCode();
	public void setBrandCode(String brandCode);
		
	

}