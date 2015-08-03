package com.ttc.ch2.ui.moduls.jobs.common;

import java.util.Date;
import java.util.List;

import org.zkoss.util.resource.Labels;

import com.google.common.collect.Lists;
import com.ttc.ch2.common.enums.CronExpresion;
import com.ttc.ch2.domain.Brand;

public class SchedulerForm {

	public static Brand emptyBrand=null;
	static
	{
		emptyBrand=new Brand();
		emptyBrand.setBrandName(Labels.getLabel("jobs.scheduler.component.combobox.emptyString"));
		emptyBrand.setId(-1l);
	}
	
	private Date chgTime;	
	private List<Brand> brands;
	private Brand selectedBrand;
	
	private List<CronTimeVO> cronTimes=null;
	private CronTimeVO selectedCronTimeVO=null;

	public SchedulerForm(List<Brand> brands,CronExpresion ce){
		buildBrands(brands);
		selectedBrand=this.brands.get(0);
		cronTimes=CronTimeVO.getList();
		
		for (CronTimeVO vo : cronTimes) {
			if(vo.getExpresion().equals(ce)){
				selectedCronTimeVO=vo;		
			}
		}
	}
	
	public Date getChgTime() {
		return chgTime;
	}

	public void setChgTime(Date chgTime) {
		this.chgTime = chgTime;
	}

	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		buildBrands(brands);
	}

	public Brand getSelectedBrand() {
		return selectedBrand;
	}

	public void setSelectedBrand(Brand selectedBrand) {
		this.selectedBrand = selectedBrand;
	}	
	
	private void buildBrands(List<Brand> brands){
		
		
		this.brands=Lists.newArrayList();
		this.brands.add(emptyBrand);
		this.brands.addAll(brands);
	}
	
	public List<CronTimeVO> getCronTimes() {
		return cronTimes;
	}

	public void setCronTimes(List<CronTimeVO> cronTimes) {
		this.cronTimes = cronTimes;
	}

	public CronTimeVO getSelectedCronTimeVO() {
		return selectedCronTimeVO;
	}

	public void setSelectedCronTimeVO(CronTimeVO selectedCronTimeVO) {
		this.selectedCronTimeVO = selectedCronTimeVO;
	}
}
