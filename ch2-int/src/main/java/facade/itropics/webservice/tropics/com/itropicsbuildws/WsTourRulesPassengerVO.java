
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsTourRulesPassengerVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsTourRulesPassengerVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AgeFrom" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="AgeTo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Type" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}passengerType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsTourRulesPassengerVO", propOrder = {
    "ageFrom",
    "ageTo",
    "type"
})
public class WsTourRulesPassengerVO {

    @XmlElement(name = "AgeFrom")
    protected Integer ageFrom;
    @XmlElement(name = "AgeTo")
    protected Integer ageTo;
    @XmlElement(name = "Type")
    protected PassengerType type;

    /**
     * Gets the value of the ageFrom property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAgeFrom() {
        return ageFrom;
    }

    /**
     * Sets the value of the ageFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAgeFrom(Integer value) {
        this.ageFrom = value;
    }

    /**
     * Gets the value of the ageTo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAgeTo() {
        return ageTo;
    }

    /**
     * Sets the value of the ageTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAgeTo(Integer value) {
        this.ageTo = value;
    }

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

}
