
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for productType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="productType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PreAccommodationProduct"/>
 *     &lt;enumeration value="PostAccommodationProduct"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "productType")
@XmlEnum
public enum ProductType {

    @XmlEnumValue("PreAccommodationProduct")
    PRE_ACCOMMODATION_PRODUCT("PreAccommodationProduct"),
    @XmlEnumValue("PostAccommodationProduct")
    POST_ACCOMMODATION_PRODUCT("PostAccommodationProduct");
    private final String value;

    ProductType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ProductType fromValue(String v) {
        for (ProductType c: ProductType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
