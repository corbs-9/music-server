package com.corbo.musicstreaming.repository;

import java.util.Collection;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.corbo.musicstreaming.entity.Artist;

public interface ArtistRepository extends CassandraRepository<Artist> {

	@Query("SELECT * FROM artist WHERE name=?0")
	Collection<Artist> findByName(String name);

	@Query("SELECT * FROM artist where first_letter=?0")
	Collection<Artist> findByFirstLetterIgnoreCase(String firstLetter);
}
