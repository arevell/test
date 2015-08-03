
package com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PassengerTypeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PassengerTypeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Adult"/>
 *     &lt;enumeration value="Child"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PassengerTypeType")
@XmlEnum
public enum PassengerTypeType {

    @XmlEnumValue("Adult")
    ADULT("Adult"),
    @XmlEnumValue("Child")
    CHILD("Child");
    private final String value;

    PassengerTypeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PassengerTypeType fromValue(String v) {
        for (PassengerTypeType c: PassengerTypeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
