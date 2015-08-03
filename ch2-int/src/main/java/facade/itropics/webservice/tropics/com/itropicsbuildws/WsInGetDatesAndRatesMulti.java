
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsInGetDatesAndRatesMulti complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsInGetDatesAndRatesMulti">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="showHiddenDepartures" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="showNotAvailableDepartures" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="tourCode" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsInGetDatesAndRatesMulti", propOrder = {
    "showHiddenDepartures",
    "showNotAvailableDepartures",
    "tourCode"
})
public class WsInGetDatesAndRatesMulti {

    protected Boolean showHiddenDepartures;
    protected Boolean showNotAvailableDepartures;
    @XmlElement(required = true)
    protected List<String> tourCode;

    /**
     * Gets the value of the showHiddenDepartures property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowHiddenDepartures() {
        return showHiddenDepartures;
    }

    /**
     * Sets the value of the showHiddenDepartures property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowHiddenDepartures(Boolean value) {
        this.showHiddenDepartures = value;
    }

    /**
     * Gets the value of the showNotAvailableDepartures property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowNotAvailableDepartures() {
        return showNotAvailableDepartures;
    }

    /**
     * Sets the value of the showNotAvailableDepartures property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowNotAvailableDepartures(Boolean value) {
        this.showNotAvailableDepartures = value;
    }

    /**
     * Gets the value of the tourCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tourCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTourCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTourCode() {
        if (tourCode == null) {
            tourCode = new ArrayList<String>();
        }
        return this.tourCode;
    }

}
