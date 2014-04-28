package com.cs9033.server.daos;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import com.cs9033.server.models.User;

@Configuration
@EnableMongoRepositories
@Import(RepositoryRestMvcConfiguration.class)
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserDAO extends MongoRepository<User, String> {
	
    public User findByFirstName(String firstName);
    public List<User> findByLastName(String lastName);

}

