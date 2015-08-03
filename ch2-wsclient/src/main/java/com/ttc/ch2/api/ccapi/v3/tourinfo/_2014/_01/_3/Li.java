
package com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for li complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="li">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element name="values" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="a" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}a"/>
 *         &lt;element name="strong" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}strong"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "li", propOrder = {
    "content"
})
public class Li {

    @XmlElementRefs({
        @XmlElementRef(name = "values", namespace = "http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0", type = JAXBElement.class),
        @XmlElementRef(name = "strong", namespace = "http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0", type = JAXBElement.class),
        @XmlElementRef(name = "a", namespace = "http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0", type = JAXBElement.class)
    })
    @XmlMixed
    protected List<Serializable> content;

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Strong }{@code >}
     * {@link String }
     * {@link JAXBElement }{@code <}{@link A }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     */
    public List<Serializable> getContent() {
        if (content == null) {
            content = new ArrayList<Serializable>();
        }
        return this.content;
    }

}
