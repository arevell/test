
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_client_WSClientVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_client_WSClientVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addressGrpNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="addressLine1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_commonvo_WSAddressVO" minOccurs="0"/>
 *         &lt;element name="age" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ageEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ageType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="birthDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="bkgID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="clientID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="clientNo" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="clientTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientTitleID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="clientType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataStatusFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="expressCheckIn" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="FTTourDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="FTTourName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupAddressID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="ITropicsAmendClientDetails" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isLeadClientFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="membershipNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="membershipType" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="membershipTypeValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="middleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nameChanged" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="pensionerNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preferredName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="redressNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="seqClientID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsClientPassportInfo" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_client_WSClientPassportInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_client_WSClientVO", propOrder = {
    "addressGrpNo",
    "addressLine1",
    "addressVO",
    "age",
    "ageEnabled",
    "ageType",
    "birthDate",
    "bkgID",
    "clientID",
    "clientNo",
    "clientTitle",
    "clientTitleID",
    "clientType",
    "dataStatusFlag",
    "expressCheckIn",
    "ftTourDate",
    "ftTourName",
    "firstName",
    "gender",
    "groupAddressID",
    "iTropicsAmendClientDetails",
    "isLeadClientFlag",
    "lastName",
    "membershipNo",
    "membershipType",
    "membershipTypeValue",
    "middleName",
    "nameChanged",
    "pensionerNo",
    "preferredName",
    "redressNumber",
    "seqClientID",
    "status",
    "wsClientPassportInfo"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingmainClientWSClientVO {

    protected int addressGrpNo;
    protected String addressLine1;
    protected ComTropicsWebserviceITropicsVoLegacyCommonvoWSAddressVO addressVO;
    protected int age;
    protected boolean ageEnabled;
    protected String ageType;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar birthDate;
    protected long bkgID;
    protected long clientID;
    protected long clientNo;
    protected String clientTitle;
    protected long clientTitleID;
    protected String clientType;
    protected String dataStatusFlag;
    protected boolean expressCheckIn;
    @XmlElement(name = "FTTourDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ftTourDate;
    @XmlElement(name = "FTTourName")
    protected String ftTourName;
    protected String firstName;
    protected String gender;
    protected long groupAddressID;
    @XmlElement(name = "ITropicsAmendClientDetails")
    protected boolean iTropicsAmendClientDetails;
    protected String isLeadClientFlag;
    protected String lastName;
    protected String membershipNo;
    protected long membershipType;
    protected String membershipTypeValue;
    protected String middleName;
    protected boolean nameChanged;
    protected String pensionerNo;
    protected String preferredName;
    protected String redressNumber;
    protected String seqClientID;
    protected String status;
    protected ComTropicsWebserviceITropicsVoLegacyBookingBookingmainClientWSClientPassportInfo wsClientPassportInfo;

    /**
     * Gets the value of the addressGrpNo property.
     * 
     */
    public int getAddressGrpNo() {
        return addressGrpNo;
    }

    /**
     * Sets the value of the addressGrpNo property.
     * 
     */
    public void setAddressGrpNo(int value) {
        this.addressGrpNo = value;
    }

    /**
     * Gets the value of the addressLine1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Sets the value of the addressLine1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressLine1(String value) {
        this.addressLine1 = value;
    }

    /**
     * Gets the value of the addressVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyCommonvoWSAddressVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyCommonvoWSAddressVO getAddressVO() {
        return addressVO;
    }

    /**
     * Sets the value of the addressVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyCommonvoWSAddressVO }
     *     
     */
    public void setAddressVO(ComTropicsWebserviceITropicsVoLegacyCommonvoWSAddressVO value) {
        this.addressVO = value;
    }

    /**
     * Gets the value of the age property.
     * 
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the value of the age property.
     * 
     */
    public void setAge(int value) {
        this.age = value;
    }

    /**
     * Gets the value of the ageEnabled property.
     * 
     */
    public boolean isAgeEnabled() {
        return ageEnabled;
    }

    /**
     * Sets the value of the ageEnabled property.
     * 
     */
    public void setAgeEnabled(boolean value) {
        this.ageEnabled = value;
    }

    /**
     * Gets the value of the ageType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgeType() {
        return ageType;
    }

    /**
     * Sets the value of the ageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgeType(String value) {
        this.ageType = value;
    }

    /**
     * Gets the value of the birthDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the value of the birthDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBirthDate(XMLGregorianCalendar value) {
        this.birthDate = value;
    }

    /**
     * Gets the value of the bkgID property.
     * 
     */
    public long getBkgID() {
        return bkgID;
    }

    /**
     * Sets the value of the bkgID property.
     * 
     */
    public void setBkgID(long value) {
        this.bkgID = value;
    }

    /**
     * Gets the value of the clientID property.
     * 
     */
    public long getClientID() {
        return clientID;
    }

    /**
     * Sets the value of the clientID property.
     * 
     */
    public void setClientID(long value) {
        this.clientID = value;
    }

    /**
     * Gets the value of the clientNo property.
     * 
     */
    public long getClientNo() {
        return clientNo;
    }

    /**
     * Sets the value of the clientNo property.
     * 
     */
    public void setClientNo(long value) {
        this.clientNo = value;
    }

    /**
     * Gets the value of the clientTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientTitle() {
        return clientTitle;
    }

    /**
     * Sets the value of the clientTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientTitle(String value) {
        this.clientTitle = value;
    }

    /**
     * Gets the value of the clientTitleID property.
     * 
     */
    public long getClientTitleID() {
        return clientTitleID;
    }

    /**
     * Sets the value of the clientTitleID property.
     * 
     */
    public void setClientTitleID(long value) {
        this.clientTitleID = value;
    }

    /**
     * Gets the value of the clientType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientType() {
        return clientType;
    }

    /**
     * Sets the value of the clientType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientType(String value) {
        this.clientType = value;
    }

    /**
     * Gets the value of the dataStatusFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataStatusFlag() {
        return dataStatusFlag;
    }

    /**
     * Sets the value of the dataStatusFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataStatusFlag(String value) {
        this.dataStatusFlag = value;
    }

    /**
     * Gets the value of the expressCheckIn property.
     * 
     */
    public boolean isExpressCheckIn() {
        return expressCheckIn;
    }

    /**
     * Sets the value of the expressCheckIn property.
     * 
     */
    public void setExpressCheckIn(boolean value) {
        this.expressCheckIn = value;
    }

    /**
     * Gets the value of the ftTourDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFTTourDate() {
        return ftTourDate;
    }

    /**
     * Sets the value of the ftTourDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFTTourDate(XMLGregorianCalendar value) {
        this.ftTourDate = value;
    }

    /**
     * Gets the value of the ftTourName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFTTourName() {
        return ftTourName;
    }

    /**
     * Sets the value of the ftTourName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFTTourName(String value) {
        this.ftTourName = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGender(String value) {
        this.gender = value;
    }

    /**
     * Gets the value of the groupAddressID property.
     * 
     */
    public long getGroupAddressID() {
        return groupAddressID;
    }

    /**
     * Sets the value of the groupAddressID property.
     * 
     */
    public void setGroupAddressID(long value) {
        this.groupAddressID = value;
    }

    /**
     * Gets the value of the iTropicsAmendClientDetails property.
     * 
     */
    public boolean isITropicsAmendClientDetails() {
        return iTropicsAmendClientDetails;
    }

    /**
     * Sets the value of the iTropicsAmendClientDetails property.
     * 
     */
    public void setITropicsAmendClientDetails(boolean value) {
        this.iTropicsAmendClientDetails = value;
    }

    /**
     * Gets the value of the isLeadClientFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsLeadClientFlag() {
        return isLeadClientFlag;
    }

    /**
     * Sets the value of the isLeadClientFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsLeadClientFlag(String value) {
        this.isLeadClientFlag = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the membershipNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMembershipNo() {
        return membershipNo;
    }

    /**
     * Sets the value of the membershipNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMembershipNo(String value) {
        this.membershipNo = value;
    }

    /**
     * Gets the value of the membershipType property.
     * 
     */
    public long getMembershipType() {
        return membershipType;
    }

    /**
     * Sets the value of the membershipType property.
     * 
     */
    public void setMembershipType(long value) {
        this.membershipType = value;
    }

    /**
     * Gets the value of the membershipTypeValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMembershipTypeValue() {
        return membershipTypeValue;
    }

    /**
     * Sets the value of the membershipTypeValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMembershipTypeValue(String value) {
        this.membershipTypeValue = value;
    }

    /**
     * Gets the value of the middleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the value of the middleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiddleName(String value) {
        this.middleName = value;
    }

    /**
     * Gets the value of the nameChanged property.
     * 
     */
    public boolean isNameChanged() {
        return nameChanged;
    }

    /**
     * Sets the value of the nameChanged property.
     * 
     */
    public void setNameChanged(boolean value) {
        this.nameChanged = value;
    }

    /**
     * Gets the value of the pensionerNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPensionerNo() {
        return pensionerNo;
    }

    /**
     * Sets the value of the pensionerNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPensionerNo(String value) {
        this.pensionerNo = value;
    }

    /**
     * Gets the value of the preferredName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferredName() {
        return preferredName;
    }

    /**
     * Sets the value of the preferredName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferredName(String value) {
        this.preferredName = value;
    }

    /**
     * Gets the value of the redressNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRedressNumber() {
        return redressNumber;
    }

    /**
     * Sets the value of the redressNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRedressNumber(String value) {
        this.redressNumber = value;
    }

    /**
     * Gets the value of the seqClientID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeqClientID() {
        return seqClientID;
    }

    /**
     * Sets the value of the seqClientID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeqClientID(String value) {
        this.seqClientID = value;
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
     * Gets the value of the wsClientPassportInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingmainClientWSClientPassportInfo }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyBookingBookingmainClientWSClientPassportInfo getWsClientPassportInfo() {
        return wsClientPassportInfo;
    }

    /**
     * Sets the value of the wsClientPassportInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingmainClientWSClientPassportInfo }
     *     
     */
    public void setWsClientPassportInfo(ComTropicsWebserviceITropicsVoLegacyBookingBookingmainClientWSClientPassportInfo value) {
        this.wsClientPassportInfo = value;
    }

}
