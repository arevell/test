
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
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_products_air_WSAirlinesRestrictionVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_products_air_WSAirlinesRestrictionVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="airlines" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_products_air_WSRestrictionAirlineVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="airlinesRestrictionId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="cityPairFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cityPairFromId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="cityPairTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cityPairToId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="concurrencyCounter" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="condition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="delFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="error" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="flightType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="nonFItAirContractId" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_products_air_WSAirlinesRestrictionVO", propOrder = {
    "airlines",
    "airlinesRestrictionId",
    "cityPairFrom",
    "cityPairFromId",
    "cityPairTo",
    "cityPairToId",
    "concurrencyCounter",
    "condition",
    "createdBy",
    "createdDate",
    "delFlag",
    "error",
    "flightType",
    "lastModifiedBy",
    "lastModifiedDate",
    "nonFItAirContractId",
    "selectedCompanyList",
    "success",
    "warning"
})
public class ComTropicsWebserviceITropicsVoLegacyProductsAirWSAirlinesRestrictionVO {

    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyProductsAirWSRestrictionAirlineVO> airlines;
    protected long airlinesRestrictionId;
    protected String cityPairFrom;
    protected long cityPairFromId;
    protected String cityPairTo;
    protected long cityPairToId;
    protected int concurrencyCounter;
    protected String condition;
    protected String createdBy;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdDate;
    protected String delFlag;
    @XmlElement(nillable = true)
    protected List<String> error;
    protected String flightType;
    protected String lastModifiedBy;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedDate;
    protected long nonFItAirContractId;
    @XmlElement(type = Long.class)
    protected List<Long> selectedCompanyList;
    @XmlElement(nillable = true)
    protected List<String> success;
    @XmlElement(nillable = true)
    protected List<String> warning;

    /**
     * Gets the value of the airlines property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the airlines property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAirlines().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyProductsAirWSRestrictionAirlineVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyProductsAirWSRestrictionAirlineVO> getAirlines() {
        if (airlines == null) {
            airlines = new ArrayList<ComTropicsWebserviceITropicsVoLegacyProductsAirWSRestrictionAirlineVO>();
        }
        return this.airlines;
    }

    /**
     * Gets the value of the airlinesRestrictionId property.
     * 
     */
    public long getAirlinesRestrictionId() {
        return airlinesRestrictionId;
    }

    /**
     * Sets the value of the airlinesRestrictionId property.
     * 
     */
    public void setAirlinesRestrictionId(long value) {
        this.airlinesRestrictionId = value;
    }

    /**
     * Gets the value of the cityPairFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityPairFrom() {
        return cityPairFrom;
    }

    /**
     * Sets the value of the cityPairFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityPairFrom(String value) {
        this.cityPairFrom = value;
    }

    /**
     * Gets the value of the cityPairFromId property.
     * 
     */
    public long getCityPairFromId() {
        return cityPairFromId;
    }

    /**
     * Sets the value of the cityPairFromId property.
     * 
     */
    public void setCityPairFromId(long value) {
        this.cityPairFromId = value;
    }

    /**
     * Gets the value of the cityPairTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityPairTo() {
        return cityPairTo;
    }

    /**
     * Sets the value of the cityPairTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityPairTo(String value) {
        this.cityPairTo = value;
    }

    /**
     * Gets the value of the cityPairToId property.
     * 
     */
    public long getCityPairToId() {
        return cityPairToId;
    }

    /**
     * Sets the value of the cityPairToId property.
     * 
     */
    public void setCityPairToId(long value) {
        this.cityPairToId = value;
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
     * Gets the value of the condition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Sets the value of the condition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCondition(String value) {
        this.condition = value;
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
     * Gets the value of the flightType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlightType() {
        return flightType;
    }

    /**
     * Sets the value of the flightType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlightType(String value) {
        this.flightType = value;
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
     * Gets the value of the nonFItAirContractId property.
     * 
     */
    public long getNonFItAirContractId() {
        return nonFItAirContractId;
    }

    /**
     * Sets the value of the nonFItAirContractId property.
     * 
     */
    public void setNonFItAirContractId(long value) {
        this.nonFItAirContractId = value;
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
