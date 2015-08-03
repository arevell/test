
package com.ttc.prodserv.mongo.domain.xmlrepo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tours complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tours">
 *   &lt;complexContent>
 *     &lt;extension base="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}soapResponse">
 *       &lt;sequence>
 *         &lt;element name="tour" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}tour" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tours", propOrder = {
    "tour"
})
public class Tours
{

    protected List<Tour> tour;

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
     * {@link Tour }
     * 
     * 
     */
    public List<Tour> getTour() {
        if (tour == null) {
            tour = new ArrayList<Tour>();
        }
        return this.tour;
    }

}
