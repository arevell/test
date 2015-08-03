
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSPymtTranDetailsVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSPymtTranDetailsVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pymtModeDiscInfoVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSPymtModeDiscInfoVO" minOccurs="0"/>
 *         &lt;element name="pymtTranVOArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSPymtTranVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="totalAmountPaid" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalCommissionPaid" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSPymtTranDetailsVO", propOrder = {
    "pymtModeDiscInfoVO",
    "pymtTranVOArray",
    "totalAmountPaid",
    "totalCommissionPaid"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtTranDetailsVO {

    protected ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtModeDiscInfoVO pymtModeDiscInfoVO;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtTranVO> pymtTranVOArray;
    protected float totalAmountPaid;
    protected float totalCommissionPaid;

    /**
     * Gets the value of the pymtModeDiscInfoVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtModeDiscInfoVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtModeDiscInfoVO getPymtModeDiscInfoVO() {
        return pymtModeDiscInfoVO;
    }

    /**
     * Sets the value of the pymtModeDiscInfoVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtModeDiscInfoVO }
     *     
     */
    public void setPymtModeDiscInfoVO(ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtModeDiscInfoVO value) {
        this.pymtModeDiscInfoVO = value;
    }

    /**
     * Gets the value of the pymtTranVOArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pymtTranVOArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPymtTranVOArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtTranVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtTranVO> getPymtTranVOArray() {
        if (pymtTranVOArray == null) {
            pymtTranVOArray = new ArrayList<ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtTranVO>();
        }
        return this.pymtTranVOArray;
    }

    /**
     * Gets the value of the totalAmountPaid property.
     * 
     */
    public float getTotalAmountPaid() {
        return totalAmountPaid;
    }

    /**
     * Sets the value of the totalAmountPaid property.
     * 
     */
    public void setTotalAmountPaid(float value) {
        this.totalAmountPaid = value;
    }

    /**
     * Gets the value of the totalCommissionPaid property.
     * 
     */
    public float getTotalCommissionPaid() {
        return totalCommissionPaid;
    }

    /**
     * Sets the value of the totalCommissionPaid property.
     * 
     */
    public void setTotalCommissionPaid(float value) {
        this.totalCommissionPaid = value;
    }

}
