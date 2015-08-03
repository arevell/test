
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsFareRuleVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsFareRuleVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fareRuleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fareRuleDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsFareRuleVO", propOrder = {
    "fareRuleCode",
    "fareRuleDesc"
})
public class WsFareRuleVO {

    protected String fareRuleCode;
    protected String fareRuleDesc;

    /**
     * Gets the value of the fareRuleCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFareRuleCode() {
        return fareRuleCode;
    }

    /**
     * Sets the value of the fareRuleCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFareRuleCode(String value) {
        this.fareRuleCode = value;
    }

    /**
     * Gets the value of the fareRuleDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFareRuleDesc() {
        return fareRuleDesc;
    }

    /**
     * Sets the value of the fareRuleDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFareRuleDesc(String value) {
        this.fareRuleDesc = value;
    }

}
