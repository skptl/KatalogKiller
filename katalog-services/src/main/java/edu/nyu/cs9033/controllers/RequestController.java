package edu.nyu.cs9033.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.nyu.cs9033.repositories.CompanyRepository;
import edu.nyu.cs9033.repositories.RequestRepository;

/**
 * @author Shilpan Patel
 * 
 */

@Controller
@RequestMapping("/requests")
public class RequestController {

	private final RequestRepository requestRepository;
	private final CompanyRepository companyRepository;

	@Autowired
	public RequestController(RequestRepository requestRepository,
			CompanyRepository companyRepository) {
		super();
		this.requestRepository = requestRepository;
		this.companyRepository = companyRepository;
	}

}
