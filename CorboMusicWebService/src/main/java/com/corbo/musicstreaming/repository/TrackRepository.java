package com.corbo.musicstreaming.repository;

import java.util.Collection;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.corbo.musicstreaming.entity.Track;

/**
 * 
 * @author corbo
 *
 */
public interface TrackRepository extends CassandraRepository<Track> {
	
	@Query("SELECT * FROM track WHERE name=?0")
	Collection<Track> findByName(String name);
	
	@Query("SELECT * FROM track WHERE album_name=?0")
	Collection<Track> findByAlbumName(String name);
	
	@Query("SELECT * FROM track WHERE artist_name=?0")
	Collection<Track> findByArtistName(String name);
	
	@Query("SELECT * FROM track WHERE location=?0")
	Track findByFileLocation(String location);
	
	@Query("SELECT * FROM track WHERE id=?0")
	Track findById(UUID id);
}
