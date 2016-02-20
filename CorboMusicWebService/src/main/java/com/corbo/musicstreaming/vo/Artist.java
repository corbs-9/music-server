package com.corbo.musicstreaming.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Artist {
	
	private String artistUuid;
	private String artistName;
	
	private Artist(Builder builder) {
		this.artistName = builder.artistName;
		this.artistUuid = builder.artistUuid;
	}
	
	public static class Builder {
		private String artistUuid;
		private String artistName;
		public Builder artistUuid(String val) {
			this.artistUuid = val;
			return this;
		}
		public Builder artistName(String val) {
			this.artistName = val;
			return this;
		}
		public Artist build() {
			return new Artist(this);
		}
	}

	public String getArtistUuid() {
		return new String(artistUuid);
	}

	public String getArtistName() {
		return new String(artistName);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
