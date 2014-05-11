package edu.nyu.cs9033.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.nyu.cs9033.models.Request;

public interface RequestRepository extends MongoRepository<Request, String> {

	public Request findByEmail(String email);

}
