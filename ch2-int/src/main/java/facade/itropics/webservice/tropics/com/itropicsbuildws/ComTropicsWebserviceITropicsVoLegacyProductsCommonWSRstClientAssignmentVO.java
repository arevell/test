
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
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_products_common_WSRstClientAssignmentVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_products_common_WSRstClientAssignmentVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="baseBookingLineItemID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="bookingLineItemID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="changesSaved" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="concurrencyCounter" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="coverType" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_commonvo_WSMasterDataVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="delFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departureID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="error" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="forcedButRemoveable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="gtDepartureBookedFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="headerVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_products_common_WSProductHeaderVO" minOccurs="0"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="mandatory" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="productCategoryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productDepartureID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="remarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roomTypeForFastTrack" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roomTypesArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_commonvo_WSMasterDataVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="rstClientDetailsVOArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_products_common_WSRstClientDetailsVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="selectedCompanyList" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="specialRequestsArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_commonvo_WSMasterDataVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="success" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_products_common_WSRstClientAssignmentVO", propOrder = {
    "baseBookingLineItemID",
    "bookingLineItemID",
    "changesSaved",
    "concurrencyCounter",
    "coverType",
    "createdBy",
    "createdDate",
    "delFlag",
    "departureID",
    "endDate",
    "error",
    "forcedButRemoveable",
    "gtDepartureBookedFlag",
    "headerVO",
    "lastModifiedBy",
    "lastModifiedDate",
    "mandatory",
    "productCategoryCode",
    "productDepartureID",
    "remarks",
    "roomTypeForFastTrack",
    "roomTypesArray",
    "rstClientDetailsVOArray",
    "selectedCompanyList",
    "specialRequestsArray",
    "startDate",
    "success",
    "warning"
})
public class ComTropicsWebserviceITropicsVoLegacyProductsCommonWSRstClientAssignmentVO {

    protected long baseBookingLineItemID;
    protected long bookingLineItemID;
    protected boolean changesSaved;
    protected int concurrencyCounter;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO> coverType;
    protected String createdBy;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdDate;
    protected String delFlag;
    protected long departureID;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    @XmlElement(nillable = true)
    protected List<String> error;
    protected boolean forcedButRemoveable;
    protected boolean gtDepartureBookedFlag;
    protected ComTropicsWebserviceITropicsVoLegacyProductsCommonWSProductHeaderVO headerVO;
    protected String lastModifiedBy;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedDate;
    protected boolean mandatory;
    protected String productCategoryCode;
    protected long productDepartureID;
    protected String remarks;
    protected String roomTypeForFastTrack;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO> roomTypesArray;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyProductsCommonWSRstClientDetailsVO> rstClientDetailsVOArray;
    @XmlElement(type = Long.class)
    protected List<Long> selectedCompanyList;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO> specialRequestsArray;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    @XmlElement(nillable = true)
    protected List<String> success;
    @XmlElement(nillable = true)
    protected List<String> warning;

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
     * Gets the value of the changesSaved property.
     * 
     */
    public boolean isChangesSaved() {
        return changesSaved;
    }

    /**
     * Sets the value of the changesSaved property.
     * 
     */
    public void setChangesSaved(boolean value) {
        this.changesSaved = value;
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
     * Gets the value of the coverType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the coverType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCoverType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO> getCoverType() {
        if (coverType == null) {
            coverType = new ArrayList<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO>();
        }
        return this.coverType;
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
     * Gets the value of the departureID property.
     * 
     */
    public long getDepartureID() {
        return departureID;
    }

    /**
     * Sets the value of the departureID property.
     * 
     */
    public void setDepartureID(long value) {
        this.departureID = value;
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
     * Gets the value of the forcedButRemoveable property.
     * 
     */
    public boolean isForcedButRemoveable() {
        return forcedButRemoveable;
    }

    /**
     * Sets the value of the forcedButRemoveable property.
     * 
     */
    public void setForcedButRemoveable(boolean value) {
        this.forcedButRemoveable = value;
    }

    /**
     * Gets the value of the gtDepartureBookedFlag property.
     * 
     */
    public boolean isGtDepartureBookedFlag() {
        return gtDepartureBookedFlag;
    }

    /**
     * Sets the value of the gtDepartureBookedFlag property.
     * 
     */
    public void setGtDepartureBookedFlag(boolean value) {
        this.gtDepartureBookedFlag = value;
    }

    /**
     * Gets the value of the headerVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyProductsCommonWSProductHeaderVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyProductsCommonWSProductHeaderVO getHeaderVO() {
        return headerVO;
    }

    /**
     * Sets the value of the headerVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyProductsCommonWSProductHeaderVO }
     *     
     */
    public void setHeaderVO(ComTropicsWebserviceITropicsVoLegacyProductsCommonWSProductHeaderVO value) {
        this.headerVO = value;
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
     * Gets the value of the productCategoryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCategoryCode() {
        return productCategoryCode;
    }

    /**
     * Sets the value of the productCategoryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCategoryCode(String value) {
        this.productCategoryCode = value;
    }

    /**
     * Gets the value of the productDepartureID property.
     * 
     */
    public long getProductDepartureID() {
        return productDepartureID;
    }

    /**
     * Sets the value of the productDepartureID property.
     * 
     */
    public void setProductDepartureID(long value) {
        this.productDepartureID = value;
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
     * Gets the value of the roomTypeForFastTrack property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomTypeForFastTrack() {
        return roomTypeForFastTrack;
    }

    /**
     * Sets the value of the roomTypeForFastTrack property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomTypeForFastTrack(String value) {
        this.roomTypeForFastTrack = value;
    }

    /**
     * Gets the value of the roomTypesArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roomTypesArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoomTypesArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO> getRoomTypesArray() {
        if (roomTypesArray == null) {
            roomTypesArray = new ArrayList<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO>();
        }
        return this.roomTypesArray;
    }

    /**
     * Gets the value of the rstClientDetailsVOArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rstClientDetailsVOArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRstClientDetailsVOArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyProductsCommonWSRstClientDetailsVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyProductsCommonWSRstClientDetailsVO> getRstClientDetailsVOArray() {
        if (rstClientDetailsVOArray == null) {
            rstClientDetailsVOArray = new ArrayList<ComTropicsWebserviceITropicsVoLegacyProductsCommonWSRstClientDetailsVO>();
        }
        return this.rstClientDetailsVOArray;
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
     * Gets the value of the specialRequestsArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the specialRequestsArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpecialRequestsArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO> getSpecialRequestsArray() {
        if (specialRequestsArray == null) {
            specialRequestsArray = new ArrayList<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO>();
        }
        return this.specialRequestsArray;
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
