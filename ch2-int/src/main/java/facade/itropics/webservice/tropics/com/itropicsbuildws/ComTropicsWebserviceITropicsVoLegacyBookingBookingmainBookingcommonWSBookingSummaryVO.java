
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
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_WSBookingSummaryVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_WSBookingSummaryVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amtPaid" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="authCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="balanceDue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="bookingInfoVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_WSBookingInfoVO" minOccurs="0"/>
 *         &lt;element name="bookingState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CCStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="canIgnoreAir" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="clientNoLimit" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="clientVOCollection" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_client_WSClientVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="departedFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="docIssueDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="gapOverlapFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="itineraryDetailsVOCollection" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_WSItineraryDetailsVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PNR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentPlanLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pymtPlanDetailsVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSPymtPlanDetailsVO" minOccurs="0"/>
 *         &lt;element name="sourceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totComm" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totDisc" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totFees" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totGrossPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totNettPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_WSBookingSummaryVO", propOrder = {
    "amtPaid",
    "authCode",
    "balanceDue",
    "bookingInfoVO",
    "bookingState",
    "ccStatus",
    "canIgnoreAir",
    "clientNoLimit",
    "clientVOCollection",
    "departedFlag",
    "docIssueDate",
    "gapOverlapFlag",
    "itineraryDetailsVOCollection",
    "pnr",
    "paymentPlanLevel",
    "pymtPlanDetailsVO",
    "sourceCode",
    "totComm",
    "totDisc",
    "totFees",
    "totGrossPrice",
    "totNettPrice"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSBookingSummaryVO {

    protected float amtPaid;
    protected String authCode;
    protected float balanceDue;
    protected ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSBookingInfoVO bookingInfoVO;
    protected String bookingState;
    @XmlElement(name = "CCStatus")
    protected String ccStatus;
    protected boolean canIgnoreAir;
    protected long clientNoLimit;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyBookingBookingmainClientWSClientVO> clientVOCollection;
    protected String departedFlag;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar docIssueDate;
    protected String gapOverlapFlag;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSItineraryDetailsVO> itineraryDetailsVOCollection;
    @XmlElement(name = "PNR")
    protected String pnr;
    protected String paymentPlanLevel;
    protected ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtPlanDetailsVO pymtPlanDetailsVO;
    protected String sourceCode;
    protected float totComm;
    protected float totDisc;
    protected float totFees;
    protected float totGrossPrice;
    protected float totNettPrice;

    /**
     * Gets the value of the amtPaid property.
     * 
     */
    public float getAmtPaid() {
        return amtPaid;
    }

    /**
     * Sets the value of the amtPaid property.
     * 
     */
    public void setAmtPaid(float value) {
        this.amtPaid = value;
    }

    /**
     * Gets the value of the authCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthCode() {
        return authCode;
    }

    /**
     * Sets the value of the authCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthCode(String value) {
        this.authCode = value;
    }

    /**
     * Gets the value of the balanceDue property.
     * 
     */
    public float getBalanceDue() {
        return balanceDue;
    }

    /**
     * Sets the value of the balanceDue property.
     * 
     */
    public void setBalanceDue(float value) {
        this.balanceDue = value;
    }

    /**
     * Gets the value of the bookingInfoVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSBookingInfoVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSBookingInfoVO getBookingInfoVO() {
        return bookingInfoVO;
    }

    /**
     * Sets the value of the bookingInfoVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSBookingInfoVO }
     *     
     */
    public void setBookingInfoVO(ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSBookingInfoVO value) {
        this.bookingInfoVO = value;
    }

    /**
     * Gets the value of the bookingState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingState() {
        return bookingState;
    }

    /**
     * Sets the value of the bookingState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingState(String value) {
        this.bookingState = value;
    }

    /**
     * Gets the value of the ccStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCCStatus() {
        return ccStatus;
    }

    /**
     * Sets the value of the ccStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCCStatus(String value) {
        this.ccStatus = value;
    }

    /**
     * Gets the value of the canIgnoreAir property.
     * 
     */
    public boolean isCanIgnoreAir() {
        return canIgnoreAir;
    }

    /**
     * Sets the value of the canIgnoreAir property.
     * 
     */
    public void setCanIgnoreAir(boolean value) {
        this.canIgnoreAir = value;
    }

    /**
     * Gets the value of the clientNoLimit property.
     * 
     */
    public long getClientNoLimit() {
        return clientNoLimit;
    }

    /**
     * Sets the value of the clientNoLimit property.
     * 
     */
    public void setClientNoLimit(long value) {
        this.clientNoLimit = value;
    }

    /**
     * Gets the value of the clientVOCollection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clientVOCollection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClientVOCollection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingmainClientWSClientVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyBookingBookingmainClientWSClientVO> getClientVOCollection() {
        if (clientVOCollection == null) {
            clientVOCollection = new ArrayList<ComTropicsWebserviceITropicsVoLegacyBookingBookingmainClientWSClientVO>();
        }
        return this.clientVOCollection;
    }

    /**
     * Gets the value of the departedFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartedFlag() {
        return departedFlag;
    }

    /**
     * Sets the value of the departedFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartedFlag(String value) {
        this.departedFlag = value;
    }

    /**
     * Gets the value of the docIssueDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDocIssueDate() {
        return docIssueDate;
    }

    /**
     * Sets the value of the docIssueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDocIssueDate(XMLGregorianCalendar value) {
        this.docIssueDate = value;
    }

    /**
     * Gets the value of the gapOverlapFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGapOverlapFlag() {
        return gapOverlapFlag;
    }

    /**
     * Sets the value of the gapOverlapFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGapOverlapFlag(String value) {
        this.gapOverlapFlag = value;
    }

    /**
     * Gets the value of the itineraryDetailsVOCollection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itineraryDetailsVOCollection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItineraryDetailsVOCollection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSItineraryDetailsVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSItineraryDetailsVO> getItineraryDetailsVOCollection() {
        if (itineraryDetailsVOCollection == null) {
            itineraryDetailsVOCollection = new ArrayList<ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSItineraryDetailsVO>();
        }
        return this.itineraryDetailsVOCollection;
    }

    /**
     * Gets the value of the pnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPNR() {
        return pnr;
    }

    /**
     * Sets the value of the pnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPNR(String value) {
        this.pnr = value;
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
     * Gets the value of the pymtPlanDetailsVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtPlanDetailsVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtPlanDetailsVO getPymtPlanDetailsVO() {
        return pymtPlanDetailsVO;
    }

    /**
     * Sets the value of the pymtPlanDetailsVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtPlanDetailsVO }
     *     
     */
    public void setPymtPlanDetailsVO(ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtPlanDetailsVO value) {
        this.pymtPlanDetailsVO = value;
    }

    /**
     * Gets the value of the sourceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceCode() {
        return sourceCode;
    }

    /**
     * Sets the value of the sourceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceCode(String value) {
        this.sourceCode = value;
    }

    /**
     * Gets the value of the totComm property.
     * 
     */
    public float getTotComm() {
        return totComm;
    }

    /**
     * Sets the value of the totComm property.
     * 
     */
    public void setTotComm(float value) {
        this.totComm = value;
    }

    /**
     * Gets the value of the totDisc property.
     * 
     */
    public float getTotDisc() {
        return totDisc;
    }

    /**
     * Sets the value of the totDisc property.
     * 
     */
    public void setTotDisc(float value) {
        this.totDisc = value;
    }

    /**
     * Gets the value of the totFees property.
     * 
     */
    public float getTotFees() {
        return totFees;
    }

    /**
     * Sets the value of the totFees property.
     * 
     */
    public void setTotFees(float value) {
        this.totFees = value;
    }

    /**
     * Gets the value of the totGrossPrice property.
     * 
     */
    public float getTotGrossPrice() {
        return totGrossPrice;
    }

    /**
     * Sets the value of the totGrossPrice property.
     * 
     */
    public void setTotGrossPrice(float value) {
        this.totGrossPrice = value;
    }

    /**
     * Gets the value of the totNettPrice property.
     * 
     */
    public float getTotNettPrice() {
        return totNettPrice;
    }

    /**
     * Sets the value of the totNettPrice property.
     * 
     */
    public void setTotNettPrice(float value) {
        this.totNettPrice = value;
    }

}
