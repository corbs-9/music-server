package com.corbo.musicstreaming.vo;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import com.corbo.musicstreaming.filesystem.Directory;

public class AudioList {
	
	private Set<AudioFile> audioFileList;
	private Set<String> musicList;
	
	public AudioList(Directory dir) {
		musicList = new HashSet<String>();
		// only do so for root directory...
		dir.getFiles().forEach(file -> handleFile(file));
		dir.getChildDirectories().forEach(childDir -> handleFolder(childDir));
	}
	
	private void handleFile(File file) {
		if (file.getName().endsWith(".mp3")) {
			musicList.add(file.getAbsolutePath());
		}
	}
	
	private void handleFolder(Directory dir) {
		dir.getChildDirectories().forEach(childDir -> handleFolder(childDir));
		dir.getFiles().forEach(file -> handleFile(file));
	}
	
	public Set<AudioFile> getAudioFileList() {
		audioFileList = new HashSet<AudioFile>();
		musicList.forEach(musicFile -> {
			audioFileList.add(new AudioFile(musicFile));
		});
		return audioFileList;
	}

}
