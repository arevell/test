
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsTourSummaryVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsTourSummaryVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="departures" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsTourSummaryDepartureVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ops" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsTourSummaryOPVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tourCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tourName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tourType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsTourSummaryVO", propOrder = {
    "departures",
    "ops",
    "tourCode",
    "tourName",
    "tourType"
})
public class WsTourSummaryVO {

    @XmlElement(nillable = true)
    protected List<WsTourSummaryDepartureVO> departures;
    @XmlElement(nillable = true)
    protected List<WsTourSummaryOPVO> ops;
    protected String tourCode;
    protected String tourName;
    protected String tourType;

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
     * Gets the value of the ops property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ops property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOps().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WsTourSummaryOPVO }
     * 
     * 
     */
    public List<WsTourSummaryOPVO> getOps() {
        if (ops == null) {
            ops = new ArrayList<WsTourSummaryOPVO>();
        }
        return this.ops;
    }

    /**
     * Gets the value of the tourCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTourCode() {
        return tourCode;
    }

    /**
     * Sets the value of the tourCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTourCode(String value) {
        this.tourCode = value;
    }

    /**
     * Gets the value of the tourName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTourName() {
        return tourName;
    }

    /**
     * Sets the value of the tourName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTourName(String value) {
        this.tourName = value;
    }

    /**
     * Gets the value of the tourType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTourType() {
        return tourType;
    }

    /**
     * Sets the value of the tourType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTourType(String value) {
        this.tourType = value;
    }

}
