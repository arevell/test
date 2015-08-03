
package com.ttc.prodserv.mongo.domain.xmlrepo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sellingCompanies complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sellingCompanies">
 *   &lt;complexContent>
 *     &lt;extension base="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}soapResponse">
 *       &lt;sequence>
 *         &lt;element name="sellingCompany" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}sellingCompany" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sellingCompanies", propOrder = {
    "sellingCompany"
})
public class SellingCompanies
{

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
