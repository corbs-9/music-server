package com.corbo.musicstreaming.entity;

import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table("track")
public class Track {

	@PrimaryKey(value = "id")
	private UUID trackId;

	@Column(value = "name")
	private String trackName;
	
	@Column(value = "album_id")
	private UUID albumId;

	@Column(value = "album_name")
	private String albumName;
	
	@Column(value = "artist_id")
	private UUID artistId;

	@Column(value = "artist_name")
	private String artistName;

	@Column(value = "number")
	private String trackNumber;

	@Column(value = "location")
	private String filePath;

	@Column(value = "year")
	private String year;

	/**
	 * @return the trackId
	 */
	public UUID getTrackId() {
		return trackId;
	}

	/**
	 * @param trackId
	 *            the trackId to set
	 */
	public void setTrackId(UUID trackId) {
		this.trackId = trackId;
	}

	/**
	 * @return the trackName
	 */
	public String getTrackName() {
		return trackName;
	}

	/**
	 * @param trackName
	 *            the trackName to set
	 */
	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	/**
	 * @return the albumName
	 */
	public String getAlbumName() {
		return albumName;
	}

	/**
	 * @param albumName
	 *            the albumName to set
	 */
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	/**
	 * @return the artistName
	 */
	public String getArtistName() {
		return artistName;
	}

	/**
	 * @param artistName
	 *            the artistName to set
	 */
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	/**
	 * @return the trackNumber
	 */
	public String getTrackNumber() {
		return trackNumber;
	}

	/**
	 * @param trackNumber
	 *            the trackNumber to set
	 */
	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath
	 *            the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public UUID getAlbumId() {
		return albumId;
	}

	public void setAlbumId(UUID albumId) {
		this.albumId = albumId;
	}

	public UUID getArtistId() {
		return artistId;
	}

	public void setArtistId(UUID artistId) {
		this.artistId = artistId;
	}

}
