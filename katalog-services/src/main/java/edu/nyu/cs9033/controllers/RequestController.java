package edu.nyu.cs9033.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.nyu.cs9033.models.Request;
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
	String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("email") String email) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				return "You successfully uploaded file !";
			} catch (Exception e) {
				return "You failed to upload !";
			}
		} else {
			return "You failed to upload, because the file was empty.";
		}
	}

}
