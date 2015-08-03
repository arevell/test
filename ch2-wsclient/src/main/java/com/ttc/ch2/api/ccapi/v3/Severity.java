
package com.ttc.ch2.api.ccapi.v3;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Severity.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Severity">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="INFO"/>
 *     &lt;enumeration value="WARNING"/>
 *     &lt;enumeration value="ERROR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Severity")
@XmlEnum
public enum Severity {

    INFO,
    WARNING,
    ERROR;

    public String value() {
        return name();
    }

    public static Severity fromValue(String v) {
        return valueOf(v);
    }

}
