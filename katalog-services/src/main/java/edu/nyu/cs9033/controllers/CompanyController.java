package edu.nyu.cs9033.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.nyu.cs9033.models.Company;
import edu.nyu.cs9033.repositories.CompanyRepository;

/**
 * @author Shilpan Patel
 * 
 */

@Controller
@RequestMapping("/companies")
public class CompanyController {

	private final CompanyRepository companyRepository;

	@Autowired
	public CompanyController(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public String greetingSubmit(
			@RequestParam("companyName") String companyName,
			@RequestParam("companyPhone") String companyPhone,
			@RequestParam("companyAddress") String companyAddress,
			@RequestParam("email") String email,
			@RequestParam("companyLogo") MultipartFile file) throws IOException {

		Company company = new Company(companyName, companyPhone,
				companyAddress, email, file.getBytes());

		companyRepository.save(company);

		return "index";
	}

	@RequestMapping(value = "/add.do", method = RequestMethod.GET)
	public String greetingSubmit() {

		return "index";
	}

}
