package com.apps.summly.service;


import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoException;
import com.apps.summly.db.DBInterface;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParseException;

import com.apps.summly.provider.News;

import com.google.gson.Gson;
import com.google.gson.JsonParser;


import com.apps.summly.db.DBDataProvider;
import com.apps.summly.utilities.Utilities;

public class ContentRetriever
{

	DBInterface DBUtils;
	
	private DB db;
	private DBCollection dbCollec;
	
	private String dbNewsRawDataCollection = DBDataProvider.DBRAWNEWSDATA;
	private String dbNewsActiveDataCollection = DBDataProvider.DBACTIVENEWSDATA;
	
	
	
	public static void main(String args[])
	{
		ContentRetriever cr = new ContentRetriever();
		cr.retrieveCurrentNewsContent();
		cr.NewsContentForTheDay("Business-0204201620:41:02");
				
	}
	
	
	public void retrieveCurrentNewsContent()
	{
		String Key="{\"Sports-0104201618:55:25\""+" : "+"{$exists:true}}";
		String currentDate = Utilities.getCurrentDateAndTime();
		DBUtils = new DBInterface();
		try
		{
			db = DBUtils.initializeMongoDB();
			dbCollec = DBUtils.getDatabaseCollection(db, dbNewsRawDataCollection);	
		}catch(MongoException ex)
		{
			ex.printStackTrace();
		}
		
		//printSingleArgsRecordFromDB(dbCollec,"newsKey","Politics-0204201618:58:52");
		
		//printAllValuesInCollection(dbCollec);
	}
	
	
	public List<DBObject> printSingleArgsRecordFromDB(DBCollection dbCollection,String Key,String Value)
	{
		BasicDBObject dbQuery;
		DBCursor cursor;
		List<DBObject> newsRecords;		
		
		dbQuery = new BasicDBObject();
		dbQuery.put(Key, Value);
		cursor = db.getCollection(DBDataProvider.DBRAWNEWSDATA).find(dbQuery);
		newsRecords = new ArrayList<DBObject>();
		System.out.println("Key: " +Key);
		System.out.println("Value: " +Value);	
		
		
		try
		{
			newsRecords=cursor.toArray();			
			//System.out.println(newsRecords.get(0));
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally{
			if(cursor != null)
			{
				cursor.close();
			}
		}
  
		return newsRecords;
	}
	
	public List<News> NewsContentForTheDay(String newsKey)
	{
		String web_url=null;
		String headline=null;
		String[] multimedia=null;
		String lead_paragraph=null;
		String source=null;
		String subType=null;
		
		int multimediaContentSize =0;
		JsonParser parser = new JsonParser();
		JsonArray newsarray = new JsonArray();
		JsonArray multimediaArray = new JsonArray();
		List<DBObject> newsRecords = new ArrayList<DBObject>();
		List<News> newsContent = new ArrayList<News>();
		
		retrieveCurrentNewsContent();
		newsRecords = printSingleArgsRecordFromDB(dbCollec,"newsKey",newsKey);	
		if(newsRecords.size() != 0)
		{   
			
			for(int newsindex=0;newsindex<=newsRecords.size()-1;newsindex++)
			{
				newsarray=parser.parse(newsRecords.get(newsindex).toString()).getAsJsonObject().get("response")
						 .getAsJsonObject().getAsJsonArray("docs");
				for(int docsindex=0;docsindex<=newsarray.size()-1;docsindex++)
				  {
					System.out.println("============== Document: "+docsindex+" =================");
						if((newsarray.get(docsindex).getAsJsonObject().get("web_url").toString().trim().contains(".html")))
						{			
							
							web_url = newsarray.get(docsindex).getAsJsonObject().get("web_url").toString().replace("\"", "").trim();
							headline = newsarray.get(docsindex).getAsJsonObject().get("headline").getAsJsonObject().get("main").toString().replace("\"", "").trim();
							
							
							/*  Retriving thumbnail and wide images and storing in array*/
							multimediaArray = newsarray.get(docsindex).getAsJsonObject().get("multimedia").getAsJsonArray();
							multimediaContentSize= newsarray.get(docsindex).getAsJsonObject().get("multimedia").getAsJsonArray().toString().length();
							
							if(multimediaContentSize > 2)
							{
								multimedia = new String[2];
								for(int mindex=0;mindex<=multimediaArray.size()-1;mindex++)
								{
									subType = newsarray.get(docsindex).getAsJsonObject().get("multimedia").getAsJsonArray().get(mindex).getAsJsonObject().get("subtype").toString().trim();
									
									if((subType.equals("\"wide\"")))
									{
										multimedia[0] = newsarray.get(docsindex).getAsJsonObject().get("multimedia").getAsJsonArray().get(mindex).getAsJsonObject().get("url").toString().replace("\"", "").trim();
								        System.out.println(newsarray.get(docsindex).getAsJsonObject().get("multimedia").getAsJsonArray().get(mindex).getAsJsonObject().get("url").toString().replace("\"", "").trim());
								        
									}
									else if((subType.equals("\"thumbnail\"")))
									{
										multimedia[1] = newsarray.get(docsindex).getAsJsonObject().get("multimedia").getAsJsonArray().get(mindex).getAsJsonObject().get("url").toString().replace("\"", "").trim(); 
										System.out.println(newsarray.get(docsindex).getAsJsonObject().get("multimedia").getAsJsonArray().get(mindex).getAsJsonObject().get("url").toString().replace("\"", "").trim());
									}
								} // end of multimedia array
								
							}							
							
							else
							{
								multimedia= new String[0];
							}
							
							
							
							lead_paragraph = newsarray.get(docsindex).getAsJsonObject().get("lead_paragraph").toString().replace("\"", "").trim();
							source = newsarray.get(docsindex).getAsJsonObject().get("source").toString().replace("\"", "").trim();
							News news = new News(newsKey,web_url,headline,multimedia,lead_paragraph,source);
							newsContent.add(news);
							//News news = new News()
							
							
							System.out.println("============== ***************************** =================");
							System.out.println();						
						}
					
					}//end of docs iteration				
				
			 }// end of news iteration
			System.out.println("NewsLength: " +newsContent.size());
		}
		else
		{
			System.out.println("Empty News Records..");
			System.exit(0);
		}
	  return newsContent;	
	}
	
	//public void parseNewsJSONRecord()
	
	public void printAllValuesInCollection(DBCollection dbCollection)
	{
		DBCursor cursor = dbCollection.find();
		while(cursor.hasNext()) {
	        System.out.println(cursor.next());
	    }
	}
}
