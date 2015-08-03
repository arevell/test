
package com.wsout.habs.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsRoomPriceVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsRoomPriceVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Adult" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsAdultChildPriceVO" minOccurs="0"/>
 *         &lt;element name="Child" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsAdultChildPriceVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsRoomPriceVO", propOrder = {
    "adult",
    "child"
})
public class WsRoomPriceVO {

    @XmlElement(name = "Adult")
    protected WsAdultChildPriceVO adult;
    @XmlElement(name = "Child")
    protected WsAdultChildPriceVO child;

    /**
     * Gets the value of the adult property.
     * 
     * @return
     *     possible object is
     *     {@link WsAdultChildPriceVO }
     *     
     */
    public WsAdultChildPriceVO getAdult() {
        return adult;
    }

    /**
     * Sets the value of the adult property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsAdultChildPriceVO }
     *     
     */
    public void setAdult(WsAdultChildPriceVO value) {
        this.adult = value;
    }

    /**
     * Gets the value of the child property.
     * 
     * @return
     *     possible object is
     *     {@link WsAdultChildPriceVO }
     *     
     */
    public WsAdultChildPriceVO getChild() {
        return child;
    }

    /**
     * Sets the value of the child property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsAdultChildPriceVO }
     *     
     */
    public void setChild(WsAdultChildPriceVO value) {
        this.child = value;
    }

}
