package com.corbo.musicstreaming.database;

import java.io.IOException;

import org.apache.cassandra.service.EmbeddedCassandraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.corbo.musicstreaming.util.AppUtils;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.Session;

@Controller
public class CassandraService {
	
	private EmbeddedCassandraService cassandra;
	
	public CassandraService() {
		String cassandraYamlFilePath = ClassLoader.getSystemResource("cassandra.yaml").toString();
		System.setProperty("cassandra.config",cassandraYamlFilePath);
		cassandra = new EmbeddedCassandraService();
		try {
			cassandra.start();
			PoolingOptions poolingOptions = new PoolingOptions();
			poolingOptions.setCoreConnectionsPerHost(HostDistance.LOCAL,1);
			poolingOptions.setMaxConnectionsPerHost(HostDistance.LOCAL, 10);
			poolingOptions.setHeartbeatIntervalSeconds(120);
			cluster = Cluster.builder().addContactPoint(host).withPort(port).
					withPoolingOptions(poolingOptions).build();
			final Metadata metadata = cluster.getMetadata();
			System.out.printf("Connected to cluster: %s\n",
					metadata.getClusterName());
			session = cluster.connect();
		} catch (IOException e) {
			System.err.println("TEST");
			e.printStackTrace();
		}
	}
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	private Cluster cluster;
	private Session session;
	private String host = "127.0.0.1";
	private int port = 9042;

	public void destroy() {
		if (session !=null) {
			try {
				logger.trace("Attempting to close the session.");
				session.close();
				logger.trace("Successfully closed the session");
			} catch (Exception e) {
				logger.error("Error whilst closing the session!");
				AppUtils.logError(logger, e);
			}
		}
		if (cluster != null) {
			try {
				logger.trace("Attempting to close the cluster");
				cluster.close();
				logger.trace("Successfully closed the cluster");
			} catch (Exception e) {
				logger.error("Error whilst closing the cluster!");
				AppUtils.logError(logger, e);
			}
		}
	}

	public Session getSession() {
		return this.session;
	}

	public void close() {
		this.cluster.close();
	}

}
