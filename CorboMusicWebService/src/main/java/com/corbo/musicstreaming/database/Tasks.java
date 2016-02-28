package com.corbo.musicstreaming.database;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;

import com.corbo.musicstreaming.entity.Album;
import com.corbo.musicstreaming.entity.Artist;
import com.corbo.musicstreaming.entity.Track;
import com.corbo.musicstreaming.filesystem.AudioFile;
import com.corbo.musicstreaming.filesystem.AudioList;
import com.corbo.musicstreaming.filesystem.Directory;
import com.corbo.musicstreaming.repository.AlbumRepository;
import com.corbo.musicstreaming.repository.ArtistRepository;
import com.corbo.musicstreaming.repository.TrackRepository;
import com.corbo.musicstreaming.util.AppUtils;

/**
 * <p>
 * A class which provides methods to perform on a database.
 * </p>
 * 
 * <p>
 * Async methods because we don't want to wait around whilst the application
 * does its thing...
 * </p>
 * 
 * @author corbo
 *
 */
@Controller
public class Tasks {

	private static final String CLASS_NAME = Tasks.class.getSimpleName();

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final WriteLock writeLock = new ReentrantReadWriteLock().writeLock();

	@Autowired
	private ArtistRepository artistRepository;
	@Autowired
	private AlbumRepository albumRepository;
	@Autowired
	private TrackRepository trackRepository;

	/**
	 * <p>
	 * A method which synchronises the data store. Made Async other wise we will
	 * be waiting for a while...
	 * </p>
	 * 
	 */
	@Async
	public void synchroniseDataStore() {
		String logDebugId = CLASS_NAME + " in synchroniseDataStore()";
		logger.debug("{} Method starts.", logDebugId);
		Directory directory = null;
		AudioList audioList = null;
		if (writeLock.tryLock()) {
			try {
				directory = new Directory("/Users/c7652683/Music/iTunes/iTunes Media/Music");
				audioList = new AudioList(directory);
				audioList.getAudioFileList().forEach(af -> {
					logger.trace("{} Processing the following AudioFile: {}", logDebugId, af.toString());
					saveArtist(af);
					saveAlbum(af);
					saveTrack(af);
				});
			} catch (Exception e) {
				logger.error("{} There was an error whilst trying to synchronise the database.", logDebugId);
				AppUtils.logError(logger, e);
			} finally {
				logger.trace("{} Unlocking the write lock for future use.", logDebugId);
				writeLock.unlock();
			}
		} else {
			logger.warn("There is already one database synchronisation occurring. Try again later.");
		}
	}

	private void saveArtist(AudioFile af) {
		String logDebugId = CLASS_NAME + " in saveArtist";
		logger.trace("{} Method starts", logDebugId);
		Artist artist = new Artist();
		artist.setArtistName(AppUtils.formatTextStringForCassandra(af.getArtist()));
		artist.setArtistId(AppUtils.generateUuidFromString(af.getArtist()));
		artist.setFirstLetter(AppUtils.getFirstLetterFromString(af.getArtist()));
		logger.trace("{} Artist to save: {}", logDebugId, artist.toString());
		try {
			artistRepository.save(artist);
			logger.trace("{} Saved the artist successfully!", logDebugId);
		} catch (Exception e) {
			logger.error("{} There was an error whilst attempting to save the artist.", logDebugId);
			AppUtils.logError(logger, e);
		}
	}

	private void saveAlbum(AudioFile af) {
		String logDebugId = CLASS_NAME + " in saveAlbum";
		logger.trace("{} Method starts", logDebugId);
		Album album = new Album();
		album.setAlbumId(AppUtils.generateUuidFromString(af.getArtist() + af.getAlbum()));
		album.setAlbumName(AppUtils.formatTextStringForCassandra(af.getAlbum()));
		album.setArtistName(AppUtils.formatTextStringForCassandra(af.getArtist()));
		logger.trace("{} Album to save: {}", logDebugId, album.toString());
		try {
			albumRepository.save(album);
			logger.trace("{} Saved the album successfully!", logDebugId);
		} catch (Exception e) {
			logger.error("{} There was an error whilst attempting to save the album.", logDebugId);
			AppUtils.logError(logger, e);
		}
	}

	private void saveTrack(AudioFile af) {
		String logDebugId = CLASS_NAME + " in saveTrack";
		logger.trace("{} Method starts", logDebugId);
		Track track = new Track();
		track.setTrackId(AppUtils.generateUuidFromString(af.getArtist() + af.getAlbum() + af.getTrackName()));
		track.setAlbumName(AppUtils.formatTextStringForCassandra(af.getAlbum()));
		track.setArtistName(AppUtils.formatTextStringForCassandra(af.getArtist()));
		track.setDuration(String.valueOf((Math.round(af.getDurationInMillis())) / 1000));
		track.setFilePath(af.getFullFilePath());
		track.setTrackName(af.getTrackName());
		track.setTrackNumber(af.getTrackNumber());
		logger.trace("{} Track to save: {}", logDebugId, track.toString());
		try {
			trackRepository.save(track);
			logger.trace("{} Saved the track successfully!", logDebugId);
		} catch (Exception e) {
			logger.error("{} There was an error whilst attempting to save the track.", logDebugId);
			AppUtils.logError(logger, e);
		}
	}
}