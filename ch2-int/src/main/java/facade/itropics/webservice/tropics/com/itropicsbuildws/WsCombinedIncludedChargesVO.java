
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsCombinedIncludedChargesVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsCombinedIncludedChargesVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MandatoryMiscellaneousProducts" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsMandatoryMiscellaneousProductsVO" minOccurs="0"/>
 *         &lt;element name="Surcharges" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsSurchargesVO" minOccurs="0"/>
 *         &lt;element name="PortTax" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsPortTaxPriceVO" minOccurs="0"/>
 *         &lt;element name="FoodFund" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsFoodFundPriceVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsCombinedIncludedChargesVO", propOrder = {
    "mandatoryMiscellaneousProducts",
    "surcharges",
    "portTax",
    "foodFund"
})
public class WsCombinedIncludedChargesVO {

    @XmlElement(name = "MandatoryMiscellaneousProducts")
    protected WsMandatoryMiscellaneousProductsVO mandatoryMiscellaneousProducts;
    @XmlElement(name = "Surcharges")
    protected WsSurchargesVO surcharges;
    @XmlElement(name = "PortTax")
    protected WsPortTaxPriceVO portTax;
    @XmlElement(name = "FoodFund")
    protected WsFoodFundPriceVO foodFund;

    /**
     * Gets the value of the mandatoryMiscellaneousProducts property.
     * 
     * @return
     *     possible object is
     *     {@link WsMandatoryMiscellaneousProductsVO }
     *     
     */
    public WsMandatoryMiscellaneousProductsVO getMandatoryMiscellaneousProducts() {
        return mandatoryMiscellaneousProducts;
    }

    /**
     * Sets the value of the mandatoryMiscellaneousProducts property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsMandatoryMiscellaneousProductsVO }
     *     
     */
    public void setMandatoryMiscellaneousProducts(WsMandatoryMiscellaneousProductsVO value) {
        this.mandatoryMiscellaneousProducts = value;
    }

    /**
     * Gets the value of the surcharges property.
     * 
     * @return
     *     possible object is
     *     {@link WsSurchargesVO }
     *     
     */
    public WsSurchargesVO getSurcharges() {
        return surcharges;
    }

    /**
     * Sets the value of the surcharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsSurchargesVO }
     *     
     */
    public void setSurcharges(WsSurchargesVO value) {
        this.surcharges = value;
    }

    /**
     * Gets the value of the portTax property.
     * 
     * @return
     *     possible object is
     *     {@link WsPortTaxPriceVO }
     *     
     */
    public WsPortTaxPriceVO getPortTax() {
        return portTax;
    }

    /**
     * Sets the value of the portTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsPortTaxPriceVO }
     *     
     */
    public void setPortTax(WsPortTaxPriceVO value) {
        this.portTax = value;
    }

    /**
     * Gets the value of the foodFund property.
     * 
     * @return
     *     possible object is
     *     {@link WsFoodFundPriceVO }
     *     
     */
    public WsFoodFundPriceVO getFoodFund() {
        return foodFund;
    }

    /**
     * Sets the value of the foodFund property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsFoodFundPriceVO }
     *     
     */
    public void setFoodFund(WsFoodFundPriceVO value) {
        this.foodFund = value;
    }

}
