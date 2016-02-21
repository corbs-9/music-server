//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.15 at 10:42:22 PM GMT 
//


package com.corbo.musicstreaming.model.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for artistCplxType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="artistCplxType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="artistName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="artistUuid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="albumList" type="{}albumList" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "artistCplxType", propOrder = {
    "artistName",
    "artistUuid",
    "albumList"
})
public class ArtistCplxType {

    @XmlElement(required = true)
    protected String artistName;
    @XmlElement(required = true)
    protected String artistUuid;
    @XmlElement(required = true)
    protected List<AlbumList> albumList;

    /**
     * Gets the value of the artistName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * Sets the value of the artistName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtistName(String value) {
        this.artistName = value;
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
     * Gets the value of the albumList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the albumList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlbumList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AlbumList }
     * 
     * 
     */
    public List<AlbumList> getAlbumList() {
        if (albumList == null) {
            albumList = new ArrayList<AlbumList>();
        }
        return this.albumList;
    }

}