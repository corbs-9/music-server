package com.corbo.musicstreaming.exceptions;

public class AddArtistException extends Exception {

	private static final long serialVersionUID = 212424045906219677L;

	public AddArtistException() {
	}

	public AddArtistException(String message) {
		super(message);
	}

	public AddArtistException(Throwable cause) {
		super(cause);
	}

	public AddArtistException(String message, Throwable cause) {
		super(message, cause);
	}

	public AddArtistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
