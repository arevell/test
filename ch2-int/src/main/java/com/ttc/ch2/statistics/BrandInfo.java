package com.ttc.ch2.statistics;

import java.util.Date;


public class BrandInfo {
		public static long SERIAL_INITIAL=-1l; 
		public static long SERIAL_WAITING=-2l;
		private Date tiOperationStart;
		private long tiSerial=SERIAL_INITIAL;
		private Date tiOperationStop;
		private Date tdExtendedOperationStart;
		private long tdExtendedSerial=SERIAL_INITIAL;
		private Date tdExtendedOperationStop;
		private long tdExtendedImportTime;
		private Date tdOperationStart;
		private long tdSerial=SERIAL_INITIAL;
		private Date tdOperationStop;
		private long tdImportTime;
		private long tiImportTime;
		private Date startShortIndexation;
		private long shortIndexationSerial=SERIAL_INITIAL;
		private Date stopShortIndexation;
		private Date startLongIndexation;
		private long longIndexationSerial=SERIAL_INITIAL;
		private Date stopLongIndexation;
		private long shortIndexationTime;
		private long longIndexationTime;
		private long toursCount;
		private long tourPairsCount;
		private long departuresCount;
		private long allTDXMLSize;
		private long allTIXMLSize;
		
		public Date getTiOperationStart() {
			return tiOperationStart;
		}
		public void setTiOperationStart(Date tiOperationStart) {
			this.tiOperationStart = tiOperationStart;
		}
		public Date getTiOperationStop() {
			return tiOperationStop;
		}
		public void setTiOperationStop(Date tiOperationStop) {
			this.tiOperationStop = tiOperationStop;
		}
		public Date getTdOperationStart() {
			return tdOperationStart;
		}
		public void setTdOperationStart(Date tdOperationStart) {
			this.tdOperationStart = tdOperationStart;
		}
		public Date getTdOperationStop() {
			return tdOperationStop;
		}
		public void setTdOperationStop(Date tdOperationStop) {
			this.tdOperationStop = tdOperationStop;
		}
		public long getTdImportTime() {
			return tdImportTime;
		}
		public void setTdImportTime(long tdImportTime) {
			this.tdImportTime = tdImportTime;
		}
		public long getTiImportTime() {
			return tiImportTime;
		}
		public void setTiImportTime(long tiImportTime) {
			this.tiImportTime = tiImportTime;
		}
		
		public long getToursCount() {
			return toursCount;
		}
		public void setToursCount(long toursCount) {
			this.toursCount = toursCount;
		}
		public long getTourPairsCount() {
			return tourPairsCount;
		}
		public void setTourPairsCount(long tourPairsCount) {
			this.tourPairsCount = tourPairsCount;
		}
		public long getDeparturesCount() {
			return departuresCount;
		}
		public void setDeparturesCount(long departuresCount) {
			this.departuresCount = departuresCount;
		}
		public long getAllTDXMLSize() {
			return allTDXMLSize;
		}
		public void setAllTDXMLSize(long allTDXMLSize) {
			this.allTDXMLSize = allTDXMLSize;
		}
		public long getAllTIXMLSize() {
			return allTIXMLSize;
		}
		public void setAllTIXMLSize(long allTIXMLSize) {
			this.allTIXMLSize = allTIXMLSize;
		}
		public Date getTdExtendedOperationStart() {
			return tdExtendedOperationStart;
		}
		public void setTdExtendedOperationStart(Date tdExtendedOperationStart) {
			this.tdExtendedOperationStart = tdExtendedOperationStart;
		}
		public Date getTdExtendedOperationStop() {
			return tdExtendedOperationStop;
		}
		public void setTdExtendedOperationStop(Date tdExtendedOperationStop) {
			this.tdExtendedOperationStop = tdExtendedOperationStop;
		}
		public long getTdExtendedImportTime() {
			return tdExtendedImportTime;
		}
		public void setTdExtendedImportTime(long tdExtendedImportTime) {
			this.tdExtendedImportTime = tdExtendedImportTime;
		}
		public long getShortIndexationTime() {
			return shortIndexationTime;
		}
		public void setShortIndexationTime(long shortIndexationTime) {
			this.shortIndexationTime = shortIndexationTime;
		}
		public long getLongIndexationTime() {
			return longIndexationTime;
		}
		public void setLongIndexationTime(long longIndexationTime) {
			this.longIndexationTime = longIndexationTime;
		}
		public Date getStartShortIndexation() {
			return startShortIndexation;
		}
		public void setStartShortIndexation(Date startShortIndexation) {
			this.startShortIndexation = startShortIndexation;
		}
		public Date getStopShortIndexation() {
			return stopShortIndexation;
		}
		public void setStopShortIndexation(Date stopShortIndexation) {
			this.stopShortIndexation = stopShortIndexation;
		}
		public Date getStartLongIndexation() {
			return startLongIndexation;
		}
		public void setStartLongIndexation(Date startLongIndexation) {
			this.startLongIndexation = startLongIndexation;
		}
		public Date getStopLongIndexation() {
			return stopLongIndexation;
		}
		public void setStopLongIndexation(Date stopLongIndexation) {
			this.stopLongIndexation = stopLongIndexation;
		}
		public long getTiSerial() {
			return tiSerial;
		}
		public void setTiSerial(long tiSerial) {
			this.tiSerial = tiSerial;
		}
		public long getTdExtendedSerial() {
			return tdExtendedSerial;
		}
		public void setTdExtendedSerial(long tdExtendedSerial) {
			this.tdExtendedSerial = tdExtendedSerial;
		}
		public long getTdSerial() {
			return tdSerial;
		}
		public void setTdSerial(long tdSerial) {
			this.tdSerial = tdSerial;
		}
		public long getShortIndexationSerial() {
			return shortIndexationSerial;
		}
		public void setShortIndexationSerial(long shortIndexationSerial) {
			this.shortIndexationSerial = shortIndexationSerial;
		}
		public long getLongIndexationSerial() {
			return longIndexationSerial;
		}
		public void setLongIndexationSerial(long longIndexationSerial) {
			this.longIndexationSerial = longIndexationSerial;
		}	
		
}
