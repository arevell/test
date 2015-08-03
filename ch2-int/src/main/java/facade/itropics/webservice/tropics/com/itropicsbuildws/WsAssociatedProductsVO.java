
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsAssociatedProductsVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsAssociatedProductsVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Accommodation" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsAccommodationVO" minOccurs="0"/>
 *         &lt;element name="Miscellaneous" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsMiscellaneousVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsAssociatedProductsVO", propOrder = {
    "accommodation",
    "miscellaneous"
})
public class WsAssociatedProductsVO {

    @XmlElement(name = "Accommodation")
    protected WsAccommodationVO accommodation;
    @XmlElement(name = "Miscellaneous")
    protected WsMiscellaneousVO miscellaneous;

    /**
     * Gets the value of the accommodation property.
     * 
     * @return
     *     possible object is
     *     {@link WsAccommodationVO }
     *     
     */
    public WsAccommodationVO getAccommodation() {
        return accommodation;
    }

    /**
     * Sets the value of the accommodation property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsAccommodationVO }
     *     
     */
    public void setAccommodation(WsAccommodationVO value) {
        this.accommodation = value;
    }

    /**
     * Gets the value of the miscellaneous property.
     * 
     * @return
     *     possible object is
     *     {@link WsMiscellaneousVO }
     *     
     */
    public WsMiscellaneousVO getMiscellaneous() {
        return miscellaneous;
    }

    /**
     * Sets the value of the miscellaneous property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsMiscellaneousVO }
     *     
     */
    public void setMiscellaneous(WsMiscellaneousVO value) {
        this.miscellaneous = value;
    }

}
