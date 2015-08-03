
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for wsTourDepartureInfoVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsTourDepartureInfoVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="adultQuadRoomPrice" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="adultSingleRoomPrice" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="adultTripleRoomPrice" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="adultTwinRoomPrice" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="availabilityStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="baseDepartureID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="childPrice" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="departureCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departureId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="endTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="guaranteedStatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="landOnlyReduction" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="maxCapacity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="notes" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsiProductNoteVO" minOccurs="0"/>
 *         &lt;element name="passengersBooked" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="startTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsTourDepartureInfoVO", propOrder = {
    "adultQuadRoomPrice",
    "adultSingleRoomPrice",
    "adultTripleRoomPrice",
    "adultTwinRoomPrice",
    "availabilityStatus",
    "baseDepartureID",
    "childPrice",
    "departureCode",
    "departureId",
    "endDate",
    "endTime",
    "guaranteedStatus",
    "landOnlyReduction",
    "maxCapacity",
    "notes",
    "passengersBooked",
    "startDate",
    "startTime"
})
public class WsTourDepartureInfoVO {

    protected long adultQuadRoomPrice;
    protected long adultSingleRoomPrice;
    protected long adultTripleRoomPrice;
    protected long adultTwinRoomPrice;
    protected String availabilityStatus;
    protected long baseDepartureID;
    protected long childPrice;
    protected String departureCode;
    protected long departureId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endTime;
    protected int guaranteedStatus;
    protected long landOnlyReduction;
    protected int maxCapacity;
    protected WsiProductNoteVO notes;
    protected int passengersBooked;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startTime;

    /**
     * Gets the value of the adultQuadRoomPrice property.
     * 
     */
    public long getAdultQuadRoomPrice() {
        return adultQuadRoomPrice;
    }

    /**
     * Sets the value of the adultQuadRoomPrice property.
     * 
     */
    public void setAdultQuadRoomPrice(long value) {
        this.adultQuadRoomPrice = value;
    }

    /**
     * Gets the value of the adultSingleRoomPrice property.
     * 
     */
    public long getAdultSingleRoomPrice() {
        return adultSingleRoomPrice;
    }

    /**
     * Sets the value of the adultSingleRoomPrice property.
     * 
     */
    public void setAdultSingleRoomPrice(long value) {
        this.adultSingleRoomPrice = value;
    }

    /**
     * Gets the value of the adultTripleRoomPrice property.
     * 
     */
    public long getAdultTripleRoomPrice() {
        return adultTripleRoomPrice;
    }

    /**
     * Sets the value of the adultTripleRoomPrice property.
     * 
     */
    public void setAdultTripleRoomPrice(long value) {
        this.adultTripleRoomPrice = value;
    }

    /**
     * Gets the value of the adultTwinRoomPrice property.
     * 
     */
    public long getAdultTwinRoomPrice() {
        return adultTwinRoomPrice;
    }

    /**
     * Sets the value of the adultTwinRoomPrice property.
     * 
     */
    public void setAdultTwinRoomPrice(long value) {
        this.adultTwinRoomPrice = value;
    }

    /**
     * Gets the value of the availabilityStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    /**
     * Sets the value of the availabilityStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAvailabilityStatus(String value) {
        this.availabilityStatus = value;
    }

    /**
     * Gets the value of the baseDepartureID property.
     * 
     */
    public long getBaseDepartureID() {
        return baseDepartureID;
    }

    /**
     * Sets the value of the baseDepartureID property.
     * 
     */
    public void setBaseDepartureID(long value) {
        this.baseDepartureID = value;
    }

    /**
     * Gets the value of the childPrice property.
     * 
     */
    public long getChildPrice() {
        return childPrice;
    }

    /**
     * Sets the value of the childPrice property.
     * 
     */
    public void setChildPrice(long value) {
        this.childPrice = value;
    }

    /**
     * Gets the value of the departureCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureCode() {
        return departureCode;
    }

    /**
     * Sets the value of the departureCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureCode(String value) {
        this.departureCode = value;
    }

    /**
     * Gets the value of the departureId property.
     * 
     */
    public long getDepartureId() {
        return departureId;
    }

    /**
     * Sets the value of the departureId property.
     * 
     */
    public void setDepartureId(long value) {
        this.departureId = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the endTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndTime(XMLGregorianCalendar value) {
        this.endTime = value;
    }

    /**
     * Gets the value of the guaranteedStatus property.
     * 
     */
    public int getGuaranteedStatus() {
        return guaranteedStatus;
    }

    /**
     * Sets the value of the guaranteedStatus property.
     * 
     */
    public void setGuaranteedStatus(int value) {
        this.guaranteedStatus = value;
    }

    /**
     * Gets the value of the landOnlyReduction property.
     * 
     */
    public long getLandOnlyReduction() {
        return landOnlyReduction;
    }

    /**
     * Sets the value of the landOnlyReduction property.
     * 
     */
    public void setLandOnlyReduction(long value) {
        this.landOnlyReduction = value;
    }

    /**
     * Gets the value of the maxCapacity property.
     * 
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * Sets the value of the maxCapacity property.
     * 
     */
    public void setMaxCapacity(int value) {
        this.maxCapacity = value;
    }

    /**
     * Gets the value of the notes property.
     * 
     * @return
     *     possible object is
     *     {@link WsiProductNoteVO }
     *     
     */
    public WsiProductNoteVO getNotes() {
        return notes;
    }

    /**
     * Sets the value of the notes property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsiProductNoteVO }
     *     
     */
    public void setNotes(WsiProductNoteVO value) {
        this.notes = value;
    }

    /**
     * Gets the value of the passengersBooked property.
     * 
     */
    public int getPassengersBooked() {
        return passengersBooked;
    }

    /**
     * Sets the value of the passengersBooked property.
     * 
     */
    public void setPassengersBooked(int value) {
        this.passengersBooked = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the startTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartTime(XMLGregorianCalendar value) {
        this.startTime = value;
    }

}
