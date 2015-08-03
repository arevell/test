
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_output_WSMVSearchPrePostAccomdationResAPIVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_output_WSMVSearchPrePostAccomdationResAPIVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="errorMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errorMessagesArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_current_common_WSErrorMessagesVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="successful" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="WSMVResrAssociatedProductsVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_products_mv_WSMVResrAssociatedProductsVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="warningAdditionalText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="warningMsgCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_output_WSMVSearchPrePostAccomdationResAPIVO", propOrder = {
    "errorMessage",
    "errorMessagesArray",
    "successful",
    "wsmvResrAssociatedProductsVO",
    "warningAdditionalText",
    "warningMsgCode"
})
public class ComTropicsWebserviceITropicsVoLegacyOutputWSMVSearchPrePostAccomdationResAPIVO {

    protected String errorMessage;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO> errorMessagesArray;
    protected boolean successful;
    @XmlElement(name = "WSMVResrAssociatedProductsVO", nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyProductsMvWSMVResrAssociatedProductsVO> wsmvResrAssociatedProductsVO;
    protected String warningAdditionalText;
    protected String warningMsgCode;

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
     * Gets the value of the wsmvResrAssociatedProductsVO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsmvResrAssociatedProductsVO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSMVResrAssociatedProductsVO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyProductsMvWSMVResrAssociatedProductsVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyProductsMvWSMVResrAssociatedProductsVO> getWSMVResrAssociatedProductsVO() {
        if (wsmvResrAssociatedProductsVO == null) {
            wsmvResrAssociatedProductsVO = new ArrayList<ComTropicsWebserviceITropicsVoLegacyProductsMvWSMVResrAssociatedProductsVO>();
        }
        return this.wsmvResrAssociatedProductsVO;
    }

    /**
     * Gets the value of the warningAdditionalText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWarningAdditionalText() {
        return warningAdditionalText;
    }

    /**
     * Sets the value of the warningAdditionalText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWarningAdditionalText(String value) {
        this.warningAdditionalText = value;
    }

    /**
     * Gets the value of the warningMsgCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWarningMsgCode() {
        return warningMsgCode;
    }

    /**
     * Sets the value of the warningMsgCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWarningMsgCode(String value) {
        this.warningMsgCode = value;
    }

}
