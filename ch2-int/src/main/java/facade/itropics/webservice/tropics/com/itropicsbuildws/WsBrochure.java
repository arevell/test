
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for wsBrochure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsBrochure">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="brandCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="brochureYear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="longDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numberOfPages" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="operatingPeriodEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="operatingPeriodStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="sellinCompanyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sellingPeriodEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="sellingPeriodStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="shortDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsBrochure", propOrder = {
    "brandCode",
    "brochureYear",
    "code",
    "longDescription",
    "name",
    "numberOfPages",
    "operatingPeriodEndDate",
    "operatingPeriodStartDate",
    "sellinCompanyCode",
    "sellingPeriodEndDate",
    "sellingPeriodStartDate",
    "shortDescription"
})
public class WsBrochure {

    protected String brandCode;
    protected String brochureYear;
    protected String code;
    protected String longDescription;
    protected String name;
    protected int numberOfPages;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar operatingPeriodEndDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar operatingPeriodStartDate;
    protected String sellinCompanyCode;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar sellingPeriodEndDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar sellingPeriodStartDate;
    protected String shortDescription;

    /**
     * Gets the value of the brandCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrandCode() {
        return brandCode;
    }

    /**
     * Sets the value of the brandCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrandCode(String value) {
        this.brandCode = value;
    }

    /**
     * Gets the value of the brochureYear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrochureYear() {
        return brochureYear;
    }

    /**
     * Sets the value of the brochureYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrochureYear(String value) {
        this.brochureYear = value;
    }

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
     * Gets the value of the longDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongDescription() {
        return longDescription;
    }

    /**
     * Sets the value of the longDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongDescription(String value) {
        this.longDescription = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the numberOfPages property.
     * 
     */
    public int getNumberOfPages() {
        return numberOfPages;
    }

    /**
     * Sets the value of the numberOfPages property.
     * 
     */
    public void setNumberOfPages(int value) {
        this.numberOfPages = value;
    }

    /**
     * Gets the value of the operatingPeriodEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOperatingPeriodEndDate() {
        return operatingPeriodEndDate;
    }

    /**
     * Sets the value of the operatingPeriodEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOperatingPeriodEndDate(XMLGregorianCalendar value) {
        this.operatingPeriodEndDate = value;
    }

    /**
     * Gets the value of the operatingPeriodStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOperatingPeriodStartDate() {
        return operatingPeriodStartDate;
    }

    /**
     * Sets the value of the operatingPeriodStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOperatingPeriodStartDate(XMLGregorianCalendar value) {
        this.operatingPeriodStartDate = value;
    }

    /**
     * Gets the value of the sellinCompanyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellinCompanyCode() {
        return sellinCompanyCode;
    }

    /**
     * Sets the value of the sellinCompanyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellinCompanyCode(String value) {
        this.sellinCompanyCode = value;
    }

    /**
     * Gets the value of the sellingPeriodEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSellingPeriodEndDate() {
        return sellingPeriodEndDate;
    }

    /**
     * Sets the value of the sellingPeriodEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSellingPeriodEndDate(XMLGregorianCalendar value) {
        this.sellingPeriodEndDate = value;
    }

    /**
     * Gets the value of the sellingPeriodStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSellingPeriodStartDate() {
        return sellingPeriodStartDate;
    }

    /**
     * Sets the value of the sellingPeriodStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSellingPeriodStartDate(XMLGregorianCalendar value) {
        this.sellingPeriodStartDate = value;
    }

    /**
     * Gets the value of the shortDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Sets the value of the shortDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortDescription(String value) {
        this.shortDescription = value;
    }

}
