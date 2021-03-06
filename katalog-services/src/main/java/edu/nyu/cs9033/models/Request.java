package edu.nyu.cs9033.models;

import org.springframework.data.annotation.Id;
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

	@Field("email")
	private String email;

	@Field("companyName")
	private String companyName;

	@Field("imageRequested")
	private byte[] imageRequested;



	public Request(boolean status, String email, String companyName) {
		super();
		this.status = status;
		this.email = email;
		this.companyName = companyName;
	}

	public Request() {
		super();
	}

	public String get_id() {
		return _id;
	}

	public boolean isStatus() {
		return status;
	}

	public String getEmail() {
		return email;
	}

	public byte[] getImageRequested() {
		return imageRequested;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setImageRequested(byte[] imageRequested) {
		this.imageRequested = imageRequested;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
