
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSInvoiceProductPymtVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSInvoiceProductPymtVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amountPaid" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="bkgInvoiceDetailsVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSBkgInvoiceDetailsVO" minOccurs="0"/>
 *         &lt;element name="invoiceProductPymtID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="productDetailsVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSProductDetailsVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSInvoiceProductPymtVO", propOrder = {
    "amountPaid",
    "bkgInvoiceDetailsVO",
    "invoiceProductPymtID",
    "productDetailsVO"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSInvoiceProductPymtVO {

    protected float amountPaid;
    protected ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSBkgInvoiceDetailsVO bkgInvoiceDetailsVO;
    protected long invoiceProductPymtID;
    protected ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSProductDetailsVO productDetailsVO;

    /**
     * Gets the value of the amountPaid property.
     * 
     */
    public float getAmountPaid() {
        return amountPaid;
    }

    /**
     * Sets the value of the amountPaid property.
     * 
     */
    public void setAmountPaid(float value) {
        this.amountPaid = value;
    }

    /**
     * Gets the value of the bkgInvoiceDetailsVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSBkgInvoiceDetailsVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSBkgInvoiceDetailsVO getBkgInvoiceDetailsVO() {
        return bkgInvoiceDetailsVO;
    }

    /**
     * Sets the value of the bkgInvoiceDetailsVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSBkgInvoiceDetailsVO }
     *     
     */
    public void setBkgInvoiceDetailsVO(ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSBkgInvoiceDetailsVO value) {
        this.bkgInvoiceDetailsVO = value;
    }

    /**
     * Gets the value of the invoiceProductPymtID property.
     * 
     */
    public long getInvoiceProductPymtID() {
        return invoiceProductPymtID;
    }

    /**
     * Sets the value of the invoiceProductPymtID property.
     * 
     */
    public void setInvoiceProductPymtID(long value) {
        this.invoiceProductPymtID = value;
    }

    /**
     * Gets the value of the productDetailsVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSProductDetailsVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSProductDetailsVO getProductDetailsVO() {
        return productDetailsVO;
    }

    /**
     * Sets the value of the productDetailsVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSProductDetailsVO }
     *     
     */
    public void setProductDetailsVO(ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSProductDetailsVO value) {
        this.productDetailsVO = value;
    }

}
