
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsSurchargesVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsSurchargesVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Price" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsPriceVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsSurchargesVO", propOrder = {
    "price"
})
public class WsSurchargesVO {

    @XmlElement(name = "Price")
    protected List<WsPriceVO> price;

    /**
     * Gets the value of the price property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the price property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrice().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WsPriceVO }
     * 
     * 
     */
    public List<WsPriceVO> getPrice() {
        if (price == null) {
            price = new ArrayList<WsPriceVO>();
        }
        return this.price;
    }

}
