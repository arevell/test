
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
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_products_air_WSNonFITAirContractNotesVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_products_air_WSNonFITAirContractNotesVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="airContractID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="airlineReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="amendCancellationFeesVOArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_products_air_WSAmendmentFeeVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="amendmentNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cancellationFeeVOArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_products_air_WSCancellationFeeVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cancellationNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="concurrencyCounter" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="contractID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="delFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departureFromDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="departureToDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="error" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="flightRestrictionsArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_products_air_WSAirlinesRestrictionVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="minLandValue" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="minStayConditions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="reservationsDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="selectedCompanyList" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_products_air_WSNonFITAirContractNotesVO", propOrder = {
    "airContractID",
    "airlineReference",
    "amendCancellationFeesVOArray",
    "amendmentNotes",
    "cancellationFeeVOArray",
    "cancellationNotes",
    "concurrencyCounter",
    "contractID",
    "createdBy",
    "createdDate",
    "delFlag",
    "departureFromDate",
    "departureToDate",
    "error",
    "flightRestrictionsArray",
    "lastModifiedBy",
    "lastModifiedDate",
    "minLandValue",
    "minStayConditions",
    "productCode",
    "productID",
    "reservationsDescription",
    "selectedCompanyList",
    "success",
    "warning"
})
public class ComTropicsWebserviceITropicsVoLegacyProductsAirWSNonFITAirContractNotesVO {

    protected String airContractID;
    protected String airlineReference;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyProductsAirWSAmendmentFeeVO> amendCancellationFeesVOArray;
    protected String amendmentNotes;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyProductsAirWSCancellationFeeVO> cancellationFeeVOArray;
    protected String cancellationNotes;
    protected int concurrencyCounter;
    protected long contractID;
    protected String createdBy;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdDate;
    protected String delFlag;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar departureFromDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar departureToDate;
    @XmlElement(nillable = true)
    protected List<String> error;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyProductsAirWSAirlinesRestrictionVO> flightRestrictionsArray;
    protected String lastModifiedBy;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedDate;
    protected double minLandValue;
    protected String minStayConditions;
    protected String productCode;
    protected long productID;
    protected String reservationsDescription;
    @XmlElement(type = Long.class)
    protected List<Long> selectedCompanyList;
    @XmlElement(nillable = true)
    protected List<String> success;
    @XmlElement(nillable = true)
    protected List<String> warning;

    /**
     * Gets the value of the airContractID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAirContractID() {
        return airContractID;
    }

    /**
     * Sets the value of the airContractID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAirContractID(String value) {
        this.airContractID = value;
    }

    /**
     * Gets the value of the airlineReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAirlineReference() {
        return airlineReference;
    }

    /**
     * Sets the value of the airlineReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAirlineReference(String value) {
        this.airlineReference = value;
    }

    /**
     * Gets the value of the amendCancellationFeesVOArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the amendCancellationFeesVOArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAmendCancellationFeesVOArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyProductsAirWSAmendmentFeeVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyProductsAirWSAmendmentFeeVO> getAmendCancellationFeesVOArray() {
        if (amendCancellationFeesVOArray == null) {
            amendCancellationFeesVOArray = new ArrayList<ComTropicsWebserviceITropicsVoLegacyProductsAirWSAmendmentFeeVO>();
        }
        return this.amendCancellationFeesVOArray;
    }

    /**
     * Gets the value of the amendmentNotes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmendmentNotes() {
        return amendmentNotes;
    }

    /**
     * Sets the value of the amendmentNotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmendmentNotes(String value) {
        this.amendmentNotes = value;
    }

    /**
     * Gets the value of the cancellationFeeVOArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cancellationFeeVOArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCancellationFeeVOArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyProductsAirWSCancellationFeeVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyProductsAirWSCancellationFeeVO> getCancellationFeeVOArray() {
        if (cancellationFeeVOArray == null) {
            cancellationFeeVOArray = new ArrayList<ComTropicsWebserviceITropicsVoLegacyProductsAirWSCancellationFeeVO>();
        }
        return this.cancellationFeeVOArray;
    }

    /**
     * Gets the value of the cancellationNotes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancellationNotes() {
        return cancellationNotes;
    }

    /**
     * Sets the value of the cancellationNotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancellationNotes(String value) {
        this.cancellationNotes = value;
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
     * Gets the value of the contractID property.
     * 
     */
    public long getContractID() {
        return contractID;
    }

    /**
     * Sets the value of the contractID property.
     * 
     */
    public void setContractID(long value) {
        this.contractID = value;
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
     * Gets the value of the departureFromDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDepartureFromDate() {
        return departureFromDate;
    }

    /**
     * Sets the value of the departureFromDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDepartureFromDate(XMLGregorianCalendar value) {
        this.departureFromDate = value;
    }

    /**
     * Gets the value of the departureToDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDepartureToDate() {
        return departureToDate;
    }

    /**
     * Sets the value of the departureToDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDepartureToDate(XMLGregorianCalendar value) {
        this.departureToDate = value;
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
     * Gets the value of the flightRestrictionsArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flightRestrictionsArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlightRestrictionsArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyProductsAirWSAirlinesRestrictionVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyProductsAirWSAirlinesRestrictionVO> getFlightRestrictionsArray() {
        if (flightRestrictionsArray == null) {
            flightRestrictionsArray = new ArrayList<ComTropicsWebserviceITropicsVoLegacyProductsAirWSAirlinesRestrictionVO>();
        }
        return this.flightRestrictionsArray;
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
     * Gets the value of the minLandValue property.
     * 
     */
    public double getMinLandValue() {
        return minLandValue;
    }

    /**
     * Sets the value of the minLandValue property.
     * 
     */
    public void setMinLandValue(double value) {
        this.minLandValue = value;
    }

    /**
     * Gets the value of the minStayConditions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinStayConditions() {
        return minStayConditions;
    }

    /**
     * Sets the value of the minStayConditions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinStayConditions(String value) {
        this.minStayConditions = value;
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
     * Gets the value of the reservationsDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReservationsDescription() {
        return reservationsDescription;
    }

    /**
     * Sets the value of the reservationsDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReservationsDescription(String value) {
        this.reservationsDescription = value;
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
