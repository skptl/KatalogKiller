package com.cs9033.katalogkiller.utilities;

import java.util.List;
import java.util.concurrent.Callable;

import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;

public class HttpCommunicator implements Callable<String> {
	
	private DefaultHttpClient mHttpClient;
	private HttpParams params;
	private String URL;
	private List<NameValuePair> nameValuePairs;
	
	public HttpCommunicator(String URL, List<NameValuePair> nameValuePairs)
	{
		super();
		this.URL = URL;
		this.nameValuePairs = nameValuePairs;
		params = new BasicHttpParams();
	    params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
	    mHttpClient = new DefaultHttpClient(params);
	}

	@Override
	public String call() throws Exception {
		HttpPost httppost = new HttpPost(URL);
		return null;
	}

}
