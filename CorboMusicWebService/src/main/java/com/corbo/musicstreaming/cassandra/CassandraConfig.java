package com.corbo.musicstreaming.cassandra;

import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.springframework.cassandra.config.java.AbstractCqlTemplateConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.CassandraTemplate;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

@Configuration
public class CassandraConfig extends AbstractCqlTemplateConfiguration {
	
	Cluster cluster = null;
	Session session = null;
	
	public CassandraConfig() throws Exception {
//	    EmbeddedCassandraServerHelper.startEmbeddedCassandra();

	    EmbeddedCassandraServerHelper.startEmbeddedCassandra("cu-cassandra.yaml", "resources/cassandra");
	    cluster = Cluster.builder().addContactPoints("127.0.0.1").withPort(9142).build();
	    session = cluster.connect();
	}
	
    @Override
    protected String getKeyspaceName() {
        return "beautifulKeyspaceNames";
    }

    @Bean
	public CassandraTemplate cassandraTemplate(Session session) {
		return new CassandraTemplate(session);
	}
}