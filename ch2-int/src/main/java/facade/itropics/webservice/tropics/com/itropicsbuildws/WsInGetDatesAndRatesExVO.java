
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for wsInGetDatesAndRatesExVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsInGetDatesAndRatesExVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="includeEpds" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="multiDepartureRequest" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsInGetDatesAndRatesMulti" minOccurs="0"/>
 *         &lt;element name="sellingCompanyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="singleDepartureRequest" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsInGetDatesAndRatesSingle" minOccurs="0"/>
 *         &lt;element name="updatedSince" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsInGetDatesAndRatesExVO", propOrder = {
    "includeEpds",
    "multiDepartureRequest",
    "sellingCompanyCode",
    "singleDepartureRequest",
    "updatedSince"
})
public class WsInGetDatesAndRatesExVO {

    protected Boolean includeEpds;
    protected WsInGetDatesAndRatesMulti multiDepartureRequest;
    @XmlElement(required = true)
    protected String sellingCompanyCode;
    protected WsInGetDatesAndRatesSingle singleDepartureRequest;
    @XmlElementRef(name = "updatedSince", type = JAXBElement.class)
    protected JAXBElement<XMLGregorianCalendar> updatedSince;

    /**
     * Gets the value of the includeEpds property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeEpds() {
        return includeEpds;
    }

    /**
     * Sets the value of the includeEpds property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeEpds(Boolean value) {
        this.includeEpds = value;
    }

    /**
     * Gets the value of the multiDepartureRequest property.
     * 
     * @return
     *     possible object is
     *     {@link WsInGetDatesAndRatesMulti }
     *     
     */
    public WsInGetDatesAndRatesMulti getMultiDepartureRequest() {
        return multiDepartureRequest;
    }

    /**
     * Sets the value of the multiDepartureRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsInGetDatesAndRatesMulti }
     *     
     */
    public void setMultiDepartureRequest(WsInGetDatesAndRatesMulti value) {
        this.multiDepartureRequest = value;
    }

    /**
     * Gets the value of the sellingCompanyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellingCompanyCode() {
        return sellingCompanyCode;
    }

    /**
     * Sets the value of the sellingCompanyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellingCompanyCode(String value) {
        this.sellingCompanyCode = value;
    }

    /**
     * Gets the value of the singleDepartureRequest property.
     * 
     * @return
     *     possible object is
     *     {@link WsInGetDatesAndRatesSingle }
     *     
     */
    public WsInGetDatesAndRatesSingle getSingleDepartureRequest() {
        return singleDepartureRequest;
    }

    /**
     * Sets the value of the singleDepartureRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsInGetDatesAndRatesSingle }
     *     
     */
    public void setSingleDepartureRequest(WsInGetDatesAndRatesSingle value) {
        this.singleDepartureRequest = value;
    }

    /**
     * Gets the value of the updatedSince property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getUpdatedSince() {
        return updatedSince;
    }

    /**
     * Sets the value of the updatedSince property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setUpdatedSince(JAXBElement<XMLGregorianCalendar> value) {
        this.updatedSince = ((JAXBElement<XMLGregorianCalendar> ) value);
    }

}
