package com.corbo.musicstreaming.vo;

/**
 * <p>
 * Value object which represents an album.
 * </p>
 * 
 * @author corbo
 *
 */
public class Album {

	private Album(Builder builder) {
		this.id = builder.id;
		this.artistId = builder.artistId;
		this.name = builder.name;
		this.year = builder.year;
	}

	private String id;
	private String artistId;
	private String name;
	private int year;

	public static class Builder {
		private String id;
		private String artistId;
		private String name;
		private int year;

		public Builder id(String val) {
			this.id = val;
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

		public Builder year(int val) {
			this.year = val;
			return this;
		}

		public Album build() {
			return new Album(this);
		}
	}

	public String getId() {
		return id;
	}

	public String getArtistId() {
		return artistId;
	}

	public String getName() {
		return name;
	}

	public int getYear() {
		return year;
	}
}
