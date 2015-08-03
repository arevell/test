
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_current_products_misctransfer_WSInFlightSegment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_current_products_misctransfer_WSInFlightSegment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arrivalCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departureCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="localArrivalDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="localDepartureDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_current_products_misctransfer_WSInFlightSegment", propOrder = {
    "arrivalCity",
    "departureCity",
    "localArrivalDateTime",
    "localDepartureDateTime"
})
public class ComTropicsWebserviceITropicsVoCurrentProductsMisctransferWSInFlightSegment {

    protected String arrivalCity;
    protected String departureCity;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar localArrivalDateTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar localDepartureDateTime;

    /**
     * Gets the value of the arrivalCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrivalCity() {
        return arrivalCity;
    }

    /**
     * Sets the value of the arrivalCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrivalCity(String value) {
        this.arrivalCity = value;
    }

    /**
     * Gets the value of the departureCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureCity() {
        return departureCity;
    }

    /**
     * Sets the value of the departureCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureCity(String value) {
        this.departureCity = value;
    }

    /**
     * Gets the value of the localArrivalDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLocalArrivalDateTime() {
        return localArrivalDateTime;
    }

    /**
     * Sets the value of the localArrivalDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLocalArrivalDateTime(XMLGregorianCalendar value) {
        this.localArrivalDateTime = value;
    }

    /**
     * Gets the value of the localDepartureDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLocalDepartureDateTime() {
        return localDepartureDateTime;
    }

    /**
     * Sets the value of the localDepartureDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLocalDepartureDateTime(XMLGregorianCalendar value) {
        this.localDepartureDateTime = value;
    }

}
