package com.corbo.musicstreaming.repository;

import java.util.Collection;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.corbo.musicstreaming.entity.Album;

/**
 * @author corbo
 */
public interface AlbumRepository extends CassandraRepository<Album> {
	
	@Query("SELECT * FROM album WHERE name=?0")
	Collection<Album> findByName(String name);
	
	@Query("SELECT * FROM album WHERE artist_name=?0")
	Collection<Album> findByArtistName(String name);
	
	@Query("SELECT * FROM album WHERE id =?0")
	Collection<Album> findById(UUID albumId);
	
}