
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
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_WSBookingInfoVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_WSBookingInfoVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addressVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_commonvo_WSAddressVO" minOccurs="0"/>
 *         &lt;element name="bkgID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="bookingChannel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bookingDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="bookingID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bookingType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bookingTypeArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_commonvo_WSMasterDataVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="consortiumName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IATACode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="leadClientName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noOfAdults" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="noOfChildren" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sellingCompanyCurrency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titlesArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_commonvo_WSMasterDataVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="travelAgencyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="travelConslFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="travelConslLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="travelConslMiddleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="travelConslTitleID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="travelConsultantID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_WSBookingInfoVO", propOrder = {
    "addressVO",
    "bkgID",
    "bookingChannel",
    "bookingDate",
    "bookingID",
    "bookingType",
    "bookingTypeArray",
    "consortiumName",
    "groupName",
    "iataCode",
    "leadClientName",
    "noOfAdults",
    "noOfChildren",
    "sellingCompanyCurrency",
    "titlesArray",
    "travelAgencyName",
    "travelConslFirstName",
    "travelConslLastName",
    "travelConslMiddleName",
    "travelConslTitleID",
    "travelConsultantID"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSBookingInfoVO {

    protected ComTropicsWebserviceITropicsVoLegacyCommonvoWSAddressVO addressVO;
    protected long bkgID;
    protected String bookingChannel;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar bookingDate;
    protected String bookingID;
    protected String bookingType;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO> bookingTypeArray;
    protected String consortiumName;
    protected String groupName;
    @XmlElement(name = "IATACode")
    protected String iataCode;
    protected String leadClientName;
    protected int noOfAdults;
    protected int noOfChildren;
    protected String sellingCompanyCurrency;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO> titlesArray;
    protected String travelAgencyName;
    protected String travelConslFirstName;
    protected String travelConslLastName;
    protected String travelConslMiddleName;
    protected long travelConslTitleID;
    protected long travelConsultantID;

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
     * Gets the value of the bookingChannel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingChannel() {
        return bookingChannel;
    }

    /**
     * Sets the value of the bookingChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingChannel(String value) {
        this.bookingChannel = value;
    }

    /**
     * Gets the value of the bookingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBookingDate() {
        return bookingDate;
    }

    /**
     * Sets the value of the bookingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBookingDate(XMLGregorianCalendar value) {
        this.bookingDate = value;
    }

    /**
     * Gets the value of the bookingID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingID() {
        return bookingID;
    }

    /**
     * Sets the value of the bookingID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingID(String value) {
        this.bookingID = value;
    }

    /**
     * Gets the value of the bookingType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingType() {
        return bookingType;
    }

    /**
     * Sets the value of the bookingType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingType(String value) {
        this.bookingType = value;
    }

    /**
     * Gets the value of the bookingTypeArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bookingTypeArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBookingTypeArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO> getBookingTypeArray() {
        if (bookingTypeArray == null) {
            bookingTypeArray = new ArrayList<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO>();
        }
        return this.bookingTypeArray;
    }

    /**
     * Gets the value of the consortiumName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsortiumName() {
        return consortiumName;
    }

    /**
     * Sets the value of the consortiumName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsortiumName(String value) {
        this.consortiumName = value;
    }

    /**
     * Gets the value of the groupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Sets the value of the groupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupName(String value) {
        this.groupName = value;
    }

    /**
     * Gets the value of the iataCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIATACode() {
        return iataCode;
    }

    /**
     * Sets the value of the iataCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIATACode(String value) {
        this.iataCode = value;
    }

    /**
     * Gets the value of the leadClientName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeadClientName() {
        return leadClientName;
    }

    /**
     * Sets the value of the leadClientName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeadClientName(String value) {
        this.leadClientName = value;
    }

    /**
     * Gets the value of the noOfAdults property.
     * 
     */
    public int getNoOfAdults() {
        return noOfAdults;
    }

    /**
     * Sets the value of the noOfAdults property.
     * 
     */
    public void setNoOfAdults(int value) {
        this.noOfAdults = value;
    }

    /**
     * Gets the value of the noOfChildren property.
     * 
     */
    public int getNoOfChildren() {
        return noOfChildren;
    }

    /**
     * Sets the value of the noOfChildren property.
     * 
     */
    public void setNoOfChildren(int value) {
        this.noOfChildren = value;
    }

    /**
     * Gets the value of the sellingCompanyCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellingCompanyCurrency() {
        return sellingCompanyCurrency;
    }

    /**
     * Sets the value of the sellingCompanyCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellingCompanyCurrency(String value) {
        this.sellingCompanyCurrency = value;
    }

    /**
     * Gets the value of the titlesArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the titlesArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTitlesArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO> getTitlesArray() {
        if (titlesArray == null) {
            titlesArray = new ArrayList<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO>();
        }
        return this.titlesArray;
    }

    /**
     * Gets the value of the travelAgencyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTravelAgencyName() {
        return travelAgencyName;
    }

    /**
     * Sets the value of the travelAgencyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTravelAgencyName(String value) {
        this.travelAgencyName = value;
    }

    /**
     * Gets the value of the travelConslFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTravelConslFirstName() {
        return travelConslFirstName;
    }

    /**
     * Sets the value of the travelConslFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTravelConslFirstName(String value) {
        this.travelConslFirstName = value;
    }

    /**
     * Gets the value of the travelConslLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTravelConslLastName() {
        return travelConslLastName;
    }

    /**
     * Sets the value of the travelConslLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTravelConslLastName(String value) {
        this.travelConslLastName = value;
    }

    /**
     * Gets the value of the travelConslMiddleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTravelConslMiddleName() {
        return travelConslMiddleName;
    }

    /**
     * Sets the value of the travelConslMiddleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTravelConslMiddleName(String value) {
        this.travelConslMiddleName = value;
    }

    /**
     * Gets the value of the travelConslTitleID property.
     * 
     */
    public long getTravelConslTitleID() {
        return travelConslTitleID;
    }

    /**
     * Sets the value of the travelConslTitleID property.
     * 
     */
    public void setTravelConslTitleID(long value) {
        this.travelConslTitleID = value;
    }

    /**
     * Gets the value of the travelConsultantID property.
     * 
     */
    public long getTravelConsultantID() {
        return travelConsultantID;
    }

    /**
     * Sets the value of the travelConsultantID property.
     * 
     */
    public void setTravelConsultantID(long value) {
        this.travelConsultantID = value;
    }

}
