
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_output_WSAppendPrePostAccomodationAPIVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_output_WSAppendPrePostAccomodationAPIVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="baseBookingLineItemID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="errorMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errorMessagesArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_current_common_WSErrorMessagesVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="prePostAccomFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="successful" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="wsRstClientAssignmentVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_products_common_WSRstClientAssignmentVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_output_WSAppendPrePostAccomodationAPIVO", propOrder = {
    "baseBookingLineItemID",
    "errorMessage",
    "errorMessagesArray",
    "prePostAccomFlag",
    "successful",
    "wsRstClientAssignmentVO"
})
public class ComTropicsWebserviceITropicsVoLegacyOutputWSAppendPrePostAccomodationAPIVO {

    protected long baseBookingLineItemID;
    protected String errorMessage;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO> errorMessagesArray;
    protected boolean prePostAccomFlag;
    protected boolean successful;
    protected ComTropicsWebserviceITropicsVoLegacyProductsCommonWSRstClientAssignmentVO wsRstClientAssignmentVO;

    /**
     * Gets the value of the baseBookingLineItemID property.
     * 
     */
    public long getBaseBookingLineItemID() {
        return baseBookingLineItemID;
    }

    /**
     * Sets the value of the baseBookingLineItemID property.
     * 
     */
    public void setBaseBookingLineItemID(long value) {
        this.baseBookingLineItemID = value;
    }

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
     * Gets the value of the prePostAccomFlag property.
     * 
     */
    public boolean isPrePostAccomFlag() {
        return prePostAccomFlag;
    }

    /**
     * Sets the value of the prePostAccomFlag property.
     * 
     */
    public void setPrePostAccomFlag(boolean value) {
        this.prePostAccomFlag = value;
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

}
