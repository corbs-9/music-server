package com.corbo.musicstreaming.model.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.corbo.musicstreaming.entity.Track;
import com.corbo.musicstreaming.model.MusicManager;
import com.corbo.musicstreaming.repository.TrackRepository;
import com.corbo.musicstreaming.util.MediaStreamer;

@Controller
public class MusicManagerImpl implements MusicManager {
	
	@Autowired
	private TrackRepository trackRepository;

	final int chunk_size = 512 * 512; // 512kb chunks

	public ResponseEntity<StreamingResponseBody> buildStream(final String trackId, final String range) {
		Track track = trackRepository.findById(UUID.fromString(trackId));
		// Optional<StreamingResponseBody> audioStream = null;
		try {
			Path path = Paths.get(track.getFilePath());
			FileSystemResource fileSystemResource = new FileSystemResource(path.toFile().getAbsolutePath());
			File file = fileSystemResource.getFile();
			if (range == null) {
				StreamingResponseBody streamer = new StreamingResponseBody() {

					@Override
					public void writeTo(OutputStream output) throws IOException {
						final FileInputStream fileInputStream = new FileInputStream(file);
						final FileChannel inputChannel = fileInputStream.getChannel();
						final WritableByteChannel outputChannel = Channels.newChannel(output);
						try {
							inputChannel.transferTo(0, inputChannel.size(), outputChannel);
							// closing the channels
							inputChannel.close();
							fileInputStream.close();
						} catch (Exception e) {

						}
					}

				};
				return ResponseEntity.ok().contentLength(fileSystemResource.contentLength())
						.contentType(MediaType.parseMediaType("audio/mpeg")).body(streamer);
			} else {
				String[] ranges = range.split("=")[1].split("-");
				final int from = Integer.parseInt(ranges[0]);
				/**
				 * Chunk media if the range upper bound is unspecified. Chrome
				 * sends "bytes=0-"
				 */
				int to = chunk_size + from;
				if (to >= file.length()) {
					to = (int) (file.length() - 1);
				}
				if (ranges.length == 2) {
					to = Integer.parseInt(ranges[1]);
				}

				final String responseRange = String.format("bytes %d-%d/%d", from, to, file.length());
				final RandomAccessFile raf = new RandomAccessFile(file, "r");
				raf.seek(from);

				final int len = to - from + 1;
				final MediaStreamer streamer = new MediaStreamer(len, raf);
				return ResponseEntity.status(206).header("Accept-Ranges", "bytes")
						.header("Content-Range", responseRange).contentLength(streamer.getLenth())
						.contentType(MediaType.parseMediaType("audio/mpeg")).lastModified(file.lastModified())
						.body(streamer);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
