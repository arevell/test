package com.ttc.ch2.statistics;

import java.util.Date;

public class BrandJmx implements BrandJmxMBean {

	private StatisticsBean statisticsBean;
	private String brandCode;
	
	public BrandJmx(String brandCode, StatisticsBean statisticsBean) {
		super();
		this.statisticsBean = statisticsBean;
		this.brandCode = brandCode;
		statisticsBean.registerBrand(brandCode);
	}
	
	@Override
	public Date getTiOperationStart() {
		return statisticsBean.getBrandInfo(brandCode).getTiOperationStart();
	}
	
	@Override
	public Date getTiOperationStop(){
		return statisticsBean.getBrandInfo(brandCode).getTiOperationStop();
	}
	
	@Override
	public Date getTdOperationStart(){
		return statisticsBean.getBrandInfo(brandCode).getTdOperationStart();
	}
	
	@Override
	public Date getTdOperationStop(){
		return statisticsBean.getBrandInfo(brandCode).getTdOperationStop();
	}	
	
	@Override
	public long getTdImportTime(){
		return statisticsBean.getBrandInfo(brandCode).getTdImportTime();
	}
	
	@Override
	public long getTiImportTime(){
		return statisticsBean.getBrandInfo(brandCode).getTiImportTime();
	}
	
		
	@Override
	public long getToursCount(){
		return statisticsBean.getBrandInfo(brandCode).getToursCount();
	}
	
	@Override
	public long getTourPairsCount(){
		return statisticsBean.getBrandInfo(brandCode).getTourPairsCount();
	}
	
	@Override
	public long getDeparturesCount(){
		return statisticsBean.getBrandInfo(brandCode).getDeparturesCount();
	}
	
	@Override
	public long getAllTDXMLSize(){
		return statisticsBean.getBrandInfo(brandCode).getAllTDXMLSize();
	}
	
	@Override
	public long getAllTIXMLSize(){
		return statisticsBean.getBrandInfo(brandCode).getAllTIXMLSize();
	}

	@Override
	public Date getExtendedTdOperationStart() {
		return statisticsBean.getBrandInfo(brandCode).getTdExtendedOperationStart();
	}

	@Override
	public Date getExtendedTdOperationStop() {
		return statisticsBean.getBrandInfo(brandCode).getTdExtendedOperationStop();
	}

	@Override
	public long getExtendedTdImportTime() {
		return statisticsBean.getBrandInfo(brandCode).getTdExtendedImportTime();
	}

	@Override
	public long getShortIndexationTime() {
		return statisticsBean.getBrandInfo(brandCode).getShortIndexationTime();
	}

	@Override
	public long getLongIndexationTime() {
		return statisticsBean.getBrandInfo(brandCode).getLongIndexationTime();
	}
}
