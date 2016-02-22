package com.corbo.musicstreaming.database;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;

import com.corbo.musicstreaming.filesystem.Directory;

/**
 * <p>
 * A class which provides methods to perform on a database.
 * </p>
 * 
 * <p>
 * Async methods because we don't want to wait around whilst the application does its thing...
 * </p>
 * 
 * @author corbo
 *
 */
@Controller
public class Tasks {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final WriteLock writeLock = new ReentrantReadWriteLock().writeLock();
	
	/**
	 * <p>
	 * A method which synchronises the data store. Made Async other wise we will be waiting for a while...
	 * </p>
	 * 
	 */
	@Async
	public void synchroniseDataStore() {
		if (writeLock.tryLock()) {
			
			try {
				logger.debug("Sleeping");
				Thread.sleep(10000L);
				logger.debug("Awoken!");
			} catch (Exception e) {
				// eeeee, exception!!
			} finally {
				writeLock.unlock();
			}
		} else {
			logger.debug("Thread already has lock! Database syncing, go away!...");
		}
		
	}
	
}
