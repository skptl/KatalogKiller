package edu.nyu.cs9033.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Shilpan Patel
 * 
 */

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

	@Field("password")
	private String password;

	@Field("address")
	private String address;

	@Field("zipCode")
	private String zipCode;
	
	@Field("phoneNumber")
	private String phoneNumber;

	public User(String fName, String lName, String email, String password,
			String address, String zipCode, String phoneNumber) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.password = password;
		this.address = address;
		this.zipCode = zipCode;
		this.phoneNumber = phoneNumber;
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

	public String getPassword() {
		return password;
	}

	public String getAddress() {
		return address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
}
