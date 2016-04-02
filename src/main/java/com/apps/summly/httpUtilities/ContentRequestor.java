package com.apps.summly.httpUtilities;


import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.mongodb.BasicDBObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.InputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.io.InputStreamReader;
import java.util.concurrent.CountDownLatch;

import com.apps.summly.utilities.Utilities;
import com.apps.summly.db.DBInterface;
import com.apps.summly.db.DBDataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode; 

import org.apache.http.HttpResponse;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.json.JSONObject;

public class ContentRequestor 
{
		
	DBInterface driver;	
	RequestConfig requestConfig;
	BufferedReader URLReader =null;	
	CloseableHttpAsyncClient httpClient;
	
	private DB db;
	private DBCollection dbCollection;
	private MongoClient mongodbClient;	
	private int mongoPort=DBDataProvider.MONGOPORT;
	private String mongoHost=DBDataProvider.MONGOHOST;
	private String databaseName=DBDataProvider.DATABASENAME;
	
	
	
	
	public static void main(String args[]) throws InterruptedException, IOException
	{
		ContentRequestor requestor = new ContentRequestor();
		requestor.initializeDBInstance();
		requestor.processMultipleContentRequest();
	}
	
	public String createQueryString(String NEWS_TYPE)
	{
		String QueryString=null;
		URIBuilder builder = new URIBuilder();
		builder.addParameter("callback", "svc_search_v2_articlesearch");
		builder.addParameter("fq", "news_desk:"+(NEWS_TYPE));
		builder.addParameter("api-key", "0cfeeba81735d896bffd4a64fa22e393:17:74788228");
		builder.addParameter("begin_date", "20160324");
		builder.addParameter("end_date", "20160324");
		builder.addParameter("sort", "newest");
		QueryString = builder.toString();		
		return QueryString;
	}

	public void initializeDBInstance()
	{
		try
    	{
			driver = new DBInterface();
			mongodbClient = driver.getDBConnection(mongoHost,mongoPort);
			db = driver.getDatabaseInstance(mongodbClient,databaseName);
			System.out.println("DB Initialized...");
			
			dbCollection=driver.getDatabaseCollection(db, DBDataProvider.DBRAWNEWSDATA);
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
	
	public void processMultipleContentRequest() throws InterruptedException, IOException
	{
		 
		 driver = new DBInterface();
		 requestConfig = RequestConfig.custom()
				.setConnectTimeout(3000)
				.setSocketTimeout(3000)
				.build();
		 
		 httpClient = HttpAsyncClients.custom()
		 .setDefaultRequestConfig(requestConfig).build();
		 
		 try
		 {
			 	
			 httpClient.start();
			
			 final HttpGet[] requests = new HttpGet[] {
	                    new HttpGet("http://api.nytimes.com/svc/search/v2/articlesearch.json"+createQueryString("Politics")),
	                    new HttpGet("http://api.nytimes.com/svc/search/v2/articlesearch.json"+createQueryString("Sports")),
	                    new HttpGet("http://api.nytimes.com/svc/search/v2/articlesearch.json"+createQueryString("Fashion")),
	                    new HttpGet("http://api.nytimes.com/svc/search/v2/articlesearch.json"+createQueryString("Business"))
			 };
			 final CountDownLatch latch = new CountDownLatch(requests.length);
			 for(final HttpGet request :requests){
				 httpClient.execute(request, new FutureCallback<HttpResponse>(){

					public void cancelled() {
						latch.countDown();
						System.out.println(request.getRequestLine()+ "Cancelled");
					}

					public void completed(HttpResponse resp) {
						String URLResponse=null;
						String updatedURLResponse=null;						
						String newsKeyForJSON=null;	
						byte[] jsonData;
						DBObject dbObject;
						ObjectMapper objectMapper;
						latch.countDown();						
						System.out.println(request.getURI()+"-->"+resp.getStatusLine());
						InputStream contents=null;
						try {
							contents = resp.getEntity().getContent();
							//URLResponse = IOUtils.toString(contents);							
							URLReader = new BufferedReader(new InputStreamReader(contents,Charset.forName("UTF-8")));
							URLResponse = URLReader.readLine();
							jsonData = URLResponse.getBytes();
							objectMapper = new ObjectMapper();
							JsonNode rootNode = objectMapper.readTree(jsonData);
							if(request.getURI().toString().indexOf("Politics") != -1)
							{								
								newsKeyForJSON = "Politics-"+Utilities.getCurrentDateAndTime();								
								((ObjectNode) rootNode).put("newsKey", newsKeyForJSON);
								updatedURLResponse=objectMapper.writeValueAsString(rootNode).toString();								
							    System.out.println(updatedURLResponse);  
								dbObject = (DBObject) JSON.parse(updatedURLResponse);								
								dbCollection.insert(dbObject);
							}
							else if(request.getURI().toString().indexOf("Business") != -1)
							{
								newsKeyForJSON = "Business-"+Utilities.getCurrentDateAndTime();								
								((ObjectNode) rootNode).put("newsKey", newsKeyForJSON);
								updatedURLResponse=objectMapper.writeValueAsString(rootNode).toString();								
							    System.out.println(updatedURLResponse);  
								dbObject = (DBObject) JSON.parse(updatedURLResponse);								
								dbCollection.insert(dbObject);
							}
							else if(request.getURI().toString().indexOf("Sports") != -1)
							{								
								newsKeyForJSON = "Sports-"+Utilities.getCurrentDateAndTime();								
								((ObjectNode) rootNode).put("newsKey", newsKeyForJSON);
								updatedURLResponse=objectMapper.writeValueAsString(rootNode).toString();								
							    System.out.println(updatedURLResponse);  
								dbObject = (DBObject) JSON.parse(updatedURLResponse);								
								dbCollection.insert(dbObject);								
							}
							else
							{
								
							}							
							
							
						} catch (UnsupportedOperationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						
					}

					public void failed(Exception exception) {
						latch.countDown();
						System.out.println(request.getRequestLine()+"-->"+exception);
						
					}
					 
				 });
			 }
			 latch.await();
			 System.out.println("Shutting down"); 
		 }finally{
			 httpClient.close();
		 }
	}
	
}


