
package com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SellingCompanies complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SellingCompanies">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SellingCompany" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}SellingCompany" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SellingCompanies", propOrder = {
    "sellingCompany"
})
public class SellingCompanies {

    @XmlElement(name = "SellingCompany", required = true)
    protected List<SellingCompany> sellingCompany;

    /**
     * Gets the value of the sellingCompany property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sellingCompany property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSellingCompany().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SellingCompany }
     * 
     * 
     */
    public List<SellingCompany> getSellingCompany() {
        if (sellingCompany == null) {
            sellingCompany = new ArrayList<SellingCompany>();
        }
        return this.sellingCompany;
    }

}
