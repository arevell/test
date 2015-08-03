
package com.wsout.habs.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsDeparturesVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsDeparturesVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Departure" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsDepartureVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="errorMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errorMessagesArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_current_common_WSErrorMessagesVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="successful" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsDeparturesVO", propOrder = {
    "departure",
    "errorMessage",
    "errorMessagesArray",
    "successful"
})
public class WsDeparturesVO {

    @XmlElement(name = "Departure")
    protected List<WsDepartureVO> departure;
    protected String errorMessage;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO> errorMessagesArray;
    protected boolean successful;

    /**
     * Gets the value of the departure property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the departure property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeparture().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WsDepartureVO }
     * 
     * 
     */
    public List<WsDepartureVO> getDeparture() {
        if (departure == null) {
            departure = new ArrayList<WsDepartureVO>();
        }
        return this.departure;
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

}
