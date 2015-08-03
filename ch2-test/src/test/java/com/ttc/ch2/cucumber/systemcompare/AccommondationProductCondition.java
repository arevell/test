package com.ttc.ch2.cucumber.systemcompare;

import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.AccommodationProductType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.ProductRoomType;

public class AccommondationProductCondition implements  CompareCondition{		
	public boolean checkCondition(Object fieldValueA, Object fieldValueB) {
		
		if (fieldValueA instanceof AccommodationProductType && fieldValueB instanceof AccommodationProductType) {				
									
			if(((AccommodationProductType) fieldValueA).getRooms() != null && ((AccommodationProductType) fieldValueB).getRooms() != null) {
				ProductRoomType roomTB = ((AccommodationProductType) fieldValueA).getRooms().getRoom().get(0);

				if (roomTB != null) {	
					for (ProductRoomType roomPS : ((AccommodationProductType) fieldValueB).getRooms().getRoom()) {

						if (roomTB.getType().toString() == roomPS.getType().toString() && 
							roomTB.getPrice()!= null && roomPS.getPrice()!= null 							
							) {									
							if(roomTB.getPrice().getAdult()!=null && roomPS.getPrice().getAdult()!=null){
								return roomTB.getPrice().getAdult().equals(roomPS.getPrice().getAdult());	
							}
							else if(roomTB.getPrice().getChild()!=null && roomPS.getPrice().getChild()!=null){
								return roomTB.getPrice().getChild().equals(roomPS.getPrice().getChild());
							}
						}
					}
				}
			}
		}
		return true;
	}
}	