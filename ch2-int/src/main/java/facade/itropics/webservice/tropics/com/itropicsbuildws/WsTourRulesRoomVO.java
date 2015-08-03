
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsTourRulesRoomVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsTourRulesRoomVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OccupancyRule" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsOccupancyRuleVO" minOccurs="0"/>
 *         &lt;element name="Price" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsRoomPriceVO" minOccurs="0"/>
 *         &lt;element name="Type" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}roomType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsTourRulesRoomVO", propOrder = {
    "occupancyRule",
    "price",
    "type"
})
public class WsTourRulesRoomVO {

    @XmlElement(name = "OccupancyRule")
    protected WsOccupancyRuleVO occupancyRule;
    @XmlElement(name = "Price")
    protected WsRoomPriceVO price;
    @XmlElement(name = "Type")
    protected RoomType type;

    /**
     * Gets the value of the occupancyRule property.
     * 
     * @return
     *     possible object is
     *     {@link WsOccupancyRuleVO }
     *     
     */
    public WsOccupancyRuleVO getOccupancyRule() {
        return occupancyRule;
    }

    /**
     * Sets the value of the occupancyRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsOccupancyRuleVO }
     *     
     */
    public void setOccupancyRule(WsOccupancyRuleVO value) {
        this.occupancyRule = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link WsRoomPriceVO }
     *     
     */
    public WsRoomPriceVO getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsRoomPriceVO }
     *     
     */
    public void setPrice(WsRoomPriceVO value) {
        this.price = value;
    }

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

}
