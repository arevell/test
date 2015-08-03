
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsTourDepartureInfoPagingVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsTourDepartureInfoPagingVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="airPriceIncluded" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="brochureCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departuresGuaranteed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="durationInDays" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="durationInNights" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="endCityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endCityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errorMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errorMessagesArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_current_common_WSErrorMessagesVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="productCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sellingCompanyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sellingCompanyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startCityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startCityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="successful" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="teenagerDiscount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="tourDepartureInfo" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsTourDepartureInfoVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsTourDepartureInfoPagingVO", propOrder = {
    "airPriceIncluded",
    "brochureCode",
    "currencyCode",
    "departuresGuaranteed",
    "durationInDays",
    "durationInNights",
    "endCityCode",
    "endCityName",
    "endCountry",
    "errorMessage",
    "errorMessagesArray",
    "productCode",
    "productDescription",
    "productId",
    "productName",
    "sellingCompanyCode",
    "sellingCompanyName",
    "startCityCode",
    "startCityName",
    "startCountry",
    "successful",
    "teenagerDiscount",
    "tourDepartureInfo"
})
public class WsTourDepartureInfoPagingVO {

    protected String airPriceIncluded;
    protected String brochureCode;
    protected String currencyCode;
    protected String departuresGuaranteed;
    protected Integer durationInDays;
    protected Integer durationInNights;
    protected String endCityCode;
    protected String endCityName;
    protected String endCountry;
    protected String errorMessage;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO> errorMessagesArray;
    protected String productCode;
    protected String productDescription;
    protected Long productId;
    protected String productName;
    protected String sellingCompanyCode;
    protected String sellingCompanyName;
    protected String startCityCode;
    protected String startCityName;
    protected String startCountry;
    protected boolean successful;
    protected Long teenagerDiscount;
    @XmlElement(nillable = true)
    protected List<WsTourDepartureInfoVO> tourDepartureInfo;

    /**
     * Gets the value of the airPriceIncluded property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAirPriceIncluded() {
        return airPriceIncluded;
    }

    /**
     * Sets the value of the airPriceIncluded property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAirPriceIncluded(String value) {
        this.airPriceIncluded = value;
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
     * Gets the value of the currencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the value of the currencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

    /**
     * Gets the value of the departuresGuaranteed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeparturesGuaranteed() {
        return departuresGuaranteed;
    }

    /**
     * Sets the value of the departuresGuaranteed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeparturesGuaranteed(String value) {
        this.departuresGuaranteed = value;
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
     * Gets the value of the endCityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndCityCode() {
        return endCityCode;
    }

    /**
     * Sets the value of the endCityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndCityCode(String value) {
        this.endCityCode = value;
    }

    /**
     * Gets the value of the endCityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndCityName() {
        return endCityName;
    }

    /**
     * Sets the value of the endCityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndCityName(String value) {
        this.endCityName = value;
    }

    /**
     * Gets the value of the endCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndCountry() {
        return endCountry;
    }

    /**
     * Sets the value of the endCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndCountry(String value) {
        this.endCountry = value;
    }

    /**
     * Gets the value of the errorMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the value of the errorMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorMessage(String value) {
        this.errorMessage = value;
    }

    /**
     * Gets the value of the errorMessagesArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the errorMessagesArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErrorMessagesArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO> getErrorMessagesArray() {
        if (errorMessagesArray == null) {
            errorMessagesArray = new ArrayList<ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO>();
        }
        return this.errorMessagesArray;
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
     * Gets the value of the productDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * Sets the value of the productDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductDescription(String value) {
        this.productDescription = value;
    }

    /**
     * Gets the value of the productId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * Sets the value of the productId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProductId(Long value) {
        this.productId = value;
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
     * Gets the value of the sellingCompanyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellingCompanyCode() {
        return sellingCompanyCode;
    }

    /**
     * Sets the value of the sellingCompanyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellingCompanyCode(String value) {
        this.sellingCompanyCode = value;
    }

    /**
     * Gets the value of the sellingCompanyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellingCompanyName() {
        return sellingCompanyName;
    }

    /**
     * Sets the value of the sellingCompanyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellingCompanyName(String value) {
        this.sellingCompanyName = value;
    }

    /**
     * Gets the value of the startCityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartCityCode() {
        return startCityCode;
    }

    /**
     * Sets the value of the startCityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartCityCode(String value) {
        this.startCityCode = value;
    }

    /**
     * Gets the value of the startCityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartCityName() {
        return startCityName;
    }

    /**
     * Sets the value of the startCityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartCityName(String value) {
        this.startCityName = value;
    }

    /**
     * Gets the value of the startCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartCountry() {
        return startCountry;
    }

    /**
     * Sets the value of the startCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartCountry(String value) {
        this.startCountry = value;
    }

    /**
     * Gets the value of the successful property.
     * 
     */
    public boolean isSuccessful() {
        return successful;
    }

    /**
     * Sets the value of the successful property.
     * 
     */
    public void setSuccessful(boolean value) {
        this.successful = value;
    }

    /**
     * Gets the value of the teenagerDiscount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTeenagerDiscount() {
        return teenagerDiscount;
    }

    /**
     * Sets the value of the teenagerDiscount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTeenagerDiscount(Long value) {
        this.teenagerDiscount = value;
    }

    /**
     * Gets the value of the tourDepartureInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tourDepartureInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTourDepartureInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WsTourDepartureInfoVO }
     * 
     * 
     */
    public List<WsTourDepartureInfoVO> getTourDepartureInfo() {
        if (tourDepartureInfo == null) {
            tourDepartureInfo = new ArrayList<WsTourDepartureInfoVO>();
        }
        return this.tourDepartureInfo;
    }

}
