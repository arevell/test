
package com.wsout.habs.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsMiscellaneousVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsMiscellaneousVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MiscellaneousProduct" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsMiscellaneousProductVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsMiscellaneousVO", propOrder = {
    "miscellaneousProduct"
})
public class WsMiscellaneousVO {

    @XmlElement(name = "MiscellaneousProduct")
    protected List<WsMiscellaneousProductVO> miscellaneousProduct;

    /**
     * Gets the value of the miscellaneousProduct property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the miscellaneousProduct property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMiscellaneousProduct().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WsMiscellaneousProductVO }
     * 
     * 
     */
    public List<WsMiscellaneousProductVO> getMiscellaneousProduct() {
        if (miscellaneousProduct == null) {
            miscellaneousProduct = new ArrayList<WsMiscellaneousProductVO>();
        }
        return this.miscellaneousProduct;
    }

}
