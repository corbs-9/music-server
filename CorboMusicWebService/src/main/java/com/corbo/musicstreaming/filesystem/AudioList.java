package com.corbo.musicstreaming.filesystem;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * A class which represents a list of audio files which reside on the HDD.
 * </p>
 * 
 * <p>
 * Feed the constructor with a directory and it will populate
 * </p>
 * 
 * @author corbo
 *
 */
public class AudioList {

	private Set<AudioFile> audioFileList;
	private Set<String> fileList;

	public AudioList(Directory dir) {
		fileList = new HashSet<String>();
		// only do so for root directory...
		dir.getFiles().forEach(file -> handleFile(file));
		dir.getChildDirectories().forEach(childDir -> handleFolder(childDir));
	}

	private void handleFile(File file) {
		if (file.getName().endsWith(".mp3")) {
			fileList.add(file.getAbsolutePath());
		}
	}

	private void handleFolder(Directory dir) {
		dir.getChildDirectories().forEach(childDir -> handleFolder(childDir));
		dir.getFiles().forEach(file -> handleFile(file));
	}

	public Set<AudioFile> getAudioFileList() {
		audioFileList = new HashSet<AudioFile>();
		fileList.forEach(musicFile -> {
			audioFileList.add(new AudioFile(musicFile));
		});
		return audioFileList;
	}

}
