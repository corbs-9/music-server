//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.24 at 11:36:41 PM GMT 
//


package com.corbo.musicstreaming.model.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for trackCplxType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="trackCplxType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="trackName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="trackUuid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="albumUuid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="artistUuid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="durationInSeconds" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="trackNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trackCplxType", propOrder = {
    "trackName",
    "trackUuid",
    "albumUuid",
    "artistUuid",
    "durationInSeconds",
    "trackNumber"
})
public class TrackCplxType {

    @XmlElement(required = true)
    protected String trackName;
    @XmlElement(required = true)
    protected String trackUuid;
    @XmlElement(required = true)
    protected String albumUuid;
    @XmlElement(required = true)
    protected String artistUuid;
    protected int durationInSeconds;
    @XmlElement(required = true)
    protected String trackNumber;

    /**
     * Gets the value of the trackName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackName() {
        return trackName;
    }

    /**
     * Sets the value of the trackName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackName(String value) {
        this.trackName = value;
    }

    /**
     * Gets the value of the trackUuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackUuid() {
        return trackUuid;
    }

    /**
     * Sets the value of the trackUuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackUuid(String value) {
        this.trackUuid = value;
    }

    /**
     * Gets the value of the albumUuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlbumUuid() {
        return albumUuid;
    }

    /**
     * Sets the value of the albumUuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlbumUuid(String value) {
        this.albumUuid = value;
    }

    /**
     * Gets the value of the artistUuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtistUuid() {
        return artistUuid;
    }

    /**
     * Sets the value of the artistUuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtistUuid(String value) {
        this.artistUuid = value;
    }

    /**
     * Gets the value of the durationInSeconds property.
     * 
     */
    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    /**
     * Sets the value of the durationInSeconds property.
     * 
     */
    public void setDurationInSeconds(int value) {
        this.durationInSeconds = value;
    }

    /**
     * Gets the value of the trackNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackNumber() {
        return trackNumber;
    }

    /**
     * Sets the value of the trackNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackNumber(String value) {
        this.trackNumber = value;
    }

}
