package com.cs9033.katalogkiller.utilities;

import java.util.concurrent.Callable;

import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;

public class HttpCommunicator implements Callable<String> {
	
	private DefaultHttpClient mHttpClient;
	private HttpParams params;
	private String URL;
	
	public HttpCommunicator(String URL)
	{
		super();
		params = new BasicHttpParams();
	    params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
	    mHttpClient = new DefaultHttpClient(params);
	    this.URL = URL;
	}

	@Override
	public String call() throws Exception {
		HttpPost httppost = new HttpPost(URL);
		return null;
	}

}
