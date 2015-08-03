
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsOperatingProductVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsOperatingProductVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ContractingSeason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Classification" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StandardName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SellableRoomTypes" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsSellableRoomTypesVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsOperatingProductVO", propOrder = {
    "code",
    "name",
    "contractingSeason",
    "category",
    "classification",
    "standardName",
    "sellableRoomTypes"
})
public class WsOperatingProductVO {

    @XmlElement(name = "Code")
    protected String code;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "ContractingSeason")
    protected String contractingSeason;
    @XmlElement(name = "Category")
    protected String category;
    @XmlElement(name = "Classification")
    protected String classification;
    @XmlElement(name = "StandardName")
    protected String standardName;
    @XmlElement(name = "SellableRoomTypes")
    protected WsSellableRoomTypesVO sellableRoomTypes;

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
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
     * Gets the value of the contractingSeason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractingSeason() {
        return contractingSeason;
    }

    /**
     * Sets the value of the contractingSeason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractingSeason(String value) {
        this.contractingSeason = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the classification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassification() {
        return classification;
    }

    /**
     * Sets the value of the classification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassification(String value) {
        this.classification = value;
    }

    /**
     * Gets the value of the standardName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStandardName() {
        return standardName;
    }

    /**
     * Sets the value of the standardName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStandardName(String value) {
        this.standardName = value;
    }

    /**
     * Gets the value of the sellableRoomTypes property.
     * 
     * @return
     *     possible object is
     *     {@link WsSellableRoomTypesVO }
     *     
     */
    public WsSellableRoomTypesVO getSellableRoomTypes() {
        return sellableRoomTypes;
    }

    /**
     * Sets the value of the sellableRoomTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsSellableRoomTypesVO }
     *     
     */
    public void setSellableRoomTypes(WsSellableRoomTypesVO value) {
        this.sellableRoomTypes = value;
    }

}
