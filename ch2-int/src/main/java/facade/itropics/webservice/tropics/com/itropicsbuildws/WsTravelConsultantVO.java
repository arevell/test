
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsTravelConsultantVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsTravelConsultantVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="middleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ta_id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="tc_id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="wsPPIConsentByTCVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsppiConsentByTCVO" minOccurs="0"/>
 *         &lt;element name="wsTravelAgencyVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsTravelAgencyVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsTravelConsultantVO", propOrder = {
    "email",
    "firstName",
    "lastName",
    "middleName",
    "taId",
    "tcId",
    "wsPPIConsentByTCVO",
    "wsTravelAgencyVO"
})
public class WsTravelConsultantVO {

    protected String email;
    protected String firstName;
    protected String lastName;
    protected String middleName;
    @XmlElement(name = "ta_id")
    protected long taId;
    @XmlElement(name = "tc_id")
    protected long tcId;
    protected WsppiConsentByTCVO wsPPIConsentByTCVO;
    protected WsTravelAgencyVO wsTravelAgencyVO;

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the middleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the value of the middleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiddleName(String value) {
        this.middleName = value;
    }

    /**
     * Gets the value of the taId property.
     * 
     */
    public long getTaId() {
        return taId;
    }

    /**
     * Sets the value of the taId property.
     * 
     */
    public void setTaId(long value) {
        this.taId = value;
    }

    /**
     * Gets the value of the tcId property.
     * 
     */
    public long getTcId() {
        return tcId;
    }

    /**
     * Sets the value of the tcId property.
     * 
     */
    public void setTcId(long value) {
        this.tcId = value;
    }

    /**
     * Gets the value of the wsPPIConsentByTCVO property.
     * 
     * @return
     *     possible object is
     *     {@link WsppiConsentByTCVO }
     *     
     */
    public WsppiConsentByTCVO getWsPPIConsentByTCVO() {
        return wsPPIConsentByTCVO;
    }

    /**
     * Sets the value of the wsPPIConsentByTCVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsppiConsentByTCVO }
     *     
     */
    public void setWsPPIConsentByTCVO(WsppiConsentByTCVO value) {
        this.wsPPIConsentByTCVO = value;
    }

    /**
     * Gets the value of the wsTravelAgencyVO property.
     * 
     * @return
     *     possible object is
     *     {@link WsTravelAgencyVO }
     *     
     */
    public WsTravelAgencyVO getWsTravelAgencyVO() {
        return wsTravelAgencyVO;
    }

    /**
     * Sets the value of the wsTravelAgencyVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsTravelAgencyVO }
     *     
     */
    public void setWsTravelAgencyVO(WsTravelAgencyVO value) {
        this.wsTravelAgencyVO = value;
    }

}
