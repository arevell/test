
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_WSErrorMessageVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_WSErrorMessageVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="errorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errorFlow" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sucessful" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_WSErrorMessageVO", propOrder = {
    "errorCode",
    "errorFlow",
    "sucessful"
})
public class ComTropicsWebserviceITropicsVoLegacyWSErrorMessageVO {

    protected String errorCode;
    protected String errorFlow;
    protected boolean sucessful;

    /**
     * Gets the value of the errorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorCode(String value) {
        this.errorCode = value;
    }

    /**
     * Gets the value of the errorFlow property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorFlow() {
        return errorFlow;
    }

    /**
     * Sets the value of the errorFlow property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorFlow(String value) {
        this.errorFlow = value;
    }

    /**
     * Gets the value of the sucessful property.
     * 
     */
    public boolean isSucessful() {
        return sucessful;
    }

    /**
     * Sets the value of the sucessful property.
     * 
     */
    public void setSucessful(boolean value) {
        this.sucessful = value;
    }

}
