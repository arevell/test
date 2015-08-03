
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_products_other_WSAccomAvailabilityAndPriceVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_products_other_WSAccomAvailabilityAndPriceVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="adultQuadPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="adultSinglePrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="adultTriplePrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="adultTwinPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="childPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="productCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_products_other_WSAccomAvailabilityAndPriceVO", propOrder = {
    "adultQuadPrice",
    "adultSinglePrice",
    "adultTriplePrice",
    "adultTwinPrice",
    "childPrice",
    "productCode",
    "productID",
    "productName",
    "status"
})
public class ComTropicsWebserviceITropicsVoLegacyProductsOtherWSAccomAvailabilityAndPriceVO {

    protected float adultQuadPrice;
    protected float adultSinglePrice;
    protected float adultTriplePrice;
    protected float adultTwinPrice;
    protected float childPrice;
    protected String productCode;
    protected long productID;
    protected String productName;
    protected String status;

    /**
     * Gets the value of the adultQuadPrice property.
     * 
     */
    public float getAdultQuadPrice() {
        return adultQuadPrice;
    }

    /**
     * Sets the value of the adultQuadPrice property.
     * 
     */
    public void setAdultQuadPrice(float value) {
        this.adultQuadPrice = value;
    }

    /**
     * Gets the value of the adultSinglePrice property.
     * 
     */
    public float getAdultSinglePrice() {
        return adultSinglePrice;
    }

    /**
     * Sets the value of the adultSinglePrice property.
     * 
     */
    public void setAdultSinglePrice(float value) {
        this.adultSinglePrice = value;
    }

    /**
     * Gets the value of the adultTriplePrice property.
     * 
     */
    public float getAdultTriplePrice() {
        return adultTriplePrice;
    }

    /**
     * Sets the value of the adultTriplePrice property.
     * 
     */
    public void setAdultTriplePrice(float value) {
        this.adultTriplePrice = value;
    }

    /**
     * Gets the value of the adultTwinPrice property.
     * 
     */
    public float getAdultTwinPrice() {
        return adultTwinPrice;
    }

    /**
     * Sets the value of the adultTwinPrice property.
     * 
     */
    public void setAdultTwinPrice(float value) {
        this.adultTwinPrice = value;
    }

    /**
     * Gets the value of the childPrice property.
     * 
     */
    public float getChildPrice() {
        return childPrice;
    }

    /**
     * Sets the value of the childPrice property.
     * 
     */
    public void setChildPrice(float value) {
        this.childPrice = value;
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

    /**
     * Gets the value of the productID property.
     * 
     */
    public long getProductID() {
        return productID;
    }

    /**
     * Sets the value of the productID property.
     * 
     */
    public void setProductID(long value) {
        this.productID = value;
    }

    /**
     * Gets the value of the productName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the value of the productName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductName(String value) {
        this.productName = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

}
