
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsTourRulesRoomsVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsTourRulesRoomsVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Room" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsTourRulesRoomVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsTourRulesRoomsVO", propOrder = {
    "room"
})
public class WsTourRulesRoomsVO {

    @XmlElement(name = "Room")
    protected List<WsTourRulesRoomVO> room;

    /**
     * Gets the value of the room property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the room property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoom().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WsTourRulesRoomVO }
     * 
     * 
     */
    public List<WsTourRulesRoomVO> getRoom() {
        if (room == null) {
            room = new ArrayList<WsTourRulesRoomVO>();
        }
        return this.room;
    }

}
