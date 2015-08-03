
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for departureStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="departureStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AVAILABLE"/>
 *     &lt;enumeration value="CLOSED"/>
 *     &lt;enumeration value="ONREQUEST"/>
 *     &lt;enumeration value="CANCELLED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "departureStatus")
@XmlEnum
public enum DepartureStatus {

    AVAILABLE,
    CLOSED,
    ONREQUEST,
    CANCELLED;

    public String value() {
        return name();
    }

    public static DepartureStatus fromValue(String v) {
        return valueOf(v);
    }

}
