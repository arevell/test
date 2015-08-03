
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_WSRstBookingVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_WSRstBookingVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="agencyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="amountPaid" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="balanceDue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="bkgID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="bookingAmount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="bookingID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bookingStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="carrier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consortiumName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consultantName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departureDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="dispatchDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="dueDeliveryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="lastDepartureDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="leadClient" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="locked" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="lockedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lockedFlow" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nettBookingAmount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="paymentLevelType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="printDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="printDueDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="trackingNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_WSRstBookingVO", propOrder = {
    "agencyName",
    "amountPaid",
    "balanceDue",
    "bkgID",
    "bookingAmount",
    "bookingID",
    "bookingStatus",
    "carrier",
    "consortiumName",
    "consultantName",
    "departureDate",
    "dispatchDate",
    "dueDeliveryDate",
    "lastDepartureDate",
    "leadClient",
    "locked",
    "lockedBy",
    "lockedFlow",
    "nettBookingAmount",
    "paymentLevelType",
    "paymentStatus",
    "printDate",
    "printDueDate",
    "trackingNo"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSRstBookingVO {

    protected String agencyName;
    protected float amountPaid;
    protected float balanceDue;
    protected long bkgID;
    protected float bookingAmount;
    protected String bookingID;
    protected String bookingStatus;
    protected String carrier;
    protected String consortiumName;
    protected String consultantName;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar departureDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dispatchDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dueDeliveryDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastDepartureDate;
    protected String leadClient;
    protected boolean locked;
    protected String lockedBy;
    protected String lockedFlow;
    protected float nettBookingAmount;
    protected String paymentLevelType;
    protected String paymentStatus;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar printDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar printDueDate;
    protected String trackingNo;

    /**
     * Gets the value of the agencyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgencyName() {
        return agencyName;
    }

    /**
     * Sets the value of the agencyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgencyName(String value) {
        this.agencyName = value;
    }

    /**
     * Gets the value of the amountPaid property.
     * 
     */
    public float getAmountPaid() {
        return amountPaid;
    }

    /**
     * Sets the value of the amountPaid property.
     * 
     */
    public void setAmountPaid(float value) {
        this.amountPaid = value;
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
     * Gets the value of the bookingAmount property.
     * 
     */
    public float getBookingAmount() {
        return bookingAmount;
    }

    /**
     * Sets the value of the bookingAmount property.
     * 
     */
    public void setBookingAmount(float value) {
        this.bookingAmount = value;
    }

    /**
     * Gets the value of the bookingID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingID() {
        return bookingID;
    }

    /**
     * Sets the value of the bookingID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingID(String value) {
        this.bookingID = value;
    }

    /**
     * Gets the value of the bookingStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingStatus() {
        return bookingStatus;
    }

    /**
     * Sets the value of the bookingStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingStatus(String value) {
        this.bookingStatus = value;
    }

    /**
     * Gets the value of the carrier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrier() {
        return carrier;
    }

    /**
     * Sets the value of the carrier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrier(String value) {
        this.carrier = value;
    }

    /**
     * Gets the value of the consortiumName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsortiumName() {
        return consortiumName;
    }

    /**
     * Sets the value of the consortiumName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsortiumName(String value) {
        this.consortiumName = value;
    }

    /**
     * Gets the value of the consultantName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsultantName() {
        return consultantName;
    }

    /**
     * Sets the value of the consultantName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsultantName(String value) {
        this.consultantName = value;
    }

    /**
     * Gets the value of the departureDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDepartureDate() {
        return departureDate;
    }

    /**
     * Sets the value of the departureDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDepartureDate(XMLGregorianCalendar value) {
        this.departureDate = value;
    }

    /**
     * Gets the value of the dispatchDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDispatchDate() {
        return dispatchDate;
    }

    /**
     * Sets the value of the dispatchDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDispatchDate(XMLGregorianCalendar value) {
        this.dispatchDate = value;
    }

    /**
     * Gets the value of the dueDeliveryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDueDeliveryDate() {
        return dueDeliveryDate;
    }

    /**
     * Sets the value of the dueDeliveryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDueDeliveryDate(XMLGregorianCalendar value) {
        this.dueDeliveryDate = value;
    }

    /**
     * Gets the value of the lastDepartureDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastDepartureDate() {
        return lastDepartureDate;
    }

    /**
     * Sets the value of the lastDepartureDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastDepartureDate(XMLGregorianCalendar value) {
        this.lastDepartureDate = value;
    }

    /**
     * Gets the value of the leadClient property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeadClient() {
        return leadClient;
    }

    /**
     * Sets the value of the leadClient property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeadClient(String value) {
        this.leadClient = value;
    }

    /**
     * Gets the value of the locked property.
     * 
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Sets the value of the locked property.
     * 
     */
    public void setLocked(boolean value) {
        this.locked = value;
    }

    /**
     * Gets the value of the lockedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLockedBy() {
        return lockedBy;
    }

    /**
     * Sets the value of the lockedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLockedBy(String value) {
        this.lockedBy = value;
    }

    /**
     * Gets the value of the lockedFlow property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLockedFlow() {
        return lockedFlow;
    }

    /**
     * Sets the value of the lockedFlow property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLockedFlow(String value) {
        this.lockedFlow = value;
    }

    /**
     * Gets the value of the nettBookingAmount property.
     * 
     */
    public float getNettBookingAmount() {
        return nettBookingAmount;
    }

    /**
     * Sets the value of the nettBookingAmount property.
     * 
     */
    public void setNettBookingAmount(float value) {
        this.nettBookingAmount = value;
    }

    /**
     * Gets the value of the paymentLevelType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentLevelType() {
        return paymentLevelType;
    }

    /**
     * Sets the value of the paymentLevelType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentLevelType(String value) {
        this.paymentLevelType = value;
    }

    /**
     * Gets the value of the paymentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * Sets the value of the paymentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentStatus(String value) {
        this.paymentStatus = value;
    }

    /**
     * Gets the value of the printDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPrintDate() {
        return printDate;
    }

    /**
     * Sets the value of the printDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPrintDate(XMLGregorianCalendar value) {
        this.printDate = value;
    }

    /**
     * Gets the value of the printDueDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPrintDueDate() {
        return printDueDate;
    }

    /**
     * Sets the value of the printDueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPrintDueDate(XMLGregorianCalendar value) {
        this.printDueDate = value;
    }

    /**
     * Gets the value of the trackingNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackingNo() {
        return trackingNo;
    }

    /**
     * Sets the value of the trackingNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackingNo(String value) {
        this.trackingNo = value;
    }

}
