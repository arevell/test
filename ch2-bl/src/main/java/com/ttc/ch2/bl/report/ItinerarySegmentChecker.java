package com.ttc.ch2.bl.report;

import java.math.BigInteger;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Itinerary;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.ItinerarySegment;

public class ItinerarySegmentChecker {

	public boolean checkItinerarySegment(Itinerary itinerary) {

		if (itinerary == null || itinerary.getItinerarySegment() == null || itinerary.getItinerarySegment().size() == 0) {
			return true;
		}

		Map<BigInteger, BigInteger> dayDurationMap = new TreeMap<BigInteger, BigInteger>();

		for (ItinerarySegment itinerarySegment : itinerary.getItinerarySegment()) {

			BigInteger startDay = itinerarySegment.getStartDay();
			BigInteger duration = itinerarySegment.getDuration();

			if (startDay == null || duration == null || dayDurationMap.containsKey(startDay)) {
				return false;
			}

			dayDurationMap.put(startDay, duration);
		}

		@SuppressWarnings("unchecked")
		Entry<BigInteger, BigInteger>[] dayDurationArray = dayDurationMap.entrySet().toArray(new Entry[0]);

		if (dayDurationArray[0].getKey().compareTo(BigInteger.ONE) != 0) {
			return false;
		} else if (dayDurationArray.length == 1) {
			return true;
		}

		for (int index = 1; index < dayDurationArray.length; index++) {

			if (dayDurationArray[index - 1].getKey().add(dayDurationArray[index - 1].getValue()).compareTo(dayDurationArray[index].getKey()) != 0) {
				return false;
			}
		}

		return true;
	}
	
	
}
