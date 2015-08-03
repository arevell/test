
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSInvoiceInfoVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSInvoiceInfoVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="invoiceInfoID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="landAirFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="pricePerClientFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="pricePerClientVOCollection" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSPricePerClientVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="productDetailsVOCollection" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSProductDetailsVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="pymtDetailsVOCollection" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSPymtDetailsVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSInvoiceInfoVO", propOrder = {
    "invoiceInfoID",
    "landAirFlag",
    "pricePerClientFlag",
    "pricePerClientVOCollection",
    "productDetailsVOCollection",
    "pymtDetailsVOCollection"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSInvoiceInfoVO {

    protected long invoiceInfoID;
    protected boolean landAirFlag;
    protected boolean pricePerClientFlag;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSPricePerClientVO> pricePerClientVOCollection;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSProductDetailsVO> productDetailsVOCollection;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtDetailsVO> pymtDetailsVOCollection;

    /**
     * Gets the value of the invoiceInfoID property.
     * 
     */
    public long getInvoiceInfoID() {
        return invoiceInfoID;
    }

    /**
     * Sets the value of the invoiceInfoID property.
     * 
     */
    public void setInvoiceInfoID(long value) {
        this.invoiceInfoID = value;
    }

    /**
     * Gets the value of the landAirFlag property.
     * 
     */
    public boolean isLandAirFlag() {
        return landAirFlag;
    }

    /**
     * Sets the value of the landAirFlag property.
     * 
     */
    public void setLandAirFlag(boolean value) {
        this.landAirFlag = value;
    }

    /**
     * Gets the value of the pricePerClientFlag property.
     * 
     */
    public boolean isPricePerClientFlag() {
        return pricePerClientFlag;
    }

    /**
     * Sets the value of the pricePerClientFlag property.
     * 
     */
    public void setPricePerClientFlag(boolean value) {
        this.pricePerClientFlag = value;
    }

    /**
     * Gets the value of the pricePerClientVOCollection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pricePerClientVOCollection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPricePerClientVOCollection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSPricePerClientVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSPricePerClientVO> getPricePerClientVOCollection() {
        if (pricePerClientVOCollection == null) {
            pricePerClientVOCollection = new ArrayList<ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSPricePerClientVO>();
        }
        return this.pricePerClientVOCollection;
    }

    /**
     * Gets the value of the productDetailsVOCollection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productDetailsVOCollection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductDetailsVOCollection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSProductDetailsVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSProductDetailsVO> getProductDetailsVOCollection() {
        if (productDetailsVOCollection == null) {
            productDetailsVOCollection = new ArrayList<ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSProductDetailsVO>();
        }
        return this.productDetailsVOCollection;
    }

    /**
     * Gets the value of the pymtDetailsVOCollection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pymtDetailsVOCollection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPymtDetailsVOCollection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtDetailsVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtDetailsVO> getPymtDetailsVOCollection() {
        if (pymtDetailsVOCollection == null) {
            pymtDetailsVOCollection = new ArrayList<ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtDetailsVO>();
        }
        return this.pymtDetailsVOCollection;
    }

}
