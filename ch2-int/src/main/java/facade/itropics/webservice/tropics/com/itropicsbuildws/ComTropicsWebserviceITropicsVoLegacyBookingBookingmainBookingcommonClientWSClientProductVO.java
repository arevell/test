
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
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_client_WSClientProductVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_client_WSClientProductVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bookingLineItemID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientProductID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fromLoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ITropicsProdDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pnr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="priceDetailsVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSPriceDetailsVO" minOccurs="0"/>
 *         &lt;element name="productCategoryID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="productCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roomID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="roomType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="toLoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsAirItineraryVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_air_booking_WSAirItineraryVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_client_WSClientProductVO", propOrder = {
    "bookingLineItemID",
    "category",
    "clientProductID",
    "duration",
    "endDate",
    "fromLoc",
    "iTropicsProdDescription",
    "pnr",
    "priceDetailsVO",
    "productCategoryID",
    "productCode",
    "productName",
    "productType",
    "roomID",
    "roomType",
    "startDate",
    "status",
    "toLoc",
    "wsAirItineraryVO"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonClientWSClientProductVO {

    protected long bookingLineItemID;
    protected String category;
    protected long clientProductID;
    protected int duration;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    protected String fromLoc;
    @XmlElement(name = "ITropicsProdDescription")
    protected String iTropicsProdDescription;
    protected String pnr;
    protected ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSPriceDetailsVO priceDetailsVO;
    protected long productCategoryID;
    protected String productCode;
    protected String productName;
    protected String productType;
    protected long roomID;
    protected String roomType;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    protected String status;
    protected String toLoc;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirItineraryVO> wsAirItineraryVO;

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
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the clientProductID property.
     * 
     */
    public long getClientProductID() {
        return clientProductID;
    }

    /**
     * Sets the value of the clientProductID property.
     * 
     */
    public void setClientProductID(long value) {
        this.clientProductID = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     */
    public void setDuration(int value) {
        this.duration = value;
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
     * Gets the value of the fromLoc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromLoc() {
        return fromLoc;
    }

    /**
     * Sets the value of the fromLoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromLoc(String value) {
        this.fromLoc = value;
    }

    /**
     * Gets the value of the iTropicsProdDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getITropicsProdDescription() {
        return iTropicsProdDescription;
    }

    /**
     * Sets the value of the iTropicsProdDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setITropicsProdDescription(String value) {
        this.iTropicsProdDescription = value;
    }

    /**
     * Gets the value of the pnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPnr() {
        return pnr;
    }

    /**
     * Sets the value of the pnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPnr(String value) {
        this.pnr = value;
    }

    /**
     * Gets the value of the priceDetailsVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSPriceDetailsVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSPriceDetailsVO getPriceDetailsVO() {
        return priceDetailsVO;
    }

    /**
     * Sets the value of the priceDetailsVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSPriceDetailsVO }
     *     
     */
    public void setPriceDetailsVO(ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSPriceDetailsVO value) {
        this.priceDetailsVO = value;
    }

    /**
     * Gets the value of the productCategoryID property.
     * 
     */
    public long getProductCategoryID() {
        return productCategoryID;
    }

    /**
     * Sets the value of the productCategoryID property.
     * 
     */
    public void setProductCategoryID(long value) {
        this.productCategoryID = value;
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
     * Gets the value of the roomID property.
     * 
     */
    public long getRoomID() {
        return roomID;
    }

    /**
     * Sets the value of the roomID property.
     * 
     */
    public void setRoomID(long value) {
        this.roomID = value;
    }

    /**
     * Gets the value of the roomType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * Sets the value of the roomType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomType(String value) {
        this.roomType = value;
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
     * Gets the value of the toLoc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToLoc() {
        return toLoc;
    }

    /**
     * Sets the value of the toLoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToLoc(String value) {
        this.toLoc = value;
    }

    /**
     * Gets the value of the wsAirItineraryVO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsAirItineraryVO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsAirItineraryVO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirItineraryVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirItineraryVO> getWsAirItineraryVO() {
        if (wsAirItineraryVO == null) {
            wsAirItineraryVO = new ArrayList<ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirItineraryVO>();
        }
        return this.wsAirItineraryVO;
    }

}
