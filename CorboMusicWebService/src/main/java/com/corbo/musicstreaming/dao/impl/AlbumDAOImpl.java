package com.corbo.musicstreaming.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.corbo.musicstreaming.dao.AlbumDAO;
import com.corbo.musicstreaming.database.CassandraConnector;
import com.corbo.musicstreaming.exceptions.AlbumNotFoundException;
import com.corbo.musicstreaming.vo.Album;
import com.corbo.musicstreaming.vo.Artist;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

@Controller
public class AlbumDAOImpl implements AlbumDAO {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String CLASS_NAME = this.getClass().getCanonicalName();

	final String queryForAlbumUuid = "select albumuuid, albumname, albumyear, artistuuid from artists where artistuuid = ?";
	final String queryAlbumsForArtist = "select albumuuid, albumname, albumyear, artistuuid from artists where artistuuid = ?";

//	@Autowired
	private CassandraConnector cassandraConnector;

	@Override
	public Album getAlbum(String albumUuid) throws AlbumNotFoundException {
		ResultSet rs;
		String logDebugId = CLASS_NAME + " in getAlbum(" + albumUuid + ")";
		logger.debug("{} Method starts.", logDebugId);
		try {
			rs = cassandraConnector.getSession().execute(queryForAlbumUuid, albumUuid);
			if (rs != null && !rs.isExhausted()) {
				final Row albumRow = rs.one();
				Optional<Album> album = albumRow != null ? Optional.of((new Album.Builder()
						.albumName(albumRow.getString("albumname")).albumUuid(albumRow.getUUID("albumuuid").toString())
						.artistUuid(albumRow.getUUID("artistuuid").toString()).albumYear(albumRow.getInt("albumyear"))
						.build())) : Optional.empty();
				if (album.isPresent()) {
					logger.debug("{} Method ends successfully.", logDebugId);
					return album.get();
				}
			}
		} catch (Exception e) {
			logger.error("{} There was an error when searching for the album via the uuid >:l", logDebugId);
			logger.error("{} Excpetion: {}", logDebugId, ExceptionUtils.getFullStackTrace(e));
		}
		throw new AlbumNotFoundException("The artist was not found for the given UUID");
	}

	@Override
	public List<Album> getAlbumsForArtist(Artist artist) {
		String logDebugId = CLASS_NAME + " in getAlbumsForArtist(" + artist + ")";
		logger.debug("{} Method starts.", logDebugId);
		try {
			return createAlbumListFromRS(
					cassandraConnector.getSession().execute(queryAlbumsForArtist, artist.getArtistUuid()));
		} catch (Exception e) {
			logger.error("{} There was an error when searching for albums via the artistuuid >:l", logDebugId);
			logger.error("{} Excpetion: {}", logDebugId, ExceptionUtils.getFullStackTrace(e));
		}
		return null;
	}

	@Override
	public List<Album> getAlbumsByFirstLetter(String firstLetter) {
		return null;
	}

	@Override
	public List<Album> searchForAlbum(String album) {
		return null;
	}

	@Override
	public void addAlbum(Album album) {
	}

	public List<Album> createAlbumListFromRS(ResultSet rs) throws AlbumNotFoundException {
		List<Album> albumList;
		if (rs != null && !rs.isExhausted()) {
			albumList = new ArrayList<Album>();
			rs.forEach(albumEntry -> {
				Album album = new Album.Builder()
						.albumName(albumEntry.getString("albumname"))
						.albumUuid(albumEntry.getUUID("albumuuid").toString())
						.artistUuid(albumEntry.getUUID("artistuuid").toString())
						.albumYear(albumEntry.getInt("albumyear"))
						.build();
				albumList.add(album);
			});
			if (!albumList.isEmpty()) {
				return albumList;
			}
		}
		throw new AlbumNotFoundException("There were no albums found within the ResultSet");
	}

}
