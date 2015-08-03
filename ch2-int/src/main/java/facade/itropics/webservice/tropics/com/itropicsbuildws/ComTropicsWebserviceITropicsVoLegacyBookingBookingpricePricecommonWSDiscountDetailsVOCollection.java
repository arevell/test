
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSDiscountDetailsVOCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSDiscountDetailsVOCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wsDiscountDetailsVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSDiscountDetailsVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSDiscountDetailsVOCollection", propOrder = {
    "wsDiscountDetailsVO"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSDiscountDetailsVOCollection {

    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSDiscountDetailsVO> wsDiscountDetailsVO;

    /**
     * Gets the value of the wsDiscountDetailsVO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsDiscountDetailsVO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsDiscountDetailsVO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSDiscountDetailsVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSDiscountDetailsVO> getWsDiscountDetailsVO() {
        if (wsDiscountDetailsVO == null) {
            wsDiscountDetailsVO = new ArrayList<ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSDiscountDetailsVO>();
        }
        return this.wsDiscountDetailsVO;
    }

}
