
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_finance_WSSecurityInfoResponseVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_finance_WSSecurityInfoResponseVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="echoData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="issuerUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paRequest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_finance_WSSecurityInfoResponseVO", propOrder = {
    "echoData",
    "issuerUrl",
    "orderCode",
    "paRequest"
})
public class ComTropicsWebserviceITropicsVoLegacyFinanceWSSecurityInfoResponseVO {

    protected String echoData;
    protected String issuerUrl;
    protected String orderCode;
    protected String paRequest;

    /**
     * Gets the value of the echoData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEchoData() {
        return echoData;
    }

    /**
     * Sets the value of the echoData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEchoData(String value) {
        this.echoData = value;
    }

    /**
     * Gets the value of the issuerUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuerUrl() {
        return issuerUrl;
    }

    /**
     * Sets the value of the issuerUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuerUrl(String value) {
        this.issuerUrl = value;
    }

    /**
     * Gets the value of the orderCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * Sets the value of the orderCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderCode(String value) {
        this.orderCode = value;
    }

    /**
     * Gets the value of the paRequest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaRequest() {
        return paRequest;
    }

    /**
     * Sets the value of the paRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaRequest(String value) {
        this.paRequest = value;
    }

}
