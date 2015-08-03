
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsIncludedProductsVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsIncludedProductsVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IncludedProduct" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsIncludedProductVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsIncludedProductsVO", propOrder = {
    "includedProduct"
})
public class WsIncludedProductsVO {

    @XmlElement(name = "IncludedProduct")
    protected List<WsIncludedProductVO> includedProduct;

    /**
     * Gets the value of the includedProduct property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the includedProduct property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIncludedProduct().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WsIncludedProductVO }
     * 
     * 
     */
    public List<WsIncludedProductVO> getIncludedProduct() {
        if (includedProduct == null) {
            includedProduct = new ArrayList<WsIncludedProductVO>();
        }
        return this.includedProduct;
    }

}
