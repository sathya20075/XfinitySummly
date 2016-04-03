package com.apps.summly.service;

import com.apps.summly.main.SummlyMain;
import com.apps.summly.provider.News;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import com.apps.summly.Interface.RInterface;
import com.apps.summly.service.ContentRetriever;



public class ContentFilter 
{

	ContentRetriever newsContentList;
	RInterface rInterface;
	RConnection connectObj;
	
	
	public static void main(String args[]) throws FileNotFoundException, REXPMismatchException
	{
		HashMap<String,BigDecimal> getContentRanks =null;
		String title ="Labor Department";
		String content=null;
		String Summary = null;
		ContentFilter contentFilter = new ContentFilter();
		SummlyMain summarizer = new SummlyMain();
		//contentFilter.NewsContentProcessor();
		content = contentFilter.NewsMainContentSummarizer();
		getContentRanks=summarizer.getSentenceRanks(content);
		Summary = summarizer.getSummary(title, content, getContentRanks);
		System.out.println(Summary);
		System.out.println("Original Length: " +content.length());
		System.out.println("Summary Length: " +Summary.length());
	}
	
	
	public void NewsContentProcessor() throws FileNotFoundException, REXPMismatchException
	{
		newsContentList = new ContentRetriever();
		String NewsExtractedContent = null;
		rInterface = new RInterface();
		connectObj=rInterface.initializeRServer(rInterface.RSERVERHOST, rInterface.RSERVERPORT);
		List<News> newsList = (List<News>)newsContentList.NewsContentForTheDay();
		
		
		
		if(newsList.size()!=0)
		{
			for(int newsIndex=0;newsIndex<=newsList.size()-1;newsIndex++)
			{
				News news =newsList.get(newsIndex);
				System.out.println("NewsURL: " +news.getWeb_url());
				NewsExtractedContent = rInterface.callHTMLContentExtractor(news.getWeb_url(), connectObj);
				System.out.println("=========== News"+" "+newsIndex+"=========================");
				System.out.println(NewsExtractedContent);
				System.out.println("==========================================================");
			}
		}
		
		
	}
	
	public void NewsContentExtractorFromHtml()
	{
		
	}
	
	public String NewsMainContentSummarizer()
	{
		StringBuilder builder = new StringBuilder();
		String content=null;
		String newsOriginalContent=null;
		String p_Content = null;
		String[] newsCArray = null;
		newsOriginalContent ="The Labor Department plans to announce on Thursday new rules that sharply reduce workplace exposure to silica, a potentially deadly mineral found in materials commonly used in construction and hydraulic fracturing, or fracking.Safety experts have urged a tightening of silica exposure standards since the 1970s because research shows that particles of the mineral, when inhaled, can cause silicosis, a disabling and sometimes fatal lung disease. However, progress was stymied for decades by resistance from affected companies and regulatory inaction.The new rules are the second major action taken by the Obama administration in recent months to address a long-known workplace hazard. In August, the Occupational Safety and Health Administration proposed lowering exposure to beryllium, an industrial metal, to one-tenth their current levels.Officials estimate that the new silica standard, when fully in effect, will save 600 lives and prevent 900 new cases of silicosis every year.âOne of the basic bargains we make is that every working person should come home safely at the end of the day,â Thomas E. Perez, the secretary of labor, said in a telephone interview.Silica, which is a component of sand and stone, is found in materials such as concrete, brick, building blocks and mortar. During procedures like sandblasting and fracking or when building materials are cut, large quantities of silica particles can be released into the air. Those particles can lodge deep in the lung, setting off processes that can lead to lung cancer as well as kidney disease, in addition to silicosis.Under the new rules, permitted exposures to silica throughout the construction industry would be cut to 50 micrograms per cubic meter of air over an eight-hour period from 250 micrograms. In other industries, which have a 100 microgram standard, it will also be reduced to 50 micrograms.Companies will be required, among other measures, to keep records of employee exposure to silica and to provide a medical examination every three years to each worker whose level of exposure is high enough to require wearing a protective respirator for at least 30 days a year. The new rules were first proposed in 2013 and went through a two-year period of review and public comment.Companies have argued that voluntary steps already adopted, like the use of respirators or vacuums that trap and collect silica dust, have sharply reduced the incidence of silicosis. And they have said the new rule would impose billions of dollars in new costs on them.In addition, several trade groups, like the National Industrial Sand Association, have questioned the health effects from changing the rules. Last year, in publicly filed comments on the new rule, the groups argued that OSHA had failed to show that reducing exposure limits to 50 micrograms within industries already covered by the 100 microgram standard would result in added safety benefits for workers.An estimated 2.3 million workers are affected by the new rule, with about two million of them employed in construction-related industries, according to Labor Department estimates.In recent years, new workers have been exposed to silicaâs risks. In 2012, for example, federal officials issued an alert about the hazards that workers faced during fracking, a process that uses sand to force oil through underground rocks.In a speech this year to an energy industry group, Dr. David Michaels, the director of OSHA, stated that many oil and gas companies were not meeting the current silica exposure standard.âWe regularly run into employers who are not in compliance with the current standard,â Dr. Michaels said in a telephone interview.While other industries will have two years to conform fully to the new exposure rules, the fracking industry will have five years.Mr. Perez, the labor secretary, acknowledged that companies might sue the department in an effort to block the regulations.But he said he thought that many companies would easily adapt to the new standard because inexpensive equipment is available to control and trap the release of silica dust.In the 1970s, when OSHA set the silica standard, the National Institute for Occupational Safety and Health was already recommending that it be reduced to the 50-microgram threshold.âThis is no different than the story of asbestos,â Mr. Perez said. âAfter 40 years, the political will has finally caught up with the science.â";
		p_Content = newsOriginalContent.replace("â","").replaceAll("(?<!Mr|mr|dr|mrs|ms|Dr|[\\d.\\d])(\\s*[\\.]\\s*)",".\n\n");
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
