
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_current_common_WSResponseHeaderVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_current_common_WSResponseHeaderVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addTravelConsultantID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="airSuccessful" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="departureAvailabilityStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errorMessagesArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_current_common_WSErrorMessagesVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="flightAvailable" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iofSuccessful" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="productNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="successful" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="WSAcceptReceiptAPIVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_output_WSAcceptReceiptAPIVO" minOccurs="0"/>
 *         &lt;element name="WSAppendInsuranceAPIVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_output_WSAppendInsuranceAPIVO" minOccurs="0"/>
 *         &lt;element name="WSAppendMiscellaneousAPIVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_output_WSAppendMiscellaneousAPIVO" minOccurs="0"/>
 *         &lt;element name="WSAppendPrePostAccomodationAPIVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_output_WSAppendPrePostAccomodationAPIVO" minOccurs="0"/>
 *         &lt;element name="WSCancelBkgAPIVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_output_WSCancelBkgAPIVO" minOccurs="0"/>
 *         &lt;element name="WSCancelProductAPIVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_output_WSCancelProductAPIVO" minOccurs="0"/>
 *         &lt;element name="WSCheckAccomAvailabiltyAndShowPricesAPIVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_output_WSCheckAccomAvailabiltyAndShowPricesAPIVO" minOccurs="0"/>
 *         &lt;element name="WSCheckForGapsOverlapsAPIVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_output_WSCheckForGapsOverlapsAPIVO" minOccurs="0"/>
 *         &lt;element name="WSCreateBookingAPIVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_output_WSCreateBookingAPIVO" minOccurs="0"/>
 *         &lt;element name="WSMVSearchPrePostAccomdationResAPIVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_output_WSMVSearchPrePostAccomdationResAPIVO" minOccurs="0"/>
 *         &lt;element name="WSNonFITAirContractNotesVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_products_air_WSNonFITAirContractNotesVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="WSReceivePymtAPIVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_output_WSReceivePymtAPIVO" minOccurs="0"/>
 *         &lt;element name="WSSearchBookingsAPIVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_output_WSSearchBookingsAPIVO" minOccurs="0"/>
 *         &lt;element name="wsAdminUserIDVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_WSAdminUserIDVO" minOccurs="0"/>
 *         &lt;element name="wsAirFareRuleAPIVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_output_WSAirFareRuleAPIVO" minOccurs="0"/>
 *         &lt;element name="wsAmendClientDetailsAPIVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_output_WSAmendClientDetailsAPIVO" minOccurs="0"/>
 *         &lt;element name="wsApplyEPDAPIVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_output_WSApplyEPDAPIVO" minOccurs="0"/>
 *         &lt;element name="wsGetBookingDetailsVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_WSGetBookingDetailsVO" minOccurs="0"/>
 *         &lt;element name="wsMarketVariationVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_WSMarketVariationVO" minOccurs="0"/>
 *         &lt;element name="wsPricePerClientVOArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSPricePerClientVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wsRstClientAssignmentVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_products_common_WSRstClientAssignmentVO" minOccurs="0"/>
 *         &lt;element name="wsSearchAirLowFareAPIVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_output_WSSearchAirLowFareAPIVO" minOccurs="0"/>
 *         &lt;element name="wsTAPaymentTypesVOArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_tam_WSTAPaymentTypesVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_current_common_WSResponseHeaderVO", propOrder = {
    "addTravelConsultantID",
    "airSuccessful",
    "departureAvailabilityStatus",
    "errorMessagesArray",
    "flightAvailable",
    "iofSuccessful",
    "productNotes",
    "successful",
    "wsAcceptReceiptAPIVO",
    "wsAppendInsuranceAPIVO",
    "wsAppendMiscellaneousAPIVO",
    "wsAppendPrePostAccomodationAPIVO",
    "wsCancelBkgAPIVO",
    "wsCancelProductAPIVO",
    "wsCheckAccomAvailabiltyAndShowPricesAPIVO",
    "wsCheckForGapsOverlapsAPIVO",
    "wsCreateBookingAPIVO",
    "wsmvSearchPrePostAccomdationResAPIVO",
    "wsNonFITAirContractNotesVO",
    "wsReceivePymtAPIVO",
    "wsSearchBookingsAPIVO",
    "wsAdminUserIDVO",
    "wsAirFareRuleAPIVO",
    "wsAmendClientDetailsAPIVO",
    "wsApplyEPDAPIVO",
    "wsGetBookingDetailsVO",
    "wsMarketVariationVO",
    "wsPricePerClientVOArray",
    "wsRstClientAssignmentVO",
    "wsSearchAirLowFareAPIVO",
    "wsTAPaymentTypesVOArray"
})
public class ComTropicsWebserviceITropicsVoCurrentCommonWSResponseHeaderVO {

    protected long addTravelConsultantID;
    protected boolean airSuccessful;
    protected String departureAvailabilityStatus;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO> errorMessagesArray;
    protected String flightAvailable;
    protected Boolean iofSuccessful;
    protected String productNotes;
    protected boolean successful;
    @XmlElement(name = "WSAcceptReceiptAPIVO")
    protected ComTropicsWebserviceITropicsVoLegacyOutputWSAcceptReceiptAPIVO wsAcceptReceiptAPIVO;
    @XmlElement(name = "WSAppendInsuranceAPIVO")
    protected ComTropicsWebserviceITropicsVoLegacyOutputWSAppendInsuranceAPIVO wsAppendInsuranceAPIVO;
    @XmlElement(name = "WSAppendMiscellaneousAPIVO")
    protected ComTropicsWebserviceITropicsVoLegacyOutputWSAppendMiscellaneousAPIVO wsAppendMiscellaneousAPIVO;
    @XmlElement(name = "WSAppendPrePostAccomodationAPIVO")
    protected ComTropicsWebserviceITropicsVoLegacyOutputWSAppendPrePostAccomodationAPIVO wsAppendPrePostAccomodationAPIVO;
    @XmlElement(name = "WSCancelBkgAPIVO")
    protected ComTropicsWebserviceITropicsVoLegacyOutputWSCancelBkgAPIVO wsCancelBkgAPIVO;
    @XmlElement(name = "WSCancelProductAPIVO")
    protected ComTropicsWebserviceITropicsVoLegacyOutputWSCancelProductAPIVO wsCancelProductAPIVO;
    @XmlElement(name = "WSCheckAccomAvailabiltyAndShowPricesAPIVO")
    protected ComTropicsWebserviceITropicsVoLegacyOutputWSCheckAccomAvailabiltyAndShowPricesAPIVO wsCheckAccomAvailabiltyAndShowPricesAPIVO;
    @XmlElement(name = "WSCheckForGapsOverlapsAPIVO")
    protected ComTropicsWebserviceITropicsVoLegacyOutputWSCheckForGapsOverlapsAPIVO wsCheckForGapsOverlapsAPIVO;
    @XmlElement(name = "WSCreateBookingAPIVO")
    protected ComTropicsWebserviceITropicsVoLegacyOutputWSCreateBookingAPIVO wsCreateBookingAPIVO;
    @XmlElement(name = "WSMVSearchPrePostAccomdationResAPIVO")
    protected ComTropicsWebserviceITropicsVoLegacyOutputWSMVSearchPrePostAccomdationResAPIVO wsmvSearchPrePostAccomdationResAPIVO;
    @XmlElement(name = "WSNonFITAirContractNotesVO", nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyProductsAirWSNonFITAirContractNotesVO> wsNonFITAirContractNotesVO;
    @XmlElement(name = "WSReceivePymtAPIVO")
    protected ComTropicsWebserviceITropicsVoLegacyOutputWSReceivePymtAPIVO wsReceivePymtAPIVO;
    @XmlElement(name = "WSSearchBookingsAPIVO")
    protected ComTropicsWebserviceITropicsVoLegacyOutputWSSearchBookingsAPIVO wsSearchBookingsAPIVO;
    protected ComTropicsWebserviceITropicsVoLegacyBookingWSAdminUserIDVO wsAdminUserIDVO;
    protected ComTropicsWebserviceITropicsVoLegacyOutputWSAirFareRuleAPIVO wsAirFareRuleAPIVO;
    protected ComTropicsWebserviceITropicsVoLegacyOutputWSAmendClientDetailsAPIVO wsAmendClientDetailsAPIVO;
    protected ComTropicsWebserviceITropicsVoLegacyOutputWSApplyEPDAPIVO wsApplyEPDAPIVO;
    protected ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSGetBookingDetailsVO wsGetBookingDetailsVO;
    protected ComTropicsWebserviceITropicsVoLegacyWSMarketVariationVO wsMarketVariationVO;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSPricePerClientVO> wsPricePerClientVOArray;
    protected ComTropicsWebserviceITropicsVoLegacyProductsCommonWSRstClientAssignmentVO wsRstClientAssignmentVO;
    protected ComTropicsWebserviceITropicsVoLegacyOutputWSSearchAirLowFareAPIVO wsSearchAirLowFareAPIVO;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyTamWSTAPaymentTypesVO> wsTAPaymentTypesVOArray;

    /**
     * Gets the value of the addTravelConsultantID property.
     * 
     */
    public long getAddTravelConsultantID() {
        return addTravelConsultantID;
    }

    /**
     * Sets the value of the addTravelConsultantID property.
     * 
     */
    public void setAddTravelConsultantID(long value) {
        this.addTravelConsultantID = value;
    }

    /**
     * Gets the value of the airSuccessful property.
     * 
     */
    public boolean isAirSuccessful() {
        return airSuccessful;
    }

    /**
     * Sets the value of the airSuccessful property.
     * 
     */
    public void setAirSuccessful(boolean value) {
        this.airSuccessful = value;
    }

    /**
     * Gets the value of the departureAvailabilityStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureAvailabilityStatus() {
        return departureAvailabilityStatus;
    }

    /**
     * Sets the value of the departureAvailabilityStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureAvailabilityStatus(String value) {
        this.departureAvailabilityStatus = value;
    }

    /**
     * Gets the value of the errorMessagesArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the errorMessagesArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErrorMessagesArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO> getErrorMessagesArray() {
        if (errorMessagesArray == null) {
            errorMessagesArray = new ArrayList<ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO>();
        }
        return this.errorMessagesArray;
    }

    /**
     * Gets the value of the flightAvailable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlightAvailable() {
        return flightAvailable;
    }

    /**
     * Sets the value of the flightAvailable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlightAvailable(String value) {
        this.flightAvailable = value;
    }

    /**
     * Gets the value of the iofSuccessful property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIofSuccessful() {
        return iofSuccessful;
    }

    /**
     * Sets the value of the iofSuccessful property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIofSuccessful(Boolean value) {
        this.iofSuccessful = value;
    }

    /**
     * Gets the value of the productNotes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductNotes() {
        return productNotes;
    }

    /**
     * Sets the value of the productNotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductNotes(String value) {
        this.productNotes = value;
    }

    /**
     * Gets the value of the successful property.
     * 
     */
    public boolean isSuccessful() {
        return successful;
    }

    /**
     * Sets the value of the successful property.
     * 
     */
    public void setSuccessful(boolean value) {
        this.successful = value;
    }

    /**
     * Gets the value of the wsAcceptReceiptAPIVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSAcceptReceiptAPIVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyOutputWSAcceptReceiptAPIVO getWSAcceptReceiptAPIVO() {
        return wsAcceptReceiptAPIVO;
    }

    /**
     * Sets the value of the wsAcceptReceiptAPIVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSAcceptReceiptAPIVO }
     *     
     */
    public void setWSAcceptReceiptAPIVO(ComTropicsWebserviceITropicsVoLegacyOutputWSAcceptReceiptAPIVO value) {
        this.wsAcceptReceiptAPIVO = value;
    }

    /**
     * Gets the value of the wsAppendInsuranceAPIVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSAppendInsuranceAPIVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyOutputWSAppendInsuranceAPIVO getWSAppendInsuranceAPIVO() {
        return wsAppendInsuranceAPIVO;
    }

    /**
     * Sets the value of the wsAppendInsuranceAPIVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSAppendInsuranceAPIVO }
     *     
     */
    public void setWSAppendInsuranceAPIVO(ComTropicsWebserviceITropicsVoLegacyOutputWSAppendInsuranceAPIVO value) {
        this.wsAppendInsuranceAPIVO = value;
    }

    /**
     * Gets the value of the wsAppendMiscellaneousAPIVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSAppendMiscellaneousAPIVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyOutputWSAppendMiscellaneousAPIVO getWSAppendMiscellaneousAPIVO() {
        return wsAppendMiscellaneousAPIVO;
    }

    /**
     * Sets the value of the wsAppendMiscellaneousAPIVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSAppendMiscellaneousAPIVO }
     *     
     */
    public void setWSAppendMiscellaneousAPIVO(ComTropicsWebserviceITropicsVoLegacyOutputWSAppendMiscellaneousAPIVO value) {
        this.wsAppendMiscellaneousAPIVO = value;
    }

    /**
     * Gets the value of the wsAppendPrePostAccomodationAPIVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSAppendPrePostAccomodationAPIVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyOutputWSAppendPrePostAccomodationAPIVO getWSAppendPrePostAccomodationAPIVO() {
        return wsAppendPrePostAccomodationAPIVO;
    }

    /**
     * Sets the value of the wsAppendPrePostAccomodationAPIVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSAppendPrePostAccomodationAPIVO }
     *     
     */
    public void setWSAppendPrePostAccomodationAPIVO(ComTropicsWebserviceITropicsVoLegacyOutputWSAppendPrePostAccomodationAPIVO value) {
        this.wsAppendPrePostAccomodationAPIVO = value;
    }

    /**
     * Gets the value of the wsCancelBkgAPIVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSCancelBkgAPIVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyOutputWSCancelBkgAPIVO getWSCancelBkgAPIVO() {
        return wsCancelBkgAPIVO;
    }

    /**
     * Sets the value of the wsCancelBkgAPIVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSCancelBkgAPIVO }
     *     
     */
    public void setWSCancelBkgAPIVO(ComTropicsWebserviceITropicsVoLegacyOutputWSCancelBkgAPIVO value) {
        this.wsCancelBkgAPIVO = value;
    }

    /**
     * Gets the value of the wsCancelProductAPIVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSCancelProductAPIVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyOutputWSCancelProductAPIVO getWSCancelProductAPIVO() {
        return wsCancelProductAPIVO;
    }

    /**
     * Sets the value of the wsCancelProductAPIVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSCancelProductAPIVO }
     *     
     */
    public void setWSCancelProductAPIVO(ComTropicsWebserviceITropicsVoLegacyOutputWSCancelProductAPIVO value) {
        this.wsCancelProductAPIVO = value;
    }

    /**
     * Gets the value of the wsCheckAccomAvailabiltyAndShowPricesAPIVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSCheckAccomAvailabiltyAndShowPricesAPIVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyOutputWSCheckAccomAvailabiltyAndShowPricesAPIVO getWSCheckAccomAvailabiltyAndShowPricesAPIVO() {
        return wsCheckAccomAvailabiltyAndShowPricesAPIVO;
    }

    /**
     * Sets the value of the wsCheckAccomAvailabiltyAndShowPricesAPIVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSCheckAccomAvailabiltyAndShowPricesAPIVO }
     *     
     */
    public void setWSCheckAccomAvailabiltyAndShowPricesAPIVO(ComTropicsWebserviceITropicsVoLegacyOutputWSCheckAccomAvailabiltyAndShowPricesAPIVO value) {
        this.wsCheckAccomAvailabiltyAndShowPricesAPIVO = value;
    }

    /**
     * Gets the value of the wsCheckForGapsOverlapsAPIVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSCheckForGapsOverlapsAPIVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyOutputWSCheckForGapsOverlapsAPIVO getWSCheckForGapsOverlapsAPIVO() {
        return wsCheckForGapsOverlapsAPIVO;
    }

    /**
     * Sets the value of the wsCheckForGapsOverlapsAPIVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSCheckForGapsOverlapsAPIVO }
     *     
     */
    public void setWSCheckForGapsOverlapsAPIVO(ComTropicsWebserviceITropicsVoLegacyOutputWSCheckForGapsOverlapsAPIVO value) {
        this.wsCheckForGapsOverlapsAPIVO = value;
    }

    /**
     * Gets the value of the wsCreateBookingAPIVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSCreateBookingAPIVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyOutputWSCreateBookingAPIVO getWSCreateBookingAPIVO() {
        return wsCreateBookingAPIVO;
    }

    /**
     * Sets the value of the wsCreateBookingAPIVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSCreateBookingAPIVO }
     *     
     */
    public void setWSCreateBookingAPIVO(ComTropicsWebserviceITropicsVoLegacyOutputWSCreateBookingAPIVO value) {
        this.wsCreateBookingAPIVO = value;
    }

    /**
     * Gets the value of the wsmvSearchPrePostAccomdationResAPIVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSMVSearchPrePostAccomdationResAPIVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyOutputWSMVSearchPrePostAccomdationResAPIVO getWSMVSearchPrePostAccomdationResAPIVO() {
        return wsmvSearchPrePostAccomdationResAPIVO;
    }

    /**
     * Sets the value of the wsmvSearchPrePostAccomdationResAPIVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSMVSearchPrePostAccomdationResAPIVO }
     *     
     */
    public void setWSMVSearchPrePostAccomdationResAPIVO(ComTropicsWebserviceITropicsVoLegacyOutputWSMVSearchPrePostAccomdationResAPIVO value) {
        this.wsmvSearchPrePostAccomdationResAPIVO = value;
    }

    /**
     * Gets the value of the wsNonFITAirContractNotesVO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsNonFITAirContractNotesVO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSNonFITAirContractNotesVO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyProductsAirWSNonFITAirContractNotesVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyProductsAirWSNonFITAirContractNotesVO> getWSNonFITAirContractNotesVO() {
        if (wsNonFITAirContractNotesVO == null) {
            wsNonFITAirContractNotesVO = new ArrayList<ComTropicsWebserviceITropicsVoLegacyProductsAirWSNonFITAirContractNotesVO>();
        }
        return this.wsNonFITAirContractNotesVO;
    }

    /**
     * Gets the value of the wsReceivePymtAPIVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSReceivePymtAPIVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyOutputWSReceivePymtAPIVO getWSReceivePymtAPIVO() {
        return wsReceivePymtAPIVO;
    }

    /**
     * Sets the value of the wsReceivePymtAPIVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSReceivePymtAPIVO }
     *     
     */
    public void setWSReceivePymtAPIVO(ComTropicsWebserviceITropicsVoLegacyOutputWSReceivePymtAPIVO value) {
        this.wsReceivePymtAPIVO = value;
    }

    /**
     * Gets the value of the wsSearchBookingsAPIVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSSearchBookingsAPIVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyOutputWSSearchBookingsAPIVO getWSSearchBookingsAPIVO() {
        return wsSearchBookingsAPIVO;
    }

    /**
     * Sets the value of the wsSearchBookingsAPIVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSSearchBookingsAPIVO }
     *     
     */
    public void setWSSearchBookingsAPIVO(ComTropicsWebserviceITropicsVoLegacyOutputWSSearchBookingsAPIVO value) {
        this.wsSearchBookingsAPIVO = value;
    }

    /**
     * Gets the value of the wsAdminUserIDVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingWSAdminUserIDVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyBookingWSAdminUserIDVO getWsAdminUserIDVO() {
        return wsAdminUserIDVO;
    }

    /**
     * Sets the value of the wsAdminUserIDVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingWSAdminUserIDVO }
     *     
     */
    public void setWsAdminUserIDVO(ComTropicsWebserviceITropicsVoLegacyBookingWSAdminUserIDVO value) {
        this.wsAdminUserIDVO = value;
    }

    /**
     * Gets the value of the wsAirFareRuleAPIVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSAirFareRuleAPIVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyOutputWSAirFareRuleAPIVO getWsAirFareRuleAPIVO() {
        return wsAirFareRuleAPIVO;
    }

    /**
     * Sets the value of the wsAirFareRuleAPIVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSAirFareRuleAPIVO }
     *     
     */
    public void setWsAirFareRuleAPIVO(ComTropicsWebserviceITropicsVoLegacyOutputWSAirFareRuleAPIVO value) {
        this.wsAirFareRuleAPIVO = value;
    }

    /**
     * Gets the value of the wsAmendClientDetailsAPIVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSAmendClientDetailsAPIVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyOutputWSAmendClientDetailsAPIVO getWsAmendClientDetailsAPIVO() {
        return wsAmendClientDetailsAPIVO;
    }

    /**
     * Sets the value of the wsAmendClientDetailsAPIVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSAmendClientDetailsAPIVO }
     *     
     */
    public void setWsAmendClientDetailsAPIVO(ComTropicsWebserviceITropicsVoLegacyOutputWSAmendClientDetailsAPIVO value) {
        this.wsAmendClientDetailsAPIVO = value;
    }

    /**
     * Gets the value of the wsApplyEPDAPIVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSApplyEPDAPIVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyOutputWSApplyEPDAPIVO getWsApplyEPDAPIVO() {
        return wsApplyEPDAPIVO;
    }

    /**
     * Sets the value of the wsApplyEPDAPIVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSApplyEPDAPIVO }
     *     
     */
    public void setWsApplyEPDAPIVO(ComTropicsWebserviceITropicsVoLegacyOutputWSApplyEPDAPIVO value) {
        this.wsApplyEPDAPIVO = value;
    }

    /**
     * Gets the value of the wsGetBookingDetailsVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSGetBookingDetailsVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSGetBookingDetailsVO getWsGetBookingDetailsVO() {
        return wsGetBookingDetailsVO;
    }

    /**
     * Sets the value of the wsGetBookingDetailsVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSGetBookingDetailsVO }
     *     
     */
    public void setWsGetBookingDetailsVO(ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSGetBookingDetailsVO value) {
        this.wsGetBookingDetailsVO = value;
    }

    /**
     * Gets the value of the wsMarketVariationVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyWSMarketVariationVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyWSMarketVariationVO getWsMarketVariationVO() {
        return wsMarketVariationVO;
    }

    /**
     * Sets the value of the wsMarketVariationVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyWSMarketVariationVO }
     *     
     */
    public void setWsMarketVariationVO(ComTropicsWebserviceITropicsVoLegacyWSMarketVariationVO value) {
        this.wsMarketVariationVO = value;
    }

    /**
     * Gets the value of the wsPricePerClientVOArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsPricePerClientVOArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsPricePerClientVOArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSPricePerClientVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSPricePerClientVO> getWsPricePerClientVOArray() {
        if (wsPricePerClientVOArray == null) {
            wsPricePerClientVOArray = new ArrayList<ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSPricePerClientVO>();
        }
        return this.wsPricePerClientVOArray;
    }

    /**
     * Gets the value of the wsRstClientAssignmentVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyProductsCommonWSRstClientAssignmentVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyProductsCommonWSRstClientAssignmentVO getWsRstClientAssignmentVO() {
        return wsRstClientAssignmentVO;
    }

    /**
     * Sets the value of the wsRstClientAssignmentVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyProductsCommonWSRstClientAssignmentVO }
     *     
     */
    public void setWsRstClientAssignmentVO(ComTropicsWebserviceITropicsVoLegacyProductsCommonWSRstClientAssignmentVO value) {
        this.wsRstClientAssignmentVO = value;
    }

    /**
     * Gets the value of the wsSearchAirLowFareAPIVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSSearchAirLowFareAPIVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyOutputWSSearchAirLowFareAPIVO getWsSearchAirLowFareAPIVO() {
        return wsSearchAirLowFareAPIVO;
    }

    /**
     * Sets the value of the wsSearchAirLowFareAPIVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyOutputWSSearchAirLowFareAPIVO }
     *     
     */
    public void setWsSearchAirLowFareAPIVO(ComTropicsWebserviceITropicsVoLegacyOutputWSSearchAirLowFareAPIVO value) {
        this.wsSearchAirLowFareAPIVO = value;
    }

    /**
     * Gets the value of the wsTAPaymentTypesVOArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsTAPaymentTypesVOArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsTAPaymentTypesVOArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyTamWSTAPaymentTypesVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyTamWSTAPaymentTypesVO> getWsTAPaymentTypesVOArray() {
        if (wsTAPaymentTypesVOArray == null) {
            wsTAPaymentTypesVOArray = new ArrayList<ComTropicsWebserviceITropicsVoLegacyTamWSTAPaymentTypesVO>();
        }
        return this.wsTAPaymentTypesVOArray;
    }

}
