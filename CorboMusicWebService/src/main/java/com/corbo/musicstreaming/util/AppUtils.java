package com.corbo.musicstreaming.util;

import java.util.UUID;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;

import com.datastax.driver.core.utils.UUIDs;

public class AppUtils {
	
	public static void logError(Logger logger, Exception e) {
		logger.error("Exception: {}", ExceptionUtils.getFullStackTrace(e));
	}
	
	public static UUID generateUuid() {
		return UUIDs.timeBased();
	}
	
	

}
