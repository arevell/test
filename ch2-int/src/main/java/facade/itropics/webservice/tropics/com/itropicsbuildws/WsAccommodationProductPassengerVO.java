
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsAccommodationProductPassengerVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsAccommodationProductPassengerVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Type" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}passengerType" minOccurs="0"/>
 *         &lt;element name="Price" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsAccommodationProductPassengerVO", propOrder = {
    "type",
    "price"
})
public class WsAccommodationProductPassengerVO {

    @XmlElement(name = "Type")
    protected PassengerType type;
    @XmlElement(name = "Price")
    protected BigDecimal price;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link PassengerType }
     *     
     */
    public PassengerType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link PassengerType }
     *     
     */
    public void setType(PassengerType value) {
        this.type = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrice(BigDecimal value) {
        this.price = value;
    }

}
