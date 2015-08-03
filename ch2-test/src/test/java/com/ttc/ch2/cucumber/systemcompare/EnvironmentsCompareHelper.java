package com.ttc.ch2.cucumber.systemcompare;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.reflect.FieldUtils;
import org.elasticsearch.common.collect.Lists;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.AccommodationProductType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.ProductRoomType;
import com.wsout.habs.itropicsbuildws.WsAccommodationProductRoomVO;
import com.wsout.habs.itropicsbuildws.WsAccommodationProductVO;

@SuppressWarnings("restriction")
public class EnvironmentsCompareHelper {

	public final static String LIST_TAG = "|list|";
	public final static String NULL_TAG = "|null|";
	public final static String AND_TAG = "|and|";
	public final static String EOL_UNIX = "\n";
	public final static String EOL_WINDOWS = "\r\n";
	public final static String ITEM_DELIMITER = ":";
	public final static String PATH_ITEM_DELIMITER = "\\.";
	public final static String NAME_ITEM_TEMPLATE = "%s:%s - %s";
	public final static String NO_DATA_RESPONSE = "%s returned no data for given tour [%s] and selling company [%s]";
	public final static String MISSING_EQUIVALENT = "item: [%s], field: [%s], binding property: [%s], value: [%s] not found in %s\r\n";
	public final static String DIFFERENCES_MESSAGE = "Differences have occurred:\r\n%s";
	public final static String DIFFERENCES_DETAILS_MESSAGE = "item: [%2$s], field: [%3$s], %1$s in %6$s: [%4$s], %1$s in %7$s: [%5$s]\r\n";
	
	private String systemNameA;
	private String systemNameB;
	
	private List<CompareCondition> dedicatedCondition=Lists.newArrayList();
	
	
	

	public EnvironmentsCompareHelper(String systemNameA, String systemNameB) {
		super();
		this.systemNameA = systemNameA;
		this.systemNameB = systemNameB;
	}
	
	/**
	 * This code need re-factoring coped from EnvironmentsComparerUtils
	 * */
	@SuppressWarnings("unchecked")
	public  void compareFields(Object fieldValueA, Object fieldValueB, String fieldPath, String fieldPathLog, String fieldLabel, String itemCode, List<String> notComparableFields, Set<String> differencesList, Map<String, String> bindingMap) throws Exception {

		String fieldRemainingPath = fieldPath;
		String fieldCurrentPath = fieldPath;

		for (String fieldItem : fieldPath.split(PATH_ITEM_DELIMITER)) {

			fieldRemainingPath = StringUtils.removeStart(fieldRemainingPath, fieldItem);
			fieldRemainingPath = StringUtils.removeStart(fieldRemainingPath, ".");

			if (LIST_TAG.equals(fieldItem)) {

				String fieldList = fieldRemainingPath.split(PATH_ITEM_DELIMITER)[0];

				fieldRemainingPath = StringUtils.removeStart(fieldRemainingPath, fieldList);
				fieldRemainingPath = StringUtils.removeStart(fieldRemainingPath, ".");

				fieldCurrentPath = StringUtils.removeEnd(fieldCurrentPath, fieldRemainingPath);
				fieldCurrentPath = StringUtils.removeEnd(fieldCurrentPath, ".");

				String fieldFullPath = StringUtils.substringBefore(fieldPathLog, fieldCurrentPath) + fieldCurrentPath;

				Collection<Object> fieldListTB = (Collection<Object>) FieldUtils.readField(fieldValueA, fieldList, true);
				Collection<Object> fieldListPS = (Collection<Object>) FieldUtils.readField(fieldValueB, fieldList, true);

				int listTBSize = fieldListTB != null ? fieldListTB.size() : 0;
				int listPSSize = fieldListPS != null ? fieldListPS.size() : 0;

				if (bindingMap != null && bindingMap.containsKey(fieldFullPath)) {

					for (int i = 0; i < listTBSize; i++) {

						fieldValueA = fieldListTB.toArray()[i];

						Object bindingPropertyValueTB = null;

						if (bindingMap.get(fieldFullPath).contains(AND_TAG)) {

							bindingPropertyValueTB = FieldUtils.readField(fieldValueA, StringUtils.substringBefore(bindingMap.get(fieldFullPath), AND_TAG), true).toString() +
													 ITEM_DELIMITER +
													 FieldUtils.readField(fieldValueA, StringUtils.substringAfter(bindingMap.get(fieldFullPath), AND_TAG), true).toString();

						} else if (NULL_TAG.equals(bindingMap.get(fieldFullPath))) {
							bindingPropertyValueTB = fieldValueA;
						} else {
							bindingPropertyValueTB = FieldUtils.readField(fieldValueA, bindingMap.get(fieldFullPath), true);
						}

						if (bindingPropertyValueTB instanceof JAXBElement) {
							bindingPropertyValueTB = ((JAXBElement<Object>) bindingPropertyValueTB).getValue();
						}

						if (bindingPropertyValueTB instanceof Enum) {
							bindingPropertyValueTB = FieldUtils.readField(bindingPropertyValueTB, "value", true);
						}

						boolean bindingFound = false;

						for (int j = 0; j < listPSSize; j++) {

							fieldValueB = fieldListPS.toArray()[j];

							Object bindingPropertyValuePS = null;

							if (bindingMap.get(fieldFullPath).contains(AND_TAG)) {

								bindingPropertyValuePS = FieldUtils.readField(fieldValueB, StringUtils.substringBefore(bindingMap.get(fieldFullPath), AND_TAG), true).toString() +
														 ITEM_DELIMITER +
														 FieldUtils.readField(fieldValueB, StringUtils.substringAfter(bindingMap.get(fieldFullPath), AND_TAG), true).toString();

							} else if (NULL_TAG.equals(bindingMap.get(fieldFullPath))) {
								bindingPropertyValuePS = fieldValueB;
							} else {
								bindingPropertyValuePS = FieldUtils.readField(fieldValueB, bindingMap.get(fieldFullPath), true);
							}

							if (bindingPropertyValuePS instanceof JAXBElement) {
								bindingPropertyValuePS = ((JAXBElement<Object>) bindingPropertyValuePS).getValue();
							}

							if (bindingPropertyValuePS instanceof Enum) {
								bindingPropertyValuePS = FieldUtils.readField(bindingPropertyValuePS, "value", true);
							}

							if (bindingPropertyValueTB != null && bindingPropertyValuePS != null && bindingPropertyValueTB.toString().equals(bindingPropertyValuePS.toString()) && checkDedicatedCondition(fieldValueA, fieldValueB)) {

								bindingFound = true;

								String fieldCurrentLabel = StringUtils.replace(fieldLabel, fieldCurrentPath, StringUtils.replace(fieldCurrentPath, LIST_TAG, "|" + bindingPropertyValueTB.toString() + "|"));

								compareFields(fieldValueA, fieldValueB, fieldRemainingPath, fieldPathLog, fieldCurrentLabel, itemCode, notComparableFields, differencesList, bindingMap);
								break;
							}
						}

						if (!bindingFound) {
							differencesList.add(String.format(MISSING_EQUIVALENT, itemCode, fieldFullPath, bindingMap.get(fieldFullPath), bindingPropertyValueTB != null ? bindingPropertyValueTB.toString() : StringUtils.EMPTY,systemNameB));
						}
					}

				} else {

					for (int i = 0; i < listTBSize && i < listPSSize; i++) {

						fieldValueA = fieldListTB.toArray()[i];
						fieldValueB = fieldListPS.toArray()[i];

						String fieldCurrentLabel = StringUtils.replace(fieldLabel, fieldCurrentPath, StringUtils.replace(fieldCurrentPath, LIST_TAG, "|" + i + "|"));

						compareFields(fieldValueA, fieldValueB, fieldRemainingPath, fieldPathLog, fieldCurrentLabel, itemCode, notComparableFields, differencesList, bindingMap);
					}
				}

				if (listTBSize != listPSSize) {
					differencesList.add(String.format(DIFFERENCES_DETAILS_MESSAGE, "size", itemCode, fieldFullPath, listTBSize, listPSSize,systemNameA,systemNameB));
				}

				fieldValueA = fieldValueB = null;

				break;
			}

			fieldValueA = StringUtils.isNotEmpty(fieldItem) ? FieldUtils.readField(fieldValueA, fieldItem, true) : fieldValueA;
			fieldValueB = StringUtils.isNotEmpty(fieldItem) ? FieldUtils.readField(fieldValueB, fieldItem, true) : fieldValueB;

			if (fieldValueA instanceof JAXBElement) {
				fieldValueA = ((JAXBElement<Object>) fieldValueA).getValue();
			}
			
			if (fieldValueA instanceof Enum) {
				fieldValueA = FieldUtils.readField(fieldValueA, "value", true);
			}
			
			if (fieldValueB instanceof JAXBElement) {
				fieldValueB = ((JAXBElement<Object>) fieldValueB).getValue();
			}
			
			if (fieldValueB instanceof Enum) {
				fieldValueB = FieldUtils.readField(fieldValueB, "value", true);
			}

			if (fieldValueA == null || fieldValueB == null) {
				break;
			}
		}
	
		
		// this solution need check 
		if (fieldValueA instanceof XMLGregorianCalendar) {
			DateTimeZone jodaTz = DateTimeZone.UTC;
			XMLGregorianCalendar value = (XMLGregorianCalendar) fieldValueA;	
			fieldValueA = new DateTime(value.toGregorianCalendar(), jodaTz);			
		}
		
		if (fieldValueB instanceof XMLGregorianCalendar) {
			DateTimeZone jodaTz = DateTimeZone.UTC;
			XMLGregorianCalendar value = (XMLGregorianCalendar) fieldValueB;
			fieldValueB = new DateTime(value.toGregorianCalendar(), jodaTz);			
		}
			
		if (!notComparableFields.contains(fieldPathLog)
				&& ((fieldValueA != null && fieldValueB != null && !StringUtils.replace(fieldValueA.toString(), EOL_WINDOWS, EOL_UNIX).equals(StringUtils.replace(fieldValueB.toString(), EOL_WINDOWS, EOL_UNIX)))
						|| (fieldValueA == null && fieldValueB != null) || (fieldValueA != null && fieldValueB == null))) {

			differencesList.add(String.format(DIFFERENCES_DETAILS_MESSAGE, "value", itemCode, fieldLabel, fieldValueA, fieldValueB,systemNameA,systemNameB));
		}
	}

	public void getFieldsList(List<String> list, Class<?> clazz, String name) {

		for (Field field : clazz.getDeclaredFields()) {

			if (checkIfSimpleCollection(field)) {

				list.add(LIST_TAG + "." + field.getName());
				continue;
			}

			Class<?> type = field.getType() == JAXBElement.class || checkIfCollection(field.getType()) ? (Class<?>) ((ParameterizedTypeImpl) field.getGenericType()).getActualTypeArguments()[0] : field.getType();

			if (checkIfSimple(type)) {
				list.add((name == null ? "" : name + ".") + field.getName());
			} else {
				getFieldsList(list, type, (name == null ? "" : name + ".") + (checkIfCollection(field.getType()) ? LIST_TAG + "." : "") + field.getName());
			}
		}
	}

	private  boolean checkIfSimple(Class<?> type) {

		if (type.isPrimitive() ||
			type.isEnum() ||
			type == BigDecimal.class ||
			type == Boolean.class ||
			type == Byte.class ||
			type == Character.class ||
			type == Double.class ||
			type == Float.class ||
			type == Integer.class ||
			type == Long.class ||
			type == Short.class ||
			type == String.class ||
			type == XMLGregorianCalendar.class ||
			type == Class.class) {

			return true;
		}

		return false;
	}

	private  boolean checkIfCollection(Type type) {

		if (type == List.class) {
			return true;
		}

		return false;
	}

	private  boolean checkIfSimpleCollection(Field field) {

		if (field.getType() == List.class) {
			return checkIfSimple((Class<?>) ((ParameterizedTypeImpl) field.getGenericType()).getActualTypeArguments()[0]);
		}

		return false;
	}

		
	public  boolean checkDedicatedCondition(Object fieldValueA, Object fieldValueB) {
		
		boolean result=true;
		for (CompareCondition compareCondition : dedicatedCondition) {			
			result=compareCondition.checkCondition(fieldValueA, fieldValueB);
			if(result==false)
				break;
		}
		return result;
	}
	
	public List<CompareCondition> getDedicatedCondition() {
		return dedicatedCondition;
	}
}
