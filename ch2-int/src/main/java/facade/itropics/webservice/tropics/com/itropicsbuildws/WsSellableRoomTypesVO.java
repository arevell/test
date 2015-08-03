
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsSellableRoomTypesVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsSellableRoomTypesVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Single" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Twin" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="TwinShare" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Triple" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="TripleShare" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Quad" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="QuadShare" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="SellableSingleRoomAlias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SellableTwinRoomAlias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SellableTwinShareRoomAlias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SellableTripleRoomAlias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SellableTripleShareRoomAlias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SellableQuadRoomAlias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SellableQuadShareRoomAlias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsSellableRoomTypesVO", propOrder = {
    "single",
    "twin",
    "twinShare",
    "triple",
    "tripleShare",
    "quad",
    "quadShare",
    "sellableSingleRoomAlias",
    "sellableTwinRoomAlias",
    "sellableTwinShareRoomAlias",
    "sellableTripleRoomAlias",
    "sellableTripleShareRoomAlias",
    "sellableQuadRoomAlias",
    "sellableQuadShareRoomAlias"
})
public class WsSellableRoomTypesVO {

    @XmlElement(name = "Single")
    protected Boolean single;
    @XmlElement(name = "Twin")
    protected Boolean twin;
    @XmlElement(name = "TwinShare")
    protected Boolean twinShare;
    @XmlElement(name = "Triple")
    protected Boolean triple;
    @XmlElement(name = "TripleShare")
    protected Boolean tripleShare;
    @XmlElement(name = "Quad")
    protected Boolean quad;
    @XmlElement(name = "QuadShare")
    protected Boolean quadShare;
    @XmlElement(name = "SellableSingleRoomAlias")
    protected String sellableSingleRoomAlias;
    @XmlElement(name = "SellableTwinRoomAlias")
    protected String sellableTwinRoomAlias;
    @XmlElement(name = "SellableTwinShareRoomAlias")
    protected String sellableTwinShareRoomAlias;
    @XmlElement(name = "SellableTripleRoomAlias")
    protected String sellableTripleRoomAlias;
    @XmlElement(name = "SellableTripleShareRoomAlias")
    protected String sellableTripleShareRoomAlias;
    @XmlElement(name = "SellableQuadRoomAlias")
    protected String sellableQuadRoomAlias;
    @XmlElement(name = "SellableQuadShareRoomAlias")
    protected String sellableQuadShareRoomAlias;

    /**
     * Gets the value of the single property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSingle() {
        return single;
    }

    /**
     * Sets the value of the single property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSingle(Boolean value) {
        this.single = value;
    }

    /**
     * Gets the value of the twin property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTwin() {
        return twin;
    }

    /**
     * Sets the value of the twin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTwin(Boolean value) {
        this.twin = value;
    }

    /**
     * Gets the value of the twinShare property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTwinShare() {
        return twinShare;
    }

    /**
     * Sets the value of the twinShare property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTwinShare(Boolean value) {
        this.twinShare = value;
    }

    /**
     * Gets the value of the triple property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTriple() {
        return triple;
    }

    /**
     * Sets the value of the triple property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTriple(Boolean value) {
        this.triple = value;
    }

    /**
     * Gets the value of the tripleShare property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTripleShare() {
        return tripleShare;
    }

    /**
     * Sets the value of the tripleShare property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTripleShare(Boolean value) {
        this.tripleShare = value;
    }

    /**
     * Gets the value of the quad property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isQuad() {
        return quad;
    }

    /**
     * Sets the value of the quad property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setQuad(Boolean value) {
        this.quad = value;
    }

    /**
     * Gets the value of the quadShare property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isQuadShare() {
        return quadShare;
    }

    /**
     * Sets the value of the quadShare property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setQuadShare(Boolean value) {
        this.quadShare = value;
    }

    /**
     * Gets the value of the sellableSingleRoomAlias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellableSingleRoomAlias() {
        return sellableSingleRoomAlias;
    }

    /**
     * Sets the value of the sellableSingleRoomAlias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellableSingleRoomAlias(String value) {
        this.sellableSingleRoomAlias = value;
    }

    /**
     * Gets the value of the sellableTwinRoomAlias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellableTwinRoomAlias() {
        return sellableTwinRoomAlias;
    }

    /**
     * Sets the value of the sellableTwinRoomAlias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellableTwinRoomAlias(String value) {
        this.sellableTwinRoomAlias = value;
    }

    /**
     * Gets the value of the sellableTwinShareRoomAlias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellableTwinShareRoomAlias() {
        return sellableTwinShareRoomAlias;
    }

    /**
     * Sets the value of the sellableTwinShareRoomAlias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellableTwinShareRoomAlias(String value) {
        this.sellableTwinShareRoomAlias = value;
    }

    /**
     * Gets the value of the sellableTripleRoomAlias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellableTripleRoomAlias() {
        return sellableTripleRoomAlias;
    }

    /**
     * Sets the value of the sellableTripleRoomAlias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellableTripleRoomAlias(String value) {
        this.sellableTripleRoomAlias = value;
    }

    /**
     * Gets the value of the sellableTripleShareRoomAlias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellableTripleShareRoomAlias() {
        return sellableTripleShareRoomAlias;
    }

    /**
     * Sets the value of the sellableTripleShareRoomAlias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellableTripleShareRoomAlias(String value) {
        this.sellableTripleShareRoomAlias = value;
    }

    /**
     * Gets the value of the sellableQuadRoomAlias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellableQuadRoomAlias() {
        return sellableQuadRoomAlias;
    }

    /**
     * Sets the value of the sellableQuadRoomAlias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellableQuadRoomAlias(String value) {
        this.sellableQuadRoomAlias = value;
    }

    /**
     * Gets the value of the sellableQuadShareRoomAlias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellableQuadShareRoomAlias() {
        return sellableQuadShareRoomAlias;
    }

    /**
     * Sets the value of the sellableQuadShareRoomAlias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellableQuadShareRoomAlias(String value) {
        this.sellableQuadShareRoomAlias = value;
    }

}
