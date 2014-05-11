package edu.nyu.cs9033.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Shilpan Patel
 * 
 */

@Document(collection = "requests")
public class Request {
	
	@Id
	private String _id;

	@Field("status")
	private boolean status;

	@Indexed(unique = true)
	@Field("email")
	private String email;
	
	@Field("imageRequested")
	private byte[] imageRequested;
	

}
