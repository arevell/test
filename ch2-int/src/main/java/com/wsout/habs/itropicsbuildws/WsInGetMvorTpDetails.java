
package com.wsout.habs.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for wsInGetMvorTpDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsInGetMvorTpDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="departuredate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="itropicssellable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="mvortpcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sellingcompanycode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsInGetMvorTpDetails", propOrder = {
    "departuredate",
    "itropicssellable",
    "mvortpcode",
    "sellingcompanycode"
})
public class WsInGetMvorTpDetails {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar departuredate;
    protected Boolean itropicssellable;
    protected String mvortpcode;
    protected String sellingcompanycode;

    /**
     * Gets the value of the departuredate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDeparturedate() {
        return departuredate;
    }

    /**
     * Sets the value of the departuredate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDeparturedate(XMLGregorianCalendar value) {
        this.departuredate = value;
    }

    /**
     * Gets the value of the itropicssellable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isItropicssellable() {
        return itropicssellable;
    }

    /**
     * Sets the value of the itropicssellable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setItropicssellable(Boolean value) {
        this.itropicssellable = value;
    }

    /**
     * Gets the value of the mvortpcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMvortpcode() {
        return mvortpcode;
    }

    /**
     * Sets the value of the mvortpcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMvortpcode(String value) {
        this.mvortpcode = value;
    }

    /**
     * Gets the value of the sellingcompanycode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellingcompanycode() {
        return sellingcompanycode;
    }

    /**
     * Sets the value of the sellingcompanycode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellingcompanycode(String value) {
        this.sellingcompanycode = value;
    }

}
