
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsAccommodationProductVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsAccommodationProductVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Type" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}productType" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Address" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsAddressVO" minOccurs="0"/>
 *         &lt;element name="Rooms" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsAccommodationProductRoomsVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsAccommodationProductVO", propOrder = {
    "type",
    "name",
    "code",
    "address",
    "rooms"
})
public class WsAccommodationProductVO {

    @XmlElement(name = "Type")
    protected ProductType type;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Code")
    protected String code;
    @XmlElement(name = "Address")
    protected WsAddressVO address;
    @XmlElement(name = "Rooms")
    protected WsAccommodationProductRoomsVO rooms;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link ProductType }
     *     
     */
    public ProductType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductType }
     *     
     */
    public void setType(ProductType value) {
        this.type = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link WsAddressVO }
     *     
     */
    public WsAddressVO getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsAddressVO }
     *     
     */
    public void setAddress(WsAddressVO value) {
        this.address = value;
    }

    /**
     * Gets the value of the rooms property.
     * 
     * @return
     *     possible object is
     *     {@link WsAccommodationProductRoomsVO }
     *     
     */
    public WsAccommodationProductRoomsVO getRooms() {
        return rooms;
    }

    /**
     * Sets the value of the rooms property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsAccommodationProductRoomsVO }
     *     
     */
    public void setRooms(WsAccommodationProductRoomsVO value) {
        this.rooms = value;
    }

}
