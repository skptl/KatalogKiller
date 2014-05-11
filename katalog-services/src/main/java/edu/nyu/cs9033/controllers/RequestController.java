package edu.nyu.cs9033.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.nyu.cs9033.repositories.RequestRepository;

/**
 * @author Shilpan Patel
 * 
 */

@Controller
@RequestMapping("/requests")
public class RequestController {

	private final RequestRepository requestRepository;

	@Autowired
	public RequestController(RequestRepository requestRepository) {
		super();
		this.requestRepository = requestRepository;
	}

	@RequestMapping(value = "/try", method = RequestMethod.POST)
	public @ResponseBody
	List<String> handleFileUpload(
			@RequestParam(value = "file", required = true) MultipartFile file,
			@RequestParam(value = "email", required = true) String email) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				return null;
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}

}
