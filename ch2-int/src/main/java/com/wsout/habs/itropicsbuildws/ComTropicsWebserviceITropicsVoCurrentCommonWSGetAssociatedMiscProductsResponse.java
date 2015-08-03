
package com.wsout.habs.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_current_common_WSGetAssociatedMiscProductsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_current_common_WSGetAssociatedMiscProductsResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_current_common_WSBaseResponseHeader">
 *       &lt;sequence>
 *         &lt;element name="successful" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="wsGetAssociatedMiscProductsResponseDetailVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_current_common_WSGetAssociatedMiscProductsResponseDetail" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_current_common_WSGetAssociatedMiscProductsResponse", propOrder = {
    "successful",
    "wsGetAssociatedMiscProductsResponseDetailVO"
})
public class ComTropicsWebserviceITropicsVoCurrentCommonWSGetAssociatedMiscProductsResponse
    extends ComTropicsWebserviceITropicsVoCurrentCommonWSBaseResponseHeader
{

    protected boolean successful;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoCurrentCommonWSGetAssociatedMiscProductsResponseDetail> wsGetAssociatedMiscProductsResponseDetailVO;

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
     * Gets the value of the wsGetAssociatedMiscProductsResponseDetailVO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsGetAssociatedMiscProductsResponseDetailVO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsGetAssociatedMiscProductsResponseDetailVO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoCurrentCommonWSGetAssociatedMiscProductsResponseDetail }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoCurrentCommonWSGetAssociatedMiscProductsResponseDetail> getWsGetAssociatedMiscProductsResponseDetailVO() {
        if (wsGetAssociatedMiscProductsResponseDetailVO == null) {
            wsGetAssociatedMiscProductsResponseDetailVO = new ArrayList<ComTropicsWebserviceITropicsVoCurrentCommonWSGetAssociatedMiscProductsResponseDetail>();
        }
        return this.wsGetAssociatedMiscProductsResponseDetailVO;
    }

}
