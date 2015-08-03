
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsClientAirFareVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsClientAirFareVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="carrier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="farebasisCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pnr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsClientName" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsClientNames" minOccurs="0"/>
 *         &lt;element name="wsFareRuleVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsFareRuleVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsClientAirFareVO", propOrder = {
    "carrier",
    "farebasisCode",
    "pnr",
    "wsClientName",
    "wsFareRuleVO"
})
public class WsClientAirFareVO {

    protected String carrier;
    protected String farebasisCode;
    protected String pnr;
    protected WsClientNames wsClientName;
    @XmlElement(nillable = true)
    protected List<WsFareRuleVO> wsFareRuleVO;

    /**
     * Gets the value of the carrier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrier() {
        return carrier;
    }

    /**
     * Sets the value of the carrier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrier(String value) {
        this.carrier = value;
    }

    /**
     * Gets the value of the farebasisCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFarebasisCode() {
        return farebasisCode;
    }

    /**
     * Sets the value of the farebasisCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFarebasisCode(String value) {
        this.farebasisCode = value;
    }

    /**
     * Gets the value of the pnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPnr() {
        return pnr;
    }

    /**
     * Sets the value of the pnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPnr(String value) {
        this.pnr = value;
    }

    /**
     * Gets the value of the wsClientName property.
     * 
     * @return
     *     possible object is
     *     {@link WsClientNames }
     *     
     */
    public WsClientNames getWsClientName() {
        return wsClientName;
    }

    /**
     * Sets the value of the wsClientName property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsClientNames }
     *     
     */
    public void setWsClientName(WsClientNames value) {
        this.wsClientName = value;
    }

    /**
     * Gets the value of the wsFareRuleVO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsFareRuleVO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsFareRuleVO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WsFareRuleVO }
     * 
     * 
     */
    public List<WsFareRuleVO> getWsFareRuleVO() {
        if (wsFareRuleVO == null) {
            wsFareRuleVO = new ArrayList<WsFareRuleVO>();
        }
        return this.wsFareRuleVO;
    }

}
