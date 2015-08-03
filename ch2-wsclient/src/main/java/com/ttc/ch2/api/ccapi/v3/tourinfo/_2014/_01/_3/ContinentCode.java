
package com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ContinentCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ContinentCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *     &lt;enumeration value="AFRI"/>
 *     &lt;enumeration value="ANTA"/>
 *     &lt;enumeration value="ASIA"/>
 *     &lt;enumeration value="AUST"/>
 *     &lt;enumeration value="CAME"/>
 *     &lt;enumeration value="EURO"/>
 *     &lt;enumeration value="NAME"/>
 *     &lt;enumeration value="SAME"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ContinentCode")
@XmlEnum
public enum ContinentCode {

    AFRI,
    ANTA,
    ASIA,
    AUST,
    CAME,
    EURO,
    NAME,
    SAME;

    public String value() {
        return name();
    }

    public static ContinentCode fromValue(String v) {
        return valueOf(v);
    }

}
