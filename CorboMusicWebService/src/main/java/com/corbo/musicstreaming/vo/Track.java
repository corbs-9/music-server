package com.corbo.musicstreaming.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * <p>
 * Class representing a track
 * </p>
 * 
 * @author corbo
 *
 */
public class Track {

	private final String id;
	private final String albumId;
	private final String artistId;
	private final String name;
	private final int number;
	private final int durationInSeconds;
	private final String fullFilePath;

	private Track(Builder b) {
		this.id = b.id;
		this.albumId = b.albumId;
		this.artistId = b.artistId;
		this.name = b.name;
		this.number = b.number;
		this.durationInSeconds = b.durationInSeconds;
		this.fullFilePath = b.fullFilePath;
	}

	public static class Builder {
		private String id;
		private String albumId;
		private String artistId;
		private String name;
		private int number;
		private int durationInSeconds;
		private String fullFilePath;

		public Builder id(String val) {
			this.id = val;
			return this;
		}

		public Builder albumId(String val) {
			this.albumId = val;
			return this;
		}

		public Builder artistId(String val) {
			this.artistId = val;
			return this;
		}

		public Builder name(String val) {
			this.name = val;
			return this;
		}

		public Builder number(int val) {
			this.number = val;
			return this;
		}

		public Builder durationInSeconds(int val) {
			this.durationInSeconds = val;
			return this;
		}

		public Builder fullFilePath(String value) {
			this.fullFilePath = value;
			return this;
		}

		public Track build() {
			return new Track(this);
		}
	}

	public final String getId() {
		return new String(id);
	}

	public final String getAlbumId() {
		return new String(albumId);
	}

	public final String getArtistId() {
		return new String(artistId);
	}

	public final String getName() {
		return new String(name);
	}

	public final int getNumber() {
		return new Integer(number);
	}

	public final int getDurationInSeconds() {
		return new Integer(durationInSeconds);
	}

	public final String getFullFilePath() {
		return new String(fullFilePath);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
