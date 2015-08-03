package com.ttc.ch2;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.reflect.FieldUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

@SuppressWarnings("restriction")
public class EnvironmentsComparerUtils {

	public final static String PRODUCT_SERVICE = "ProductService";
	public final static String CONTENT_HUB = "ContentHub 2.0";
	public final static String LIST_TAG = "|list|";
	public final static String NULL_TAG = "|null|";
	public final static String AND_TAG = "|and|";
	public final static String EOL_UNIX = "\n";
	public final static String EOL_WINDOWS = "\r\n";
	public final static String ITEM_DELIMITER = ":";
	public final static String PATH_ITEM_DELIMITER = "\\.";
	public final static String NAME_ITEM_TEMPLATE = "%s:%s - %s";
	public final static String NO_DATA_RESPONSE = "%s returned no data for given tour [%s] and selling company [%s]";
	public final static String MISSING_EQUIVALENT = "item: [%s], field: [%s], binding property: [%s], value: [%s] not found in ProductService\r\n";
	public final static String DIFFERENCES_MESSAGE = "Differences have occurred:\r\n%s";
	public final static String DIFFERENCES_DETAILS_MESSAGE = "item: [%2$s], field: [%3$s], %1$s in ContentHub 2.0: [%4$s], %1$s in ProductService: [%5$s]\r\n";

	@SuppressWarnings("unchecked")
	public static void compareFields(Object fieldValueCH, Object fieldValuePS, String fieldPath, String fieldPathLog, String fieldLabel, String itemCode, List<String> notComparableFields, Set<String> differencesList, Map<String, String> bindingMap) throws Exception {

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

				Collection<Object> fieldListTB = (Collection<Object>) FieldUtils.readField(fieldValueCH, fieldList, true);
				Collection<Object> fieldListPS = (Collection<Object>) FieldUtils.readField(fieldValuePS, fieldList, true);

				int listTBSize = fieldListTB != null ? fieldListTB.size() : 0;
				int listPSSize = fieldListPS != null ? fieldListPS.size() : 0;

				if (bindingMap != null && bindingMap.containsKey(fieldFullPath)) {

					for (int i = 0; i < listTBSize; i++) {

						fieldValueCH = fieldListTB.toArray()[i];

						Object bindingPropertyValueTB = null;

						if (bindingMap.get(fieldFullPath).contains(AND_TAG)) {

							bindingPropertyValueTB = FieldUtils.readField(fieldValueCH, StringUtils.substringBefore(bindingMap.get(fieldFullPath), AND_TAG), true).toString() +
													 ITEM_DELIMITER +
													 FieldUtils.readField(fieldValueCH, StringUtils.substringAfter(bindingMap.get(fieldFullPath), AND_TAG), true).toString();

						} else if (NULL_TAG.equals(bindingMap.get(fieldFullPath))) {
							bindingPropertyValueTB = fieldValueCH;
						} else {
							bindingPropertyValueTB = FieldUtils.readField(fieldValueCH, bindingMap.get(fieldFullPath), true);
						}

						if (bindingPropertyValueTB instanceof JAXBElement) {
							bindingPropertyValueTB = ((JAXBElement<Object>) bindingPropertyValueTB).getValue();
						}

						if (bindingPropertyValueTB instanceof Enum) {
							bindingPropertyValueTB = FieldUtils.readField(bindingPropertyValueTB, "value", true);
						}

						boolean bindingFound = false;

						for (int j = 0; j < listPSSize; j++) {

							fieldValuePS = fieldListPS.toArray()[j];

							Object bindingPropertyValuePS = null;

							if (bindingMap.get(fieldFullPath).contains(AND_TAG)) {

								bindingPropertyValuePS = FieldUtils.readField(fieldValuePS, StringUtils.substringBefore(bindingMap.get(fieldFullPath), AND_TAG), true).toString() +
														 ITEM_DELIMITER +
														 FieldUtils.readField(fieldValuePS, StringUtils.substringAfter(bindingMap.get(fieldFullPath), AND_TAG), true).toString();

							} else if (NULL_TAG.equals(bindingMap.get(fieldFullPath))) {
								bindingPropertyValuePS = fieldValuePS;
							} else {
								bindingPropertyValuePS = FieldUtils.readField(fieldValuePS, bindingMap.get(fieldFullPath), true);
							}

							if (bindingPropertyValuePS instanceof JAXBElement) {
								bindingPropertyValuePS = ((JAXBElement<Object>) bindingPropertyValuePS).getValue();
							}

							if (bindingPropertyValuePS instanceof Enum) {
								bindingPropertyValuePS = FieldUtils.readField(bindingPropertyValuePS, "value", true);
							}

							if (bindingPropertyValueTB != null && bindingPropertyValuePS != null && bindingPropertyValueTB.toString().equals(bindingPropertyValuePS.toString())) {

								bindingFound = true;

								String fieldCurrentLabel = StringUtils.replace(fieldLabel, fieldCurrentPath, StringUtils.replace(fieldCurrentPath, LIST_TAG, "|" + bindingPropertyValueTB.toString() + "|"));

								compareFields(fieldValueCH, fieldValuePS, fieldRemainingPath, fieldPathLog, fieldCurrentLabel, itemCode, notComparableFields, differencesList, bindingMap);
								break;
							}
						}

						if (!bindingFound) {
							differencesList.add(String.format(MISSING_EQUIVALENT, itemCode, fieldFullPath, bindingMap.get(fieldFullPath), bindingPropertyValueTB != null ? bindingPropertyValueTB.toString() : StringUtils.EMPTY));
						}
					}

				} else {

					for (int i = 0; i < listTBSize && i < listPSSize; i++) {

						fieldValueCH = fieldListTB.toArray()[i];
						fieldValuePS = fieldListPS.toArray()[i];

						String fieldCurrentLabel = StringUtils.replace(fieldLabel, fieldCurrentPath, StringUtils.replace(fieldCurrentPath, LIST_TAG, "|" + i + "|"));

						compareFields(fieldValueCH, fieldValuePS, fieldRemainingPath, fieldPathLog, fieldCurrentLabel, itemCode, notComparableFields, differencesList, bindingMap);
					}
				}

				if (listTBSize != listPSSize) {
					differencesList.add(String.format(DIFFERENCES_DETAILS_MESSAGE, "size", itemCode, fieldFullPath, listTBSize, listPSSize));
				}

				fieldValueCH = fieldValuePS = null;

				break;
			}

			fieldValueCH = StringUtils.isNotEmpty(fieldItem) ? FieldUtils.readField(fieldValueCH, fieldItem, true) : fieldValueCH;
			fieldValuePS = StringUtils.isNotEmpty(fieldItem) ? FieldUtils.readField(fieldValuePS, fieldItem, true) : fieldValuePS;

			if (fieldValuePS instanceof JAXBElement) {
				fieldValuePS = ((JAXBElement<Object>) fieldValuePS).getValue();
			}

			if (fieldValueCH instanceof Enum) {
				fieldValueCH = FieldUtils.readField(fieldValueCH, "value", true);
			}

			if (fieldValuePS instanceof Enum) {
				fieldValuePS = FieldUtils.readField(fieldValuePS, "value", true);
			}

			if (fieldValueCH == null || fieldValuePS == null) {
				break;
			}
		}
	
		
		// this solution need check 
		if (fieldValueCH instanceof XMLGregorianCalendar) {
			DateTimeZone jodaTz = DateTimeZone.UTC;
			XMLGregorianCalendar value = (XMLGregorianCalendar) fieldValueCH;	
			fieldValueCH = new DateTime(value.toGregorianCalendar(), jodaTz);			
		}
		
		if (fieldValuePS instanceof XMLGregorianCalendar) {
			DateTimeZone jodaTz = DateTimeZone.UTC;
			XMLGregorianCalendar value = (XMLGregorianCalendar) fieldValuePS;
			fieldValuePS = new DateTime(value.toGregorianCalendar(), jodaTz);			
		}
			
		if (!notComparableFields.contains(fieldPathLog)
				&& ((fieldValueCH != null && fieldValuePS != null && !StringUtils.replace(fieldValueCH.toString(), EOL_WINDOWS, EOL_UNIX).equals(StringUtils.replace(fieldValuePS.toString(), EOL_WINDOWS, EOL_UNIX)))
						|| (fieldValueCH == null && fieldValuePS != null) || (fieldValueCH != null && fieldValuePS == null))) {

			differencesList.add(String.format(DIFFERENCES_DETAILS_MESSAGE, "value", itemCode, fieldLabel, fieldValueCH, fieldValuePS));
		}
	}

	public static void getFieldsList(List<String> list, Class<?> clazz, String name) {

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

	public static boolean checkIfSimple(Class<?> type) {

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

	public static boolean checkIfCollection(Type type) {

		if (type == List.class) {
			return true;
		}

		return false;
	}

	public static boolean checkIfSimpleCollection(Field field) {

		if (field.getType() == List.class) {
			return checkIfSimple((Class<?>) ((ParameterizedTypeImpl) field.getGenericType()).getActualTypeArguments()[0]);
		}

		return false;
	}
}
