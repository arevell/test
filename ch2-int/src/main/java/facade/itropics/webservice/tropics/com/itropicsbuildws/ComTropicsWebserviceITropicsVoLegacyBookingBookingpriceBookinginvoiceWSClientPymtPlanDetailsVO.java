
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSClientPymtPlanDetailsVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSClientPymtPlanDetailsVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bkgID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="cardTypesArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_commonvo_WSCreditCardMasterVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="clientPymtPlanVOCollection" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSClientPymtPlanVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="concurrencyCounter" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="paymentPlanType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pymtDetailsCollection" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="pymtModeDiscInfoVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSPymtModeDiscInfoVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSClientPymtPlanDetailsVO", propOrder = {
    "bkgID",
    "cardTypesArray",
    "clientPymtPlanVOCollection",
    "concurrencyCounter",
    "paymentPlanType",
    "pymtDetailsCollection",
    "pymtModeDiscInfoVO"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSClientPymtPlanDetailsVO {

    protected long bkgID;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyCommonvoWSCreditCardMasterVO> cardTypesArray;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSClientPymtPlanVO> clientPymtPlanVOCollection;
    protected int concurrencyCounter;
    protected String paymentPlanType;
    @XmlElement(nillable = true)
    protected List<String> pymtDetailsCollection;
    protected ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtModeDiscInfoVO pymtModeDiscInfoVO;

    /**
     * Gets the value of the bkgID property.
     * 
     */
    public long getBkgID() {
        return bkgID;
    }

    /**
     * Sets the value of the bkgID property.
     * 
     */
    public void setBkgID(long value) {
        this.bkgID = value;
    }

    /**
     * Gets the value of the cardTypesArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cardTypesArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCardTypesArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyCommonvoWSCreditCardMasterVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyCommonvoWSCreditCardMasterVO> getCardTypesArray() {
        if (cardTypesArray == null) {
            cardTypesArray = new ArrayList<ComTropicsWebserviceITropicsVoLegacyCommonvoWSCreditCardMasterVO>();
        }
        return this.cardTypesArray;
    }

    /**
     * Gets the value of the clientPymtPlanVOCollection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clientPymtPlanVOCollection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClientPymtPlanVOCollection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSClientPymtPlanVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSClientPymtPlanVO> getClientPymtPlanVOCollection() {
        if (clientPymtPlanVOCollection == null) {
            clientPymtPlanVOCollection = new ArrayList<ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSClientPymtPlanVO>();
        }
        return this.clientPymtPlanVOCollection;
    }

    /**
     * Gets the value of the concurrencyCounter property.
     * 
     */
    public int getConcurrencyCounter() {
        return concurrencyCounter;
    }

    /**
     * Sets the value of the concurrencyCounter property.
     * 
     */
    public void setConcurrencyCounter(int value) {
        this.concurrencyCounter = value;
    }

    /**
     * Gets the value of the paymentPlanType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentPlanType() {
        return paymentPlanType;
    }

    /**
     * Sets the value of the paymentPlanType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentPlanType(String value) {
        this.paymentPlanType = value;
    }

    /**
     * Gets the value of the pymtDetailsCollection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pymtDetailsCollection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPymtDetailsCollection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPymtDetailsCollection() {
        if (pymtDetailsCollection == null) {
            pymtDetailsCollection = new ArrayList<String>();
        }
        return this.pymtDetailsCollection;
    }

    /**
     * Gets the value of the pymtModeDiscInfoVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtModeDiscInfoVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtModeDiscInfoVO getPymtModeDiscInfoVO() {
        return pymtModeDiscInfoVO;
    }

    /**
     * Sets the value of the pymtModeDiscInfoVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtModeDiscInfoVO }
     *     
     */
    public void setPymtModeDiscInfoVO(ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtModeDiscInfoVO value) {
        this.pymtModeDiscInfoVO = value;
    }

}
