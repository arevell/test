
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSAmountDetailsVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSAmountDetailsVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="clientID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="clientName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pymtPlanID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="receivedAmount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="seqClientID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSAmountDetailsVO", propOrder = {
    "amount",
    "clientID",
    "clientName",
    "lastModifiedBy",
    "pymtPlanID",
    "receivedAmount",
    "seqClientID"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSAmountDetailsVO {

    protected float amount;
    protected long clientID;
    protected String clientName;
    protected String lastModifiedBy;
    protected long pymtPlanID;
    protected float receivedAmount;
    protected String seqClientID;

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
     * Gets the value of the clientID property.
     * 
     */
    public long getClientID() {
        return clientID;
    }

    /**
     * Sets the value of the clientID property.
     * 
     */
    public void setClientID(long value) {
        this.clientID = value;
    }

    /**
     * Gets the value of the clientName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Sets the value of the clientName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientName(String value) {
        this.clientName = value;
    }

    /**
     * Gets the value of the lastModifiedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * Sets the value of the lastModifiedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastModifiedBy(String value) {
        this.lastModifiedBy = value;
    }

    /**
     * Gets the value of the pymtPlanID property.
     * 
     */
    public long getPymtPlanID() {
        return pymtPlanID;
    }

    /**
     * Sets the value of the pymtPlanID property.
     * 
     */
    public void setPymtPlanID(long value) {
        this.pymtPlanID = value;
    }

    /**
     * Gets the value of the receivedAmount property.
     * 
     */
    public float getReceivedAmount() {
        return receivedAmount;
    }

    /**
     * Sets the value of the receivedAmount property.
     * 
     */
    public void setReceivedAmount(float value) {
        this.receivedAmount = value;
    }

    /**
     * Gets the value of the seqClientID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeqClientID() {
        return seqClientID;
    }

    /**
     * Sets the value of the seqClientID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeqClientID(String value) {
        this.seqClientID = value;
    }

}
