
package com.wsout.habs.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsTourOfBrandVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsTourOfBrandVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TourCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OperatingProductCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OnlineBookable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="BrandInfo" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsTourBrandInfoVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsTourOfBrandVO", propOrder = {
    "tourCode",
    "operatingProductCode",
    "onlineBookable",
    "brandInfo"
})
public class WsTourOfBrandVO {

    @XmlElement(name = "TourCode")
    protected String tourCode;
    @XmlElement(name = "OperatingProductCode")
    protected String operatingProductCode;
    @XmlElement(name = "OnlineBookable")
    protected Boolean onlineBookable;
    @XmlElement(name = "BrandInfo")
    protected List<WsTourBrandInfoVO> brandInfo;

    /**
     * Gets the value of the tourCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTourCode() {
        return tourCode;
    }

    /**
     * Sets the value of the tourCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTourCode(String value) {
        this.tourCode = value;
    }

    /**
     * Gets the value of the operatingProductCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatingProductCode() {
        return operatingProductCode;
    }

    /**
     * Sets the value of the operatingProductCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatingProductCode(String value) {
        this.operatingProductCode = value;
    }

    /**
     * Gets the value of the onlineBookable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOnlineBookable() {
        return onlineBookable;
    }

    /**
     * Sets the value of the onlineBookable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOnlineBookable(Boolean value) {
        this.onlineBookable = value;
    }

    /**
     * Gets the value of the brandInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the brandInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBrandInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WsTourBrandInfoVO }
     * 
     * 
     */
    public List<WsTourBrandInfoVO> getBrandInfo() {
        if (brandInfo == null) {
            brandInfo = new ArrayList<WsTourBrandInfoVO>();
        }
        return this.brandInfo;
    }

}
