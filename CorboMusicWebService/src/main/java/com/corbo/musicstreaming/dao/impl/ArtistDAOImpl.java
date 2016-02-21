package com.corbo.musicstreaming.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.corbo.musicstreaming.dao.ArtistDAO;
import com.corbo.musicstreaming.database.CassandraService;
import com.corbo.musicstreaming.exceptions.AddArtistException;
import com.corbo.musicstreaming.exceptions.ArtistNotFoundException;
import com.corbo.musicstreaming.vo.Artist;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

@Controller
public class ArtistDAOImpl implements ArtistDAO {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	final String CLASS_NAME = this.getClass().getCanonicalName();
	final String queryForArtistUuid = "select artistuuid, artistname from artists where artistuuid = ?";
	final String queryForAllArtists = "select artistuuid, artistname from artists";
	final String insertArtist = "insert into artists (artistuuid, artistname, artistfirstletter) values (?,?,?)";
	final String queryArtistByFirstInitial = "select artistuuid, artistname from artists where artistfirstletter = ?";
	final String searchForArtistQuery = "select artistuuid, artistname from artists where artistname = ?";
	final String deleteArtistQuery = "delete from artists where artistname = ?";

	@Autowired
	private CassandraService cassandraConnector;

	@Override
	public Artist getArtist(String artistUuid) throws ArtistNotFoundException {
		logger.error("CassandraNull?"+(cassandraConnector != null));
		ResultSet rs = null;
		String logDebugId = CLASS_NAME + " in getArtist(" + artistUuid + ")";
		logger.debug("{} Method starts.", logDebugId);
		try {
			rs = cassandraConnector.getSession().execute(queryForArtistUuid, artistUuid);
			if (rs != null) {
				final Row artistRow = null;
				final Optional<Artist> artist = artistRow != null
						? Optional.of((new Artist.Builder()
								.artistName(artistRow.getString("artistname"))
								.artistUuid(artistRow.getUUID("artistuuid").toString())
								.build()))
						: Optional.empty();
				if (artist.isPresent()) {
					logger.debug("{} Artist to return: {}", logDebugId, artist.get().toString());
					logger.debug("{} Method ends successfully.", logDebugId);
					return artist.get();
				} else {
					logger.error("{} Uh oh. We can't seem to find the artist :/", logDebugId);
				}
			}
		} catch (Exception e) {
			logger.error("{} There was an error when searching for the artist via the uuid >:l", logDebugId);
			logger.error("{} Excpetion: {}", logDebugId, ExceptionUtils.getFullStackTrace(e));
		}
		throw new ArtistNotFoundException();
	}

	@Override
	public List<Artist> getAllArtists() throws ArtistNotFoundException {
		ResultSet rs = null;
		String logDebugId = CLASS_NAME + " in getAllArtist()";
		logger.debug("{} Method starts.", logDebugId);
		try {
			rs = cassandraConnector.getSession().execute(queryForAllArtists);
			if (rs != null && !rs.isExhausted()) {
				return createArtistListFromRS(rs);
			}
		} catch (Exception e) {
			logger.error("{} There was an error when listing all artists >:l", logDebugId);
			logger.error("{} Excpetion: {}", logDebugId, ExceptionUtils.getFullStackTrace(e));
		}
		throw new ArtistNotFoundException();
	}

	@Override
	public List<Artist> getArtistsByFirstVarChar(String firstVarChar) throws ArtistNotFoundException {
		ResultSet rs = null;
		String logDebugId = CLASS_NAME + " in getArtistsByFirstVarChar()";
		logger.debug("{} Method starts.", logDebugId);
		try {
			rs = cassandraConnector.getSession().execute(queryArtistByFirstInitial, firstVarChar.toUpperCase());
			if (rs != null && !rs.isExhausted()) {
				return createArtistListFromRS(rs);
			} 
		} catch (Exception e) {
			logger.error("{} There was an error when listing all artists by first varchar >:l", logDebugId);
			logger.error("{} Excpetion: {}", logDebugId, ExceptionUtils.getFullStackTrace(e));
		}
		throw new ArtistNotFoundException("no artists");
	}

	@Override
	public Artist searchForArtist(String artistName) throws ArtistNotFoundException {
		ResultSet rs = null;
		String logDebugId = CLASS_NAME + " in (" + artistName + ")";
		logger.debug("{} Method starts.", logDebugId);
		try {
			rs = cassandraConnector.getSession().execute(searchForArtistQuery, artistName);
			if (rs != null) {
				final Row artistRow = rs.one();
				final Optional<Artist> artist = artistRow != null
						? Optional.of((new Artist.Builder()
								.artistName(artistRow.getString("artistname"))
								.artistUuid(artistRow.getString("artistuuid"))
								.build()))
						: Optional.empty();
				if (artist.isPresent()) {
					logger.debug("{} Artist to return: {}", logDebugId, artist.get().toString());
					logger.debug("{} Method ends successfully.", logDebugId);
					return artist.get();
				} else {
					logger.error("{} Uh oh. We can't seem to find the artist :/", logDebugId);
					System.err.println("No artist");
				}
			}
			System.out.println("TEST");
		} catch (Exception e) {
			logger.error("{} There was an error when searching for the artist via the uuid >:l", logDebugId);
			logger.error("{} Excpetion: {}", logDebugId, ExceptionUtils.getFullStackTrace(e));
			System.out.println("Exception: "+e);
		}
		throw new ArtistNotFoundException();
	}

	@Override
	public void addArtist(Artist artist) throws AddArtistException {
		String logDebugId = CLASS_NAME + " in addArtist()";
		logger.debug("{} Method starts.", logDebugId);
		try {
			logger.trace("{} About to try and insert a record into the artists table...");
			cassandraConnector.getSession().execute(insertArtist, UUID.fromString(artist.getArtistUuid()), artist.getArtistName(), artist.getArtistName().substring(0, 1).toUpperCase());
		} catch (Exception e) {
			logger.error("{} Uh oh, there was an exception trying to insert a new artist!", logDebugId);
			logger.error("{} Exception: {}", logDebugId, ExceptionUtils.getFullStackTrace(e));
			throw new AddArtistException(e);
		}
		logger.debug("{} Method ends.", logDebugId);
	}
	
	private List<Artist> createArtistListFromRS(ResultSet rs) throws ArtistNotFoundException {
		List<Artist> artistList;
		artistList = new ArrayList<Artist>();
		rs.all().forEach(entry -> {
			Artist artist = new Artist.Builder()
					.artistName(entry.getString("artistname"))
					.artistUuid(entry.getUUID("artistuuid").toString())
					.build();
			artistList.add(artist);
			logger.debug(artist.toString());
		});
		if (artistList.isEmpty()) {
			throw new ArtistNotFoundException("There are no artists in the list!");
		}
		return artistList;
	}

}
