
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_air_booking_WSPTCFareInfoVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_air_booking_WSPTCFareInfoVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="airFuelTaxVOArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_air_booking_WSAirTaxVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="airTaxVOArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_air_booking_WSAirTaxVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="baseFareAmount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="calculatedBaseFareAmount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="fareBasisCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="markupID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paxTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paxTypeCodeRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="seqmentFareBasisVOArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_air_booking_WSSeqmentFareBasisVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="totalAirTax" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalCalculatedFareAmount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalFareAmount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_air_booking_WSPTCFareInfoVO", propOrder = {
    "airFuelTaxVOArray",
    "airTaxVOArray",
    "baseFareAmount",
    "calculatedBaseFareAmount",
    "fareBasisCode",
    "markupID",
    "paxTypeCode",
    "paxTypeCodeRef",
    "seqmentFareBasisVOArray",
    "totalAirTax",
    "totalCalculatedFareAmount",
    "totalFareAmount"
})
public class ComTropicsWebserviceITropicsVoLegacyAirBookingWSPTCFareInfoVO {

    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirTaxVO> airFuelTaxVOArray;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirTaxVO> airTaxVOArray;
    protected float baseFareAmount;
    protected float calculatedBaseFareAmount;
    protected String fareBasisCode;
    protected String markupID;
    protected String paxTypeCode;
    protected String paxTypeCodeRef;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyAirBookingWSSeqmentFareBasisVO> seqmentFareBasisVOArray;
    protected float totalAirTax;
    protected float totalCalculatedFareAmount;
    protected float totalFareAmount;

    /**
     * Gets the value of the airFuelTaxVOArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the airFuelTaxVOArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAirFuelTaxVOArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirTaxVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirTaxVO> getAirFuelTaxVOArray() {
        if (airFuelTaxVOArray == null) {
            airFuelTaxVOArray = new ArrayList<ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirTaxVO>();
        }
        return this.airFuelTaxVOArray;
    }

    /**
     * Gets the value of the airTaxVOArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the airTaxVOArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAirTaxVOArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirTaxVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirTaxVO> getAirTaxVOArray() {
        if (airTaxVOArray == null) {
            airTaxVOArray = new ArrayList<ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirTaxVO>();
        }
        return this.airTaxVOArray;
    }

    /**
     * Gets the value of the baseFareAmount property.
     * 
     */
    public float getBaseFareAmount() {
        return baseFareAmount;
    }

    /**
     * Sets the value of the baseFareAmount property.
     * 
     */
    public void setBaseFareAmount(float value) {
        this.baseFareAmount = value;
    }

    /**
     * Gets the value of the calculatedBaseFareAmount property.
     * 
     */
    public float getCalculatedBaseFareAmount() {
        return calculatedBaseFareAmount;
    }

    /**
     * Sets the value of the calculatedBaseFareAmount property.
     * 
     */
    public void setCalculatedBaseFareAmount(float value) {
        this.calculatedBaseFareAmount = value;
    }

    /**
     * Gets the value of the fareBasisCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFareBasisCode() {
        return fareBasisCode;
    }

    /**
     * Sets the value of the fareBasisCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFareBasisCode(String value) {
        this.fareBasisCode = value;
    }

    /**
     * Gets the value of the markupID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarkupID() {
        return markupID;
    }

    /**
     * Sets the value of the markupID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarkupID(String value) {
        this.markupID = value;
    }

    /**
     * Gets the value of the paxTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaxTypeCode() {
        return paxTypeCode;
    }

    /**
     * Sets the value of the paxTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaxTypeCode(String value) {
        this.paxTypeCode = value;
    }

    /**
     * Gets the value of the paxTypeCodeRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaxTypeCodeRef() {
        return paxTypeCodeRef;
    }

    /**
     * Sets the value of the paxTypeCodeRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaxTypeCodeRef(String value) {
        this.paxTypeCodeRef = value;
    }

    /**
     * Gets the value of the seqmentFareBasisVOArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the seqmentFareBasisVOArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSeqmentFareBasisVOArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyAirBookingWSSeqmentFareBasisVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyAirBookingWSSeqmentFareBasisVO> getSeqmentFareBasisVOArray() {
        if (seqmentFareBasisVOArray == null) {
            seqmentFareBasisVOArray = new ArrayList<ComTropicsWebserviceITropicsVoLegacyAirBookingWSSeqmentFareBasisVO>();
        }
        return this.seqmentFareBasisVOArray;
    }

    /**
     * Gets the value of the totalAirTax property.
     * 
     */
    public float getTotalAirTax() {
        return totalAirTax;
    }

    /**
     * Sets the value of the totalAirTax property.
     * 
     */
    public void setTotalAirTax(float value) {
        this.totalAirTax = value;
    }

    /**
     * Gets the value of the totalCalculatedFareAmount property.
     * 
     */
    public float getTotalCalculatedFareAmount() {
        return totalCalculatedFareAmount;
    }

    /**
     * Sets the value of the totalCalculatedFareAmount property.
     * 
     */
    public void setTotalCalculatedFareAmount(float value) {
        this.totalCalculatedFareAmount = value;
    }

    /**
     * Gets the value of the totalFareAmount property.
     * 
     */
    public float getTotalFareAmount() {
        return totalFareAmount;
    }

    /**
     * Sets the value of the totalFareAmount property.
     * 
     */
    public void setTotalFareAmount(float value) {
        this.totalFareAmount = value;
    }

}
