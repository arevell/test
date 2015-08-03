
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_current_products_accom_WSInValidateRoomOccupancyVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_current_products_accom_WSInValidateRoomOccupancyVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WSRoomOccupancyVOArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_current_products_accom_WSRoomOccupancyVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_current_products_accom_WSInValidateRoomOccupancyVO", propOrder = {
    "productCode",
    "wsRoomOccupancyVOArray"
})
public class ComTropicsWebserviceITropicsVoCurrentProductsAccomWSInValidateRoomOccupancyVO {

    protected String productCode;
    @XmlElement(name = "WSRoomOccupancyVOArray", nillable = true)
    protected List<ComTropicsWebserviceITropicsVoCurrentProductsAccomWSRoomOccupancyVO> wsRoomOccupancyVOArray;

    /**
     * Gets the value of the productCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * Sets the value of the productCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCode(String value) {
        this.productCode = value;
    }

    /**
     * Gets the value of the wsRoomOccupancyVOArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsRoomOccupancyVOArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSRoomOccupancyVOArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoCurrentProductsAccomWSRoomOccupancyVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoCurrentProductsAccomWSRoomOccupancyVO> getWSRoomOccupancyVOArray() {
        if (wsRoomOccupancyVOArray == null) {
            wsRoomOccupancyVOArray = new ArrayList<ComTropicsWebserviceITropicsVoCurrentProductsAccomWSRoomOccupancyVO>();
        }
        return this.wsRoomOccupancyVOArray;
    }

}
