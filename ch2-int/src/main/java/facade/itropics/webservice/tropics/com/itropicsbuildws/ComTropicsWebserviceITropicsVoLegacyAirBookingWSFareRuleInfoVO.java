
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_air_booking_WSFareRuleInfoVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_air_booking_WSFareRuleInfoVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="airline" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="arrivalAirport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departureAirport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fareReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_air_booking_WSFareRuleInfoVO", propOrder = {
    "airline",
    "arrivalAirport",
    "departureAirport",
    "fareReference"
})
public class ComTropicsWebserviceITropicsVoLegacyAirBookingWSFareRuleInfoVO {

    protected String airline;
    protected String arrivalAirport;
    protected String departureAirport;
    protected String fareReference;

    /**
     * Gets the value of the airline property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAirline() {
        return airline;
    }

    /**
     * Sets the value of the airline property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAirline(String value) {
        this.airline = value;
    }

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
     * Gets the value of the fareReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFareReference() {
        return fareReference;
    }

    /**
     * Sets the value of the fareReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFareReference(String value) {
        this.fareReference = value;
    }

}
