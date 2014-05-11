package edu.nyu.cs9033.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.nyu.cs9033.repositories.RequestRepository;

/**
 * @author Shilpan Patel
 * 
 */

@Controller
@RequestMapping("/requests")
public class RequestController {

	private final RequestRepository requestRepository;

	public RequestController(RequestRepository requestRepository) {
		super();
		this.requestRepository = requestRepository;
	}

	@RequestMapping(value = "/test/{email}", method = RequestMethod.GET)
	public @ResponseBody
	String root(@PathVariable(value = "email") String email) {

		return "Yoo.. MongoDB !!";
	}

}
