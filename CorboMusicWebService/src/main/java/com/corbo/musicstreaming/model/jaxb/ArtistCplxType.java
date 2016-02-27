package com.corbo.musicstreaming.model.jaxb;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class ArtistCplxType {

	@ApiModelProperty(notes = "The name of the artist", required = true)
	protected String artistName;	
	@ApiModelProperty(notes = "The artist Id", required = true)
	protected String artistId;
	@ApiModelProperty(notes = "A list of albums for the artist", required = true)
	protected List<AlbumList> albumList;

	/**
	 * Gets the value of the artistName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getArtistName() {
		return artistName;
	}

	/**
	 * Sets the value of the artistName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setArtistName(String value) {
		this.artistName = value;
	}

	/**
	 * Gets the value of the artistUuid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getArtistId() {
		return artistId;
	}

	/**
	 * Sets the value of the artistUuid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setArtistId(String value) {
		this.artistId = value;
	}

	/**
	 * Gets the value of the albumList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the albumList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getAlbumList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link AlbumList
	 * }
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
