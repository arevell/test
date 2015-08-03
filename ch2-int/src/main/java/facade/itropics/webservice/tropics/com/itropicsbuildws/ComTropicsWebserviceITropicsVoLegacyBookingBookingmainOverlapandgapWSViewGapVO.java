
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_overlapandgap_WSViewGapVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_overlapandgap_WSViewGapVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clientProductID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="gapDateFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="gapDateTo" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="gapRange" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postGapProductName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postGapStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="postGapStartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="preGapProductEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="preGapProductEndTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="preGapProductName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_overlapandgap_WSViewGapVO", propOrder = {
    "clientProductID",
    "gapDateFrom",
    "gapDateTo",
    "gapRange",
    "postGapProductName",
    "postGapStartDate",
    "postGapStartTime",
    "preGapProductEndDate",
    "preGapProductEndTime",
    "preGapProductName",
    "remarks"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingmainOverlapandgapWSViewGapVO {

    protected long clientProductID;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar gapDateFrom;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar gapDateTo;
    protected String gapRange;
    protected String postGapProductName;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar postGapStartDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar postGapStartTime;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar preGapProductEndDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar preGapProductEndTime;
    protected String preGapProductName;
    protected String remarks;

    /**
     * Gets the value of the clientProductID property.
     * 
     */
    public long getClientProductID() {
        return clientProductID;
    }

    /**
     * Sets the value of the clientProductID property.
     * 
     */
    public void setClientProductID(long value) {
        this.clientProductID = value;
    }

    /**
     * Gets the value of the gapDateFrom property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getGapDateFrom() {
        return gapDateFrom;
    }

    /**
     * Sets the value of the gapDateFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setGapDateFrom(XMLGregorianCalendar value) {
        this.gapDateFrom = value;
    }

    /**
     * Gets the value of the gapDateTo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getGapDateTo() {
        return gapDateTo;
    }

    /**
     * Sets the value of the gapDateTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setGapDateTo(XMLGregorianCalendar value) {
        this.gapDateTo = value;
    }

    /**
     * Gets the value of the gapRange property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGapRange() {
        return gapRange;
    }

    /**
     * Sets the value of the gapRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGapRange(String value) {
        this.gapRange = value;
    }

    /**
     * Gets the value of the postGapProductName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostGapProductName() {
        return postGapProductName;
    }

    /**
     * Sets the value of the postGapProductName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostGapProductName(String value) {
        this.postGapProductName = value;
    }

    /**
     * Gets the value of the postGapStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPostGapStartDate() {
        return postGapStartDate;
    }

    /**
     * Sets the value of the postGapStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPostGapStartDate(XMLGregorianCalendar value) {
        this.postGapStartDate = value;
    }

    /**
     * Gets the value of the postGapStartTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPostGapStartTime() {
        return postGapStartTime;
    }

    /**
     * Sets the value of the postGapStartTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPostGapStartTime(XMLGregorianCalendar value) {
        this.postGapStartTime = value;
    }

    /**
     * Gets the value of the preGapProductEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPreGapProductEndDate() {
        return preGapProductEndDate;
    }

    /**
     * Sets the value of the preGapProductEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPreGapProductEndDate(XMLGregorianCalendar value) {
        this.preGapProductEndDate = value;
    }

    /**
     * Gets the value of the preGapProductEndTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPreGapProductEndTime() {
        return preGapProductEndTime;
    }

    /**
     * Sets the value of the preGapProductEndTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPreGapProductEndTime(XMLGregorianCalendar value) {
        this.preGapProductEndTime = value;
    }

    /**
     * Gets the value of the preGapProductName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreGapProductName() {
        return preGapProductName;
    }

    /**
     * Sets the value of the preGapProductName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreGapProductName(String value) {
        this.preGapProductName = value;
    }

    /**
     * Gets the value of the remarks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * Sets the value of the remarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemarks(String value) {
        this.remarks = value;
    }

}
