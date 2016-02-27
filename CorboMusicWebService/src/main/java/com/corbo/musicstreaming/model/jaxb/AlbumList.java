package com.corbo.musicstreaming.model.jaxb;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//@ApiModel(description = "A container for a list of albums")
public class AlbumList {

	@ApiModelProperty(notes = "Contains a list of albums", required = true)
	protected List<AlbumCplxType> album;

	/**
	 * Gets the value of the album property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the object. This is why there is not a
	 * <CODE>set</CODE> method for the album property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getAlbum().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link AlbumCplxType }
	 * 
	 * 
	 */
	public List<AlbumCplxType> getAlbum() {
		if (album == null) {
			album = new ArrayList<AlbumCplxType>();
		}
		return this.album;
	}

}
