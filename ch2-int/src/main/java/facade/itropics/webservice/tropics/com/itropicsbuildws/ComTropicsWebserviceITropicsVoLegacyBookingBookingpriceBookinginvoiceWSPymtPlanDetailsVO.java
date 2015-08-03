
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSPymtPlanDetailsVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSPymtPlanDetailsVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pymtDetailsVOCollection" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSPymtDetailsVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="totComm" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totDiscount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totFees" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totGrossPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totGstVat" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totNettPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totOutstandingBalance" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totPaymentReceived" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSPymtPlanDetailsVO", propOrder = {
    "pymtDetailsVOCollection",
    "totComm",
    "totDiscount",
    "totFees",
    "totGrossPrice",
    "totGstVat",
    "totNettPrice",
    "totOutstandingBalance",
    "totPaymentReceived"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtPlanDetailsVO {

    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtDetailsVO> pymtDetailsVOCollection;
    protected float totComm;
    protected float totDiscount;
    protected float totFees;
    protected float totGrossPrice;
    protected float totGstVat;
    protected float totNettPrice;
    protected float totOutstandingBalance;
    protected float totPaymentReceived;

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

    /**
     * Gets the value of the totComm property.
     * 
     */
    public float getTotComm() {
        return totComm;
    }

    /**
     * Sets the value of the totComm property.
     * 
     */
    public void setTotComm(float value) {
        this.totComm = value;
    }

    /**
     * Gets the value of the totDiscount property.
     * 
     */
    public float getTotDiscount() {
        return totDiscount;
    }

    /**
     * Sets the value of the totDiscount property.
     * 
     */
    public void setTotDiscount(float value) {
        this.totDiscount = value;
    }

    /**
     * Gets the value of the totFees property.
     * 
     */
    public float getTotFees() {
        return totFees;
    }

    /**
     * Sets the value of the totFees property.
     * 
     */
    public void setTotFees(float value) {
        this.totFees = value;
    }

    /**
     * Gets the value of the totGrossPrice property.
     * 
     */
    public float getTotGrossPrice() {
        return totGrossPrice;
    }

    /**
     * Sets the value of the totGrossPrice property.
     * 
     */
    public void setTotGrossPrice(float value) {
        this.totGrossPrice = value;
    }

    /**
     * Gets the value of the totGstVat property.
     * 
     */
    public float getTotGstVat() {
        return totGstVat;
    }

    /**
     * Sets the value of the totGstVat property.
     * 
     */
    public void setTotGstVat(float value) {
        this.totGstVat = value;
    }

    /**
     * Gets the value of the totNettPrice property.
     * 
     */
    public float getTotNettPrice() {
        return totNettPrice;
    }

    /**
     * Sets the value of the totNettPrice property.
     * 
     */
    public void setTotNettPrice(float value) {
        this.totNettPrice = value;
    }

    /**
     * Gets the value of the totOutstandingBalance property.
     * 
     */
    public float getTotOutstandingBalance() {
        return totOutstandingBalance;
    }

    /**
     * Sets the value of the totOutstandingBalance property.
     * 
     */
    public void setTotOutstandingBalance(float value) {
        this.totOutstandingBalance = value;
    }

    /**
     * Gets the value of the totPaymentReceived property.
     * 
     */
    public float getTotPaymentReceived() {
        return totPaymentReceived;
    }

    /**
     * Sets the value of the totPaymentReceived property.
     * 
     */
    public void setTotPaymentReceived(float value) {
        this.totPaymentReceived = value;
    }

}
