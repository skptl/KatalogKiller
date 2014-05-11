package edu.nyu.cs9033.controllers;

import java.util.List;

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

	@RequestMapping(value = "/test/{email}", method = RequestMethod.GET)
	public @ResponseBody
	String root(@PathVariable(value = "email") String email) {

		for (int i = 0; i < 999; i++) {
			User user = new User(email, email, i + email, email, email, i + "",i*i+"");
			User saved = userRepository.save(user);
			System.out.println(saved.get_id());
		}
		return "Yoo.. MongoDB !!";
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
	List<User> findByEmail(@PathVariable(value = "email") String email) {

		if (email == null)
			return null;
		List<User> users = userRepository.findByEmail(email);

		return users;
	}

	@RequestMapping(value = "/add", consumes = "application/json", method = RequestMethod.POST)
	public @ResponseBody
	User addUser(@RequestBody User user) {

		BindException errors = new BindException(user, "user");
		UserValidator validator = new UserValidator();
		validator.validate(user, errors);
		if (errors.getErrorCount() > 0)
			return null;
		List<User> existing = userRepository.findByEmail(user.getEmail());
		if (existing != null && existing.size() != 0)
			return null;
		User saved = userRepository.save(user);

		return saved;
	}
}
