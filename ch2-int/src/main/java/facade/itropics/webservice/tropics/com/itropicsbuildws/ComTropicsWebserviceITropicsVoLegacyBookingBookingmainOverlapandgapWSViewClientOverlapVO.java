
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_overlapandgap_WSViewClientOverlapVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_overlapandgap_WSViewClientOverlapVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clientID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="clientName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="viewOverlapVOCollection" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_overlapandgap_WSViewOverlapVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_overlapandgap_WSViewClientOverlapVO", propOrder = {
    "clientID",
    "clientName",
    "viewOverlapVOCollection"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingmainOverlapandgapWSViewClientOverlapVO {

    protected long clientID;
    protected String clientName;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyBookingBookingmainOverlapandgapWSViewOverlapVO> viewOverlapVOCollection;

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
     * Gets the value of the viewOverlapVOCollection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the viewOverlapVOCollection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getViewOverlapVOCollection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingmainOverlapandgapWSViewOverlapVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyBookingBookingmainOverlapandgapWSViewOverlapVO> getViewOverlapVOCollection() {
        if (viewOverlapVOCollection == null) {
            viewOverlapVOCollection = new ArrayList<ComTropicsWebserviceITropicsVoLegacyBookingBookingmainOverlapandgapWSViewOverlapVO>();
        }
        return this.viewOverlapVOCollection;
    }

}
