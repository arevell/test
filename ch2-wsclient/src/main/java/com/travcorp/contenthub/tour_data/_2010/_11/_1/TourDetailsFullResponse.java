
package com.travcorp.contenthub.tour_data._2010._11._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ttsl.marketlocalisedtourdata._2010._11._1.MarketLocalisedTourData;


/**
 * <p>Java class for tourDetailsFullResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tourDetailsFullResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.ttsl.com/MarketLocalisedTourData/2010/11/1.0}MarketLocalisedTourData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tourDetailsFullResponse", propOrder = {
    "marketLocalisedTourData"
})
public class TourDetailsFullResponse {

    @XmlElement(name = "MarketLocalisedTourData", namespace = "http://www.ttsl.com/MarketLocalisedTourData/2010/11/1.0")
    protected MarketLocalisedTourData marketLocalisedTourData;

    /**
     * Gets the value of the marketLocalisedTourData property.
     * 
     * @return
     *     possible object is
     *     {@link MarketLocalisedTourData }
     *     
     */
    public MarketLocalisedTourData getMarketLocalisedTourData() {
        return marketLocalisedTourData;
    }

    /**
     * Sets the value of the marketLocalisedTourData property.
     * 
     * @param value
     *     allowed object is
     *     {@link MarketLocalisedTourData }
     *     
     */
    public void setMarketLocalisedTourData(MarketLocalisedTourData value) {
        this.marketLocalisedTourData = value;
    }

}
