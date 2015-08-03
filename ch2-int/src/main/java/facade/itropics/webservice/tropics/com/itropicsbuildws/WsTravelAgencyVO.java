
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsTravelAgencyVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsTravelAgencyVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="agencyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iataCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="travelAgencyAddress" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_commonvo_WSAddressVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsTravelAgencyVO", propOrder = {
    "agencyName",
    "iataCode",
    "taCode",
    "travelAgencyAddress"
})
public class WsTravelAgencyVO {

    protected String agencyName;
    protected String iataCode;
    protected String taCode;
    protected ComTropicsWebserviceITropicsVoLegacyCommonvoWSAddressVO travelAgencyAddress;

    /**
     * Gets the value of the agencyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgencyName() {
        return agencyName;
    }

    /**
     * Sets the value of the agencyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgencyName(String value) {
        this.agencyName = value;
    }

    /**
     * Gets the value of the iataCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIataCode() {
        return iataCode;
    }

    /**
     * Sets the value of the iataCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIataCode(String value) {
        this.iataCode = value;
    }

    /**
     * Gets the value of the taCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaCode() {
        return taCode;
    }

    /**
     * Sets the value of the taCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaCode(String value) {
        this.taCode = value;
    }

    /**
     * Gets the value of the travelAgencyAddress property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyCommonvoWSAddressVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyCommonvoWSAddressVO getTravelAgencyAddress() {
        return travelAgencyAddress;
    }

    /**
     * Sets the value of the travelAgencyAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyCommonvoWSAddressVO }
     *     
     */
    public void setTravelAgencyAddress(ComTropicsWebserviceITropicsVoLegacyCommonvoWSAddressVO value) {
        this.travelAgencyAddress = value;
    }

}
