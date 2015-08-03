
package com.ttc.ch2.api.ccapi.v3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CurrentBrandStatusType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CurrentBrandStatusType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="snapshot" type="{http://www.ttc.com/ch2/api/ccapi/v3}SnapshotType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="brandStatus" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CurrentBrandStatusType", propOrder = {
    "snapshot"
})
public class CurrentBrandStatusType {

    protected SnapshotType snapshot;
    @XmlAttribute(required = true)
    protected String brandStatus;

    /**
     * Gets the value of the snapshot property.
     * 
     * @return
     *     possible object is
     *     {@link SnapshotType }
     *     
     */
    public SnapshotType getSnapshot() {
        return snapshot;
    }

    /**
     * Sets the value of the snapshot property.
     * 
     * @param value
     *     allowed object is
     *     {@link SnapshotType }
     *     
     */
    public void setSnapshot(SnapshotType value) {
        this.snapshot = value;
    }

    /**
     * Gets the value of the brandStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrandStatus() {
        return brandStatus;
    }

    /**
     * Sets the value of the brandStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrandStatus(String value) {
        this.brandStatus = value;
    }

}
