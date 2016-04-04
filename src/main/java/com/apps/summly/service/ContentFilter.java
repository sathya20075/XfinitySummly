package com.apps.summly.service;

import com.apps.summly.main.SummlyMain;
import com.apps.summly.provider.News;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import com.apps.summly.Interface.RInterface;
import com.apps.summly.provider.ActiveNews;
import com.apps.summly.service.ContentRetriever;
import com.apps.summly.db.DBDataProvider;
import com.apps.summly.db.DBInterface;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ContentFilter 
{	
	RInterface rInterface;
	RConnection connectObj;
	ObjectMapper newsMapper;
	ContentRetriever newsContentList;
	DBInterface DBUtils;
	private DB db;
	private DBCollection dbCollec;
	private String dbNewsActiveDataCollection = DBDataProvider.DBACTIVENEWSDATA;
	
	public static void main(String args[]) throws FileNotFoundException, REXPMismatchException, JsonProcessingException
	{
		HashMap<String,BigDecimal> getContentRanks =null;
		String title ="Labor Department";
		String content=null;
		String Summary = null;
		ContentFilter contentFilter = new ContentFilter();
		SummlyMain summarizer = new SummlyMain();
		contentFilter.NewsContentProcessor();
		//content = contentFilter.NewsMainContentSummarizer();
		/*getContentRanks = summarizer.getSentenceRanks(content);
		Summary = summarizer.getSummary(title, content, getContentRanks);
		System.out.println(Summary);
		System.out.println("Original Length: " +content.length());
		System.out.println("Summary Length: " +Summary.length());*/
	}
	
	
	public void NewsContentProcessor() throws FileNotFoundException, REXPMismatchException, JsonProcessingException
	{
		
		newsContentList = new ContentRetriever();
		String NewsExtractedContent = null;
		String NewsBeforeSummarizer = null;
		String NewsTitle = null;
		String NewsSummary = null;
		String thumbNailImage = null;
		String wideImage = null;
		DBObject dbObject;
		HashMap<String,BigDecimal> getContentRanks =null;
		rInterface = new RInterface();
		newsMapper = new ObjectMapper();
		DBUtils = new DBInterface();
		SummlyMain summarizer = new SummlyMain();
		connectObj=rInterface.initializeRServer(rInterface.RSERVERHOST, rInterface.RSERVERPORT);
		List<News> newsList = (List<News>)newsContentList.NewsContentForTheDay("Business-0204201620:41:02");
		
		if(newsList.size()!=0)
		{
			db = DBUtils.initializeMongoDB();
			dbCollec = DBUtils.getDatabaseCollection(db, dbNewsActiveDataCollection);
			
			for(int newsIndex=0;newsIndex<=newsList.size()-1;newsIndex++)
			{
				News news =newsList.get(newsIndex);
				//System.out.println("NewsURL: " +news.getWeb_url());
				//System.out.println("NewsHeadline: " +news.getHeadline());
				NewsTitle = news.getHeadline();
				
				NewsExtractedContent = rInterface.callHTMLContentExtractor(news.getWeb_url(), connectObj);
				System.out.println("=========== News"+" "+newsIndex+"=========================");
				//System.out.println("NewsURL: " +news.getWeb_url());
				NewsBeforeSummarizer = NewsMainContentSummarizer(NewsExtractedContent);
				//System.out.println(NewsBeforeSummarizer);
				if(NewsBeforeSummarizer.length() < 1000)
				{
					NewsSummary = NewsBeforeSummarizer.replaceAll("(?m)^[ \t]*\r?\n", "").trim();
				}
				else
				{
					getContentRanks=summarizer.getSentenceRanks(NewsBeforeSummarizer);
					NewsSummary = summarizer.getSummary(NewsTitle, NewsBeforeSummarizer, getContentRanks);
				}
				if(news.getMultimedia().length <= 0)
				{
					thumbNailImage = "http://www.nytimes.com/images/2016/03/01/upshot/01up-healthmemory/01up-healthmemory-thumbStandard-v3.jpg";
					wideImage ="http://www.nytimes.com/images/2016/03/01/upshot/01up-healthmemory/01up-healthmemory-thumbWide-v2.jpg";
				}
				else
				{					
					for(String image : news.getMultimedia())
					{
						if(image.contains("thumbStandard"))
						{
							thumbNailImage = "http://www.nytimes.com/"+image;
						}
						else if(image.contains("thumbWide"))
						{
							wideImage = "http://www.nytimes.com/"+image;
						}
						else
						{
							thumbNailImage = "http://www.nytimes.com/images/2016/03/01/upshot/01up-healthmemory/01up-healthmemory-thumbStandard-v3.jpg";
							wideImage ="http://www.nytimes.com/images/2016/03/01/upshot/01up-healthmemory/01up-healthmemory-thumbWide-v2.jpg";
						}
					}
				}
				/*System.out.println("SummarizedContent::: "+NewsSummary);
				System.out.println("Headline:::: "+news.getHeadline());
				System.out.println("Thumbnail image: "+thumbNailImage);
				System.out.println("Wide image: "+wideImage);
				System.out.println("LeadParagraph::: "+news.lead_paragraph);
				System.out.println("Source:::::: "+news.source);*/
				ActiveNews newsActive = new ActiveNews(news.getNewsKey(), news.getWeb_url(), NewsSummary, news.getHeadline(),
						thumbNailImage, wideImage, news.lead_paragraph, news.source);
				String jsonInString= newsMapper.writeValueAsString(newsActive);
				dbObject = (DBObject) JSON.parse(jsonInString);
				dbCollec.insert(dbObject);
				System.out.println(jsonInString);
				System.out.println("==========================================================");
				
				
				
			}//end of for loop
		}
		
		
		
		
	}
	
	/*public void createAValidJSONAndInsertIntoDB()
	{
		newsMapper = new ObjectMapper();
		ActiveNews newsActive = ActiveNews(String newsId, String newsURL, String summarizedContent, String newsHeadline,
				String thumbNailImage, String wideImage, String leadParagraph, String source)
		
		
	}*/
	
	
	
	public String NewsMainContentSummarizer(String newsOContent)
	{
		StringBuilder builder = new StringBuilder();			
		String p_Content = null;
		String[] newsCArray = null;
		
		p_Content = newsOContent.replace("â","").replaceAll("(?<!Mr|mr|dr|mrs|ms|Dr|[\\d.\\d])(\\s*[\\.]\\s*)",".\n\n");
		//System.out.println(x.replaceAll("â(?<!Mr|mrs|ms|Dr)(\\s*[\\.\\?\\!]\\s*)","\n"));
		newsCArray = p_Content.split("\n\n");
		
	   for(int i=0;i<=newsCArray.length-1;i++)
	    {
	    	//System.out.println(newsCArray[i]);
	    	//sBuilder.append(newsCArray[i].toString());
	    	
	    	if(i == newsCArray.length-1)
	    	{	    
	    		
	    		builder.append(newsCArray[i]+".\n") ;
	    	}	    	
	    	else if(i == newsCArray.length/2)
	    	{
	    		
	    		builder.append(newsCArray[i]+"\n\n") ;
	    	}
	    	else if(i == newsCArray.length/4)
	    	{
	    		
	    		builder.append(newsCArray[i]+"\n\n") ;
	    	}
	    	else
	    	{
	    		builder.append(newsCArray[i]+"\n") ;
	    	}
	    	
	     }
	   
	     
	    //System.out.println(content.toString());
	   return builder.toString(); 
		
	}
	
	
}
