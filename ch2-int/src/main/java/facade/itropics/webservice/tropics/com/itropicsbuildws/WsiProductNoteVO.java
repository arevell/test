
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsiProductNoteVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsiProductNoteVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sellingCompany" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tour" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departure" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsiProductNoteVO", propOrder = {
    "sellingCompany",
    "tour",
    "departure"
})
public class WsiProductNoteVO {

    protected String sellingCompany;
    protected String tour;
    protected String departure;

    /**
     * Gets the value of the sellingCompany property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellingCompany() {
        return sellingCompany;
    }

    /**
     * Sets the value of the sellingCompany property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellingCompany(String value) {
        this.sellingCompany = value;
    }

    /**
     * Gets the value of the tour property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTour() {
        return tour;
    }

    /**
     * Sets the value of the tour property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTour(String value) {
        this.tour = value;
    }

    /**
     * Gets the value of the departure property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeparture() {
        return departure;
    }

    /**
     * Sets the value of the departure property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeparture(String value) {
        this.departure = value;
    }

}
