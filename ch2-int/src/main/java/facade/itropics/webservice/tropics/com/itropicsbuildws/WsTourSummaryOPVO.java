
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsTourSummaryOPVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsTourSummaryOPVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="departures" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsTourSummaryDepartureVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="opCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsTourSummaryOPVO", propOrder = {
    "departures",
    "name",
    "opCode"
})
public class WsTourSummaryOPVO {

    @XmlElement(nillable = true)
    protected List<WsTourSummaryDepartureVO> departures;
    protected String name;
    protected String opCode;

    /**
     * Gets the value of the departures property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the departures property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDepartures().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WsTourSummaryDepartureVO }
     * 
     * 
     */
    public List<WsTourSummaryDepartureVO> getDepartures() {
        if (departures == null) {
            departures = new ArrayList<WsTourSummaryDepartureVO>();
        }
        return this.departures;
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
     * Gets the value of the opCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpCode() {
        return opCode;
    }

    /**
     * Sets the value of the opCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpCode(String value) {
        this.opCode = value;
    }

}
