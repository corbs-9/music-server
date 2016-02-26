package com.corbo.musicstreaming.database;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
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

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String CLASS_NAME = this.getClass().getName();

	private String createKeyspaceCql = "CREATE KEYSPACE IF NOT EXISTS music WITH REPLICATION = "
			+ "{ 'class' : 'SimpleStrategy', 'replication_factor' : 1 }";
	private String createArtistTableCql = "CREATE TABLE IF NOT EXISTS music.artists "
			+ "( id uuid, first_letter text, name text, PRIMARY KEY (id, name) )";
	private String createAlbumTableCql = "CREATE TABLE IF NOT EXISTS music.albums ( id uuid, name text, "
			+ "artist_name text, PRIMARY KEY (id, name) )";
	private String createTrackTableCql = "CREATE TABLE IF NOT EXISTS music.tracks "
			+ "( id uuid, name text, album_name text, artist_name text, duration int, location text, number int, year int, "
			+ "PRIMARY KEY (id, name) )";
	private String indexArtistFirstLetter = "CREATE INDEX IF NOT EXISTS artist_first_letter ON music.artists (first_letter)";
	private String indexArtistOnAlbums = "CREATE INDEX IF NOT EXISTS album_artist ON music.albums (artist_name)";
	private String indexArtistOnTracks = "CREATE INDEX IF NOT EXISTS track_artist ON music.tracks (artist_name)";
	private String indexAlbumOnTracks = "CREATE INDEX IF NOT EXISTS track_album ON music.tracks (album_name)";

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
		URL url = ClassLoader.getSystemResource("cassandra.yaml");
		if (url == null) {
			throw new IllegalArgumentException("Can't find the cassandra.yaml file on the class path");
		}
		try {
			Path path = Paths.get(new URI(url.toString()));
			String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
			content = content.replaceAll("user.home", System.getProperty("user.home"));
			Files.write(path, content.getBytes(StandardCharsets.UTF_8));
		} catch (Exception e) {

		}
		System.setProperty("cassandra.config", url.toString());
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
		logger.debug("{} Creating index on album table for artist column if not present", logDebugId);
		getSession().execute(indexArtistOnAlbums);
		logger.debug("{} Creating index on tracks table for artist column if not present", logDebugId);
		getSession().execute(indexArtistOnTracks);
		logger.debug("{} Creating index on tracks table for album column if not present", logDebugId);
		getSession().execute(indexAlbumOnTracks);
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