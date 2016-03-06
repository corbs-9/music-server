package com.corbo.musicstreaming.util;

import java.util.UUID;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;

public class AppUtils {

	public static void logError(Logger logger, Exception e) {
		logger.error("Exception: {}", ExceptionUtils.getStackTrace(e));
	}

	public static UUID generateUuid() {
		return UUID.randomUUID();
	}
	
	public static UUID generateUuidFromString(String string) {
		return UUID.nameUUIDFromBytes(string.getBytes());
	}
	
	public static String formatTextStringForCassandra(String text) {
		if (null != text && !"".equals(text)) {
			return WordUtils.capitalize(text.toLowerCase());
		}
		return null;
	}
	
	public static String getFirstLetterFromString(String text) {
		if (null != text && !"".equals(text)) {
			return text.substring(0, 1);
		}
		return null;
	}
}
