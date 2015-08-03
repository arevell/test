
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for wsTourSummaryDepartureVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsTourSummaryDepartureVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="departureCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departureEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="departureStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsTourSummaryDepartureVO", propOrder = {
    "departureCode",
    "departureEndDate",
    "departureStartDate"
})
public class WsTourSummaryDepartureVO {

    protected String departureCode;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar departureEndDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar departureStartDate;

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
     * Gets the value of the departureEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDepartureEndDate() {
        return departureEndDate;
    }

    /**
     * Sets the value of the departureEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDepartureEndDate(XMLGregorianCalendar value) {
        this.departureEndDate = value;
    }

    /**
     * Gets the value of the departureStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDepartureStartDate() {
        return departureStartDate;
    }

    /**
     * Sets the value of the departureStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDepartureStartDate(XMLGregorianCalendar value) {
        this.departureStartDate = value;
    }

}
