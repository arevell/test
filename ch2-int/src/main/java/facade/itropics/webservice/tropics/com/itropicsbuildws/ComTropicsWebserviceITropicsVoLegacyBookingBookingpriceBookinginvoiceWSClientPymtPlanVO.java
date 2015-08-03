
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSClientPymtPlanVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSClientPymtPlanVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amountDetailsVOArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSAmountDetailsVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="balanceAmount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="clientID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="clientName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="discount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="seqClientID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalAmount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSClientPymtPlanVO", propOrder = {
    "amountDetailsVOArray",
    "balanceAmount",
    "clientID",
    "clientName",
    "discount",
    "seqClientID",
    "totalAmount"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSClientPymtPlanVO {

    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSAmountDetailsVO> amountDetailsVOArray;
    protected float balanceAmount;
    protected long clientID;
    protected String clientName;
    protected float discount;
    protected String seqClientID;
    protected float totalAmount;

    /**
     * Gets the value of the amountDetailsVOArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the amountDetailsVOArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAmountDetailsVOArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSAmountDetailsVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSAmountDetailsVO> getAmountDetailsVOArray() {
        if (amountDetailsVOArray == null) {
            amountDetailsVOArray = new ArrayList<ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSAmountDetailsVO>();
        }
        return this.amountDetailsVOArray;
    }

    /**
     * Gets the value of the balanceAmount property.
     * 
     */
    public float getBalanceAmount() {
        return balanceAmount;
    }

    /**
     * Sets the value of the balanceAmount property.
     * 
     */
    public void setBalanceAmount(float value) {
        this.balanceAmount = value;
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
     * Gets the value of the discount property.
     * 
     */
    public float getDiscount() {
        return discount;
    }

    /**
     * Sets the value of the discount property.
     * 
     */
    public void setDiscount(float value) {
        this.discount = value;
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

    /**
     * Gets the value of the totalAmount property.
     * 
     */
    public float getTotalAmount() {
        return totalAmount;
    }

    /**
     * Sets the value of the totalAmount property.
     * 
     */
    public void setTotalAmount(float value) {
        this.totalAmount = value;
    }

}
