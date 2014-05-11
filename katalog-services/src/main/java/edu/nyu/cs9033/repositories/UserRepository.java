package edu.nyu.cs9033.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.nyu.cs9033.models.User;

/**
 * @author Shilpan Patel
 * 
 */

public interface UserRepository extends MongoRepository<User, String> {

	public User findByEmail(String email);

}
