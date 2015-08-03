
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSDiscountDetailsVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSDiscountDetailsVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="discountCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="discountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="discountPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="discountTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSDiscountDetailsVO", propOrder = {
    "discountCode",
    "discountName",
    "discountPrice",
    "discountTypeCode"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSDiscountDetailsVO {

    protected String discountCode;
    protected String discountName;
    protected float discountPrice;
    protected String discountTypeCode;

    /**
     * Gets the value of the discountCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountCode() {
        return discountCode;
    }

    /**
     * Sets the value of the discountCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountCode(String value) {
        this.discountCode = value;
    }

    /**
     * Gets the value of the discountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountName() {
        return discountName;
    }

    /**
     * Sets the value of the discountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountName(String value) {
        this.discountName = value;
    }

    /**
     * Gets the value of the discountPrice property.
     * 
     */
    public float getDiscountPrice() {
        return discountPrice;
    }

    /**
     * Sets the value of the discountPrice property.
     * 
     */
    public void setDiscountPrice(float value) {
        this.discountPrice = value;
    }

    /**
     * Gets the value of the discountTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountTypeCode() {
        return discountTypeCode;
    }

    /**
     * Sets the value of the discountTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountTypeCode(String value) {
        this.discountTypeCode = value;
    }

}
