
package com.wsout.habs.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsTourBrandInfoVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsTourBrandInfoVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BrandCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CompanyInfo" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsTourBrandCompanyInfoVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsTourBrandInfoVO", propOrder = {
    "brandCode",
    "companyInfo"
})
public class WsTourBrandInfoVO {

    @XmlElement(name = "BrandCode")
    protected String brandCode;
    @XmlElement(name = "CompanyInfo")
    protected List<WsTourBrandCompanyInfoVO> companyInfo;

    /**
     * Gets the value of the brandCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrandCode() {
        return brandCode;
    }

    /**
     * Sets the value of the brandCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrandCode(String value) {
        this.brandCode = value;
    }

    /**
     * Gets the value of the companyInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the companyInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCompanyInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WsTourBrandCompanyInfoVO }
     * 
     * 
     */
    public List<WsTourBrandCompanyInfoVO> getCompanyInfo() {
        if (companyInfo == null) {
            companyInfo = new ArrayList<WsTourBrandCompanyInfoVO>();
        }
        return this.companyInfo;
    }

}
