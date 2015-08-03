
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_current_products_misctransfer_WSMarketVariationTourPackage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_current_products_misctransfer_WSMarketVariationTourPackage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departureCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="productCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_current_products_misctransfer_WSMarketVariationTourPackage", propOrder = {
    "code",
    "departureCode",
    "endCity",
    "endDate",
    "productCategory",
    "startCity",
    "startDate"
})
public class ComTropicsWebserviceITropicsVoCurrentProductsMisctransferWSMarketVariationTourPackage {

    protected String code;
    protected String departureCode;
    protected String endCity;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    protected String productCategory;
    protected String startCity;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the departureCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureCode() {
        return departureCode;
    }

    /**
     * Sets the value of the departureCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureCode(String value) {
        this.departureCode = value;
    }

    /**
     * Gets the value of the endCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndCity() {
        return endCity;
    }

    /**
     * Sets the value of the endCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndCity(String value) {
        this.endCity = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the productCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCategory() {
        return productCategory;
    }

    /**
     * Sets the value of the productCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCategory(String value) {
        this.productCategory = value;
    }

    /**
     * Gets the value of the startCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartCity() {
        return startCity;
    }

    /**
     * Sets the value of the startCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartCity(String value) {
        this.startCity = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

}
