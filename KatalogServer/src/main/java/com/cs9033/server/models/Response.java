package com.cs9033.server.models;

import java.io.Serializable;

public class Response implements Serializable {
	
	/**
	 * Response Bean
	 */
	private static final long serialVersionUID = 2854822025284722178L;
	
	private int _id;
	private int _responseCode;
	private String _response;
	private String _error;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int get_responseCode() {
		return _responseCode;
	}

	public void set_responseCode(int _responseCode) {
		this._responseCode = _responseCode;
	}

	public String get_response() {
		return _response;
	}

	public void set_response(String _response) {
		this._response = _response;
	}

	public String get_error() {
		return _error;
	}

	public void set_error(String _error) {
		this._error = _error;
	}

}
