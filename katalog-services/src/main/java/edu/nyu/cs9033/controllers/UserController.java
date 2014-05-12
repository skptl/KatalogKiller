package edu.nyu.cs9033.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.nyu.cs9033.models.User;
import edu.nyu.cs9033.repositories.UserRepository;
import edu.nyu.cs9033.validators.UserValidator;

/**
 * @author Shilpan Patel
 * 
 */

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	MongoTemplate mongoTemplate;
	private final UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@RequestMapping(value = "/test/{email}/gen", method = RequestMethod.GET)
	public @ResponseBody
	String root(@PathVariable(value = "email") String email) {
		
		System.out.println(email);

		for (int i = 0; i < 99; i++) {
			User user = new User(email, email, i + email, email, email, i + "",
					i * i + "");
			User saved = userRepository.save(user);
			System.out.println(saved.get_id());
		}
		return "Added 100 fake users.";
	}

	@RequestMapping(value = "/delete-all", method = RequestMethod.GET)
	public @ResponseBody
	String deleteAll() {
		mongoTemplate.getDb().dropDatabase();
		return "Everything gone !!";
	}

	@RequestMapping(value = "/show-all", method = RequestMethod.GET)
	public @ResponseBody
	Iterable<User> showAll() {
		Iterable<User> users = userRepository.findAll();
		return users;
	}

	@RequestMapping(value = "/find/id/{id}", method = RequestMethod.GET)
	public @ResponseBody
	User findById(@PathVariable(value = "id") String id) {

		if (id == null)
			return null;
		User user = userRepository.findOne(id);
		return user;
	}

	@RequestMapping(value = "/find/email/{email}", method = RequestMethod.GET)
	public @ResponseBody
	User findByEmail(@PathVariable(value = "email") String email) {

		if (email == null)
			return null;
		User users = userRepository.findByEmail(email);
		
		return users;
	}

	@RequestMapping(value = "/authenticate/{email}/{pswd}")
	public @ResponseBody
	boolean authenticate(@PathVariable(value = "email") String email,
			@PathVariable(value = "pswd") String pswd) {

		User user = userRepository.findByEmail(email);

		if (user == null)
			return false;

		if (user.getPassword().equalsIgnoreCase(pswd))
			return true;

		return false;
	}

	@RequestMapping(value = "/add", consumes = "application/json", method = RequestMethod.POST)
	public @ResponseBody
	User addUser(@RequestBody User user) {

		BindException errors = new BindException(user, "user");
		UserValidator validator = new UserValidator();
		validator.validate(user, errors);
		if (errors.getErrorCount() > 0)
			return null;
		User existing = userRepository.findByEmail(user.getEmail());
		if (existing != null)
			return null;
		user.set_id(null);
		User saved = userRepository.save(user);

		return saved;
	}

	@RequestMapping(value = "/update", consumes = "application/json", method = RequestMethod.POST)
	public @ResponseBody
	User updateUser(@RequestBody User user) {

		BindException errors = new BindException(user, "user");
		UserValidator validator = new UserValidator();
		validator.validate(user, errors);
		if (errors.getErrorCount() > 0)
			return null;

		User existing = userRepository.findByEmail(user.getEmail());
		if (existing == null)
			return null;

		userRepository.delete(existing);
		user.set_id(null);

		User saved = userRepository.save(user);

		return saved;
	}
}
