
package com.ttc.prodserv.mongo.domain.xmlrepo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for surcharges complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="surcharges">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="surcharge" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}surcharge" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "surcharges", propOrder = {
    "surcharge"
})
public class Surcharges {

    protected List<Surcharge> surcharge;

    /**
     * Gets the value of the surcharge property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the surcharge property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSurcharge().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Surcharge }
     * 
     * 
     */
    public List<Surcharge> getSurcharge() {
        if (surcharge == null) {
            surcharge = new ArrayList<Surcharge>();
        }
        return this.surcharge;
    }

}
