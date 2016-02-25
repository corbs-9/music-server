package com.corbo.musicstreaming.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

/**
 * <p>
 * Class representing an artist.
 * </p>
 * 
 * @author corbo
 *
 */
@Table(value = "artists")
public class Artist {

	@PrimaryKey
	private final String id;
	@Column(value = "name")
	private final String name;

	private Artist(Builder builder) {
		this.name = builder.name;
		this.id = builder.id;
	}

	public static class Builder {
		private String id;
		private String name;

		public Builder id(String val) {
			this.id = val;
			return this;
		}

		public Builder name(String val) {
			this.name = val;
			return this;
		}

		public Artist build() {
			return new Artist(this);
		}
	}

	public final String getId() {
		return new String(id);
	}

	public final String getName() {
		return new String(name);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
