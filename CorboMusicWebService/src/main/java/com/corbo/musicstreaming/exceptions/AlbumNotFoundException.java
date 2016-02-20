package com.corbo.musicstreaming.exceptions;

public class AlbumNotFoundException extends Exception {

	private static final long serialVersionUID = -1456175413723003075L;

	public AlbumNotFoundException() {
	}

	public AlbumNotFoundException(String message) {
		super(message);
	}

	public AlbumNotFoundException(Throwable cause) {
		super(cause);
	}

	public AlbumNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public AlbumNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
