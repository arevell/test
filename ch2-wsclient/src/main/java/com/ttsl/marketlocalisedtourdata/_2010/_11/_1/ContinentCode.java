
package com.ttsl.marketlocalisedtourdata._2010._11._1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for continentCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="continentCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
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
@XmlType(name = "continentCode")
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
