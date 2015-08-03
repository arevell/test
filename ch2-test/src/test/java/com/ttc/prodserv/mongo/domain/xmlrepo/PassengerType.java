
package com.ttc.prodserv.mongo.domain.xmlrepo;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for passengerType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="passengerType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Adult"/>
 *     &lt;enumeration value="Child"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "passengerType")
@XmlEnum
public enum PassengerType {

    @XmlEnumValue("Adult")
    ADULT("Adult"),
    @XmlEnumValue("Child")
    CHILD("Child");
    private final String value;

    PassengerType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PassengerType fromValue(String v) {
        for (PassengerType c: PassengerType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
