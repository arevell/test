package com.ttc.ch2.statistics;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ttc.ch2.dao.tour.ContentRepositoryDAO;

@Service
public class StatisticsBean {
	
	@Inject
	private ContentRepositoryDAO dao;
	
	private static Logger logger = LoggerFactory.getLogger(StatisticsBean.class);
	
	private Map<String, BrandInfo> brandMap = new HashMap<String, BrandInfo>();
	private Random random = new Random();
	
	public void registerBrand(String brandCode) {
		BrandInfo bi = new BrandInfo();
		brandMap.put(brandCode, bi);
	}
	
	public BrandInfo getBrandInfo(String brandCode) {
		return brandMap.get(brandCode);
	}
	
	public long registerStartTIUpload(String brandCode){
		BrandInfo bi = brandMap.get(brandCode);
		if(bi.getTiSerial() == BrandInfo.SERIAL_INITIAL ) {
			bi.setTiOperationStop(null);
			bi.setTiOperationStart(new Date());
			long serial = random.nextLong();
			bi.setTiSerial(serial);
			return serial;
		}else {
			return BrandInfo.SERIAL_WAITING;
		}
		
	}
	
	public void registerStopTIUpload(String brandCode, long serial) {
		BrandInfo bi = brandMap.get(brandCode);
		if(bi.getTiSerial() == serial ) {
			bi.setTiOperationStop(new Date());
			bi.setTiImportTime(bi.getTiOperationStop().getTime() - bi.getTiOperationStart().getTime());
			updateStatsFromDB(brandCode);
			bi.setTiSerial(BrandInfo.SERIAL_INITIAL);
		}
	}
	
	public long registerStartTDImport(String brandCode) {
		BrandInfo bi = brandMap.get(brandCode);
		if(bi.getTdSerial() == BrandInfo.SERIAL_INITIAL) {
			bi.setTdOperationStop(null);
			bi.setTdOperationStart(new Date());
			long serial = random.nextLong();
			bi.setTdSerial(serial);
			return serial;
		}else {
			return BrandInfo.SERIAL_WAITING;
		}
	}
	
	public void registerStopTDImport(String brandCode, long serial) {
		BrandInfo bi = brandMap.get(brandCode);
		if(bi.getTdSerial() == serial) {
			bi.setTdOperationStop(new Date());
			bi.setTdImportTime(bi.getTdOperationStop().getTime() - bi.getTdOperationStart().getTime());
			updateStatsFromDB(brandCode);
			bi.setTdSerial(BrandInfo.SERIAL_INITIAL);
		}
	}
	
    public long registerStartExtendedTDImport(String brandCode) {
    	BrandInfo bi = brandMap.get(brandCode);
    	if( bi.getTdExtendedSerial() == BrandInfo.SERIAL_INITIAL ) {
    		bi.setTdExtendedOperationStop(null);
    		bi.setTdExtendedOperationStart(new Date());
    		long serial = random.nextLong();
			bi.setTdExtendedSerial(serial);
			return serial;
    	}
    	else {
    		return BrandInfo.SERIAL_WAITING;
    	}
	}
	
	public void registerStopExtendedTDImport(String brandCode, long serial) {
		BrandInfo bi = brandMap.get(brandCode);
		if(bi.getTdExtendedSerial() == serial) {
			bi.setTdExtendedOperationStop(new Date());
			bi.setTdExtendedImportTime(bi.getTdExtendedOperationStop().getTime() - bi.getTdExtendedOperationStart().getTime());
			updateStatsFromDB(brandCode);
			bi.setTdExtendedSerial(BrandInfo.SERIAL_INITIAL);
		}
	}
	
	public long registerStartShortIndexationDelete(String brandCode) {
		BrandInfo bi = brandMap.get(brandCode);
		if(bi.getShortIndexationSerial() == BrandInfo.SERIAL_INITIAL) {
			bi.setStartShortIndexation(new Date());
			bi.setStopShortIndexation(null);
			long serial = random.nextLong();
			bi.setShortIndexationSerial(serial);
			return serial;
		}
		else {
    		return BrandInfo.SERIAL_WAITING;
    	}
	}
	
    public void registerStopShortIndexationDelete(String brandCode, long serial) {
    	BrandInfo bi = brandMap.get(brandCode);
    	if( bi.getShortIndexationSerial() == serial) {
    		bi.setStopShortIndexation(new Date());
    		bi.setShortIndexationTime(bi.getStopShortIndexation().getTime() - bi.getStartShortIndexation().getTime());
    		bi.setShortIndexationSerial(BrandInfo.SERIAL_INITIAL);
    	}
	}
    
    public long registerStartShortIndexationAdd(String brandCode) {
    	BrandInfo bi = brandMap.get(brandCode);
    	if(bi.getShortIndexationSerial() == BrandInfo.SERIAL_INITIAL) {
    		bi.setStartShortIndexation(new Date());
    		bi.setStopShortIndexation(null);
    		long serial = random.nextLong();
			bi.setShortIndexationSerial(serial);
			return serial;
		}
		else {
    		return BrandInfo.SERIAL_WAITING;
    	}
	}
	
    public void registerStopShortIndexationAdd(String brandCode, long serial) {
    	BrandInfo bi = brandMap.get(brandCode);
    	if( bi.getShortIndexationSerial() == serial) {
    		bi.setStopShortIndexation(new Date());
    		bi.setShortIndexationTime(bi.getShortIndexationTime() + (bi.getStopShortIndexation().getTime() - bi.getStartShortIndexation().getTime()));
    		bi.setShortIndexationSerial(BrandInfo.SERIAL_INITIAL);
    	}
	}
    
    public long registerStartLongIndexation(String brandCode) {
    	BrandInfo bi = brandMap.get(brandCode);
    	if(bi.getLongIndexationSerial() == BrandInfo.SERIAL_INITIAL) {
    		bi.setStopLongIndexation(null);
    		bi.setStartLongIndexation(new Date());
    		long serial = random.nextLong();
			bi.setLongIndexationSerial(serial);
			return serial;
		}
		else {
    		return BrandInfo.SERIAL_WAITING;
    	}
    	
	}
	
    public void registerStopLongIndexation(String brandCode, long serial) {
    	BrandInfo bi = brandMap.get(brandCode);
    	if(bi.getLongIndexationSerial() == serial) {
    		bi.setStopLongIndexation(new Date());
    		bi.setLongIndexationTime(bi.getStopLongIndexation().getTime() - bi.getStartLongIndexation().getTime());
    		bi.setLongIndexationSerial(BrandInfo.SERIAL_INITIAL);
    	}
	}
    
    public void registerTourPairsCount(String brandCode, long tourCount) {
    	BrandInfo bi = brandMap.get(brandCode);
    	bi.setTourPairsCount(tourCount);
    }
    
    private void updateStatsFromDB(String brandCode) {
    	try {
    		Long[] stats = dao.getCRStatistics(brandCode);
    		BrandInfo bi = brandMap.get(brandCode);
    		bi.setToursCount(stats[0]);
    		bi.setDeparturesCount(stats[1]);
    		bi.setAllTIXMLSize(stats[2] == null ? 0 : stats[2]);
    		bi.setAllTDXMLSize(stats[3] == null ? 0 : stats[3]);
    	}catch(Exception e) {
    		logger.error("", e);
    	}
    }
    
    
}
