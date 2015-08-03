
package com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RoomTypeCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RoomTypeCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Single"/>
 *     &lt;enumeration value="Twin"/>
 *     &lt;enumeration value="TwinShare"/>
 *     &lt;enumeration value="Triple"/>
 *     &lt;enumeration value="TripleShare"/>
 *     &lt;enumeration value="Quad"/>
 *     &lt;enumeration value="QuadShare"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RoomTypeCodeType")
@XmlEnum
public enum RoomTypeCodeType {

    @XmlEnumValue("Single")
    SINGLE("Single"),
    @XmlEnumValue("Twin")
    TWIN("Twin"),
    @XmlEnumValue("TwinShare")
    TWIN_SHARE("TwinShare"),
    @XmlEnumValue("Triple")
    TRIPLE("Triple"),
    @XmlEnumValue("TripleShare")
    TRIPLE_SHARE("TripleShare"),
    @XmlEnumValue("Quad")
    QUAD("Quad"),
    @XmlEnumValue("QuadShare")
    QUAD_SHARE("QuadShare");
    private final String value;

    RoomTypeCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RoomTypeCodeType fromValue(String v) {
        for (RoomTypeCodeType c: RoomTypeCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
