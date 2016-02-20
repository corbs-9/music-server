package com.corbo.musicstreaming.exceptions;

import java.io.IOException;

public class MusicStreamingException extends IOException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5653081025218021790L;

	public MusicStreamingException(String message) {
		super(message);
	}

	public MusicStreamingException(Throwable cause) {
		super(cause);
	}

	public MusicStreamingException(String message, Throwable cause) {
		super(message, cause);
	}
}
