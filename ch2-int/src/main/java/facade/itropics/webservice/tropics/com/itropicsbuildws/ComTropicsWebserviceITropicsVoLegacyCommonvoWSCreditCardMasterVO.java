
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_commonvo_WSCreditCardMasterVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_commonvo_WSCreditCardMasterVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cardLength" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cardPrefix" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cardType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditCardCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditCardMasterID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="creditCardName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="masterName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="searchMasterVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_commonvo_WSSearchMasterVO" minOccurs="0"/>
 *         &lt;element name="updateFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_commonvo_WSCreditCardMasterVO", propOrder = {
    "cardLength",
    "cardPrefix",
    "cardType",
    "creditCardCode",
    "creditCardMasterID",
    "creditCardName",
    "masterName",
    "searchMasterVO",
    "updateFlag"
})
public class ComTropicsWebserviceITropicsVoLegacyCommonvoWSCreditCardMasterVO {

    protected int cardLength;
    protected int cardPrefix;
    protected String cardType;
    protected String creditCardCode;
    protected long creditCardMasterID;
    protected String creditCardName;
    protected String masterName;
    protected ComTropicsWebserviceITropicsVoLegacyCommonvoWSSearchMasterVO searchMasterVO;
    protected boolean updateFlag;

    /**
     * Gets the value of the cardLength property.
     * 
     */
    public int getCardLength() {
        return cardLength;
    }

    /**
     * Sets the value of the cardLength property.
     * 
     */
    public void setCardLength(int value) {
        this.cardLength = value;
    }

    /**
     * Gets the value of the cardPrefix property.
     * 
     */
    public int getCardPrefix() {
        return cardPrefix;
    }

    /**
     * Sets the value of the cardPrefix property.
     * 
     */
    public void setCardPrefix(int value) {
        this.cardPrefix = value;
    }

    /**
     * Gets the value of the cardType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * Sets the value of the cardType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardType(String value) {
        this.cardType = value;
    }

    /**
     * Gets the value of the creditCardCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardCode() {
        return creditCardCode;
    }

    /**
     * Sets the value of the creditCardCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardCode(String value) {
        this.creditCardCode = value;
    }

    /**
     * Gets the value of the creditCardMasterID property.
     * 
     */
    public long getCreditCardMasterID() {
        return creditCardMasterID;
    }

    /**
     * Sets the value of the creditCardMasterID property.
     * 
     */
    public void setCreditCardMasterID(long value) {
        this.creditCardMasterID = value;
    }

    /**
     * Gets the value of the creditCardName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardName() {
        return creditCardName;
    }

    /**
     * Sets the value of the creditCardName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardName(String value) {
        this.creditCardName = value;
    }

    /**
     * Gets the value of the masterName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMasterName() {
        return masterName;
    }

    /**
     * Sets the value of the masterName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMasterName(String value) {
        this.masterName = value;
    }

    /**
     * Gets the value of the searchMasterVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyCommonvoWSSearchMasterVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyCommonvoWSSearchMasterVO getSearchMasterVO() {
        return searchMasterVO;
    }

    /**
     * Sets the value of the searchMasterVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyCommonvoWSSearchMasterVO }
     *     
     */
    public void setSearchMasterVO(ComTropicsWebserviceITropicsVoLegacyCommonvoWSSearchMasterVO value) {
        this.searchMasterVO = value;
    }

    /**
     * Gets the value of the updateFlag property.
     * 
     */
    public boolean isUpdateFlag() {
        return updateFlag;
    }

    /**
     * Sets the value of the updateFlag property.
     * 
     */
    public void setUpdateFlag(boolean value) {
        this.updateFlag = value;
    }

}
