
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_current_products_misctransfer_WSInFlightTransfersVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_current_products_misctransfer_WSInFlightTransfersVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accommodation" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_current_products_misctransfer_WSInAccommodation" minOccurs="0"/>
 *         &lt;element name="adminUserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="airBookedByTropicsFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="iofFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="marketVariationTourPackage" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_current_products_misctransfer_WSMarketVariationTourPackage" minOccurs="0"/>
 *         &lt;element name="sellingCompanyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WSInFlightSegments" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_current_products_misctransfer_WSInFlightSegment" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_current_products_misctransfer_WSInFlightTransfersVO", propOrder = {
    "accommodation",
    "adminUserId",
    "airBookedByTropicsFlag",
    "iofFlag",
    "marketVariationTourPackage",
    "sellingCompanyCode",
    "wsInFlightSegments"
})
public class ComTropicsWebserviceITropicsVoCurrentProductsMisctransferWSInFlightTransfersVO {

    protected ComTropicsWebserviceITropicsVoCurrentProductsMisctransferWSInAccommodation accommodation;
    protected String adminUserId;
    protected boolean airBookedByTropicsFlag;
    protected boolean iofFlag;
    protected ComTropicsWebserviceITropicsVoCurrentProductsMisctransferWSMarketVariationTourPackage marketVariationTourPackage;
    protected String sellingCompanyCode;
    @XmlElement(name = "WSInFlightSegments", nillable = true)
    protected List<ComTropicsWebserviceITropicsVoCurrentProductsMisctransferWSInFlightSegment> wsInFlightSegments;

    /**
     * Gets the value of the accommodation property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoCurrentProductsMisctransferWSInAccommodation }
     *     
     */
    public ComTropicsWebserviceITropicsVoCurrentProductsMisctransferWSInAccommodation getAccommodation() {
        return accommodation;
    }

    /**
     * Sets the value of the accommodation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoCurrentProductsMisctransferWSInAccommodation }
     *     
     */
    public void setAccommodation(ComTropicsWebserviceITropicsVoCurrentProductsMisctransferWSInAccommodation value) {
        this.accommodation = value;
    }

    /**
     * Gets the value of the adminUserId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdminUserId() {
        return adminUserId;
    }

    /**
     * Sets the value of the adminUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdminUserId(String value) {
        this.adminUserId = value;
    }

    /**
     * Gets the value of the airBookedByTropicsFlag property.
     * 
     */
    public boolean isAirBookedByTropicsFlag() {
        return airBookedByTropicsFlag;
    }

    /**
     * Sets the value of the airBookedByTropicsFlag property.
     * 
     */
    public void setAirBookedByTropicsFlag(boolean value) {
        this.airBookedByTropicsFlag = value;
    }

    /**
     * Gets the value of the iofFlag property.
     * 
     */
    public boolean isIofFlag() {
        return iofFlag;
    }

    /**
     * Sets the value of the iofFlag property.
     * 
     */
    public void setIofFlag(boolean value) {
        this.iofFlag = value;
    }

    /**
     * Gets the value of the marketVariationTourPackage property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoCurrentProductsMisctransferWSMarketVariationTourPackage }
     *     
     */
    public ComTropicsWebserviceITropicsVoCurrentProductsMisctransferWSMarketVariationTourPackage getMarketVariationTourPackage() {
        return marketVariationTourPackage;
    }

    /**
     * Sets the value of the marketVariationTourPackage property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoCurrentProductsMisctransferWSMarketVariationTourPackage }
     *     
     */
    public void setMarketVariationTourPackage(ComTropicsWebserviceITropicsVoCurrentProductsMisctransferWSMarketVariationTourPackage value) {
        this.marketVariationTourPackage = value;
    }

    /**
     * Gets the value of the sellingCompanyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellingCompanyCode() {
        return sellingCompanyCode;
    }

    /**
     * Sets the value of the sellingCompanyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellingCompanyCode(String value) {
        this.sellingCompanyCode = value;
    }

    /**
     * Gets the value of the wsInFlightSegments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsInFlightSegments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSInFlightSegments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoCurrentProductsMisctransferWSInFlightSegment }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoCurrentProductsMisctransferWSInFlightSegment> getWSInFlightSegments() {
        if (wsInFlightSegments == null) {
            wsInFlightSegments = new ArrayList<ComTropicsWebserviceITropicsVoCurrentProductsMisctransferWSInFlightSegment>();
        }
        return this.wsInFlightSegments;
    }

}
