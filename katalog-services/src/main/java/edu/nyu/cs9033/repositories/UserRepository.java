package edu.nyu.cs9033.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.nyu.cs9033.models.User;

public interface UserRepository extends MongoRepository<User, String> {

	public List<User> findByEmail(String email);

}
