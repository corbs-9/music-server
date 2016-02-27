package com.corbo.musicstreaming.model.jaxb;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//@ApiModel(description = "A container for a list of artists")
public class ArtistList {

	@ApiModelProperty(notes = "Contains a list of artists", required = true)
	protected List<ArtistCplxType> artist;

	/**
	 * Gets the value of the artist property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the artist property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getArtist().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link ArtistCplxType }
	 * 
	 * 
	 */
	public List<ArtistCplxType> getArtist() {
		if (artist == null) {
			artist = new ArrayList<ArtistCplxType>();
		}
		return this.artist;
	}

}
