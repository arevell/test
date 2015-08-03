
package com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProductRoomsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductRoomsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Room" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}ProductRoomType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductRoomsType", propOrder = {
    "room"
})
public class ProductRoomsType {

    @XmlElement(name = "Room", required = true)
    protected List<ProductRoomType> room;

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
     * {@link ProductRoomType }
     * 
     * 
     */
    public List<ProductRoomType> getRoom() {
        if (room == null) {
            room = new ArrayList<ProductRoomType>();
        }
        return this.room;
    }

}
