
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_air_booking_WSAirItineraryVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_air_booking_WSAirItineraryVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arrivalAirport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="arrivalAirportCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="arrivalAirportName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="arrivalDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="classOfService" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departureAirport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departureAirportCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departureAirportName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departureDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="fareBasisCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flightNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="flightSequenceNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="isAlreadyBooked" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isInboundFlight" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="marketingAirline" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="marketingAirlineFullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="marketingCabin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operatingAirline" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operatingAirlineFullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="requestedSegmentRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stopQuantity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="utcArrivalDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="utcDepartureDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_air_booking_WSAirItineraryVO", propOrder = {
    "arrivalAirport",
    "arrivalAirportCity",
    "arrivalAirportName",
    "arrivalDate",
    "classOfService",
    "departureAirport",
    "departureAirportCity",
    "departureAirportName",
    "departureDate",
    "fareBasisCode",
    "flightNo",
    "flightSequenceNumber",
    "isAlreadyBooked",
    "isInboundFlight",
    "marketingAirline",
    "marketingAirlineFullName",
    "marketingCabin",
    "operatingAirline",
    "operatingAirlineFullName",
    "requestedSegmentRef",
    "status",
    "stopQuantity",
    "utcArrivalDate",
    "utcDepartureDate"
})
public class ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirItineraryVO {

    protected String arrivalAirport;
    protected String arrivalAirportCity;
    protected String arrivalAirportName;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar arrivalDate;
    protected String classOfService;
    protected String departureAirport;
    protected String departureAirportCity;
    protected String departureAirportName;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar departureDate;
    protected String fareBasisCode;
    protected int flightNo;
    protected int flightSequenceNumber;
    protected boolean isAlreadyBooked;
    protected boolean isInboundFlight;
    protected String marketingAirline;
    protected String marketingAirlineFullName;
    protected String marketingCabin;
    protected String operatingAirline;
    protected String operatingAirlineFullName;
    protected String requestedSegmentRef;
    protected String status;
    protected int stopQuantity;
    protected String utcArrivalDate;
    protected String utcDepartureDate;

    /**
     * Gets the value of the arrivalAirport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrivalAirport() {
        return arrivalAirport;
    }

    /**
     * Sets the value of the arrivalAirport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrivalAirport(String value) {
        this.arrivalAirport = value;
    }

    /**
     * Gets the value of the arrivalAirportCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrivalAirportCity() {
        return arrivalAirportCity;
    }

    /**
     * Sets the value of the arrivalAirportCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrivalAirportCity(String value) {
        this.arrivalAirportCity = value;
    }

    /**
     * Gets the value of the arrivalAirportName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    /**
     * Sets the value of the arrivalAirportName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrivalAirportName(String value) {
        this.arrivalAirportName = value;
    }

    /**
     * Gets the value of the arrivalDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Sets the value of the arrivalDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setArrivalDate(XMLGregorianCalendar value) {
        this.arrivalDate = value;
    }

    /**
     * Gets the value of the classOfService property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassOfService() {
        return classOfService;
    }

    /**
     * Sets the value of the classOfService property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassOfService(String value) {
        this.classOfService = value;
    }

    /**
     * Gets the value of the departureAirport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureAirport() {
        return departureAirport;
    }

    /**
     * Sets the value of the departureAirport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureAirport(String value) {
        this.departureAirport = value;
    }

    /**
     * Gets the value of the departureAirportCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureAirportCity() {
        return departureAirportCity;
    }

    /**
     * Sets the value of the departureAirportCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureAirportCity(String value) {
        this.departureAirportCity = value;
    }

    /**
     * Gets the value of the departureAirportName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureAirportName() {
        return departureAirportName;
    }

    /**
     * Sets the value of the departureAirportName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureAirportName(String value) {
        this.departureAirportName = value;
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
     * Gets the value of the fareBasisCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFareBasisCode() {
        return fareBasisCode;
    }

    /**
     * Sets the value of the fareBasisCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFareBasisCode(String value) {
        this.fareBasisCode = value;
    }

    /**
     * Gets the value of the flightNo property.
     * 
     */
    public int getFlightNo() {
        return flightNo;
    }

    /**
     * Sets the value of the flightNo property.
     * 
     */
    public void setFlightNo(int value) {
        this.flightNo = value;
    }

    /**
     * Gets the value of the flightSequenceNumber property.
     * 
     */
    public int getFlightSequenceNumber() {
        return flightSequenceNumber;
    }

    /**
     * Sets the value of the flightSequenceNumber property.
     * 
     */
    public void setFlightSequenceNumber(int value) {
        this.flightSequenceNumber = value;
    }

    /**
     * Gets the value of the isAlreadyBooked property.
     * 
     */
    public boolean isIsAlreadyBooked() {
        return isAlreadyBooked;
    }

    /**
     * Sets the value of the isAlreadyBooked property.
     * 
     */
    public void setIsAlreadyBooked(boolean value) {
        this.isAlreadyBooked = value;
    }

    /**
     * Gets the value of the isInboundFlight property.
     * 
     */
    public boolean isIsInboundFlight() {
        return isInboundFlight;
    }

    /**
     * Sets the value of the isInboundFlight property.
     * 
     */
    public void setIsInboundFlight(boolean value) {
        this.isInboundFlight = value;
    }

    /**
     * Gets the value of the marketingAirline property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarketingAirline() {
        return marketingAirline;
    }

    /**
     * Sets the value of the marketingAirline property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarketingAirline(String value) {
        this.marketingAirline = value;
    }

    /**
     * Gets the value of the marketingAirlineFullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarketingAirlineFullName() {
        return marketingAirlineFullName;
    }

    /**
     * Sets the value of the marketingAirlineFullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarketingAirlineFullName(String value) {
        this.marketingAirlineFullName = value;
    }

    /**
     * Gets the value of the marketingCabin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarketingCabin() {
        return marketingCabin;
    }

    /**
     * Sets the value of the marketingCabin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarketingCabin(String value) {
        this.marketingCabin = value;
    }

    /**
     * Gets the value of the operatingAirline property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatingAirline() {
        return operatingAirline;
    }

    /**
     * Sets the value of the operatingAirline property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatingAirline(String value) {
        this.operatingAirline = value;
    }

    /**
     * Gets the value of the operatingAirlineFullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatingAirlineFullName() {
        return operatingAirlineFullName;
    }

    /**
     * Sets the value of the operatingAirlineFullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatingAirlineFullName(String value) {
        this.operatingAirlineFullName = value;
    }

    /**
     * Gets the value of the requestedSegmentRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestedSegmentRef() {
        return requestedSegmentRef;
    }

    /**
     * Sets the value of the requestedSegmentRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestedSegmentRef(String value) {
        this.requestedSegmentRef = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the stopQuantity property.
     * 
     */
    public int getStopQuantity() {
        return stopQuantity;
    }

    /**
     * Sets the value of the stopQuantity property.
     * 
     */
    public void setStopQuantity(int value) {
        this.stopQuantity = value;
    }

    /**
     * Gets the value of the utcArrivalDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUtcArrivalDate() {
        return utcArrivalDate;
    }

    /**
     * Sets the value of the utcArrivalDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUtcArrivalDate(String value) {
        this.utcArrivalDate = value;
    }

    /**
     * Gets the value of the utcDepartureDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUtcDepartureDate() {
        return utcDepartureDate;
    }

    /**
     * Sets the value of the utcDepartureDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUtcDepartureDate(String value) {
        this.utcDepartureDate = value;
    }

}
