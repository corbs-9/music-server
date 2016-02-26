package com.corbo.musicstreaming.vo;

import java.io.Serializable;

import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class ArtistKey implements Serializable {

	private static final long serialVersionUID = 3897681140402704033L;

}
