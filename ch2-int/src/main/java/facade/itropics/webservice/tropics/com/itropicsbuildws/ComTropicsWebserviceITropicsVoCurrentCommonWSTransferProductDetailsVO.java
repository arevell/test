
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_current_common_WSTransferProductDetailsVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_current_common_WSTransferProductDetailsVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arrival" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="complementary" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="cost" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ITropicsDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_current_common_WSTransferProductDetailsVO", propOrder = {
    "arrival",
    "complementary",
    "cost",
    "iTropicsDesc",
    "productCode"
})
public class ComTropicsWebserviceITropicsVoCurrentCommonWSTransferProductDetailsVO {

    protected boolean arrival;
    protected boolean complementary;
    protected double cost;
    @XmlElement(name = "ITropicsDesc")
    protected String iTropicsDesc;
    protected String productCode;

    /**
     * Gets the value of the arrival property.
     * 
     */
    public boolean isArrival() {
        return arrival;
    }

    /**
     * Sets the value of the arrival property.
     * 
     */
    public void setArrival(boolean value) {
        this.arrival = value;
    }

    /**
     * Gets the value of the complementary property.
     * 
     */
    public boolean isComplementary() {
        return complementary;
    }

    /**
     * Sets the value of the complementary property.
     * 
     */
    public void setComplementary(boolean value) {
        this.complementary = value;
    }

    /**
     * Gets the value of the cost property.
     * 
     */
    public double getCost() {
        return cost;
    }

    /**
     * Sets the value of the cost property.
     * 
     */
    public void setCost(double value) {
        this.cost = value;
    }

    /**
     * Gets the value of the iTropicsDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getITropicsDesc() {
        return iTropicsDesc;
    }

    /**
     * Sets the value of the iTropicsDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setITropicsDesc(String value) {
        this.iTropicsDesc = value;
    }

    /**
     * Gets the value of the productCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * Sets the value of the productCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCode(String value) {
        this.productCode = value;
    }

}
