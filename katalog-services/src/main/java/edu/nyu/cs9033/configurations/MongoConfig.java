package edu.nyu.cs9033.configurations;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * @author Shilpan Patel
 * 
 */

@Configuration
@EnableMongoRepositories(basePackages = "edu.nyu.cs9033.repositories")
public class MongoConfig extends AbstractMongoConfiguration {

	private static final String dbName = "katalog-killer";
	private static final String dbServer = "127.0.0.1";
	private static final int dbPort = 27017;

	public @Bean
	MongoTemplate mongoTemplate(Mongo mongo) throws UnknownHostException {
		return new MongoTemplate(mongo, dbName);
	}

	public @Bean
	Mongo mongo() throws UnknownHostException {
		return new MongoClient(dbServer, dbPort);
	}

	@Override
	protected String getDatabaseName() {
		return dbName;
	}

	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception {
		return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
	}

}
