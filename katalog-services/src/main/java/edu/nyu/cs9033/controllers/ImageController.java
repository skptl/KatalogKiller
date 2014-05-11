package edu.nyu.cs9033.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFSDBFile;

import edu.nyu.cs9033.repositories.FileRepository;

/**
 * @author Shilpan Patel
 * 
 */

@Controller
@RequestMapping("/images")
public class ImageController {

	private final FileRepository fileRepository;

	@Autowired
	public ImageController(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public @ResponseBody
	String provideUploadInfo() {
		InputStream inputStream = null;
		try {
			File file = new File("/Users/CoRrUpT/Desktop/a.png");
			inputStream = new FileInputStream(file);
			fileRepository.save(inputStream, "image/png", file.getName());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "You can upload a file by posting to this same URL.";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody
	String handleFileUpload(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				System.out.println(new String(bytes));
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("uploaded")));
				stream.write(bytes);
				stream.close();
				return "You successfully uploaded file !";
			} catch (Exception e) {
				return "You failed to upload !";
			}
		} else {
			return "You failed to upload, because the file was empty.";
		}
	}

	@RequestMapping("/show")
	public @ResponseBody
	ResponseEntity<byte[]> showPhoto(@PathVariable(value = "name") String name)
			throws IOException {

		GridFSDBFile gridFSDBFile = fileRepository.getByFilename("a.png");

		if (gridFSDBFile == null) {
			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.TEXT_PLAIN);
			return new ResponseEntity<byte[]>("Not Found".getBytes(), headers,
					HttpStatus.CREATED);
		}

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(IOUtils.toByteArray(gridFSDBFile
				.getInputStream()), headers, HttpStatus.CREATED);
	}
	

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
    	System.out.println("HI");
        //model.addAttribute("name", name);
        return "index";
    }

}
