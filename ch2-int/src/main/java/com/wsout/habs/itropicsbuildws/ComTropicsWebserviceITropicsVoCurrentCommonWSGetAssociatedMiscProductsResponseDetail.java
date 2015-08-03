
package com.wsout.habs.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_current_common_WSGetAssociatedMiscProductsResponseDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_current_common_WSGetAssociatedMiscProductsResponseDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="adultPrice" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="benefitFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="childPrice" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="dateOfProduct" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dayNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="departureCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="forcedButRemovable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="mandatory" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="miscProductCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="miscProductDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="miscProductId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="miscProductName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="miscProductTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mvAssociatedProductId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="productCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productCategoryId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="productCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sellingCompanyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceTypeID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="srcPrice" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_current_common_WSGetAssociatedMiscProductsResponseDetail", propOrder = {
    "adultPrice",
    "benefitFlag",
    "childPrice",
    "dateOfProduct",
    "dayNumber",
    "departureCode",
    "endDate",
    "forcedButRemovable",
    "mandatory",
    "miscProductCode",
    "miscProductDescription",
    "miscProductId",
    "miscProductName",
    "miscProductTypeCode",
    "mvAssociatedProductId",
    "productCategory",
    "productCategoryId",
    "productCode",
    "sellingCompanyCode",
    "serviceTypeID",
    "srcPrice",
    "startDate"
})
public class ComTropicsWebserviceITropicsVoCurrentCommonWSGetAssociatedMiscProductsResponseDetail {

    protected double adultPrice;
    protected boolean benefitFlag;
    protected double childPrice;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateOfProduct;
    protected long dayNumber;
    protected String departureCode;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    protected boolean forcedButRemovable;
    protected boolean mandatory;
    protected String miscProductCode;
    protected String miscProductDescription;
    protected long miscProductId;
    protected String miscProductName;
    protected String miscProductTypeCode;
    protected long mvAssociatedProductId;
    protected String productCategory;
    protected long productCategoryId;
    protected String productCode;
    protected String sellingCompanyCode;
    protected long serviceTypeID;
    protected double srcPrice;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;

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
     * Gets the value of the benefitFlag property.
     * 
     */
    public boolean isBenefitFlag() {
        return benefitFlag;
    }

    /**
     * Sets the value of the benefitFlag property.
     * 
     */
    public void setBenefitFlag(boolean value) {
        this.benefitFlag = value;
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
     * Gets the value of the dateOfProduct property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateOfProduct() {
        return dateOfProduct;
    }

    /**
     * Sets the value of the dateOfProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateOfProduct(XMLGregorianCalendar value) {
        this.dateOfProduct = value;
    }

    /**
     * Gets the value of the dayNumber property.
     * 
     */
    public long getDayNumber() {
        return dayNumber;
    }

    /**
     * Sets the value of the dayNumber property.
     * 
     */
    public void setDayNumber(long value) {
        this.dayNumber = value;
    }

    /**
     * Gets the value of the departureCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureCode() {
        return departureCode;
    }

    /**
     * Sets the value of the departureCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureCode(String value) {
        this.departureCode = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the forcedButRemovable property.
     * 
     */
    public boolean isForcedButRemovable() {
        return forcedButRemovable;
    }

    /**
     * Sets the value of the forcedButRemovable property.
     * 
     */
    public void setForcedButRemovable(boolean value) {
        this.forcedButRemovable = value;
    }

    /**
     * Gets the value of the mandatory property.
     * 
     */
    public boolean isMandatory() {
        return mandatory;
    }

    /**
     * Sets the value of the mandatory property.
     * 
     */
    public void setMandatory(boolean value) {
        this.mandatory = value;
    }

    /**
     * Gets the value of the miscProductCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiscProductCode() {
        return miscProductCode;
    }

    /**
     * Sets the value of the miscProductCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiscProductCode(String value) {
        this.miscProductCode = value;
    }

    /**
     * Gets the value of the miscProductDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiscProductDescription() {
        return miscProductDescription;
    }

    /**
     * Sets the value of the miscProductDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiscProductDescription(String value) {
        this.miscProductDescription = value;
    }

    /**
     * Gets the value of the miscProductId property.
     * 
     */
    public long getMiscProductId() {
        return miscProductId;
    }

    /**
     * Sets the value of the miscProductId property.
     * 
     */
    public void setMiscProductId(long value) {
        this.miscProductId = value;
    }

    /**
     * Gets the value of the miscProductName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiscProductName() {
        return miscProductName;
    }

    /**
     * Sets the value of the miscProductName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiscProductName(String value) {
        this.miscProductName = value;
    }

    /**
     * Gets the value of the miscProductTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiscProductTypeCode() {
        return miscProductTypeCode;
    }

    /**
     * Sets the value of the miscProductTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiscProductTypeCode(String value) {
        this.miscProductTypeCode = value;
    }

    /**
     * Gets the value of the mvAssociatedProductId property.
     * 
     */
    public long getMvAssociatedProductId() {
        return mvAssociatedProductId;
    }

    /**
     * Sets the value of the mvAssociatedProductId property.
     * 
     */
    public void setMvAssociatedProductId(long value) {
        this.mvAssociatedProductId = value;
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
     * Gets the value of the productCategoryId property.
     * 
     */
    public long getProductCategoryId() {
        return productCategoryId;
    }

    /**
     * Sets the value of the productCategoryId property.
     * 
     */
    public void setProductCategoryId(long value) {
        this.productCategoryId = value;
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
     * Gets the value of the serviceTypeID property.
     * 
     */
    public long getServiceTypeID() {
        return serviceTypeID;
    }

    /**
     * Sets the value of the serviceTypeID property.
     * 
     */
    public void setServiceTypeID(long value) {
        this.serviceTypeID = value;
    }

    /**
     * Gets the value of the srcPrice property.
     * 
     */
    public double getSrcPrice() {
        return srcPrice;
    }

    /**
     * Sets the value of the srcPrice property.
     * 
     */
    public void setSrcPrice(double value) {
        this.srcPrice = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

}
