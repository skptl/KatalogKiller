package com.cs9033.server.models;

import java.io.Serializable;

public class Response implements Serializable {

	/**
	 * Response Bean
	 */
	private static final long serialVersionUID = 2854822025284722178L;

	private final int _id;
	private final int _responseCode;
	private final String _response;
	private final String _error;

	public Response(int _id, int _responseCode, String _response, String _error) {
		this._id = _id;
		this._responseCode = _responseCode;
		this._response = _response;
		this._error = _error;
	}

	public int get_id() {
		return _id;
	}

	public int get_responseCode() {
		return _responseCode;
	}

	public String get_response() {
		return _response;
	}

	public String get_error() {
		return _error;
	}

}
