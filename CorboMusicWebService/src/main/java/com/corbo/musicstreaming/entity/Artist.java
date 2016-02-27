package com.corbo.musicstreaming.entity;

import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table("artist")
public class Artist {

	@PrimaryKey(value = "id")
	private UUID artistId;
	
	@Column(value = "name")
	private String artistName;

	@Column(value = "first_letter")
	private String firstLetter;

	/**
	 * @return the artistId
	 */
	public UUID getArtistId() {
		return artistId;
	}

	/**
	 * @param artistId the artistId to set
	 */
	public void setArtistId(UUID artistId) {
		this.artistId = artistId;
	}

	/**
	 * @return the artistName
	 */
	public String getArtistName() {
		return artistName;
	}

	/**
	 * @param artistName the artistName to set
	 */
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	/**
	 * @return the firstLetter
	 */
	public String getFirstLetter() {
		return firstLetter;
	}

	/**
	 * @param firstLetter the firstLetter to set
	 */
	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}