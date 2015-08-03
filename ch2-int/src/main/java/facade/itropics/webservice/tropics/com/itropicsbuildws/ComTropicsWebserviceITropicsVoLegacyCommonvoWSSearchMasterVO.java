
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
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_commonvo_WSSearchMasterVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_commonvo_WSSearchMasterVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="categoryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cityID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="compType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="companyID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="concurrencyCounter" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="contractType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="countryID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="delFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="error" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="genericMasterName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="masterName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryKey" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="regionID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="roleID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="selectedCompanyList" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="success" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="supplyPurposeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supplyPurposeTableName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_commonvo_WSSearchMasterVO", propOrder = {
    "categoryCode",
    "cityID",
    "compType",
    "companyID",
    "concurrencyCounter",
    "contractType",
    "countryID",
    "createdBy",
    "createdDate",
    "delFlag",
    "destType",
    "error",
    "genericMasterName",
    "lastModifiedBy",
    "lastModifiedDate",
    "masterName",
    "primaryKey",
    "regionID",
    "roleID",
    "selectedCompanyList",
    "success",
    "supplyPurposeName",
    "supplyPurposeTableName",
    "warning"
})
public class ComTropicsWebserviceITropicsVoLegacyCommonvoWSSearchMasterVO {

    protected String categoryCode;
    protected long cityID;
    protected String compType;
    protected long companyID;
    protected int concurrencyCounter;
    protected String contractType;
    protected long countryID;
    protected String createdBy;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdDate;
    protected String delFlag;
    protected String destType;
    @XmlElement(nillable = true)
    protected List<String> error;
    protected String genericMasterName;
    protected String lastModifiedBy;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedDate;
    protected String masterName;
    protected long primaryKey;
    protected long regionID;
    protected long roleID;
    @XmlElement(type = Long.class)
    protected List<Long> selectedCompanyList;
    @XmlElement(nillable = true)
    protected List<String> success;
    protected String supplyPurposeName;
    protected String supplyPurposeTableName;
    @XmlElement(nillable = true)
    protected List<String> warning;

    /**
     * Gets the value of the categoryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryCode() {
        return categoryCode;
    }

    /**
     * Sets the value of the categoryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryCode(String value) {
        this.categoryCode = value;
    }

    /**
     * Gets the value of the cityID property.
     * 
     */
    public long getCityID() {
        return cityID;
    }

    /**
     * Sets the value of the cityID property.
     * 
     */
    public void setCityID(long value) {
        this.cityID = value;
    }

    /**
     * Gets the value of the compType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompType() {
        return compType;
    }

    /**
     * Sets the value of the compType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompType(String value) {
        this.compType = value;
    }

    /**
     * Gets the value of the companyID property.
     * 
     */
    public long getCompanyID() {
        return companyID;
    }

    /**
     * Sets the value of the companyID property.
     * 
     */
    public void setCompanyID(long value) {
        this.companyID = value;
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
     * Gets the value of the contractType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractType() {
        return contractType;
    }

    /**
     * Sets the value of the contractType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractType(String value) {
        this.contractType = value;
    }

    /**
     * Gets the value of the countryID property.
     * 
     */
    public long getCountryID() {
        return countryID;
    }

    /**
     * Sets the value of the countryID property.
     * 
     */
    public void setCountryID(long value) {
        this.countryID = value;
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
     * Gets the value of the destType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestType() {
        return destType;
    }

    /**
     * Sets the value of the destType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestType(String value) {
        this.destType = value;
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
     * Gets the value of the genericMasterName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenericMasterName() {
        return genericMasterName;
    }

    /**
     * Sets the value of the genericMasterName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenericMasterName(String value) {
        this.genericMasterName = value;
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
     * Gets the value of the masterName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMasterName() {
        return masterName;
    }

    /**
     * Sets the value of the masterName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMasterName(String value) {
        this.masterName = value;
    }

    /**
     * Gets the value of the primaryKey property.
     * 
     */
    public long getPrimaryKey() {
        return primaryKey;
    }

    /**
     * Sets the value of the primaryKey property.
     * 
     */
    public void setPrimaryKey(long value) {
        this.primaryKey = value;
    }

    /**
     * Gets the value of the regionID property.
     * 
     */
    public long getRegionID() {
        return regionID;
    }

    /**
     * Sets the value of the regionID property.
     * 
     */
    public void setRegionID(long value) {
        this.regionID = value;
    }

    /**
     * Gets the value of the roleID property.
     * 
     */
    public long getRoleID() {
        return roleID;
    }

    /**
     * Sets the value of the roleID property.
     * 
     */
    public void setRoleID(long value) {
        this.roleID = value;
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
     * Gets the value of the supplyPurposeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplyPurposeName() {
        return supplyPurposeName;
    }

    /**
     * Sets the value of the supplyPurposeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplyPurposeName(String value) {
        this.supplyPurposeName = value;
    }

    /**
     * Gets the value of the supplyPurposeTableName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplyPurposeTableName() {
        return supplyPurposeTableName;
    }

    /**
     * Sets the value of the supplyPurposeTableName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplyPurposeTableName(String value) {
        this.supplyPurposeTableName = value;
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
