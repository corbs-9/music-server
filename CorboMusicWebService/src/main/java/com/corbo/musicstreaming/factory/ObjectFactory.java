package com.corbo.musicstreaming.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.corbo.musicstreaming.entity.Track;
import com.corbo.musicstreaming.model.jaxb.AlbumCplxType;
import com.corbo.musicstreaming.model.jaxb.AlbumList;
import com.corbo.musicstreaming.model.jaxb.ArtistCplxType;
import com.corbo.musicstreaming.model.jaxb.ArtistList;
import com.corbo.musicstreaming.model.jaxb.MusicList;
import com.corbo.musicstreaming.model.jaxb.TrackCplxType;
import com.corbo.musicstreaming.model.jaxb.TrackList;

public class ObjectFactory {

	public static MusicList createMusicListFromTrackList(Collection<Track> trackCollection) {
		MusicList musicList = new MusicList();
		ArtistList artistList = new ArtistList();
		Map<String, ArrayList<String>> artistToAlbumMap = new HashMap<String, ArrayList<String>>();
		Map<String, ArrayList<TrackCplxType>> albumToTrackMap = new HashMap<String, ArrayList<TrackCplxType>>();
		trackCollection.parallelStream().forEach(t -> {
			if (!artistToAlbumMap.containsKey(t.getArtistName())) {
				artistToAlbumMap.put(t.getArtistName(), new ArrayList<String>());
			}
			if (!artistToAlbumMap.get(t.getArtistName()).contains(t.getAlbumName())) {
				artistToAlbumMap.get(t.getArtistName()).add(t.getAlbumName());
			}
			if (!albumToTrackMap.containsKey(t.getAlbumName())) {
				albumToTrackMap.put(t.getAlbumName(), new ArrayList<TrackCplxType>());
			}
			TrackCplxType trackCplx = new TrackCplxType();
			trackCplx.setTrackId(t.getTrackId().toString());
			trackCplx.setTrackName(t.getTrackName());
			trackCplx.setTrackNumber(t.getTrackNumber());
			trackCplx.setDurationInSeconds(Integer.parseInt(t.getDuration()));
			albumToTrackMap.get(t.getAlbumName()).add(trackCplx);
		});
		artistToAlbumMap.forEach((artist, albumList) -> {
			ArtistCplxType artistCplx = new ArtistCplxType();
			artistCplx.setArtistName(artist);
			AlbumList albumList2 = new AlbumList();
			albumList.forEach(album -> {
				AlbumCplxType albumCplx = new AlbumCplxType();
				albumCplx.setAlbumName(album);
				TrackList trackList = new TrackList();
				albumToTrackMap.get(album).forEach(track -> trackList.getTrack().add(track));
				albumCplx.setTrackList(trackList);
				albumList2.getAlbum().add(albumCplx);
			});
			artistCplx.getAlbumList().add(albumList2);
			artistList.getArtist().add(artistCplx);
		});
		musicList.setArtistList(artistList);
		return musicList;
	}
}