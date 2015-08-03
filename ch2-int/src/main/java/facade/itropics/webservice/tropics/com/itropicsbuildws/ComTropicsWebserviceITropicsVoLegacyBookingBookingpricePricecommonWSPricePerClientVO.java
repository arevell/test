
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSPricePerClientVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSPricePerClientVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clientID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="clientName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientNamesVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSClientNamesVO" minOccurs="0"/>
 *         &lt;element name="invoiceInfoVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSInvoiceInfoVO" minOccurs="0"/>
 *         &lt;element name="priceDetailsVOCollection" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSPriceDetailsVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="seqClientID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalCommission" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalDiscount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalGrossPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalGstVat" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalNettPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalTax" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSPricePerClientVO", propOrder = {
    "clientID",
    "clientName",
    "clientNamesVO",
    "invoiceInfoVO",
    "priceDetailsVOCollection",
    "seqClientID",
    "totalCommission",
    "totalDiscount",
    "totalGrossPrice",
    "totalGstVat",
    "totalNettPrice",
    "totalTax"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSPricePerClientVO {

    protected long clientID;
    protected String clientName;
    protected ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSClientNamesVO clientNamesVO;
    protected ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSInvoiceInfoVO invoiceInfoVO;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSPriceDetailsVO> priceDetailsVOCollection;
    protected String seqClientID;
    protected float totalCommission;
    protected float totalDiscount;
    protected float totalGrossPrice;
    protected float totalGstVat;
    protected float totalNettPrice;
    protected float totalTax;

    /**
     * Gets the value of the clientID property.
     * 
     */
    public long getClientID() {
        return clientID;
    }

    /**
     * Sets the value of the clientID property.
     * 
     */
    public void setClientID(long value) {
        this.clientID = value;
    }

    /**
     * Gets the value of the clientName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Sets the value of the clientName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientName(String value) {
        this.clientName = value;
    }

    /**
     * Gets the value of the clientNamesVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSClientNamesVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSClientNamesVO getClientNamesVO() {
        return clientNamesVO;
    }

    /**
     * Sets the value of the clientNamesVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSClientNamesVO }
     *     
     */
    public void setClientNamesVO(ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSClientNamesVO value) {
        this.clientNamesVO = value;
    }

    /**
     * Gets the value of the invoiceInfoVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSInvoiceInfoVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSInvoiceInfoVO getInvoiceInfoVO() {
        return invoiceInfoVO;
    }

    /**
     * Sets the value of the invoiceInfoVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSInvoiceInfoVO }
     *     
     */
    public void setInvoiceInfoVO(ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSInvoiceInfoVO value) {
        this.invoiceInfoVO = value;
    }

    /**
     * Gets the value of the priceDetailsVOCollection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the priceDetailsVOCollection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPriceDetailsVOCollection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSPriceDetailsVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSPriceDetailsVO> getPriceDetailsVOCollection() {
        if (priceDetailsVOCollection == null) {
            priceDetailsVOCollection = new ArrayList<ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSPriceDetailsVO>();
        }
        return this.priceDetailsVOCollection;
    }

    /**
     * Gets the value of the seqClientID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeqClientID() {
        return seqClientID;
    }

    /**
     * Sets the value of the seqClientID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeqClientID(String value) {
        this.seqClientID = value;
    }

    /**
     * Gets the value of the totalCommission property.
     * 
     */
    public float getTotalCommission() {
        return totalCommission;
    }

    /**
     * Sets the value of the totalCommission property.
     * 
     */
    public void setTotalCommission(float value) {
        this.totalCommission = value;
    }

    /**
     * Gets the value of the totalDiscount property.
     * 
     */
    public float getTotalDiscount() {
        return totalDiscount;
    }

    /**
     * Sets the value of the totalDiscount property.
     * 
     */
    public void setTotalDiscount(float value) {
        this.totalDiscount = value;
    }

    /**
     * Gets the value of the totalGrossPrice property.
     * 
     */
    public float getTotalGrossPrice() {
        return totalGrossPrice;
    }

    /**
     * Sets the value of the totalGrossPrice property.
     * 
     */
    public void setTotalGrossPrice(float value) {
        this.totalGrossPrice = value;
    }

    /**
     * Gets the value of the totalGstVat property.
     * 
     */
    public float getTotalGstVat() {
        return totalGstVat;
    }

    /**
     * Sets the value of the totalGstVat property.
     * 
     */
    public void setTotalGstVat(float value) {
        this.totalGstVat = value;
    }

    /**
     * Gets the value of the totalNettPrice property.
     * 
     */
    public float getTotalNettPrice() {
        return totalNettPrice;
    }

    /**
     * Sets the value of the totalNettPrice property.
     * 
     */
    public void setTotalNettPrice(float value) {
        this.totalNettPrice = value;
    }

    /**
     * Gets the value of the totalTax property.
     * 
     */
    public float getTotalTax() {
        return totalTax;
    }

    /**
     * Sets the value of the totalTax property.
     * 
     */
    public void setTotalTax(float value) {
        this.totalTax = value;
    }

}
