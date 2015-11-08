package com.v5ent.rapid4j.test.library.jedis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
 
public class HttpRestClient {
	private static String REST_URI = "http://172.25.2.14";
	private static Logger logger = Logger.getLogger(HttpRestClient.class);

	private static DefaultHttpClient httpClient = new DefaultHttpClient();
	
	private static DefaultHttpClient getClientResource() {
	   /* httpClient.getCredentialsProvider().setCredentials(
	            new AuthScope("192.168.1.160", 8002, null),
	            new UsernamePasswordCredentials("kermit", "kermit")
	            );*/
		return httpClient;
	}
	
	public static String getSeqno(String param) throws ClientProtocolException, IOException{
		HttpGet getRequest = new HttpGet(REST_URI+"/seqno?"+param);
	    HttpResponse response = getClientResource().execute(getRequest);
	    BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
	    String output;
	    while ((output = br.readLine()) != null) {
	      System.out.println("->"+output);
	    }
	    httpClient.getConnectionManager().shutdown();
	    
	    return  output;
	}
	
  public static void main(String[] args) throws Exception, IOException {
	  HttpGet getRequest = new HttpGet(REST_URI+"/seqno?sysTemNo=ERP&seqName=WH-ZONE-ID&iVar=00");
    HttpResponse response = getClientResource().execute(getRequest);
    BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
 
    String output;
    System.out.println("Output from Server .... \n");
    while ((output = br.readLine()) != null) {
      System.out.println(output);
    }
 
    httpClient.getConnectionManager().shutdown();
  }
}
