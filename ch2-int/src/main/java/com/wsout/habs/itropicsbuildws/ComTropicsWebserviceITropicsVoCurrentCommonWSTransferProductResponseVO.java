
package com.wsout.habs.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_current_common_WSTransferProductResponseVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_current_common_WSTransferProductResponseVO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_current_common_WSBaseResponseHeader">
 *       &lt;sequence>
 *         &lt;element name="arrivalSuccessful" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="departureSuccessful" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="transferProductDetailsVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_current_common_WSTransferProductDetailsVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_current_common_WSTransferProductResponseVO", propOrder = {
    "arrivalSuccessful",
    "departureSuccessful",
    "transferProductDetailsVO"
})
public class ComTropicsWebserviceITropicsVoCurrentCommonWSTransferProductResponseVO
    extends ComTropicsWebserviceITropicsVoCurrentCommonWSBaseResponseHeader
{

    protected boolean arrivalSuccessful;
    protected boolean departureSuccessful;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoCurrentCommonWSTransferProductDetailsVO> transferProductDetailsVO;

    /**
     * Gets the value of the arrivalSuccessful property.
     * 
     */
    public boolean isArrivalSuccessful() {
        return arrivalSuccessful;
    }

    /**
     * Sets the value of the arrivalSuccessful property.
     * 
     */
    public void setArrivalSuccessful(boolean value) {
        this.arrivalSuccessful = value;
    }

    /**
     * Gets the value of the departureSuccessful property.
     * 
     */
    public boolean isDepartureSuccessful() {
        return departureSuccessful;
    }

    /**
     * Sets the value of the departureSuccessful property.
     * 
     */
    public void setDepartureSuccessful(boolean value) {
        this.departureSuccessful = value;
    }

    /**
     * Gets the value of the transferProductDetailsVO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transferProductDetailsVO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransferProductDetailsVO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoCurrentCommonWSTransferProductDetailsVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoCurrentCommonWSTransferProductDetailsVO> getTransferProductDetailsVO() {
        if (transferProductDetailsVO == null) {
            transferProductDetailsVO = new ArrayList<ComTropicsWebserviceITropicsVoCurrentCommonWSTransferProductDetailsVO>();
        }
        return this.transferProductDetailsVO;
    }

}
