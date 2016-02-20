package com.corbo.musicstreaming.vo;

public class Album {

	private Album(Builder builder) {
		this.albumUuid = builder.albumUuid;
		this.artistUuid = builder.artistUuid;
		this.albumName = builder.albumName;
		this.albumYear = builder.albumYear;
	}

	private String albumUuid;
	private String artistUuid;
	private String albumName;
	private int albumYear;

	public static class Builder {
		private String albumUuid;
		private String artistUuid;
		private String albumName;
		private int albumYear;

		public Builder albumUuid(String val) {
			this.albumUuid = val;
			return this;
		}

		public Builder artistUuid(String val) {
			this.artistUuid = val;
			return this;
		}

		public Builder albumName(String val) {
			this.albumName = val;
			return this;
		}

		public Builder albumYear(int val) {
			this.albumYear = val;
			return this;
		}

		public Album build() {
			return new Album(this);
		}
	}

	public String getAlbumUuid() {
		return new String(albumUuid);
	}

	public String getArtistUuid() {
		return new String(artistUuid);
	}

	public String getAlbumName() {
		return new String(albumName);
	}
	
	public int getAlbumYear() {
		return new Integer(albumYear);
	}
}
