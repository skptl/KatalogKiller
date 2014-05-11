package edu.nyu.cs9033.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.nyu.cs9033.models.Company;

/**
 * @author Shilpan Patel
 * 
 */

public interface CompanyRepository extends MongoRepository<Company, String> {

	public Company findByEmail(String email);

}
