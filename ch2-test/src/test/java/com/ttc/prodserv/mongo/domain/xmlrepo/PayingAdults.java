
package com.ttc.prodserv.mongo.domain.xmlrepo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for payingAdults complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="payingAdults">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="payingAdult" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}payingAdult" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "payingAdults", propOrder = {
    "payingAdult"
})
public class PayingAdults {

    protected List<PayingAdult> payingAdult;

    /**
     * Gets the value of the payingAdult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the payingAdult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPayingAdult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PayingAdult }
     * 
     * 
     */
    public List<PayingAdult> getPayingAdult() {
        if (payingAdult == null) {
            payingAdult = new ArrayList<PayingAdult>();
        }
        return this.payingAdult;
    }

}
