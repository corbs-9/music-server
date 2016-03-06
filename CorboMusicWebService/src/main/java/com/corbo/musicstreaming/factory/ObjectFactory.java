package com.corbo.musicstreaming.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corbo.musicstreaming.entity.Artist;
import com.corbo.musicstreaming.entity.Track;
import com.corbo.musicstreaming.model.jaxb.AlbumCplxType;
import com.corbo.musicstreaming.model.jaxb.AlbumList;
import com.corbo.musicstreaming.model.jaxb.ArtistCplxType;
import com.corbo.musicstreaming.model.jaxb.ArtistList;
import com.corbo.musicstreaming.model.jaxb.MusicList;
import com.corbo.musicstreaming.model.jaxb.TrackCplxType;
import com.corbo.musicstreaming.model.jaxb.TrackList;

public class ObjectFactory {
	
	private static Logger logger = LoggerFactory.getLogger(ObjectFactory.class);

	public static MusicList createMusicListFromTrackList(Collection<Track> trackCollection) {
		MusicList musicList = new MusicList();
		ArtistList artistList = new ArtistList();
		Map<String, ArrayList<String>> artistToAlbumMap = new HashMap<String, ArrayList<String>>();
		Map<String, ArrayList<Track>> albumToTrackMap = new HashMap<String, ArrayList<Track>>();
		trackCollection.forEach(t -> {
			if (!artistToAlbumMap.containsKey(t.getArtistName())) {
				artistToAlbumMap.put(t.getArtistName(), new ArrayList<String>());
			}
			if (!artistToAlbumMap.get(t.getArtistName()).contains(t.getAlbumName())) {
				artistToAlbumMap.get(t.getArtistName()).add(t.getAlbumName());
			}
			if (!albumToTrackMap.containsKey(t.getAlbumName())) {
				albumToTrackMap.put(t.getAlbumName(), new ArrayList<Track>());
			}
			albumToTrackMap.get(t.getAlbumName()).add(t);
		});
		artistToAlbumMap.forEach((artist, albumList) -> {
			ArtistCplxType artistCplx = new ArtistCplxType();
			artistCplx.setArtistName(artist);
			AlbumList albumList2 = new AlbumList();
			albumList.forEach(album -> {
				AlbumCplxType albumCplx = new AlbumCplxType();
				albumCplx.setAlbumName(album);
				TrackList trackList = new TrackList();
				albumToTrackMap.get(album).forEach(track -> { 
					logger.debug("Track={}", ToStringBuilder.reflectionToString(track));
					artistCplx.setArtistId(track.getArtistId().toString());
					albumCplx.setAlbumId(track.getAlbumId().toString());
					TrackCplxType trackCplx = new TrackCplxType();
					trackCplx.setTrackId(track.getTrackId().toString());
					trackCplx.setAlbumName(track.getAlbumName());
					trackCplx.setArtistName(track.getArtistName());
					trackCplx.setTrackName(track.getTrackName());
					trackCplx.setTrackNumber(track.getTrackNumber());
					trackList.getTrack().add(trackCplx);
				});
				albumCplx.setTrackList(trackList);
				albumList2.getAlbum().add(albumCplx);
			});
			artistCplx.getAlbumList().add(albumList2);
			artistList.getArtist().add(artistCplx);
		});
		musicList.setArtistList(artistList);
		return musicList;
	}

	public static MusicList createMusicListArtists(Collection<Artist> artists) {
		MusicList musicList = new MusicList();
		ArtistList artistList = new ArtistList();
		musicList.setArtistList(artistList);
		artists.stream().forEach(artist -> {
			ArtistCplxType art = new ArtistCplxType();
			art.setArtistName(artist.getArtistName());
			art.setArtistId(artist.getArtistId().toString());
			artistList.getArtist().add(art);
		});
		return musicList;
	}
}