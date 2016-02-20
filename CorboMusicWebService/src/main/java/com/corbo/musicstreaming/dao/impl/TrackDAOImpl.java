package com.corbo.musicstreaming.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.corbo.musicstreaming.dao.TrackDAO;
import com.corbo.musicstreaming.database.CassandraConnector;
import com.corbo.musicstreaming.vo.Album;
import com.corbo.musicstreaming.vo.Artist;
import com.corbo.musicstreaming.vo.Track;

@Controller
public class TrackDAOImpl implements TrackDAO {
	
//	@Autowired
	private CassandraConnector cassandraConnector;

	@Override
	public Track getTrack(String trackUuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Track> getTracksForAlbum(Album album) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Track> getTracksForArtist(Artist artist) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Track> searchForTrack(String track) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTrack(Track track) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTrack(Track track) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTrack(Track track) {
		// TODO Auto-generated method stub
		
	}
}
