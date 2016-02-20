package com.corbo.musicstreaming.cassandra;

import org.apache.cassandra.service.CassandraDaemon;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@EnableAutoConfiguration
//@org.cassandraunit.spring.EmbeddedCassandra
public class EmbeddedCassandra extends CassandraDaemon {
	
//	public EmbeddedCassandra() {
//		System.out.println("Starting cassandra");
//		if (cassandraDaemon != null) {
//			/* nothing to do Cassandra is already started */
//			return;
//		}
//		System.setProperty("cassandra.config", "file://");
//		System.setProperty("cassandra-foreground", "true");
//        System.setProperty("cassandra.native.epoll.enabled", "false");
//		final CountDownLatch startupLatch = new CountDownLatch(1);
//		ExecutorService executor = Executors.newSingleThreadExecutor();
//		executor.execute(new Runnable() {
//			@Override
//			public void run() {
//				cassandraDaemon = new CassandraDaemon();
//				cassandraDaemon.activate();
//				startupLatch.countDown();
//			}
//		});
//		try {
//            if (!startupLatch.await(10000L, TimeUnit.MILLISECONDS)) {
//                throw new AssertionError("Cassandra daemon did not start within timeout");
//            }
//        } catch (InterruptedException e) {
//            throw new AssertionError(e);
//        } finally {
//            executor.shutdown();
//        }
//		System.out.println("Started cassandra");
//	}

}
