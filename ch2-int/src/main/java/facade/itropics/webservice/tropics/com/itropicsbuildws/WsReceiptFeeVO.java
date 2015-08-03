
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsReceiptFeeVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsReceiptFeeVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="calculationAmount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="calculationType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsReceiptFeeVO", propOrder = {
    "amount",
    "calculationAmount",
    "calculationType",
    "name"
})
public class WsReceiptFeeVO {

    protected float amount;
    protected float calculationAmount;
    protected String calculationType;
    protected String name;

    /**
     * Gets the value of the amount property.
     * 
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     */
    public void setAmount(float value) {
        this.amount = value;
    }

    /**
     * Gets the value of the calculationAmount property.
     * 
     */
    public float getCalculationAmount() {
        return calculationAmount;
    }

    /**
     * Sets the value of the calculationAmount property.
     * 
     */
    public void setCalculationAmount(float value) {
        this.calculationAmount = value;
    }

    /**
     * Gets the value of the calculationType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalculationType() {
        return calculationType;
    }

    /**
     * Sets the value of the calculationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalculationType(String value) {
        this.calculationType = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
