
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSBookingCancellationFeesVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSBookingCancellationFeesVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bookingCommission" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="bookingGrossPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="cancellationFeesCommission" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="cancellationFeesReasonsArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_commonvo_WSMasterDataVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cancellationReasonID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="cancelledClientIDs" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cancelledProdIDs" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="clientAmendCancelFeesVOCollection" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSClientAmendCancelFeesVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="feesID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="feesLevelFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nettBookingPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="parentScreen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productAmendCancelFeesVOCollection" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSProductAmendCancelFeesVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="remarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totCurExtFees" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totCurTTCFees" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totRevExtFees" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totRevTTCFees" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalAmountPaid" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalBalanceAmount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalCancellationFees" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="waivingFeeReasonID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="waivingFeesReasonsArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_commonvo_WSMasterDataVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSBookingCancellationFeesVO", propOrder = {
    "bookingCommission",
    "bookingGrossPrice",
    "cancellationFeesCommission",
    "cancellationFeesReasonsArray",
    "cancellationReasonID",
    "cancelledClientIDs",
    "cancelledProdIDs",
    "clientAmendCancelFeesVOCollection",
    "feesID",
    "feesLevelFlag",
    "nettBookingPrice",
    "parentScreen",
    "productAmendCancelFeesVOCollection",
    "remarks",
    "totCurExtFees",
    "totCurTTCFees",
    "totRevExtFees",
    "totRevTTCFees",
    "totalAmountPaid",
    "totalBalanceAmount",
    "totalCancellationFees",
    "waivingFeeReasonID",
    "waivingFeesReasonsArray"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSBookingCancellationFeesVO {

    protected float bookingCommission;
    protected float bookingGrossPrice;
    protected float cancellationFeesCommission;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO> cancellationFeesReasonsArray;
    protected long cancellationReasonID;
    @XmlElement(nillable = true)
    protected List<Long> cancelledClientIDs;
    @XmlElement(nillable = true)
    protected List<Long> cancelledProdIDs;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSClientAmendCancelFeesVO> clientAmendCancelFeesVOCollection;
    protected long feesID;
    protected String feesLevelFlag;
    protected float nettBookingPrice;
    protected String parentScreen;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSProductAmendCancelFeesVO> productAmendCancelFeesVOCollection;
    protected String remarks;
    protected float totCurExtFees;
    protected float totCurTTCFees;
    protected float totRevExtFees;
    protected float totRevTTCFees;
    protected float totalAmountPaid;
    protected float totalBalanceAmount;
    protected float totalCancellationFees;
    protected long waivingFeeReasonID;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO> waivingFeesReasonsArray;

    /**
     * Gets the value of the bookingCommission property.
     * 
     */
    public float getBookingCommission() {
        return bookingCommission;
    }

    /**
     * Sets the value of the bookingCommission property.
     * 
     */
    public void setBookingCommission(float value) {
        this.bookingCommission = value;
    }

    /**
     * Gets the value of the bookingGrossPrice property.
     * 
     */
    public float getBookingGrossPrice() {
        return bookingGrossPrice;
    }

    /**
     * Sets the value of the bookingGrossPrice property.
     * 
     */
    public void setBookingGrossPrice(float value) {
        this.bookingGrossPrice = value;
    }

    /**
     * Gets the value of the cancellationFeesCommission property.
     * 
     */
    public float getCancellationFeesCommission() {
        return cancellationFeesCommission;
    }

    /**
     * Sets the value of the cancellationFeesCommission property.
     * 
     */
    public void setCancellationFeesCommission(float value) {
        this.cancellationFeesCommission = value;
    }

    /**
     * Gets the value of the cancellationFeesReasonsArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cancellationFeesReasonsArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCancellationFeesReasonsArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO> getCancellationFeesReasonsArray() {
        if (cancellationFeesReasonsArray == null) {
            cancellationFeesReasonsArray = new ArrayList<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO>();
        }
        return this.cancellationFeesReasonsArray;
    }

    /**
     * Gets the value of the cancellationReasonID property.
     * 
     */
    public long getCancellationReasonID() {
        return cancellationReasonID;
    }

    /**
     * Sets the value of the cancellationReasonID property.
     * 
     */
    public void setCancellationReasonID(long value) {
        this.cancellationReasonID = value;
    }

    /**
     * Gets the value of the cancelledClientIDs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cancelledClientIDs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCancelledClientIDs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getCancelledClientIDs() {
        if (cancelledClientIDs == null) {
            cancelledClientIDs = new ArrayList<Long>();
        }
        return this.cancelledClientIDs;
    }

    /**
     * Gets the value of the cancelledProdIDs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cancelledProdIDs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCancelledProdIDs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getCancelledProdIDs() {
        if (cancelledProdIDs == null) {
            cancelledProdIDs = new ArrayList<Long>();
        }
        return this.cancelledProdIDs;
    }

    /**
     * Gets the value of the clientAmendCancelFeesVOCollection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clientAmendCancelFeesVOCollection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClientAmendCancelFeesVOCollection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSClientAmendCancelFeesVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSClientAmendCancelFeesVO> getClientAmendCancelFeesVOCollection() {
        if (clientAmendCancelFeesVOCollection == null) {
            clientAmendCancelFeesVOCollection = new ArrayList<ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSClientAmendCancelFeesVO>();
        }
        return this.clientAmendCancelFeesVOCollection;
    }

    /**
     * Gets the value of the feesID property.
     * 
     */
    public long getFeesID() {
        return feesID;
    }

    /**
     * Sets the value of the feesID property.
     * 
     */
    public void setFeesID(long value) {
        this.feesID = value;
    }

    /**
     * Gets the value of the feesLevelFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeesLevelFlag() {
        return feesLevelFlag;
    }

    /**
     * Sets the value of the feesLevelFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeesLevelFlag(String value) {
        this.feesLevelFlag = value;
    }

    /**
     * Gets the value of the nettBookingPrice property.
     * 
     */
    public float getNettBookingPrice() {
        return nettBookingPrice;
    }

    /**
     * Sets the value of the nettBookingPrice property.
     * 
     */
    public void setNettBookingPrice(float value) {
        this.nettBookingPrice = value;
    }

    /**
     * Gets the value of the parentScreen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentScreen() {
        return parentScreen;
    }

    /**
     * Sets the value of the parentScreen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentScreen(String value) {
        this.parentScreen = value;
    }

    /**
     * Gets the value of the productAmendCancelFeesVOCollection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productAmendCancelFeesVOCollection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductAmendCancelFeesVOCollection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSProductAmendCancelFeesVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSProductAmendCancelFeesVO> getProductAmendCancelFeesVOCollection() {
        if (productAmendCancelFeesVOCollection == null) {
            productAmendCancelFeesVOCollection = new ArrayList<ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSProductAmendCancelFeesVO>();
        }
        return this.productAmendCancelFeesVOCollection;
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
     * Gets the value of the totCurExtFees property.
     * 
     */
    public float getTotCurExtFees() {
        return totCurExtFees;
    }

    /**
     * Sets the value of the totCurExtFees property.
     * 
     */
    public void setTotCurExtFees(float value) {
        this.totCurExtFees = value;
    }

    /**
     * Gets the value of the totCurTTCFees property.
     * 
     */
    public float getTotCurTTCFees() {
        return totCurTTCFees;
    }

    /**
     * Sets the value of the totCurTTCFees property.
     * 
     */
    public void setTotCurTTCFees(float value) {
        this.totCurTTCFees = value;
    }

    /**
     * Gets the value of the totRevExtFees property.
     * 
     */
    public float getTotRevExtFees() {
        return totRevExtFees;
    }

    /**
     * Sets the value of the totRevExtFees property.
     * 
     */
    public void setTotRevExtFees(float value) {
        this.totRevExtFees = value;
    }

    /**
     * Gets the value of the totRevTTCFees property.
     * 
     */
    public float getTotRevTTCFees() {
        return totRevTTCFees;
    }

    /**
     * Sets the value of the totRevTTCFees property.
     * 
     */
    public void setTotRevTTCFees(float value) {
        this.totRevTTCFees = value;
    }

    /**
     * Gets the value of the totalAmountPaid property.
     * 
     */
    public float getTotalAmountPaid() {
        return totalAmountPaid;
    }

    /**
     * Sets the value of the totalAmountPaid property.
     * 
     */
    public void setTotalAmountPaid(float value) {
        this.totalAmountPaid = value;
    }

    /**
     * Gets the value of the totalBalanceAmount property.
     * 
     */
    public float getTotalBalanceAmount() {
        return totalBalanceAmount;
    }

    /**
     * Sets the value of the totalBalanceAmount property.
     * 
     */
    public void setTotalBalanceAmount(float value) {
        this.totalBalanceAmount = value;
    }

    /**
     * Gets the value of the totalCancellationFees property.
     * 
     */
    public float getTotalCancellationFees() {
        return totalCancellationFees;
    }

    /**
     * Sets the value of the totalCancellationFees property.
     * 
     */
    public void setTotalCancellationFees(float value) {
        this.totalCancellationFees = value;
    }

    /**
     * Gets the value of the waivingFeeReasonID property.
     * 
     */
    public long getWaivingFeeReasonID() {
        return waivingFeeReasonID;
    }

    /**
     * Sets the value of the waivingFeeReasonID property.
     * 
     */
    public void setWaivingFeeReasonID(long value) {
        this.waivingFeeReasonID = value;
    }

    /**
     * Gets the value of the waivingFeesReasonsArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the waivingFeesReasonsArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWaivingFeesReasonsArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO> getWaivingFeesReasonsArray() {
        if (waivingFeesReasonsArray == null) {
            waivingFeesReasonsArray = new ArrayList<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO>();
        }
        return this.waivingFeesReasonsArray;
    }

}
