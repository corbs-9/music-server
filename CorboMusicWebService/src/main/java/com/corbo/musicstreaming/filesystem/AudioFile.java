package com.corbo.musicstreaming.filesystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class AudioFile {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public AudioFile(String fullFilePath) {
		try {
			InputStream input = new FileInputStream(new File(fullFilePath));
			ContentHandler handler = (ContentHandler) new DefaultHandler();
			Metadata metadata = new Metadata();
			Parser parser = new Mp3Parser();
			ParseContext parseCtx = new ParseContext();
			parser.parse(input, handler, metadata, parseCtx);
			input.close();

			this.fullFilePath = fullFilePath;
			trackName = metadata.get("title") != null ? WordUtils.capitalize(metadata.get("title").toLowerCase())
					: "UNKNOWN_TRACK_NAME";
			album = metadata.get("xmpDM:album") != null
					? WordUtils.capitalize(metadata.get("xmpDM:album").toLowerCase()) : "UNKNOWN_ALBUM_MAME";
			artist = metadata.get("xmpDM:artist") != null
					? WordUtils.capitalize(metadata.get("xmpDM:artist").toLowerCase()) : "UNKNOWN_ARTIST_NAME";
			logger.debug("**********************\n {}", artist);
			trackNumber = metadata.get("xmpDM:trackNumber") != null ? metadata.get("xmpDM:trackNumber")
					: "NO_TRACK_NUMBER";
			durationInMillis = Float.parseFloat(metadata.get("xmpDM:duration"));

			if (trackNumber.contains("/")) {
				trackNumber = trackNumber.substring(0, trackNumber.indexOf("/"));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		}
	}

	private String artist;
	private String album;
	private String trackName;
	private String trackNumber;
	private float durationInMillis;
	private String fullFilePath;

	public String getArtist() {
		return artist;
	}

	public String getAlbum() {
		return album;
	}

	public String getTrackName() {
		return trackName;
	}

	public String getTrackNumber() {
		return this.trackNumber;
	}

	public float getDurationInMillis() {
		return durationInMillis;
	}

	public String getFullFilePath() {
		return this.fullFilePath;
	}

}
