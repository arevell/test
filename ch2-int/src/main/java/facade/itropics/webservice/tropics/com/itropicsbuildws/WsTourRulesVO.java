
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsTourRulesVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsTourRulesVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CombinedIncludedCharges" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsCombinedIncludedChargesVO" minOccurs="0"/>
 *         &lt;element name="Passengers" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsTourRulesPassengersVO" minOccurs="0"/>
 *         &lt;element name="Rooms" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsTourRulesRoomsVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsTourRulesVO", propOrder = {
    "combinedIncludedCharges",
    "passengers",
    "rooms"
})
public class WsTourRulesVO {

    @XmlElement(name = "CombinedIncludedCharges")
    protected WsCombinedIncludedChargesVO combinedIncludedCharges;
    @XmlElement(name = "Passengers")
    protected WsTourRulesPassengersVO passengers;
    @XmlElement(name = "Rooms")
    protected WsTourRulesRoomsVO rooms;

    /**
     * Gets the value of the combinedIncludedCharges property.
     * 
     * @return
     *     possible object is
     *     {@link WsCombinedIncludedChargesVO }
     *     
     */
    public WsCombinedIncludedChargesVO getCombinedIncludedCharges() {
        return combinedIncludedCharges;
    }

    /**
     * Sets the value of the combinedIncludedCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsCombinedIncludedChargesVO }
     *     
     */
    public void setCombinedIncludedCharges(WsCombinedIncludedChargesVO value) {
        this.combinedIncludedCharges = value;
    }

    /**
     * Gets the value of the passengers property.
     * 
     * @return
     *     possible object is
     *     {@link WsTourRulesPassengersVO }
     *     
     */
    public WsTourRulesPassengersVO getPassengers() {
        return passengers;
    }

    /**
     * Sets the value of the passengers property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsTourRulesPassengersVO }
     *     
     */
    public void setPassengers(WsTourRulesPassengersVO value) {
        this.passengers = value;
    }

    /**
     * Gets the value of the rooms property.
     * 
     * @return
     *     possible object is
     *     {@link WsTourRulesRoomsVO }
     *     
     */
    public WsTourRulesRoomsVO getRooms() {
        return rooms;
    }

    /**
     * Sets the value of the rooms property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsTourRulesRoomsVO }
     *     
     */
    public void setRooms(WsTourRulesRoomsVO value) {
        this.rooms = value;
    }

}
