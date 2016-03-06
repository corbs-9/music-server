package com.corbo.musicstreaming.filesystem;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.corbo.musicstreaming.util.AppUtils;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v1Genres;
import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.musicbrainz.mp3.tagger.Tools.Tools;

public class AudioFile {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public AudioFile(String fullFilePath)
			throws IOException, SAXException, UnsupportedTagException, InvalidDataException {
		logger.debug("Dealing with file: {}", fullFilePath);
		File file = new File(fullFilePath);
		Mp3File mp3File = new Mp3File(file);
		ID3v1 id3 = Tools.getId3v1Tag(mp3File);
		try {
			ID3v24Tag id3v24 = (ID3v24Tag) id3;
			this.artist = id3v24.getAlbumArtist();
			if (this.artist == null) {
				this.artist = id3.getArtist();
			}
		} catch (ClassCastException e) {
			this.artist = id3.getArtist();
		}
		String genre = null;
		if (id3.getGenre()!= -1) {
			genre = ID3v1Genres.GENRES[id3.getGenre()];
		}
		logger.debug("GENRE={}", genre);
		this.album = id3.getAlbum();
		this.track = id3.getTitle();
		this.fullFilePath = fullFilePath;
		this.trackNumber = id3.getTrack();
		if (trackNumber.contains("/")) {
			this.trackNumber = this.trackNumber.substring(0, this.trackNumber.indexOf("/"));
		}
		this.artistId = AppUtils.generateUuidFromString(this.artist);
		this.albumId = AppUtils.generateUuidFromString(this.artist + this.album);
		this.trackId = AppUtils.generateUuidFromString(this.artist + this.album + this.track);
	}

	private String artist;
	private UUID artistId;
	private String album;
	private UUID albumId;
	private String track;
	private UUID trackId;
	private String trackNumber;
	private String fullFilePath;

	public String getArtist() {
		return artist;
	}

	public String getAlbum() {
		return album;
	}

	public String getTrack() {
		return track;
	}

	public String getTrackNumber() {
		return this.trackNumber;
	}

	public String getFullFilePath() {
		return this.fullFilePath;
	}

	public UUID getArtistId() {
		return artistId;
	}

	public UUID getAlbumId() {
		return albumId;
	}

	public UUID getTrackId() {
		return trackId;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
