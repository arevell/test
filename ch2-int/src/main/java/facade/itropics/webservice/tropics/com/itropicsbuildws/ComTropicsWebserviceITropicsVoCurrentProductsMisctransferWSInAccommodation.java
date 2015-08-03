
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_current_products_misctransfer_WSInAccommodation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_current_products_misctransfer_WSInAccommodation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="postAccommodationBooked" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="postEndCityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="preAccommodationBooked" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="preStartCityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_current_products_misctransfer_WSInAccommodation", propOrder = {
    "postAccommodationBooked",
    "postEndCityCode",
    "postEndDate",
    "preAccommodationBooked",
    "preStartCityCode",
    "preStartDate"
})
public class ComTropicsWebserviceITropicsVoCurrentProductsMisctransferWSInAccommodation {

    protected boolean postAccommodationBooked;
    protected String postEndCityCode;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar postEndDate;
    protected boolean preAccommodationBooked;
    protected String preStartCityCode;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar preStartDate;

    /**
     * Gets the value of the postAccommodationBooked property.
     * 
     */
    public boolean isPostAccommodationBooked() {
        return postAccommodationBooked;
    }

    /**
     * Sets the value of the postAccommodationBooked property.
     * 
     */
    public void setPostAccommodationBooked(boolean value) {
        this.postAccommodationBooked = value;
    }

    /**
     * Gets the value of the postEndCityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostEndCityCode() {
        return postEndCityCode;
    }

    /**
     * Sets the value of the postEndCityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostEndCityCode(String value) {
        this.postEndCityCode = value;
    }

    /**
     * Gets the value of the postEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPostEndDate() {
        return postEndDate;
    }

    /**
     * Sets the value of the postEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPostEndDate(XMLGregorianCalendar value) {
        this.postEndDate = value;
    }

    /**
     * Gets the value of the preAccommodationBooked property.
     * 
     */
    public boolean isPreAccommodationBooked() {
        return preAccommodationBooked;
    }

    /**
     * Sets the value of the preAccommodationBooked property.
     * 
     */
    public void setPreAccommodationBooked(boolean value) {
        this.preAccommodationBooked = value;
    }

    /**
     * Gets the value of the preStartCityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreStartCityCode() {
        return preStartCityCode;
    }

    /**
     * Sets the value of the preStartCityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreStartCityCode(String value) {
        this.preStartCityCode = value;
    }

    /**
     * Gets the value of the preStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPreStartDate() {
        return preStartDate;
    }

    /**
     * Sets the value of the preStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPreStartDate(XMLGregorianCalendar value) {
        this.preStartDate = value;
    }

}
