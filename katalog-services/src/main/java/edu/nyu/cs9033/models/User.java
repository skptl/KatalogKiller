package edu.nyu.cs9033.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "users")
public class User {

	@Id
	private String _id;

	@Field("fName")
	private String fName;

	@Field("lName")
	private String lName;

	@Indexed(unique = true)
	@Field("email")
	private String email;

	@Field("address")
	private String address;

	@Field("zipCode")
	private String zipCode;

	public User(String fName, String lName, String email, String address,
			String zipCode) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.address = address;
		this.zipCode = zipCode;
	}

	public User() {
		super();
	}

	public String get_id() {
		return _id;
	}

	public String getfName() {
		return fName;
	}

	public String getlName() {
		return lName;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getZipCode() {
		return zipCode;
	}

}
