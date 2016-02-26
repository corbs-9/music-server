package com.corbo.musicstreaming.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

/**
 * Media streaming utility
 *
 */
public class MediaStreamer implements StreamingResponseBody {

	private int length;
	private final RandomAccessFile raf;
	final byte[] buf = new byte[4096];

	public MediaStreamer(int length, RandomAccessFile raf) {
		this.length = length;
		this.raf = raf;
	}

	@Override
	public void writeTo(OutputStream outputStream) throws IOException {
		try {
			while (length != 0) {
				int read = raf.read(buf, 0, buf.length > length ? length : buf.length);
				outputStream.write(buf, 0, read);
				length -= read;
			}
		} finally {
			raf.close();
		}
	}

	public int getLenth() {
		return length;
	}
}