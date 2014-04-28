package com.cs9033.server.parsers;

import com.cs9033.server.models.Response;
import com.google.gson.Gson;

public class ResponseParser {
	
	String toJsonString(Response response)
	{
		Gson gson = new Gson();
		String result = gson.toJson(response);
		return result;
	}
	
	Response toResponeModel(String JSON)
	{
		Gson gson = new Gson();
		Response response = gson.fromJson(JSON, Response.class);
		return response;
	}

}
