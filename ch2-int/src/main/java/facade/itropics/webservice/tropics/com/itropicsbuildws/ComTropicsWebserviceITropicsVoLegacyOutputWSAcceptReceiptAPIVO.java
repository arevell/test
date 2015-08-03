
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_output_WSAcceptReceiptAPIVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_output_WSAcceptReceiptAPIVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="errorMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errorMessagesArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_current_common_WSErrorMessagesVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="is3DSecureRequest" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="successful" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="wsApplyEpdTropicsMessage" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_WSTropicsMessage" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wsBookingSummaryVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_WSBookingSummaryVO" minOccurs="0"/>
 *         &lt;element name="wsSecurityInfoResponseVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_finance_WSSecurityInfoResponseVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_output_WSAcceptReceiptAPIVO", propOrder = {
    "errorMessage",
    "errorMessagesArray",
    "is3DSecureRequest",
    "successful",
    "wsApplyEpdTropicsMessage",
    "wsBookingSummaryVO",
    "wsSecurityInfoResponseVO"
})
public class ComTropicsWebserviceITropicsVoLegacyOutputWSAcceptReceiptAPIVO {

    protected String errorMessage;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO> errorMessagesArray;
    protected boolean is3DSecureRequest;
    protected boolean successful;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyWSTropicsMessage> wsApplyEpdTropicsMessage;
    protected ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSBookingSummaryVO wsBookingSummaryVO;
    protected ComTropicsWebserviceITropicsVoLegacyFinanceWSSecurityInfoResponseVO wsSecurityInfoResponseVO;

    /**
     * Gets the value of the errorMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the value of the errorMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorMessage(String value) {
        this.errorMessage = value;
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
     * Gets the value of the is3DSecureRequest property.
     * 
     */
    public boolean isIs3DSecureRequest() {
        return is3DSecureRequest;
    }

    /**
     * Sets the value of the is3DSecureRequest property.
     * 
     */
    public void setIs3DSecureRequest(boolean value) {
        this.is3DSecureRequest = value;
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
     * Gets the value of the wsApplyEpdTropicsMessage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsApplyEpdTropicsMessage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsApplyEpdTropicsMessage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyWSTropicsMessage }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyWSTropicsMessage> getWsApplyEpdTropicsMessage() {
        if (wsApplyEpdTropicsMessage == null) {
            wsApplyEpdTropicsMessage = new ArrayList<ComTropicsWebserviceITropicsVoLegacyWSTropicsMessage>();
        }
        return this.wsApplyEpdTropicsMessage;
    }

    /**
     * Gets the value of the wsBookingSummaryVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSBookingSummaryVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSBookingSummaryVO getWsBookingSummaryVO() {
        return wsBookingSummaryVO;
    }

    /**
     * Sets the value of the wsBookingSummaryVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSBookingSummaryVO }
     *     
     */
    public void setWsBookingSummaryVO(ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSBookingSummaryVO value) {
        this.wsBookingSummaryVO = value;
    }

    /**
     * Gets the value of the wsSecurityInfoResponseVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyFinanceWSSecurityInfoResponseVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyFinanceWSSecurityInfoResponseVO getWsSecurityInfoResponseVO() {
        return wsSecurityInfoResponseVO;
    }

    /**
     * Sets the value of the wsSecurityInfoResponseVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyFinanceWSSecurityInfoResponseVO }
     *     
     */
    public void setWsSecurityInfoResponseVO(ComTropicsWebserviceITropicsVoLegacyFinanceWSSecurityInfoResponseVO value) {
        this.wsSecurityInfoResponseVO = value;
    }

}
