
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsAccommodationProductRoomVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsAccommodationProductRoomVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Type" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}roomType" minOccurs="0"/>
 *         &lt;element name="Passengers" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsAccommodationProductPassengersVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsAccommodationProductRoomVO", propOrder = {
    "type",
    "passengers"
})
public class WsAccommodationProductRoomVO {

    @XmlElement(name = "Type")
    protected RoomType type;
    @XmlElement(name = "Passengers")
    protected WsAccommodationProductPassengersVO passengers;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link RoomType }
     *     
     */
    public RoomType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomType }
     *     
     */
    public void setType(RoomType value) {
        this.type = value;
    }

    /**
     * Gets the value of the passengers property.
     * 
     * @return
     *     possible object is
     *     {@link WsAccommodationProductPassengersVO }
     *     
     */
    public WsAccommodationProductPassengersVO getPassengers() {
        return passengers;
    }

    /**
     * Sets the value of the passengers property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsAccommodationProductPassengersVO }
     *     
     */
    public void setPassengers(WsAccommodationProductPassengersVO value) {
        this.passengers = value;
    }

}
