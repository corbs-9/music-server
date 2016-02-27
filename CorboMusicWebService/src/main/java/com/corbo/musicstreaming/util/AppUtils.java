package com.corbo.musicstreaming.util;

import java.util.UUID;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;

public class AppUtils {

	public static void logError(Logger logger, Exception e) {
		logger.error("Exception: {}", ExceptionUtils.getFullStackTrace(e));
	}

	public static UUID generateUuid() {
		return UUID.randomUUID();
	}
	
	public static UUID generateUuidFromString(String text) {
		return UUID.nameUUIDFromBytes(text.getBytes());
	}
}
