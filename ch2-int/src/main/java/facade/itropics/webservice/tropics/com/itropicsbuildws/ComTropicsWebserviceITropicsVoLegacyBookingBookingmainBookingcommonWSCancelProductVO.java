
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_WSCancelProductVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_WSCancelProductVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bookingCancellationFeesVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSBookingCancellationFeesVO" minOccurs="0"/>
 *         &lt;element name="bookingSummaryVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_WSBookingSummaryVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_WSCancelProductVO", propOrder = {
    "bookingCancellationFeesVO",
    "bookingSummaryVO"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSCancelProductVO {

    protected ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSBookingCancellationFeesVO bookingCancellationFeesVO;
    protected ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSBookingSummaryVO bookingSummaryVO;

    /**
     * Gets the value of the bookingCancellationFeesVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSBookingCancellationFeesVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSBookingCancellationFeesVO getBookingCancellationFeesVO() {
        return bookingCancellationFeesVO;
    }

    /**
     * Sets the value of the bookingCancellationFeesVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSBookingCancellationFeesVO }
     *     
     */
    public void setBookingCancellationFeesVO(ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSBookingCancellationFeesVO value) {
        this.bookingCancellationFeesVO = value;
    }

    /**
     * Gets the value of the bookingSummaryVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSBookingSummaryVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSBookingSummaryVO getBookingSummaryVO() {
        return bookingSummaryVO;
    }

    /**
     * Sets the value of the bookingSummaryVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSBookingSummaryVO }
     *     
     */
    public void setBookingSummaryVO(ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSBookingSummaryVO value) {
        this.bookingSummaryVO = value;
    }

}
