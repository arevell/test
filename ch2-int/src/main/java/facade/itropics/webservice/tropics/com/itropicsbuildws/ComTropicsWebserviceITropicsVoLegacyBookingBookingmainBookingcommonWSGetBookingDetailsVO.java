
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_WSGetBookingDetailsVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_WSGetBookingDetailsVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="agentsReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bookingDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="bookingVersionNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="cardFeeWaived" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="cardFeeWaivedReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errorMessagesArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_current_common_WSErrorMessagesVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="itropicsdotinvoiceconsenttext" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sellingCoCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="successful" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="travelConsultantVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsTravelConsultantVO" minOccurs="0"/>
 *         &lt;element name="wsBookingClientDetails" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_client_WSBookingClientDetails" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_WSGetBookingDetailsVO", propOrder = {
    "agentsReference",
    "bookingDate",
    "bookingVersionNumber",
    "cardFeeWaived",
    "cardFeeWaivedReason",
    "errorMessagesArray",
    "itropicsdotinvoiceconsenttext",
    "sellingCoCurrencyCode",
    "successful",
    "travelConsultantVO",
    "wsBookingClientDetails"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSGetBookingDetailsVO {

    protected String agentsReference;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar bookingDate;
    protected long bookingVersionNumber;
    protected boolean cardFeeWaived;
    protected String cardFeeWaivedReason;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO> errorMessagesArray;
    protected String itropicsdotinvoiceconsenttext;
    protected String sellingCoCurrencyCode;
    protected boolean successful;
    protected WsTravelConsultantVO travelConsultantVO;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonClientWSBookingClientDetails> wsBookingClientDetails;

    /**
     * Gets the value of the agentsReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentsReference() {
        return agentsReference;
    }

    /**
     * Sets the value of the agentsReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentsReference(String value) {
        this.agentsReference = value;
    }

    /**
     * Gets the value of the bookingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBookingDate() {
        return bookingDate;
    }

    /**
     * Sets the value of the bookingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBookingDate(XMLGregorianCalendar value) {
        this.bookingDate = value;
    }

    /**
     * Gets the value of the bookingVersionNumber property.
     * 
     */
    public long getBookingVersionNumber() {
        return bookingVersionNumber;
    }

    /**
     * Sets the value of the bookingVersionNumber property.
     * 
     */
    public void setBookingVersionNumber(long value) {
        this.bookingVersionNumber = value;
    }

    /**
     * Gets the value of the cardFeeWaived property.
     * 
     */
    public boolean isCardFeeWaived() {
        return cardFeeWaived;
    }

    /**
     * Sets the value of the cardFeeWaived property.
     * 
     */
    public void setCardFeeWaived(boolean value) {
        this.cardFeeWaived = value;
    }

    /**
     * Gets the value of the cardFeeWaivedReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardFeeWaivedReason() {
        return cardFeeWaivedReason;
    }

    /**
     * Sets the value of the cardFeeWaivedReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardFeeWaivedReason(String value) {
        this.cardFeeWaivedReason = value;
    }

    /**
     * Gets the value of the errorMessagesArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the errorMessagesArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErrorMessagesArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO> getErrorMessagesArray() {
        if (errorMessagesArray == null) {
            errorMessagesArray = new ArrayList<ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO>();
        }
        return this.errorMessagesArray;
    }

    /**
     * Gets the value of the itropicsdotinvoiceconsenttext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItropicsdotinvoiceconsenttext() {
        return itropicsdotinvoiceconsenttext;
    }

    /**
     * Sets the value of the itropicsdotinvoiceconsenttext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItropicsdotinvoiceconsenttext(String value) {
        this.itropicsdotinvoiceconsenttext = value;
    }

    /**
     * Gets the value of the sellingCoCurrencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellingCoCurrencyCode() {
        return sellingCoCurrencyCode;
    }

    /**
     * Sets the value of the sellingCoCurrencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellingCoCurrencyCode(String value) {
        this.sellingCoCurrencyCode = value;
    }

    /**
     * Gets the value of the successful property.
     * 
     */
    public boolean isSuccessful() {
        return successful;
    }

    /**
     * Sets the value of the successful property.
     * 
     */
    public void setSuccessful(boolean value) {
        this.successful = value;
    }

    /**
     * Gets the value of the travelConsultantVO property.
     * 
     * @return
     *     possible object is
     *     {@link WsTravelConsultantVO }
     *     
     */
    public WsTravelConsultantVO getTravelConsultantVO() {
        return travelConsultantVO;
    }

    /**
     * Sets the value of the travelConsultantVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsTravelConsultantVO }
     *     
     */
    public void setTravelConsultantVO(WsTravelConsultantVO value) {
        this.travelConsultantVO = value;
    }

    /**
     * Gets the value of the wsBookingClientDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsBookingClientDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsBookingClientDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonClientWSBookingClientDetails }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonClientWSBookingClientDetails> getWsBookingClientDetails() {
        if (wsBookingClientDetails == null) {
            wsBookingClientDetails = new ArrayList<ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonClientWSBookingClientDetails>();
        }
        return this.wsBookingClientDetails;
    }

}
