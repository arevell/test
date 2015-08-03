
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_client_WSBookingClientDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_client_WSBookingClientDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wsClientProductVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_client_WSClientProductVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wsClientVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_client_WSClientVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_client_WSBookingClientDetails", propOrder = {
    "wsClientProductVO",
    "wsClientVO"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonClientWSBookingClientDetails {

    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonClientWSClientProductVO> wsClientProductVO;
    protected ComTropicsWebserviceITropicsVoLegacyBookingBookingmainClientWSClientVO wsClientVO;

    /**
     * Gets the value of the wsClientProductVO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsClientProductVO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsClientProductVO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonClientWSClientProductVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonClientWSClientProductVO> getWsClientProductVO() {
        if (wsClientProductVO == null) {
            wsClientProductVO = new ArrayList<ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonClientWSClientProductVO>();
        }
        return this.wsClientProductVO;
    }

    /**
     * Gets the value of the wsClientVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingmainClientWSClientVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyBookingBookingmainClientWSClientVO getWsClientVO() {
        return wsClientVO;
    }

    /**
     * Sets the value of the wsClientVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingmainClientWSClientVO }
     *     
     */
    public void setWsClientVO(ComTropicsWebserviceITropicsVoLegacyBookingBookingmainClientWSClientVO value) {
        this.wsClientVO = value;
    }

}
