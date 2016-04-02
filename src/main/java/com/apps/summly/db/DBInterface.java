package com.apps.summly.db;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;


import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class DBInterface
{
	private MongoClient mongodbClient;
	private DB db;
	private DBCollection dbCollec;
	private String mongoHost="localhost";
	private String databaseName=DBDataProvider.DATABASENAME;
	private String dbCollectionName="";
	private int mongoPort=27017;
	private static List<String> dbRecords;
	
	public static void main(String args[])
	{
		/*
		 * Sample code to retrieve and print the values from MongoDB
		 * 
		 */
		DBInterface driver = new DBInterface();
		driver.initializeMongoDB();
		
	}
	
	
	public DBInterface() {
		super();		
		
	}

    public DB initializeMongoDB()
    {
    	
    	try
    	{
    		mongodbClient = getDBConnection(mongoHost,mongoPort);
    		db = getDatabaseInstance(mongodbClient,databaseName);
    		System.out.println("DB Initialized...");
    		
    		
    		//printSingleArgsRecordFromDB(dbCollec,"info.area.geocode.value","600117");
    		
    		
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
      return db;	
    }

	public MongoClient getDBConnection(String mongoHost,int mongoPort) throws UnknownHostException  
	{
		MongoClient dbClient=null;
		try
		{
			dbClient = new MongoClient(mongoHost,mongoPort);	
			System.out.println("DBConnection Established..!!");
		}catch(MongoException e)
		{
			System.out.println("Unknown MongoHost...!!! Quitting..!!!" +e.getCode());
			System.exit(0);
		}		
		
		return dbClient;
	}
	
	public DB getDatabaseInstance(MongoClient dbClient, String databaseName)
	{
		DB db=null;
		try
		{
			db = dbClient.getDB(databaseName);
		}catch(Exception e)
		{
			System.out.println("Unable to get DB Instance..!!! Quitting..~!!");
			System.exit(0);
		}
		
		return db;
	}
	
	public DBCollection getDatabaseCollection(DB db,String dbCollectionName)
	{	
		DBCollection dbCollectionRef=null;
		try
		{
			dbCollectionRef = db.getCollection(dbCollectionName);
		}catch(Exception e)
		{
			System.out.println("Unable to get DBCollection Instance..!!! Quitting!!!");
			System.exit(0);
		}
		return dbCollectionRef;
	}
	
	public String printSingleArgsRecordFromDB(DBCollection dbCollection,String Key,String Value)
	{
		BasicDBObject dbQuery;
		DBCursor cursorObj;
		List<String> dbEASRecordsForRegion;
		String dbEASRecordRegion;
		
		dbQuery = new BasicDBObject(Key,Value);
		cursorObj =dbCollection.find(dbQuery);
		dbEASRecordsForRegion = new ArrayList<String>();
		System.out.println("Key" +Key);
		System.out.println("Value" +Value);
		dbEASRecordRegion=cursorObj.next().toString();
		
		try
		{
			while(cursorObj.hasNext())
			{
				System.out.println(cursorObj.next());
				
				dbEASRecordsForRegion.add(cursorObj.next().toString());
			}
		}finally{
			cursorObj.close();
		}
	  return dbEASRecordRegion;
		
		/*try
		{
			while(cursorObj.hasNext())
			{
				System.out.println(cursorObj.next());
			}
		}finally{
			cursorObj.close();
		}*/
		
	}
	
	public List<String> printSingleArgsRecordFromDB(DBCollection dbCollection,String Key,int Value)
	{
		BasicDBObject dbQuery;
		DBCursor cursorObj;
		List<String> dbEASRecordsForRegion;
		
		dbQuery = new BasicDBObject(Key,Value);
		cursorObj =dbCollection.find(dbQuery);
		dbEASRecordsForRegion = new ArrayList<String>();
		System.out.println("Key" +Key);
		System.out.println("Value" +Value);
		try
		{
			while(cursorObj.hasNext())
			{
				System.out.println(cursorObj.next());
				
				dbEASRecordsForRegion.add(cursorObj.next().toString());
			}
		}finally{
			cursorObj.close();
		}
	  return dbEASRecordsForRegion;
	}
	
	public List<String> fetchMultipleDBRecords(BasicDBObject dbQuery,DBCursor cursorObj,DBCollection dbCollection,List<String> dbQueryArgs)
	{
		dbQuery = new BasicDBObject();
		
		return dbRecords;
	}
	
	
	/*
	 * 
	 * Yet to develop insert or update methods for MongoDB
	 */
	 

	
}
