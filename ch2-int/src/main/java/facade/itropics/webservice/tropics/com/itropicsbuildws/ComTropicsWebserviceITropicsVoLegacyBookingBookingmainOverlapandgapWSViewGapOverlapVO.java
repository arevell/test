
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_overlapandgap_WSViewGapOverlapVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_overlapandgap_WSViewGapOverlapVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="viewClientGapVOCollection" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_overlapandgap_WSViewClientGapVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="viewClientOverlapVOCollection" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_overlapandgap_WSViewClientOverlapVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_overlapandgap_WSViewGapOverlapVO", propOrder = {
    "viewClientGapVOCollection",
    "viewClientOverlapVOCollection"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingmainOverlapandgapWSViewGapOverlapVO {

    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyBookingBookingmainOverlapandgapWSViewClientGapVO> viewClientGapVOCollection;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyBookingBookingmainOverlapandgapWSViewClientOverlapVO> viewClientOverlapVOCollection;

    /**
     * Gets the value of the viewClientGapVOCollection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the viewClientGapVOCollection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getViewClientGapVOCollection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingmainOverlapandgapWSViewClientGapVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyBookingBookingmainOverlapandgapWSViewClientGapVO> getViewClientGapVOCollection() {
        if (viewClientGapVOCollection == null) {
            viewClientGapVOCollection = new ArrayList<ComTropicsWebserviceITropicsVoLegacyBookingBookingmainOverlapandgapWSViewClientGapVO>();
        }
        return this.viewClientGapVOCollection;
    }

    /**
     * Gets the value of the viewClientOverlapVOCollection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the viewClientOverlapVOCollection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getViewClientOverlapVOCollection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingmainOverlapandgapWSViewClientOverlapVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyBookingBookingmainOverlapandgapWSViewClientOverlapVO> getViewClientOverlapVOCollection() {
        if (viewClientOverlapVOCollection == null) {
            viewClientOverlapVOCollection = new ArrayList<ComTropicsWebserviceITropicsVoLegacyBookingBookingmainOverlapandgapWSViewClientOverlapVO>();
        }
        return this.viewClientOverlapVOCollection;
    }

}
