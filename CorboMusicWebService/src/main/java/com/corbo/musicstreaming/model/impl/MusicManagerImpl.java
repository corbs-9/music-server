package com.corbo.musicstreaming.model.impl;

import java.io.ByteArrayInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.corbo.musicstreaming.model.MusicManager;

@Controller
public class MusicManagerImpl implements MusicManager {

	final int chunk_size = 1024 * 1024; // 1MB chunks

	public ResponseEntity<ByteArrayResource> buildStream(final String trackId) {
		try {
			Path path = Paths.get("/Users/corbo/song.mp3");
			FileSystemResource file = new FileSystemResource(path.toFile().getAbsolutePath());
			byte[] res = IOUtils.toByteArray(file.getInputStream());
			return ResponseEntity
					.ok()
					.contentLength(file.contentLength())
					.contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
					.body(new ByteArrayResource(res));
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
