
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSPymtModeDiscInfoVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSPymtModeDiscInfoVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="creditCardTypeID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="paymentPlanLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pymtMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pymtModeDiscStatus" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSPymtModeDiscInfoVO", propOrder = {
    "amount",
    "creditCardTypeID",
    "paymentPlanLevel",
    "pymtMode",
    "pymtModeDiscStatus"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtModeDiscInfoVO {

    protected float amount;
    protected long creditCardTypeID;
    protected String paymentPlanLevel;
    protected String pymtMode;
    protected long pymtModeDiscStatus;

    /**
     * Gets the value of the amount property.
     * 
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     */
    public void setAmount(float value) {
        this.amount = value;
    }

    /**
     * Gets the value of the creditCardTypeID property.
     * 
     */
    public long getCreditCardTypeID() {
        return creditCardTypeID;
    }

    /**
     * Sets the value of the creditCardTypeID property.
     * 
     */
    public void setCreditCardTypeID(long value) {
        this.creditCardTypeID = value;
    }

    /**
     * Gets the value of the paymentPlanLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentPlanLevel() {
        return paymentPlanLevel;
    }

    /**
     * Sets the value of the paymentPlanLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentPlanLevel(String value) {
        this.paymentPlanLevel = value;
    }

    /**
     * Gets the value of the pymtMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPymtMode() {
        return pymtMode;
    }

    /**
     * Sets the value of the pymtMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPymtMode(String value) {
        this.pymtMode = value;
    }

    /**
     * Gets the value of the pymtModeDiscStatus property.
     * 
     */
    public long getPymtModeDiscStatus() {
        return pymtModeDiscStatus;
    }

    /**
     * Sets the value of the pymtModeDiscStatus property.
     * 
     */
    public void setPymtModeDiscStatus(long value) {
        this.pymtModeDiscStatus = value;
    }

}
