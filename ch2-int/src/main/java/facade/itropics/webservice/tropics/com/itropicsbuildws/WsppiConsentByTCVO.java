
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for wsppiConsentByTCVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsppiConsentByTCVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dateOfPPIConsentByTC" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ppiAgreedByTCFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ppiSignatureByTC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsppiConsentByTCVO", propOrder = {
    "dateOfPPIConsentByTC",
    "ppiAgreedByTCFlag",
    "ppiSignatureByTC"
})
public class WsppiConsentByTCVO {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateOfPPIConsentByTC;
    protected String ppiAgreedByTCFlag;
    protected String ppiSignatureByTC;

    /**
     * Gets the value of the dateOfPPIConsentByTC property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateOfPPIConsentByTC() {
        return dateOfPPIConsentByTC;
    }

    /**
     * Sets the value of the dateOfPPIConsentByTC property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateOfPPIConsentByTC(XMLGregorianCalendar value) {
        this.dateOfPPIConsentByTC = value;
    }

    /**
     * Gets the value of the ppiAgreedByTCFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPpiAgreedByTCFlag() {
        return ppiAgreedByTCFlag;
    }

    /**
     * Sets the value of the ppiAgreedByTCFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPpiAgreedByTCFlag(String value) {
        this.ppiAgreedByTCFlag = value;
    }

    /**
     * Gets the value of the ppiSignatureByTC property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPpiSignatureByTC() {
        return ppiSignatureByTC;
    }

    /**
     * Sets the value of the ppiSignatureByTC property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPpiSignatureByTC(String value) {
        this.ppiSignatureByTC = value;
    }

}
