
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for wsDepartureArrayVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsDepartureArrayVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="adultPortTax" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="adultQuadRoomPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="adultTripleRoomPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="adultTwinRoomPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="adultsingleRoomPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="childPortTax" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="childPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="childQuadRoomPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="childSingleRoomPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="childTripleRoomPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="childTwinRoomPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="combinedAdultQuadRoomPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="combinedAdultSingleRoomPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="combinedAdultTripleRoomPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="combinedAdultTwinRoomPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="combinedChildQuadRoomPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="combinedChildSingleRoomPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="combinedChildTripleRoomPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="combinedChildTwinRoomPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="departureCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departureStatus" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}departureStatus" minOccurs="0"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="epdDetails" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_current_epd_tour_WSEPDisountDetailsVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="foodFundPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="guaranteeStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="guaranteedstatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hasEPD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="landonlyReduction" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="notes" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsiProductNoteVO" minOccurs="0"/>
 *         &lt;element name="priceIndicative" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="teenagerDiscount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsDepartureArrayVO", propOrder = {
    "adultPortTax",
    "adultQuadRoomPrice",
    "adultTripleRoomPrice",
    "adultTwinRoomPrice",
    "adultsingleRoomPrice",
    "childPortTax",
    "childPrice",
    "childQuadRoomPrice",
    "childSingleRoomPrice",
    "childTripleRoomPrice",
    "childTwinRoomPrice",
    "combinedAdultQuadRoomPrice",
    "combinedAdultSingleRoomPrice",
    "combinedAdultTripleRoomPrice",
    "combinedAdultTwinRoomPrice",
    "combinedChildQuadRoomPrice",
    "combinedChildSingleRoomPrice",
    "combinedChildTripleRoomPrice",
    "combinedChildTwinRoomPrice",
    "departureCode",
    "departureStatus",
    "endDate",
    "epdDetails",
    "foodFundPrice",
    "guaranteeStatus",
    "guaranteedstatus",
    "hasEPD",
    "landonlyReduction",
    "notes",
    "priceIndicative",
    "startDate",
    "teenagerDiscount"
})
public class WsDepartureArrayVO {

    protected BigDecimal adultPortTax;
    protected BigDecimal adultQuadRoomPrice;
    protected BigDecimal adultTripleRoomPrice;
    protected BigDecimal adultTwinRoomPrice;
    protected BigDecimal adultsingleRoomPrice;
    protected BigDecimal childPortTax;
    protected BigDecimal childPrice;
    protected BigDecimal childQuadRoomPrice;
    protected BigDecimal childSingleRoomPrice;
    protected BigDecimal childTripleRoomPrice;
    protected BigDecimal childTwinRoomPrice;
    protected BigDecimal combinedAdultQuadRoomPrice;
    protected BigDecimal combinedAdultSingleRoomPrice;
    protected BigDecimal combinedAdultTripleRoomPrice;
    protected BigDecimal combinedAdultTwinRoomPrice;
    protected BigDecimal combinedChildQuadRoomPrice;
    protected BigDecimal combinedChildSingleRoomPrice;
    protected BigDecimal combinedChildTripleRoomPrice;
    protected BigDecimal combinedChildTwinRoomPrice;
    protected String departureCode;
    protected DepartureStatus departureStatus;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoCurrentEpdTourWSEPDisountDetailsVO> epdDetails;
    protected BigDecimal foodFundPrice;
    protected String guaranteeStatus;
    protected String guaranteedstatus;
    protected Boolean hasEPD;
    protected BigDecimal landonlyReduction;
    protected WsiProductNoteVO notes;
    protected Boolean priceIndicative;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    protected BigDecimal teenagerDiscount;

    /**
     * Gets the value of the adultPortTax property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAdultPortTax() {
        return adultPortTax;
    }

    /**
     * Sets the value of the adultPortTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAdultPortTax(BigDecimal value) {
        this.adultPortTax = value;
    }

    /**
     * Gets the value of the adultQuadRoomPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAdultQuadRoomPrice() {
        return adultQuadRoomPrice;
    }

    /**
     * Sets the value of the adultQuadRoomPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAdultQuadRoomPrice(BigDecimal value) {
        this.adultQuadRoomPrice = value;
    }

    /**
     * Gets the value of the adultTripleRoomPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAdultTripleRoomPrice() {
        return adultTripleRoomPrice;
    }

    /**
     * Sets the value of the adultTripleRoomPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAdultTripleRoomPrice(BigDecimal value) {
        this.adultTripleRoomPrice = value;
    }

    /**
     * Gets the value of the adultTwinRoomPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAdultTwinRoomPrice() {
        return adultTwinRoomPrice;
    }

    /**
     * Sets the value of the adultTwinRoomPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAdultTwinRoomPrice(BigDecimal value) {
        this.adultTwinRoomPrice = value;
    }

    /**
     * Gets the value of the adultsingleRoomPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAdultsingleRoomPrice() {
        return adultsingleRoomPrice;
    }

    /**
     * Sets the value of the adultsingleRoomPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAdultsingleRoomPrice(BigDecimal value) {
        this.adultsingleRoomPrice = value;
    }

    /**
     * Gets the value of the childPortTax property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getChildPortTax() {
        return childPortTax;
    }

    /**
     * Sets the value of the childPortTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setChildPortTax(BigDecimal value) {
        this.childPortTax = value;
    }

    /**
     * Gets the value of the childPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getChildPrice() {
        return childPrice;
    }

    /**
     * Sets the value of the childPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setChildPrice(BigDecimal value) {
        this.childPrice = value;
    }

    /**
     * Gets the value of the childQuadRoomPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getChildQuadRoomPrice() {
        return childQuadRoomPrice;
    }

    /**
     * Sets the value of the childQuadRoomPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setChildQuadRoomPrice(BigDecimal value) {
        this.childQuadRoomPrice = value;
    }

    /**
     * Gets the value of the childSingleRoomPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getChildSingleRoomPrice() {
        return childSingleRoomPrice;
    }

    /**
     * Sets the value of the childSingleRoomPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setChildSingleRoomPrice(BigDecimal value) {
        this.childSingleRoomPrice = value;
    }

    /**
     * Gets the value of the childTripleRoomPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getChildTripleRoomPrice() {
        return childTripleRoomPrice;
    }

    /**
     * Sets the value of the childTripleRoomPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setChildTripleRoomPrice(BigDecimal value) {
        this.childTripleRoomPrice = value;
    }

    /**
     * Gets the value of the childTwinRoomPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getChildTwinRoomPrice() {
        return childTwinRoomPrice;
    }

    /**
     * Sets the value of the childTwinRoomPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setChildTwinRoomPrice(BigDecimal value) {
        this.childTwinRoomPrice = value;
    }

    /**
     * Gets the value of the combinedAdultQuadRoomPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCombinedAdultQuadRoomPrice() {
        return combinedAdultQuadRoomPrice;
    }

    /**
     * Sets the value of the combinedAdultQuadRoomPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCombinedAdultQuadRoomPrice(BigDecimal value) {
        this.combinedAdultQuadRoomPrice = value;
    }

    /**
     * Gets the value of the combinedAdultSingleRoomPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCombinedAdultSingleRoomPrice() {
        return combinedAdultSingleRoomPrice;
    }

    /**
     * Sets the value of the combinedAdultSingleRoomPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCombinedAdultSingleRoomPrice(BigDecimal value) {
        this.combinedAdultSingleRoomPrice = value;
    }

    /**
     * Gets the value of the combinedAdultTripleRoomPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCombinedAdultTripleRoomPrice() {
        return combinedAdultTripleRoomPrice;
    }

    /**
     * Sets the value of the combinedAdultTripleRoomPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCombinedAdultTripleRoomPrice(BigDecimal value) {
        this.combinedAdultTripleRoomPrice = value;
    }

    /**
     * Gets the value of the combinedAdultTwinRoomPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCombinedAdultTwinRoomPrice() {
        return combinedAdultTwinRoomPrice;
    }

    /**
     * Sets the value of the combinedAdultTwinRoomPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCombinedAdultTwinRoomPrice(BigDecimal value) {
        this.combinedAdultTwinRoomPrice = value;
    }

    /**
     * Gets the value of the combinedChildQuadRoomPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCombinedChildQuadRoomPrice() {
        return combinedChildQuadRoomPrice;
    }

    /**
     * Sets the value of the combinedChildQuadRoomPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCombinedChildQuadRoomPrice(BigDecimal value) {
        this.combinedChildQuadRoomPrice = value;
    }

    /**
     * Gets the value of the combinedChildSingleRoomPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCombinedChildSingleRoomPrice() {
        return combinedChildSingleRoomPrice;
    }

    /**
     * Sets the value of the combinedChildSingleRoomPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCombinedChildSingleRoomPrice(BigDecimal value) {
        this.combinedChildSingleRoomPrice = value;
    }

    /**
     * Gets the value of the combinedChildTripleRoomPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCombinedChildTripleRoomPrice() {
        return combinedChildTripleRoomPrice;
    }

    /**
     * Sets the value of the combinedChildTripleRoomPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCombinedChildTripleRoomPrice(BigDecimal value) {
        this.combinedChildTripleRoomPrice = value;
    }

    /**
     * Gets the value of the combinedChildTwinRoomPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCombinedChildTwinRoomPrice() {
        return combinedChildTwinRoomPrice;
    }

    /**
     * Sets the value of the combinedChildTwinRoomPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCombinedChildTwinRoomPrice(BigDecimal value) {
        this.combinedChildTwinRoomPrice = value;
    }

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
     * Gets the value of the departureStatus property.
     * 
     * @return
     *     possible object is
     *     {@link DepartureStatus }
     *     
     */
    public DepartureStatus getDepartureStatus() {
        return departureStatus;
    }

    /**
     * Sets the value of the departureStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link DepartureStatus }
     *     
     */
    public void setDepartureStatus(DepartureStatus value) {
        this.departureStatus = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the epdDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the epdDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEpdDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoCurrentEpdTourWSEPDisountDetailsVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoCurrentEpdTourWSEPDisountDetailsVO> getEpdDetails() {
        if (epdDetails == null) {
            epdDetails = new ArrayList<ComTropicsWebserviceITropicsVoCurrentEpdTourWSEPDisountDetailsVO>();
        }
        return this.epdDetails;
    }

    /**
     * Gets the value of the foodFundPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFoodFundPrice() {
        return foodFundPrice;
    }

    /**
     * Sets the value of the foodFundPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFoodFundPrice(BigDecimal value) {
        this.foodFundPrice = value;
    }

    /**
     * Gets the value of the guaranteeStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuaranteeStatus() {
        return guaranteeStatus;
    }

    /**
     * Sets the value of the guaranteeStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuaranteeStatus(String value) {
        this.guaranteeStatus = value;
    }

    /**
     * Gets the value of the guaranteedstatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuaranteedstatus() {
        return guaranteedstatus;
    }

    /**
     * Sets the value of the guaranteedstatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuaranteedstatus(String value) {
        this.guaranteedstatus = value;
    }

    /**
     * Gets the value of the hasEPD property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHasEPD() {
        return hasEPD;
    }

    /**
     * Sets the value of the hasEPD property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasEPD(Boolean value) {
        this.hasEPD = value;
    }

    /**
     * Gets the value of the landonlyReduction property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLandonlyReduction() {
        return landonlyReduction;
    }

    /**
     * Sets the value of the landonlyReduction property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLandonlyReduction(BigDecimal value) {
        this.landonlyReduction = value;
    }

    /**
     * Gets the value of the notes property.
     * 
     * @return
     *     possible object is
     *     {@link WsiProductNoteVO }
     *     
     */
    public WsiProductNoteVO getNotes() {
        return notes;
    }

    /**
     * Sets the value of the notes property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsiProductNoteVO }
     *     
     */
    public void setNotes(WsiProductNoteVO value) {
        this.notes = value;
    }

    /**
     * Gets the value of the priceIndicative property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPriceIndicative() {
        return priceIndicative;
    }

    /**
     * Sets the value of the priceIndicative property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPriceIndicative(Boolean value) {
        this.priceIndicative = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the teenagerDiscount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTeenagerDiscount() {
        return teenagerDiscount;
    }

    /**
     * Sets the value of the teenagerDiscount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTeenagerDiscount(BigDecimal value) {
        this.teenagerDiscount = value;
    }

}
