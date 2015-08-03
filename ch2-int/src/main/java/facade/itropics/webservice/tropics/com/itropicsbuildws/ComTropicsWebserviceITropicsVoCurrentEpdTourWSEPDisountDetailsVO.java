
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_current_epd_tour_WSEPDisountDetailsVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_current_epd_tour_WSEPDisountDetailsVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="discountId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="paymentDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="percentage" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_current_epd_tour_WSEPDisountDetailsVO", propOrder = {
    "amount",
    "discountId",
    "paymentDate",
    "percentage"
})
public class ComTropicsWebserviceITropicsVoCurrentEpdTourWSEPDisountDetailsVO {

    protected float amount;
    protected long discountId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar paymentDate;
    protected boolean percentage;

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
     * Gets the value of the discountId property.
     * 
     */
    public long getDiscountId() {
        return discountId;
    }

    /**
     * Sets the value of the discountId property.
     * 
     */
    public void setDiscountId(long value) {
        this.discountId = value;
    }

    /**
     * Gets the value of the paymentDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPaymentDate() {
        return paymentDate;
    }

    /**
     * Sets the value of the paymentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPaymentDate(XMLGregorianCalendar value) {
        this.paymentDate = value;
    }

    /**
     * Gets the value of the percentage property.
     * 
     */
    public boolean isPercentage() {
        return percentage;
    }

    /**
     * Sets the value of the percentage property.
     * 
     */
    public void setPercentage(boolean value) {
        this.percentage = value;
    }

}
