
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
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_products_mv_WSMVResrAssociatedProductsVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_products_mv_WSMVResrAssociatedProductsVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="associatedProduct" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_products_common_WSMVAssociatedProductWithPriceVO" minOccurs="0"/>
 *         &lt;element name="baseBookingLineItemID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="bookingLineItemID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="concurrencyCounter" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="delFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="error" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="MVAPID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="noOfClients" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="notesDisplayEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="notesDisplayStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="pricePerAdult" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="pricingBasis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rowIndicateStyle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="selectedCompanyList" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="success" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="totalPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
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
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_products_mv_WSMVResrAssociatedProductsVO", propOrder = {
    "associatedProduct",
    "baseBookingLineItemID",
    "bookingLineItemID",
    "concurrencyCounter",
    "createdBy",
    "createdDate",
    "delFlag",
    "endDate",
    "error",
    "lastModifiedBy",
    "lastModifiedDate",
    "mvapid",
    "noOfClients",
    "notesDisplayEndDate",
    "notesDisplayStartDate",
    "pricePerAdult",
    "pricingBasis",
    "productCategory",
    "productCode",
    "productID",
    "productName",
    "productType",
    "rowIndicateStyle",
    "selectedCompanyList",
    "startDate",
    "status",
    "success",
    "totalPrice",
    "warning"
})
public class ComTropicsWebserviceITropicsVoLegacyProductsMvWSMVResrAssociatedProductsVO {

    protected ComTropicsWebserviceITropicsVoLegacyProductsCommonWSMVAssociatedProductWithPriceVO associatedProduct;
    protected long baseBookingLineItemID;
    protected long bookingLineItemID;
    protected int concurrencyCounter;
    protected String createdBy;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdDate;
    protected String delFlag;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    @XmlElement(nillable = true)
    protected List<String> error;
    protected String lastModifiedBy;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedDate;
    @XmlElement(name = "MVAPID")
    protected long mvapid;
    protected int noOfClients;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar notesDisplayEndDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar notesDisplayStartDate;
    protected float pricePerAdult;
    protected String pricingBasis;
    protected String productCategory;
    protected String productCode;
    protected long productID;
    protected String productName;
    protected String productType;
    protected String rowIndicateStyle;
    @XmlElement(type = Long.class)
    protected List<Long> selectedCompanyList;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    protected String status;
    @XmlElement(nillable = true)
    protected List<String> success;
    protected float totalPrice;
    @XmlElement(nillable = true)
    protected List<String> warning;

    /**
     * Gets the value of the associatedProduct property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyProductsCommonWSMVAssociatedProductWithPriceVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyProductsCommonWSMVAssociatedProductWithPriceVO getAssociatedProduct() {
        return associatedProduct;
    }

    /**
     * Sets the value of the associatedProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyProductsCommonWSMVAssociatedProductWithPriceVO }
     *     
     */
    public void setAssociatedProduct(ComTropicsWebserviceITropicsVoLegacyProductsCommonWSMVAssociatedProductWithPriceVO value) {
        this.associatedProduct = value;
    }

    /**
     * Gets the value of the baseBookingLineItemID property.
     * 
     */
    public long getBaseBookingLineItemID() {
        return baseBookingLineItemID;
    }

    /**
     * Sets the value of the baseBookingLineItemID property.
     * 
     */
    public void setBaseBookingLineItemID(long value) {
        this.baseBookingLineItemID = value;
    }

    /**
     * Gets the value of the bookingLineItemID property.
     * 
     */
    public long getBookingLineItemID() {
        return bookingLineItemID;
    }

    /**
     * Sets the value of the bookingLineItemID property.
     * 
     */
    public void setBookingLineItemID(long value) {
        this.bookingLineItemID = value;
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
     * Gets the value of the mvapid property.
     * 
     */
    public long getMVAPID() {
        return mvapid;
    }

    /**
     * Sets the value of the mvapid property.
     * 
     */
    public void setMVAPID(long value) {
        this.mvapid = value;
    }

    /**
     * Gets the value of the noOfClients property.
     * 
     */
    public int getNoOfClients() {
        return noOfClients;
    }

    /**
     * Sets the value of the noOfClients property.
     * 
     */
    public void setNoOfClients(int value) {
        this.noOfClients = value;
    }

    /**
     * Gets the value of the notesDisplayEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNotesDisplayEndDate() {
        return notesDisplayEndDate;
    }

    /**
     * Sets the value of the notesDisplayEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNotesDisplayEndDate(XMLGregorianCalendar value) {
        this.notesDisplayEndDate = value;
    }

    /**
     * Gets the value of the notesDisplayStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNotesDisplayStartDate() {
        return notesDisplayStartDate;
    }

    /**
     * Sets the value of the notesDisplayStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNotesDisplayStartDate(XMLGregorianCalendar value) {
        this.notesDisplayStartDate = value;
    }

    /**
     * Gets the value of the pricePerAdult property.
     * 
     */
    public float getPricePerAdult() {
        return pricePerAdult;
    }

    /**
     * Sets the value of the pricePerAdult property.
     * 
     */
    public void setPricePerAdult(float value) {
        this.pricePerAdult = value;
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
     * Gets the value of the productType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductType() {
        return productType;
    }

    /**
     * Sets the value of the productType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductType(String value) {
        this.productType = value;
    }

    /**
     * Gets the value of the rowIndicateStyle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRowIndicateStyle() {
        return rowIndicateStyle;
    }

    /**
     * Sets the value of the rowIndicateStyle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRowIndicateStyle(String value) {
        this.rowIndicateStyle = value;
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
     * Gets the value of the totalPrice property.
     * 
     */
    public float getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the value of the totalPrice property.
     * 
     */
    public void setTotalPrice(float value) {
        this.totalPrice = value;
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
