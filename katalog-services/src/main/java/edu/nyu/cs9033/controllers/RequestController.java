package edu.nyu.cs9033.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.nyu.cs9033.models.Company;
import edu.nyu.cs9033.models.Request;
import edu.nyu.cs9033.opencv.ImageMatcher;
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

	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public @ResponseBody
	List<String> receiveRequest(
			@RequestParam("imageToScan") MultipartFile imageToScan) {
		List<String> name = new ArrayList<String>();
		try {
			File tmpFile1 = File.createTempFile("temp", ".png");
			System.out.println(tmpFile1.getAbsolutePath());
			FileOutputStream output1 = new FileOutputStream(tmpFile1);
			IOUtils.write(imageToScan.getBytes(), output1);

			List<Company> list = companyRepository.findAll();

			for (Company company : list) {
				File tmpFile2 = File.createTempFile("temp", ".png");
				System.out.println(tmpFile2.getAbsolutePath());
				FileOutputStream output2 = new FileOutputStream(tmpFile2);
				IOUtils.write(company.getCompanyLogo(), output2);
				if (ImageMatcher.matchImage(tmpFile2.getAbsolutePath(),
						tmpFile1.getAbsolutePath())) {
					System.out.println(company.getCompanyName());
					name.add(company.getCompanyName());
				}
			}
		} catch (Exception e) {

		}

		return name;
	}

	@RequestMapping(value = "/add.do", method = RequestMethod.GET)
	public String receiveRequest() {

		return "test";
	}
	
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public @ResponseBody
	List<String> receiveRequest(
			@RequestParam("imageToScan") MultipartFile imageToScan) {
		List<String> name = new ArrayList<String>();
		try {
			File tmpFile1 = File.createTempFile("temp", ".png");
			System.out.println(tmpFile1.getAbsolutePath());
			FileOutputStream output1 = new FileOutputStream(tmpFile1);
			IOUtils.write(imageToScan.getBytes(), output1);

			List<Company> list = companyRepository.findAll();

			for (Company company : list) {
				File tmpFile2 = File.createTempFile("temp", ".png");
				System.out.println(tmpFile2.getAbsolutePath());
				FileOutputStream output2 = new FileOutputStream(tmpFile2);
				IOUtils.write(company.getCompanyLogo(), output2);
				if (ImageMatcher.matchImage(tmpFile2.getAbsolutePath(),
						tmpFile1.getAbsolutePath())) {
					System.out.println(company.getCompanyName());
					name.add(company.getCompanyName());
				}
			}
		} catch (Exception e) {

		}

		return name;
	}

}
