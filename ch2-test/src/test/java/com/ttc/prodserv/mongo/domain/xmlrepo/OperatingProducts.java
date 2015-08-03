
package com.ttc.prodserv.mongo.domain.xmlrepo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for operatingProducts complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="operatingProducts">
 *   &lt;complexContent>
 *     &lt;extension base="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}soapResponse">
 *       &lt;sequence>
 *         &lt;element name="operatingProduct" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}operatingProduct" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "operatingProducts", propOrder = {
    "operatingProduct"
})
public class OperatingProducts
{

    protected List<OperatingProduct> operatingProduct;

    /**
     * Gets the value of the operatingProduct property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the operatingProduct property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOperatingProduct().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OperatingProduct }
     * 
     * 
     */
    public List<OperatingProduct> getOperatingProduct() {
        if (operatingProduct == null) {
            operatingProduct = new ArrayList<OperatingProduct>();
        }
        return this.operatingProduct;
    }

}
