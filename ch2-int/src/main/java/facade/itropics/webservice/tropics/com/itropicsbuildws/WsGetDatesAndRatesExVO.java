
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsGetDatesAndRatesExVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsGetDatesAndRatesExVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="message" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsMessage" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="successful" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="tour" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsTourVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsGetDatesAndRatesExVO", propOrder = {
    "message",
    "successful",
    "tour"
})
public class WsGetDatesAndRatesExVO {

    @XmlElement(nillable = true)
    protected List<WsMessage> message;
    protected boolean successful;
    @XmlElement(nillable = true)
    protected List<WsTourVO> tour;

    /**
     * Gets the value of the message property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the message property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WsMessage }
     * 
     * 
     */
    public List<WsMessage> getMessage() {
        if (message == null) {
            message = new ArrayList<WsMessage>();
        }
        return this.message;
    }

    /**
     * Gets the value of the successful property.
     * 
     */
    public boolean isSuccessful() {
        return successful;
    }

    /**
     * Sets the value of the successful property.
     * 
     */
    public void setSuccessful(boolean value) {
        this.successful = value;
    }

    /**
     * Gets the value of the tour property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tour property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTour().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WsTourVO }
     * 
     * 
     */
    public List<WsTourVO> getTour() {
        if (tour == null) {
            tour = new ArrayList<WsTourVO>();
        }
        return this.tour;
    }

}
