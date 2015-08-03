
package com.wsout.habs.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsProdDepartureEPDDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsProdDepartureEPDDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="departureCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsEPDDetailsVOArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_current_epd_tour_WSEPDisountDetailsVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsProdDepartureEPDDetails", propOrder = {
    "departureCode",
    "wsEPDDetailsVOArray"
})
public class WsProdDepartureEPDDetails {

    protected String departureCode;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoCurrentEpdTourWSEPDisountDetailsVO> wsEPDDetailsVOArray;

    /**
     * Gets the value of the departureCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureCode() {
        return departureCode;
    }

    /**
     * Sets the value of the departureCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureCode(String value) {
        this.departureCode = value;
    }

    /**
     * Gets the value of the wsEPDDetailsVOArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsEPDDetailsVOArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsEPDDetailsVOArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoCurrentEpdTourWSEPDisountDetailsVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoCurrentEpdTourWSEPDisountDetailsVO> getWsEPDDetailsVOArray() {
        if (wsEPDDetailsVOArray == null) {
            wsEPDDetailsVOArray = new ArrayList<ComTropicsWebserviceITropicsVoCurrentEpdTourWSEPDisountDetailsVO>();
        }
        return this.wsEPDDetailsVOArray;
    }

}
