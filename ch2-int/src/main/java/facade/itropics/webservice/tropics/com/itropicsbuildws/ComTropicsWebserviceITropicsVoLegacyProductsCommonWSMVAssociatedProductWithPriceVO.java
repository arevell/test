
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_products_common_WSMVAssociatedProductWithPriceVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_products_common_WSMVAssociatedProductWithPriceVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="adultPrice" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="associatedProductCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="associatedProductFromDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="associatedProductName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="associatedProductRemovedFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="associatedProductToDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="associatedProductType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="childPrice" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="concurrencyCounter" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="dayNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="delFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="error" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="familyCover" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="isForcedButRemovableFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isMandatoryFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="mastersVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_commonvo_WSMasterVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="minDurationForInsurance" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="mvAssociatedProductID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="partnerCover" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pricingBasis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pricingCommission" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="productCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="quadPrice" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="remarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="selectedCompanyList" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="singleCover" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="singlePrice" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="success" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="teenPrice" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="triplePrice" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="twinPrice" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="warning" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_products_common_WSMVAssociatedProductWithPriceVO", propOrder = {
    "adultPrice",
    "associatedProductCode",
    "associatedProductFromDate",
    "associatedProductName",
    "associatedProductRemovedFlag",
    "associatedProductToDate",
    "associatedProductType",
    "childPrice",
    "concurrencyCounter",
    "createdBy",
    "createdDate",
    "dayNumber",
    "delFlag",
    "error",
    "familyCover",
    "isForcedButRemovableFlag",
    "isMandatoryFlag",
    "lastModifiedBy",
    "lastModifiedDate",
    "mastersVO",
    "minDurationForInsurance",
    "mvAssociatedProductID",
    "partnerCover",
    "pricingBasis",
    "pricingCommission",
    "productCategory",
    "productID",
    "quadPrice",
    "remarks",
    "selectedCompanyList",
    "singleCover",
    "singlePrice",
    "success",
    "teenPrice",
    "triplePrice",
    "twinPrice",
    "warning"
})
public class ComTropicsWebserviceITropicsVoLegacyProductsCommonWSMVAssociatedProductWithPriceVO {

    protected double adultPrice;
    protected String associatedProductCode;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar associatedProductFromDate;
    protected String associatedProductName;
    protected String associatedProductRemovedFlag;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar associatedProductToDate;
    protected String associatedProductType;
    protected double childPrice;
    protected int concurrencyCounter;
    protected String createdBy;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdDate;
    protected int dayNumber;
    protected String delFlag;
    @XmlElement(nillable = true)
    protected List<String> error;
    protected double familyCover;
    protected String isForcedButRemovableFlag;
    protected String isMandatoryFlag;
    protected String lastModifiedBy;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedDate;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterVO> mastersVO;
    protected long minDurationForInsurance;
    protected long mvAssociatedProductID;
    protected double partnerCover;
    protected String pricingBasis;
    protected double pricingCommission;
    protected String productCategory;
    protected long productID;
    protected double quadPrice;
    protected String remarks;
    @XmlElement(type = Long.class)
    protected List<Long> selectedCompanyList;
    protected double singleCover;
    protected double singlePrice;
    @XmlElement(nillable = true)
    protected List<String> success;
    protected double teenPrice;
    protected double triplePrice;
    protected double twinPrice;
    @XmlElement(nillable = true)
    protected List<String> warning;

    /**
     * Gets the value of the adultPrice property.
     * 
     */
    public double getAdultPrice() {
        return adultPrice;
    }

    /**
     * Sets the value of the adultPrice property.
     * 
     */
    public void setAdultPrice(double value) {
        this.adultPrice = value;
    }

    /**
     * Gets the value of the associatedProductCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssociatedProductCode() {
        return associatedProductCode;
    }

    /**
     * Sets the value of the associatedProductCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssociatedProductCode(String value) {
        this.associatedProductCode = value;
    }

    /**
     * Gets the value of the associatedProductFromDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAssociatedProductFromDate() {
        return associatedProductFromDate;
    }

    /**
     * Sets the value of the associatedProductFromDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAssociatedProductFromDate(XMLGregorianCalendar value) {
        this.associatedProductFromDate = value;
    }

    /**
     * Gets the value of the associatedProductName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssociatedProductName() {
        return associatedProductName;
    }

    /**
     * Sets the value of the associatedProductName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssociatedProductName(String value) {
        this.associatedProductName = value;
    }

    /**
     * Gets the value of the associatedProductRemovedFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssociatedProductRemovedFlag() {
        return associatedProductRemovedFlag;
    }

    /**
     * Sets the value of the associatedProductRemovedFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssociatedProductRemovedFlag(String value) {
        this.associatedProductRemovedFlag = value;
    }

    /**
     * Gets the value of the associatedProductToDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAssociatedProductToDate() {
        return associatedProductToDate;
    }

    /**
     * Sets the value of the associatedProductToDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAssociatedProductToDate(XMLGregorianCalendar value) {
        this.associatedProductToDate = value;
    }

    /**
     * Gets the value of the associatedProductType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssociatedProductType() {
        return associatedProductType;
    }

    /**
     * Sets the value of the associatedProductType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssociatedProductType(String value) {
        this.associatedProductType = value;
    }

    /**
     * Gets the value of the childPrice property.
     * 
     */
    public double getChildPrice() {
        return childPrice;
    }

    /**
     * Sets the value of the childPrice property.
     * 
     */
    public void setChildPrice(double value) {
        this.childPrice = value;
    }

    /**
     * Gets the value of the concurrencyCounter property.
     * 
     */
    public int getConcurrencyCounter() {
        return concurrencyCounter;
    }

    /**
     * Sets the value of the concurrencyCounter property.
     * 
     */
    public void setConcurrencyCounter(int value) {
        this.concurrencyCounter = value;
    }

    /**
     * Gets the value of the createdBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the value of the createdBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedBy(String value) {
        this.createdBy = value;
    }

    /**
     * Gets the value of the createdDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the value of the createdDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedDate(XMLGregorianCalendar value) {
        this.createdDate = value;
    }

    /**
     * Gets the value of the dayNumber property.
     * 
     */
    public int getDayNumber() {
        return dayNumber;
    }

    /**
     * Sets the value of the dayNumber property.
     * 
     */
    public void setDayNumber(int value) {
        this.dayNumber = value;
    }

    /**
     * Gets the value of the delFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * Sets the value of the delFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelFlag(String value) {
        this.delFlag = value;
    }

    /**
     * Gets the value of the error property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the error property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getError().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getError() {
        if (error == null) {
            error = new ArrayList<String>();
        }
        return this.error;
    }

    /**
     * Gets the value of the familyCover property.
     * 
     */
    public double getFamilyCover() {
        return familyCover;
    }

    /**
     * Sets the value of the familyCover property.
     * 
     */
    public void setFamilyCover(double value) {
        this.familyCover = value;
    }

    /**
     * Gets the value of the isForcedButRemovableFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsForcedButRemovableFlag() {
        return isForcedButRemovableFlag;
    }

    /**
     * Sets the value of the isForcedButRemovableFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsForcedButRemovableFlag(String value) {
        this.isForcedButRemovableFlag = value;
    }

    /**
     * Gets the value of the isMandatoryFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsMandatoryFlag() {
        return isMandatoryFlag;
    }

    /**
     * Sets the value of the isMandatoryFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsMandatoryFlag(String value) {
        this.isMandatoryFlag = value;
    }

    /**
     * Gets the value of the lastModifiedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * Sets the value of the lastModifiedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastModifiedBy(String value) {
        this.lastModifiedBy = value;
    }

    /**
     * Gets the value of the lastModifiedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * Sets the value of the lastModifiedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastModifiedDate(XMLGregorianCalendar value) {
        this.lastModifiedDate = value;
    }

    /**
     * Gets the value of the mastersVO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mastersVO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMastersVO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterVO> getMastersVO() {
        if (mastersVO == null) {
            mastersVO = new ArrayList<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterVO>();
        }
        return this.mastersVO;
    }

    /**
     * Gets the value of the minDurationForInsurance property.
     * 
     */
    public long getMinDurationForInsurance() {
        return minDurationForInsurance;
    }

    /**
     * Sets the value of the minDurationForInsurance property.
     * 
     */
    public void setMinDurationForInsurance(long value) {
        this.minDurationForInsurance = value;
    }

    /**
     * Gets the value of the mvAssociatedProductID property.
     * 
     */
    public long getMvAssociatedProductID() {
        return mvAssociatedProductID;
    }

    /**
     * Sets the value of the mvAssociatedProductID property.
     * 
     */
    public void setMvAssociatedProductID(long value) {
        this.mvAssociatedProductID = value;
    }

    /**
     * Gets the value of the partnerCover property.
     * 
     */
    public double getPartnerCover() {
        return partnerCover;
    }

    /**
     * Sets the value of the partnerCover property.
     * 
     */
    public void setPartnerCover(double value) {
        this.partnerCover = value;
    }

    /**
     * Gets the value of the pricingBasis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricingBasis() {
        return pricingBasis;
    }

    /**
     * Sets the value of the pricingBasis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricingBasis(String value) {
        this.pricingBasis = value;
    }

    /**
     * Gets the value of the pricingCommission property.
     * 
     */
    public double getPricingCommission() {
        return pricingCommission;
    }

    /**
     * Sets the value of the pricingCommission property.
     * 
     */
    public void setPricingCommission(double value) {
        this.pricingCommission = value;
    }

    /**
     * Gets the value of the productCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCategory() {
        return productCategory;
    }

    /**
     * Sets the value of the productCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCategory(String value) {
        this.productCategory = value;
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
     * Gets the value of the quadPrice property.
     * 
     */
    public double getQuadPrice() {
        return quadPrice;
    }

    /**
     * Sets the value of the quadPrice property.
     * 
     */
    public void setQuadPrice(double value) {
        this.quadPrice = value;
    }

    /**
     * Gets the value of the remarks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * Sets the value of the remarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemarks(String value) {
        this.remarks = value;
    }

    /**
     * Gets the value of the selectedCompanyList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectedCompanyList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectedCompanyList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getSelectedCompanyList() {
        if (selectedCompanyList == null) {
            selectedCompanyList = new ArrayList<Long>();
        }
        return this.selectedCompanyList;
    }

    /**
     * Gets the value of the singleCover property.
     * 
     */
    public double getSingleCover() {
        return singleCover;
    }

    /**
     * Sets the value of the singleCover property.
     * 
     */
    public void setSingleCover(double value) {
        this.singleCover = value;
    }

    /**
     * Gets the value of the singlePrice property.
     * 
     */
    public double getSinglePrice() {
        return singlePrice;
    }

    /**
     * Sets the value of the singlePrice property.
     * 
     */
    public void setSinglePrice(double value) {
        this.singlePrice = value;
    }

    /**
     * Gets the value of the success property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the success property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSuccess().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSuccess() {
        if (success == null) {
            success = new ArrayList<String>();
        }
        return this.success;
    }

    /**
     * Gets the value of the teenPrice property.
     * 
     */
    public double getTeenPrice() {
        return teenPrice;
    }

    /**
     * Sets the value of the teenPrice property.
     * 
     */
    public void setTeenPrice(double value) {
        this.teenPrice = value;
    }

    /**
     * Gets the value of the triplePrice property.
     * 
     */
    public double getTriplePrice() {
        return triplePrice;
    }

    /**
     * Sets the value of the triplePrice property.
     * 
     */
    public void setTriplePrice(double value) {
        this.triplePrice = value;
    }

    /**
     * Gets the value of the twinPrice property.
     * 
     */
    public double getTwinPrice() {
        return twinPrice;
    }

    /**
     * Sets the value of the twinPrice property.
     * 
     */
    public void setTwinPrice(double value) {
        this.twinPrice = value;
    }

    /**
     * Gets the value of the warning property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the warning property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWarning().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getWarning() {
        if (warning == null) {
            warning = new ArrayList<String>();
        }
        return this.warning;
    }

}
