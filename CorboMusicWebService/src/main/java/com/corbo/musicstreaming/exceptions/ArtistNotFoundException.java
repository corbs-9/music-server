package com.corbo.musicstreaming.exceptions;

public class ArtistNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3993660835770103261L;

	public ArtistNotFoundException() {
	}

	public ArtistNotFoundException(String message) {
		super(message);
	}

	public ArtistNotFoundException(Throwable cause) {
		super(cause);
	}

	public ArtistNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
