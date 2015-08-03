
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_current_products_accom_WSRoomOccupancyVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_current_products_accom_WSRoomOccupancyVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="adultPax" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="childPax" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="roomType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_current_products_accom_WSRoomOccupancyVO", propOrder = {
    "adultPax",
    "childPax",
    "roomType"
})
public class ComTropicsWebserviceITropicsVoCurrentProductsAccomWSRoomOccupancyVO {

    protected long adultPax;
    protected long childPax;
    protected String roomType;

    /**
     * Gets the value of the adultPax property.
     * 
     */
    public long getAdultPax() {
        return adultPax;
    }

    /**
     * Sets the value of the adultPax property.
     * 
     */
    public void setAdultPax(long value) {
        this.adultPax = value;
    }

    /**
     * Gets the value of the childPax property.
     * 
     */
    public long getChildPax() {
        return childPax;
    }

    /**
     * Sets the value of the childPax property.
     * 
     */
    public void setChildPax(long value) {
        this.childPax = value;
    }

    /**
     * Gets the value of the roomType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * Sets the value of the roomType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomType(String value) {
        this.roomType = value;
    }

}
