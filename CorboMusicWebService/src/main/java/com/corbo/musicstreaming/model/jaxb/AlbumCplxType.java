package com.corbo.musicstreaming.model.jaxb;

import io.swagger.annotations.ApiModelProperty;

public class AlbumCplxType {

	@ApiModelProperty(notes = "The name of the album", required = true)
	protected String albumName;

	@ApiModelProperty(notes = "The name of the artist", required = true)
	protected String artistName;

	@ApiModelProperty(notes = "The track list associated with the album", required = true)
	protected TrackList trackList;

	/**
	 * Gets the value of the albumName property. s
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAlbumName() {
		return albumName;
	}

	/**
	 * Sets the value of the albumName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAlbumName(String value) {
		this.albumName = value;
	}

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
	 * Gets the value of the trackList property.
	 * 
	 * @return possible object is {@link TrackList }
	 * 
	 */
	public TrackList getTrackList() {
		return trackList;
	}

	/**
	 * Sets the value of the trackList property.
	 * 
	 * @param value
	 *            allowed object is {@link TrackList }
	 * 
	 */
	public void setTrackList(TrackList value) {
		this.trackList = value;
	}

}
