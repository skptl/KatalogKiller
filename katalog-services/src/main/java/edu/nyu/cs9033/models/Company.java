package edu.nyu.cs9033.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Shilpan Patel
 * 
 */

@Document(collection = "companies")
public class Company {

	@Id
	private String _id;

	@Field("companyName")
	private String companyName;

	@Field("companyPhone")
	private String companyPhone;

	@Field("companyAddress")
	private String companyAddress;

	@Indexed(unique = true)
	@Field("email")
	private String email;

	@Field("companyLogo")
	private byte[] companyLogo;

	public Company() {
		super();
	}

	public Company(String companyName, String companyPhone,
			String companyAddress, String email, byte[] companyLogo) {
		super();
		this.companyName = companyName;
		this.companyPhone = companyPhone;
		this.companyAddress = companyAddress;
		this.email = email;
		this.companyLogo = companyLogo;
	}

	public String get_id() {
		return _id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public String getEmail() {
		return email;
	}

	public byte[] getCompanyLogo() {
		return companyLogo;
	}

}
