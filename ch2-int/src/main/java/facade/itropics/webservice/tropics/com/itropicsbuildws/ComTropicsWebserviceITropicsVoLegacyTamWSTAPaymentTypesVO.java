
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_tam_WSTAPaymentTypesVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_tam_WSTAPaymentTypesVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="consortiumCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consortiumName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="effectiveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="sellingCompanyID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="validPaymentMethods" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_tam_WSTAPaymentTypesVO", propOrder = {
    "consortiumCode",
    "consortiumName",
    "effectiveDate",
    "sellingCompanyID",
    "validPaymentMethods"
})
public class ComTropicsWebserviceITropicsVoLegacyTamWSTAPaymentTypesVO {

    protected String consortiumCode;
    protected String consortiumName;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar effectiveDate;
    protected long sellingCompanyID;
    protected String validPaymentMethods;

    /**
     * Gets the value of the consortiumCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsortiumCode() {
        return consortiumCode;
    }

    /**
     * Sets the value of the consortiumCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsortiumCode(String value) {
        this.consortiumCode = value;
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
     * Gets the value of the effectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Sets the value of the effectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEffectiveDate(XMLGregorianCalendar value) {
        this.effectiveDate = value;
    }

    /**
     * Gets the value of the sellingCompanyID property.
     * 
     */
    public long getSellingCompanyID() {
        return sellingCompanyID;
    }

    /**
     * Sets the value of the sellingCompanyID property.
     * 
     */
    public void setSellingCompanyID(long value) {
        this.sellingCompanyID = value;
    }

    /**
     * Gets the value of the validPaymentMethods property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidPaymentMethods() {
        return validPaymentMethods;
    }

    /**
     * Sets the value of the validPaymentMethods property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidPaymentMethods(String value) {
        this.validPaymentMethods = value;
    }

}
