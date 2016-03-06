package com.corbo.musicstreaming.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.cassandra.service.EmbeddedCassandraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.corbo.musicstreaming.util.AppUtils;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

@Controller
public class CassandraService {

	private final String cassandraConfigDirectoryLocation = "/.musicserver/cassandra/conf/";
	private final String cassandraConfigFileName = "cassandra.yaml";
	private final String userHomeDir = "file://" + System.getProperty("user.home");

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String CLASS_NAME = this.getClass().getName();

	private String createKeyspaceCql = "CREATE KEYSPACE IF NOT EXISTS music WITH REPLICATION = "
			+ "{ 'class' : 'SimpleStrategy', 'replication_factor' : 1 }";
	private String createArtistTableCql = "CREATE TABLE IF NOT EXISTS music.artist "
			+ "( id uuid, first_letter text, name text, PRIMARY KEY (id) )";
	private String createAlbumTableCql = "CREATE TABLE IF NOT EXISTS music.album ( id uuid, name text, "
			+ "artist_name text, PRIMARY KEY (id) )";
	private String createTrackTableCql = "CREATE TABLE IF NOT EXISTS music.track "
			+ "( id uuid, name text, album_id uuid, album_name text, artist_id uuid, artist_name text, location text, number text, year text, "
			+ "PRIMARY KEY (id) )";
	private String indexArtistFirstLetter = "CREATE INDEX IF NOT EXISTS artist_first_letter ON music.artist (first_letter)";
	private String indexArtistName = "CREATE INDEX IF NOT EXISTS artist_name ON music.artist (name)";
	private String indexAlbumName = "CREATE INDEX IF NOT EXISTS album_name ON music.album (name)";
	private String indexArtistOnAlbums = "CREATE INDEX IF NOT EXISTS album_artist ON music.album (artist_name)";
	private String indexArtistOnTracks = "CREATE INDEX IF NOT EXISTS track_artist ON music.track (artist_name)";
	private String indexAlbumOnTracks = "CREATE INDEX IF NOT EXISTS track_album ON music.track (album_name)";
	private String indexLocationOnTracks = "CREATE INDEX IF NOT EXISTS track_location ON music.track (location)";

	private EmbeddedCassandraService cassandra;

	public CassandraService() {
		init();
	}

	private void init() {
		String logDebugId = CLASS_NAME + " in init()";
		logger.debug("{} Method starts.", logDebugId);
		logger.debug("{} Setting the cassandra property file as a system property", logDebugId);
		setCassandraProperties();
		logger.debug("{} Starting an embedded Cassandra database", logDebugId);
		startCassandra();
		logger.debug("{} Creating a cluster and a session for our application to use", logDebugId);
		createCluster();
		logger.debug("{} Creating the keyspace if not present", logDebugId);
		createKeySpace();
		logger.debug("{} Creating the table schema if not present", logDebugId);
		createTableSchema();
	}

	private void setCassandraProperties() {
		InputStream inputStream = null;
		Path path = null;
		File file = null;
		try {
			inputStream = ClassLoader.getSystemResourceAsStream("cassandra.yaml");
			path = Paths.get(new URI(userHomeDir + cassandraConfigDirectoryLocation));
			file = path.toFile();
			if (!file.exists()) {
				file.mkdirs();
			}
			path = Paths.get(new URI(userHomeDir + cassandraConfigDirectoryLocation + cassandraConfigFileName));
			file = path.toFile();
			if (!file.exists()) {
				Files.copy(inputStream, path);
			}
			String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
			content = content.replaceAll("user.home", System.getProperty("user.home"));
			Files.write(path, content.getBytes(StandardCharsets.UTF_8));
			System.setProperty("cassandra.config", "file://"+file.getAbsolutePath());
		} catch (Exception e) {
			// AppUtils.logError(logger, e);
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void startCassandra() {
		try {
			cassandra = new EmbeddedCassandraService();
			cassandra.start();
		} catch (IOException e) {
			logger.error("There was an error when starting Cassandra :(");
			AppUtils.logError(logger, e);
		}
	}

	private void createCluster() {
		cluster = Cluster.builder().addContactPoint(host).withPort(port).build();
		session = cluster.connect();
	}

	private void createKeySpace() {
		getSession().execute(createKeyspaceCql);
	}

	private void createTableSchema() {
		String logDebugId = CLASS_NAME + " in createTableSchema()";
		logger.debug("{} Creating the artist table schema if not present", logDebugId);
		getSession().execute(createArtistTableCql);
		logger.debug("{} Creating the album schema if not present", logDebugId);
		getSession().execute(createAlbumTableCql);
		logger.debug("{} Creating the track schema if not present", logDebugId);
		getSession().execute(createTrackTableCql);
		logger.debug("{} Creating index on artist table for first_letter if not present", logDebugId);
		getSession().execute(indexArtistFirstLetter);
		logger.debug("{} Creating index on artist table for name if not present", logDebugId);
		getSession().execute(indexArtistName);
		logger.debug("{} Creating index on album table for artist column if not present", logDebugId);
		getSession().execute(indexArtistOnAlbums);
		logger.debug("{} Creating index on album table for name column if not present", logDebugId);
		getSession().execute(indexAlbumName);
		logger.debug("{} Creating index on tracks table for artist column if not present", logDebugId);
		getSession().execute(indexArtistOnTracks);
		logger.debug("{} Creating index on tracks table for album column if not present", logDebugId);
		getSession().execute(indexAlbumOnTracks);
		logger.debug("{} Creating index on tracks table for location column if not present", logDebugId);
		getSession().execute(indexLocationOnTracks);
		logger.debug("{} Closing cluster as we've set up the database and don't need it anymore...", logDebugId);
		destroy();
	}

	private Cluster cluster;
	private Session session;
	private String host = "127.0.0.1";
	private int port = 9042;

	private void destroy() {
		if (session != null) {
			try {
				logger.trace("Attempting to close the session.");
				session.close();
				logger.trace("Successfully closed the session");
			} catch (Exception e) {
				logger.error("Error whilst closing the session!");
				AppUtils.logError(logger, e);
			}
		}
		if (cluster != null) {
			try {
				logger.trace("Attempting to close the cluster");
				cluster.close();
				logger.trace("Successfully closed the cluster");
			} catch (Exception e) {
				logger.error("Error whilst closing the cluster!");
				AppUtils.logError(logger, e);
			}
		}
	}

	private Session getSession() {
		return this.session;
	}
}