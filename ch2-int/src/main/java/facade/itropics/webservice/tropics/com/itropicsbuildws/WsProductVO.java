
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsProductVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsProductVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StartCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EndCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DurationInDays" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="DurationInNights" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="BrochureName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BrochureCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OnlineBookable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IsTourPackage" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="OperatingProduct" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsOperatingProductVO" minOccurs="0"/>
 *         &lt;element name="IncludedProducts" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsIncludedProductsVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsProductVO", propOrder = {
    "code",
    "name",
    "description",
    "startCity",
    "endCity",
    "durationInDays",
    "durationInNights",
    "brochureName",
    "brochureCode",
    "onlineBookable",
    "isTourPackage",
    "operatingProduct",
    "includedProducts"
})
public class WsProductVO {

    @XmlElement(name = "Code")
    protected String code;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "StartCity")
    protected String startCity;
    @XmlElement(name = "EndCity")
    protected String endCity;
    @XmlElement(name = "DurationInDays")
    protected Integer durationInDays;
    @XmlElement(name = "DurationInNights")
    protected Integer durationInNights;
    @XmlElement(name = "BrochureName")
    protected String brochureName;
    @XmlElement(name = "BrochureCode")
    protected String brochureCode;
    @XmlElement(name = "OnlineBookable")
    protected Boolean onlineBookable;
    @XmlElement(name = "IsTourPackage")
    protected Boolean isTourPackage;
    @XmlElement(name = "OperatingProduct")
    protected WsOperatingProductVO operatingProduct;
    @XmlElement(name = "IncludedProducts")
    protected WsIncludedProductsVO includedProducts;

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
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

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the startCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartCity() {
        return startCity;
    }

    /**
     * Sets the value of the startCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartCity(String value) {
        this.startCity = value;
    }

    /**
     * Gets the value of the endCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndCity() {
        return endCity;
    }

    /**
     * Sets the value of the endCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndCity(String value) {
        this.endCity = value;
    }

    /**
     * Gets the value of the durationInDays property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDurationInDays() {
        return durationInDays;
    }

    /**
     * Sets the value of the durationInDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDurationInDays(Integer value) {
        this.durationInDays = value;
    }

    /**
     * Gets the value of the durationInNights property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDurationInNights() {
        return durationInNights;
    }

    /**
     * Sets the value of the durationInNights property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDurationInNights(Integer value) {
        this.durationInNights = value;
    }

    /**
     * Gets the value of the brochureName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrochureName() {
        return brochureName;
    }

    /**
     * Sets the value of the brochureName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrochureName(String value) {
        this.brochureName = value;
    }

    /**
     * Gets the value of the brochureCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrochureCode() {
        return brochureCode;
    }

    /**
     * Sets the value of the brochureCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrochureCode(String value) {
        this.brochureCode = value;
    }

    /**
     * Gets the value of the onlineBookable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOnlineBookable() {
        return onlineBookable;
    }

    /**
     * Sets the value of the onlineBookable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOnlineBookable(Boolean value) {
        this.onlineBookable = value;
    }

    /**
     * Gets the value of the isTourPackage property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsTourPackage() {
        return isTourPackage;
    }

    /**
     * Sets the value of the isTourPackage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsTourPackage(Boolean value) {
        this.isTourPackage = value;
    }

    /**
     * Gets the value of the operatingProduct property.
     * 
     * @return
     *     possible object is
     *     {@link WsOperatingProductVO }
     *     
     */
    public WsOperatingProductVO getOperatingProduct() {
        return operatingProduct;
    }

    /**
     * Sets the value of the operatingProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsOperatingProductVO }
     *     
     */
    public void setOperatingProduct(WsOperatingProductVO value) {
        this.operatingProduct = value;
    }

    /**
     * Gets the value of the includedProducts property.
     * 
     * @return
     *     possible object is
     *     {@link WsIncludedProductsVO }
     *     
     */
    public WsIncludedProductsVO getIncludedProducts() {
        return includedProducts;
    }

    /**
     * Sets the value of the includedProducts property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsIncludedProductsVO }
     *     
     */
    public void setIncludedProducts(WsIncludedProductsVO value) {
        this.includedProducts = value;
    }

}
