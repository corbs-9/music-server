package com.corbo.musicstreaming.model.jaxb;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//@ApiModel(description = "The top level response element to be returned to clients.")
public class MusicList {

	@ApiModelProperty(notes = "A list of artists", required = true)
	protected ArtistList artistList;

	/**
	 * Gets the value of the artistList property.
	 * 
	 * @return possible object is {@link ArtistList }
	 * 
	 */
	public ArtistList getArtistList() {
		return artistList;
	}

	/**
	 * Sets the value of the artistList property.
	 * 
	 * @param value
	 *            allowed object is {@link ArtistList }
	 * 
	 */
	public void setArtistList(ArtistList value) {
		this.artistList = value;
	}

}
