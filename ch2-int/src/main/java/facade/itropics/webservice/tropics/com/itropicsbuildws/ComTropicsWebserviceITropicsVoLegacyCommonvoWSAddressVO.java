
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
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_commonvo_WSAddressVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_commonvo_WSAddressVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addressline1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressline2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressline3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressline4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cityID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="cityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="concurrencyCounter" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="contact1part1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contact1part2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contact1part3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contact2part1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contact2part2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contact2part3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="countryID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="countryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="delFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emergencypart1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emergencypart2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emmergencypart3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="error" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="fax1part1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fax1part2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fax1part3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fax2part1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fax2part2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fax2part3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="regionID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="regionName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="selectedCompanyList" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="success" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="warning" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="webSite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="zipcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_commonvo_WSAddressVO", propOrder = {
    "addressline1",
    "addressline2",
    "addressline3",
    "addressline4",
    "cityID",
    "cityName",
    "concurrencyCounter",
    "contact1Part1",
    "contact1Part2",
    "contact1Part3",
    "contact2Part1",
    "contact2Part2",
    "contact2Part3",
    "countryID",
    "countryName",
    "createdBy",
    "createdDate",
    "delFlag",
    "email",
    "emergencypart1",
    "emergencypart2",
    "emmergencypart3",
    "error",
    "fax1Part1",
    "fax1Part2",
    "fax1Part3",
    "fax2Part1",
    "fax2Part2",
    "fax2Part3",
    "lastModifiedBy",
    "lastModifiedDate",
    "regionID",
    "regionName",
    "selectedCompanyList",
    "success",
    "warning",
    "webSite",
    "zipcode"
})
public class ComTropicsWebserviceITropicsVoLegacyCommonvoWSAddressVO {

    protected String addressline1;
    protected String addressline2;
    protected String addressline3;
    protected String addressline4;
    protected long cityID;
    protected String cityName;
    protected int concurrencyCounter;
    @XmlElement(name = "contact1part1")
    protected String contact1Part1;
    @XmlElement(name = "contact1part2")
    protected String contact1Part2;
    @XmlElement(name = "contact1part3")
    protected String contact1Part3;
    @XmlElement(name = "contact2part1")
    protected String contact2Part1;
    @XmlElement(name = "contact2part2")
    protected String contact2Part2;
    @XmlElement(name = "contact2part3")
    protected String contact2Part3;
    protected long countryID;
    protected String countryName;
    protected String createdBy;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdDate;
    protected String delFlag;
    protected String email;
    protected String emergencypart1;
    protected String emergencypart2;
    protected String emmergencypart3;
    @XmlElement(nillable = true)
    protected List<String> error;
    @XmlElement(name = "fax1part1")
    protected String fax1Part1;
    @XmlElement(name = "fax1part2")
    protected String fax1Part2;
    @XmlElement(name = "fax1part3")
    protected String fax1Part3;
    @XmlElement(name = "fax2part1")
    protected String fax2Part1;
    @XmlElement(name = "fax2part2")
    protected String fax2Part2;
    @XmlElement(name = "fax2part3")
    protected String fax2Part3;
    protected String lastModifiedBy;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedDate;
    protected long regionID;
    protected String regionName;
    @XmlElement(type = Long.class)
    protected List<Long> selectedCompanyList;
    @XmlElement(nillable = true)
    protected List<String> success;
    @XmlElement(nillable = true)
    protected List<String> warning;
    protected String webSite;
    protected String zipcode;

    /**
     * Gets the value of the addressline1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressline1() {
        return addressline1;
    }

    /**
     * Sets the value of the addressline1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressline1(String value) {
        this.addressline1 = value;
    }

    /**
     * Gets the value of the addressline2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressline2() {
        return addressline2;
    }

    /**
     * Sets the value of the addressline2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressline2(String value) {
        this.addressline2 = value;
    }

    /**
     * Gets the value of the addressline3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressline3() {
        return addressline3;
    }

    /**
     * Sets the value of the addressline3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressline3(String value) {
        this.addressline3 = value;
    }

    /**
     * Gets the value of the addressline4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressline4() {
        return addressline4;
    }

    /**
     * Sets the value of the addressline4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressline4(String value) {
        this.addressline4 = value;
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
     * Gets the value of the cityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Sets the value of the cityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityName(String value) {
        this.cityName = value;
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
     * Gets the value of the contact1Part1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContact1Part1() {
        return contact1Part1;
    }

    /**
     * Sets the value of the contact1Part1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContact1Part1(String value) {
        this.contact1Part1 = value;
    }

    /**
     * Gets the value of the contact1Part2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContact1Part2() {
        return contact1Part2;
    }

    /**
     * Sets the value of the contact1Part2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContact1Part2(String value) {
        this.contact1Part2 = value;
    }

    /**
     * Gets the value of the contact1Part3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContact1Part3() {
        return contact1Part3;
    }

    /**
     * Sets the value of the contact1Part3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContact1Part3(String value) {
        this.contact1Part3 = value;
    }

    /**
     * Gets the value of the contact2Part1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContact2Part1() {
        return contact2Part1;
    }

    /**
     * Sets the value of the contact2Part1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContact2Part1(String value) {
        this.contact2Part1 = value;
    }

    /**
     * Gets the value of the contact2Part2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContact2Part2() {
        return contact2Part2;
    }

    /**
     * Sets the value of the contact2Part2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContact2Part2(String value) {
        this.contact2Part2 = value;
    }

    /**
     * Gets the value of the contact2Part3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContact2Part3() {
        return contact2Part3;
    }

    /**
     * Sets the value of the contact2Part3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContact2Part3(String value) {
        this.contact2Part3 = value;
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
     * Gets the value of the countryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Sets the value of the countryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryName(String value) {
        this.countryName = value;
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
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the emergencypart1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmergencypart1() {
        return emergencypart1;
    }

    /**
     * Sets the value of the emergencypart1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmergencypart1(String value) {
        this.emergencypart1 = value;
    }

    /**
     * Gets the value of the emergencypart2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmergencypart2() {
        return emergencypart2;
    }

    /**
     * Sets the value of the emergencypart2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmergencypart2(String value) {
        this.emergencypart2 = value;
    }

    /**
     * Gets the value of the emmergencypart3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmmergencypart3() {
        return emmergencypart3;
    }

    /**
     * Sets the value of the emmergencypart3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmmergencypart3(String value) {
        this.emmergencypart3 = value;
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
     * Gets the value of the fax1Part1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFax1Part1() {
        return fax1Part1;
    }

    /**
     * Sets the value of the fax1Part1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFax1Part1(String value) {
        this.fax1Part1 = value;
    }

    /**
     * Gets the value of the fax1Part2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFax1Part2() {
        return fax1Part2;
    }

    /**
     * Sets the value of the fax1Part2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFax1Part2(String value) {
        this.fax1Part2 = value;
    }

    /**
     * Gets the value of the fax1Part3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFax1Part3() {
        return fax1Part3;
    }

    /**
     * Sets the value of the fax1Part3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFax1Part3(String value) {
        this.fax1Part3 = value;
    }

    /**
     * Gets the value of the fax2Part1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFax2Part1() {
        return fax2Part1;
    }

    /**
     * Sets the value of the fax2Part1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFax2Part1(String value) {
        this.fax2Part1 = value;
    }

    /**
     * Gets the value of the fax2Part2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFax2Part2() {
        return fax2Part2;
    }

    /**
     * Sets the value of the fax2Part2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFax2Part2(String value) {
        this.fax2Part2 = value;
    }

    /**
     * Gets the value of the fax2Part3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFax2Part3() {
        return fax2Part3;
    }

    /**
     * Sets the value of the fax2Part3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFax2Part3(String value) {
        this.fax2Part3 = value;
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
     * Gets the value of the regionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * Sets the value of the regionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegionName(String value) {
        this.regionName = value;
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

    /**
     * Gets the value of the webSite property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebSite() {
        return webSite;
    }

    /**
     * Sets the value of the webSite property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebSite(String value) {
        this.webSite = value;
    }

    /**
     * Gets the value of the zipcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * Sets the value of the zipcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZipcode(String value) {
        this.zipcode = value;
    }

}
