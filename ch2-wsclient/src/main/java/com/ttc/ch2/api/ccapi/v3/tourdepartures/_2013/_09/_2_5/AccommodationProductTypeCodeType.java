
package com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccommodationProductTypeCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AccommodationProductTypeCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PreAccommodation"/>
 *     &lt;enumeration value="PostAccommodation"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AccommodationProductTypeCodeType")
@XmlEnum
public enum AccommodationProductTypeCodeType {

    @XmlEnumValue("PreAccommodation")
    PRE_ACCOMMODATION("PreAccommodation"),
    @XmlEnumValue("PostAccommodation")
    POST_ACCOMMODATION("PostAccommodation");
    private final String value;

    AccommodationProductTypeCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AccommodationProductTypeCodeType fromValue(String v) {
        for (AccommodationProductTypeCodeType c: AccommodationProductTypeCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
