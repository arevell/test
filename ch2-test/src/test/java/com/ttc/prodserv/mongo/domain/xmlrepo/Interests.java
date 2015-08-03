
package com.ttc.prodserv.mongo.domain.xmlrepo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for interests complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="interests">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="archaeologicalSites" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="art" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="experiencingLocalCulture" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="history" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="literature" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="music" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="otherInterests" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="otherInterestsDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="photography" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="samplingLocalCuisine" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="shopping" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="wineTasting" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "interests", propOrder = {
    "archaeologicalSites",
    "art",
    "experiencingLocalCulture",
    "history",
    "literature",
    "music",
    "otherInterests",
    "otherInterestsDescription",
    "photography",
    "samplingLocalCuisine",
    "shopping",
    "wineTasting"
})
public class Interests {

    protected boolean archaeologicalSites;
    protected boolean art;
    protected boolean experiencingLocalCulture;
    protected boolean history;
    protected boolean literature;
    protected boolean music;
    protected boolean otherInterests;
    protected String otherInterestsDescription;
    protected boolean photography;
    protected boolean samplingLocalCuisine;
    protected boolean shopping;
    protected boolean wineTasting;

    /**
     * Gets the value of the archaeologicalSites property.
     * 
     */
    public boolean isArchaeologicalSites() {
        return archaeologicalSites;
    }

    /**
     * Sets the value of the archaeologicalSites property.
     * 
     */
    public void setArchaeologicalSites(boolean value) {
        this.archaeologicalSites = value;
    }

    /**
     * Gets the value of the art property.
     * 
     */
    public boolean isArt() {
        return art;
    }

    /**
     * Sets the value of the art property.
     * 
     */
    public void setArt(boolean value) {
        this.art = value;
    }

    /**
     * Gets the value of the experiencingLocalCulture property.
     * 
     */
    public boolean isExperiencingLocalCulture() {
        return experiencingLocalCulture;
    }

    /**
     * Sets the value of the experiencingLocalCulture property.
     * 
     */
    public void setExperiencingLocalCulture(boolean value) {
        this.experiencingLocalCulture = value;
    }

    /**
     * Gets the value of the history property.
     * 
     */
    public boolean isHistory() {
        return history;
    }

    /**
     * Sets the value of the history property.
     * 
     */
    public void setHistory(boolean value) {
        this.history = value;
    }

    /**
     * Gets the value of the literature property.
     * 
     */
    public boolean isLiterature() {
        return literature;
    }

    /**
     * Sets the value of the literature property.
     * 
     */
    public void setLiterature(boolean value) {
        this.literature = value;
    }

    /**
     * Gets the value of the music property.
     * 
     */
    public boolean isMusic() {
        return music;
    }

    /**
     * Sets the value of the music property.
     * 
     */
    public void setMusic(boolean value) {
        this.music = value;
    }

    /**
     * Gets the value of the otherInterests property.
     * 
     */
    public boolean isOtherInterests() {
        return otherInterests;
    }

    /**
     * Sets the value of the otherInterests property.
     * 
     */
    public void setOtherInterests(boolean value) {
        this.otherInterests = value;
    }

    /**
     * Gets the value of the otherInterestsDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherInterestsDescription() {
        return otherInterestsDescription;
    }

    /**
     * Sets the value of the otherInterestsDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherInterestsDescription(String value) {
        this.otherInterestsDescription = value;
    }

    /**
     * Gets the value of the photography property.
     * 
     */
    public boolean isPhotography() {
        return photography;
    }

    /**
     * Sets the value of the photography property.
     * 
     */
    public void setPhotography(boolean value) {
        this.photography = value;
    }

    /**
     * Gets the value of the samplingLocalCuisine property.
     * 
     */
    public boolean isSamplingLocalCuisine() {
        return samplingLocalCuisine;
    }

    /**
     * Sets the value of the samplingLocalCuisine property.
     * 
     */
    public void setSamplingLocalCuisine(boolean value) {
        this.samplingLocalCuisine = value;
    }

    /**
     * Gets the value of the shopping property.
     * 
     */
    public boolean isShopping() {
        return shopping;
    }

    /**
     * Sets the value of the shopping property.
     * 
     */
    public void setShopping(boolean value) {
        this.shopping = value;
    }

    /**
     * Gets the value of the wineTasting property.
     * 
     */
    public boolean isWineTasting() {
        return wineTasting;
    }

    /**
     * Sets the value of the wineTasting property.
     * 
     */
    public void setWineTasting(boolean value) {
        this.wineTasting = value;
    }

}
