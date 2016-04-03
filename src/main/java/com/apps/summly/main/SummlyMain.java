package com.apps.summly.main;


import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SummlyMain
{
	
	public static void main(String args[]){
	
		HashMap<String,BigDecimal> getContentRanks =null;
		String title ="At New York Auto Show, a Parade of New Models";
		
		String content = "";
		
		content += "Vehicle sales in the United States are running at a record pace, prompting automakers to consider ways to capitalize on the boom times.\n";
		content += "One of their strategies is becoming increasingly clear. Automakers are quickly expanding their product lineups, introducing a blitz of new models in virtually every market segment â from giant sport utility vehicles to compact electric cars.\n";
		content += "Many of the latest offerings were on display on Wednesday at the media previews for the New York International Auto Show. Both Nissan and Volkswagenâs Audi luxury brand unveiled high-horsepower sports cars, and Fordâs Lincoln division showed a new version of its full-size Navigator S.U.V. At the other end of the spectrum, Hyundai introduced a new sedan devoted to hybrid and electric power.\n";
		content += "The parade of new models provide another reminder of the striking turnaround in the auto industry. Just a few years ago, after a deep recession, automakers faced major restructuring, including the government-backed bankruptcies of General Motors and Chrysler.\n"; 
		content += "Back then, the goal was to pare back brands and models to save money. But now, with most companies posting big profits, they are splurging on new investments and technology.\n";
		content += "And with the industry coming off record sales of 17.5 million vehicles last year in the United States, no automaker wants to be underrepresented in hot segments like small S.U.V.s, or even niche products, such as sports cars.âThe industry as a whole has never been as healthy as it is now,â said Carlos Ghosn, chief executive of Nissan and Renault. âItâs a very favorable environment.\n";
		content += "Nissan, which has enjoyed strong sales of its mainstream cars and S.U.V.s, is adding to its lineup with new versions of its GT-R sports car and Titan pickup truck. The goal, Mr. Ghosn said, is to increase market share in the robust American market..\n\n\n";
		content += "Market share is a result of the investment that you do,â said Mr. Ghosn. âWeâre doing all this investment to get a good return by having a bigger piece of the pie.\n";
		content += "Industry sales so far this year are running ahead of the pace set in 2015. But there are concerns among Wall Street analysts that automakers may fall back into bad habits such as overproduction or subsidizing sales with heavy incentives.Some analysts have cautioned that the sales cycle may be peaking and maybe even headed for an inevitable downturn.The sales results in the U.S. so far this year have been positive, but there remains a question of when will the next shoe drop,â said Jack R. Nerad, an analyst with the auto research firm Kelley Blue Book.\n";
		content += "Analysts point out troubling signs that the industry is trying to prop up sales. Some carmakers, for example, are pushing slower-selling models with more discounts and cheap leases, and are making mostly cosmetic changes to older vehicles to freshen their appeal.Industry executives say that the competitive environment is driving them to add more models in segments that are growing, such as S.U.V.s.âCompetition is always good for us and the consumer,â Mark Fields, chief executive of Ford Motor, said. Weâre not going to spend money for the sake of it, but we want to meet the demands of the market.âIn Fordâs case, the company needed to invest in a new Navigator to take advantage of the renewed popularity of big S.U.V.s with three rows of seating.\n";
		content += "Company executives admitted that the vehicle had grown old in comparison with the Cadillac Escalade produced by General Motors.It also is a linchpin in Fordâs long-term strategy to build Lincoln into a stronger competitor in the high-profit luxury segment. âWe plan to get an even bigger share of the full-size luxury S.U.V.s,â said Mr. Fields.The German luxury brands are also aggressively adding new products. Audi, for example, introduced a convertible version of its R8 sports car powered by a huge V10 engine.Audi executives say companies need to stretch their product portfolios to appeal to the specific tastes of various consumers.âWe are seeing more fragmented market segments, so we have to come up with new concepts,â said Dietmar Voggenreiter, a member of Audiâs board of management.Other companies are simply eager to enter segments that are dominated by competitors.\n";
		content += "The Korean carmaker Hyundai competes head-on with Toyota in many passenger car and S.U.V. categories. But the company did not have a product to rival Toyotaâs top-selling line of Prius hybrids, powered by a combination of gasoline engines and batteries.On Wednesday, Hyundai introduced its new Ioniq sedan that will be available in hybrid, plug-in hybrid and pure electric versions. The automaker is also ramping up its luxury Genesis brand to broaden its clientele of wealthier consumers.Yet even as Hyundai and others step up their offerings of hybrids and electric vehicles, leaders in various segments continue to broaden their own lineups to stay ahead.\n";
		content += "Toyota has dominated hybrid sales for years with the Prius, but the company recently introduced a new generation of the basic model and, on Wednesday, introduced a new variation called Prius Prime.Among other things, Prius Prime lets drivers operate the vehicle solely on battery power, in addition to its traditional hybrid mode. The car is also packed with autonomous features like parking assistance and cameras that monitor blind spots in the driverâs field of vision.Toyota calls the vehicle the most technologically advanced hybrid on the market â serving notice that it will keep investing to stay ahead of the competition.\n";
		content += "Yet even as Hyundai and others step up their offerings of hybrids and electric vehicles, leaders in various segments continue to broaden their own lineups to stay ahead.Toyota has dominated hybrid sales for years with the Prius, but the company recently introduced a new generation of the basic model and, on Wednesday, introduced a new variation called Prius Prime.Among other things, Prius Prime lets drivers operate the vehicle solely on battery power, in addition to its traditional hybrid mode. The car is also packed with autonomous features like parking assistance and cameras that monitor blind spots in the driverâs field of vision.Toyota calls the vehicle the most technologically advanced hybrid on the market â serving notice that it will keep investing to stay ahead of the competition.\n";
		
		
		String c ="Vehicle sales in the United States are running at a record pace, prompting automakers to consider ways to capitalize on the boom times.One of their strategies is becoming increasingly clear. Automakers are quickly expanding their product lineups, introducing a blitz of new models in virtually every market segment â from giant sport utility vehicles to compact electric cars.Many of the latest offerings were on display on Wednesday at the media previews for the New York International Auto Show. Both Nissan and Volkswagenâs Audi luxury brand unveiled high-horsepower sports cars, and Fordâs Lincoln division showed a new version of its full-size Navigator S.U.V. At the other end of the spectrum, Hyundai introduced a new sedan devoted to hybrid and electric power.The parade of new models provide another reminder of the striking turnaround in the auto industry. Just a few years ago, after a deep recession, automakers faced major restructuring, including the government-backed bankruptcies of General Motors and Chrysler.Back then, the goal was to pare back brands and models to save money. But now, with most companies posting big profits, they are splurging on new investments and technology.And with the industry coming off record sales of 17.5 million vehicles last year in the United States, no automaker wants to be underrepresented in hot segments like small S.U.V.s, or even niche products, such as sports cars.âThe industry as a whole has never been as healthy as it is now,â said Carlos Ghosn, chief executive of Nissan and Renault. âItâs a very favorable environment.âNissan, which has enjoyed strong sales of its mainstream cars and S.U.V.s, is adding to its lineup with new versions of its GT-R sports car and Titan pickup truck. The goal, Mr. Ghosn said, is to increase market share in the robust American market.âMarket share is a result of the investment that you do,â said Mr. Ghosn. âWeâre doing all this investment to get a good return by having a bigger piece of the pie.âIndustry sales so far this year are running ahead of the pace set in 2015. But there are concerns among Wall Street analysts that automakers may fall back into bad habits such as overproduction or subsidizing sales with heavy incentives.Some analysts have cautioned that the sales cycle may be peaking and maybe even headed for an inevitable downturn.âThe sales results in the U.S. so far this year have been positive, but there remains a question of when will the next shoe drop,â said Jack R. Nerad, an analyst with the auto research firm Kelley Blue Book.Analysts point out troubling signs that the industry is trying to prop up sales. Some carmakers, for example, are pushing slower-selling models with more discounts and cheap leases, and are making mostly cosmetic changes to older vehicles to freshen their appeal.Industry executives say that the competitive environment is driving them to add more models in segments that are growing, such as S.U.V.s.âCompetition is always good for us and the consumer,â Mark Fields, chief executive of Ford Motor, said. âWeâre not going to spend money for the sake of it, but we want to meet the demands of the market.âIn Fordâs case, the company needed to invest in a new Navigator to take advantage of the renewed popularity of big S.U.V.s with three rows of seating.Company executives admitted that the vehicle had grown old in comparison with the Cadillac Escalade produced by General Motors.It also is a linchpin in Fordâs long-term strategy to build Lincoln into a stronger competitor in the high-profit luxury segment. âWe plan to get an even bigger share of the full-size luxury S.U.V.s,â said Mr. Fields.The German luxury brands are also aggressively adding new products. Audi, for example, introduced a convertible version of its R8 sports car powered by a huge V10 engine.Audi executives say companies need to stretch their product portfolios to appeal to the specific tastes of various consumers.âWe are seeing more fragmented market segments, so we have to come up with new concepts,â said Dietmar Voggenreiter, a member of Audiâs board of management.Other companies are simply eager to enter segments that are dominated by competitors.The Korean carmaker Hyundai competes head-on with Toyota in many passenger car and S.U.V. categories. But the company did not have a product to rival Toyotaâs top-selling line of Prius hybrids, powered by a combination of gasoline engines and batteries.On Wednesday, Hyundai introduced its new Ioniq sedan that will be available in hybrid, plug-in hybrid and pure electric versions. The automaker is also ramping up its luxury Genesis brand to broaden its clientele of wealthier consumers.Yet even as Hyundai and others step up their offerings of hybrids and electric vehicles, leaders in various segments continue to broaden their own lineups to stay ahead.Toyota has dominated hybrid sales for years with the Prius, but the company recently introduced a new generation of the basic model and, on Wednesday, introduced a new variation called Prius Prime.Among other things, Prius Prime lets drivers operate the vehicle solely on battery power, in addition to its traditional hybrid mode. The car is also packed with autonomous features like parking assistance and cameras that monitor blind spots in the driverâs field of vision.Toyota calls the vehicle the most technologically advanced hybrid on the market â serving notice that it will keep investing to stay ahead of the competition.";
		SummlyMain summly = new SummlyMain();
		//summly.splitContentToSentences(content);
		//summly.splitContentToParagraphs(content);
		String sen1 ="Want to try Swayy out without having to wait? Go to this secret URL and enter the promotion code thenextweb . The first 300 people to use the code will get access.";
		String sen2 ="This is not nature god";
		String Summary = null;
		//BigDecimal norm =summly.returnNormalizationValueOfSentences(sen1,sen2);
		
		//summly.formatSentences(sen1);
		getContentRanks=summly.getSentenceRanks(content);
		
		/*Iterator contentIterator = getContentRanks.keySet().iterator();
		while(contentIterator.hasNext())
		{
			String key = contentIterator.next().toString();
			//System.out.println("Key: "+ key);
			//System.out.println("Value:" +getContentRanks.get(key));
			
		}*/
		Summary = summly.getSummary(title, content, getContentRanks);
		System.out.println(Summary);
		System.out.println("Original Length: " +content.length());
		System.out.println("Summary Length: " +Summary.length());
	}
	
	public List<String> splitContentToSentences(String content)
	{
		
		List<String> sentencesrepo = null;
		
		if(content.length() != 0)
		{
			//System.out.println("Length:" +content.length());
		 String[] result = content.split(".\n");
		 sentencesrepo = new ArrayList<String>();
		 for(String s:result){
			  //System.out.println(s);
			 sentencesrepo.add(s);
		 }
		}
		return sentencesrepo;
	}
	
	public List<String> splitContentToParagraphs(String content)
	{
		List<String> paragraphrepo = null;
		if(content.length() != 0)
		{
			String[] result = content.split("\n\n");
			paragraphrepo = new ArrayList<String>();
			for(String s:result){
				//System.out.println(s);
				paragraphrepo.add(s);
			}
		}
		return paragraphrepo;
	}
    
	public String formatSentences(String Sentence)
	{
		String sentence = null;
		if(!Sentence.isEmpty())
		{
			sentence = Sentence.replaceAll("\\W*", "");
			//System.out.println("Sentence: " +sentence);
		}
		
		return sentence;
	}
	
	/**
	 * @param sen1
	 * @param sen2
	 */
	public BigDecimal returnNormalizationValueOfSentences(String sentence1, String sentence2)
	{
		String[] str1Array={};
		String[] str2Array={};
		BigDecimal normValue;
		BigDecimal commonTokensSize;
		BigDecimal averageSentenceLength;
		/*sen1 ="This is the nature god for sure";
		sen2 ="This is not nature god";*/
		if(sentence1.isEmpty() != true)
		{
			str1Array = sentence1.replace(".", "").replace(",", "").replace("?", "").replace("!","").split(" ");
			str2Array = sentence2.replace(".", "").replace(",", "").replace("?", "").replace("!","").split(" ");
		}
		else{
			System.exit(0);
		}
		 
		
		Collection<String> listOne = new ArrayList<String>(Arrays.asList(str1Array));
		Collection<String> listTwo = new ArrayList<String>(Arrays.asList(str2Array));
             
		listOne.retainAll( listTwo );
		//System.out.println( listOne );
		int sentenceLength = (str1Array.length+str2Array.length)/2;
		commonTokensSize = new BigDecimal(listOne.size());
		averageSentenceLength = new BigDecimal(sentenceLength);
		
		normValue = commonTokensSize.divide(averageSentenceLength, 4, RoundingMode.CEILING);
		
		//System.out.println(normValue);		
		
		return normValue;
		
	}
	
    /**
     * @param content
     */
    public HashMap<String,BigDecimal> getSentenceRanks(String content)
    {
    	
    	int sentencerepoLength=0;
    	BigDecimal score;
    	BigDecimal[][] scorerepo={};
    	List<String> sentencesList = null;
    	HashMap<String,BigDecimal> sentDict = null;
    	
    	
    	if(content.isEmpty())
    	{
    		System.out.println("Empty Content..Exiting the app..");
    		System.exit(0);
    	}
    	else
    	{
    		sentencesList = splitContentToSentences(content);
    		if(sentencesList.size() != 0)
    		{    			
    			sentencerepoLength=sentencesList.size();
    			scorerepo =  new BigDecimal[sentencerepoLength][sentencerepoLength];
    			for(int i=0;i<=sentencerepoLength-1;i++)
    			{
    				for(int j=0;j<=sentencerepoLength-1;j++)
    				{
    					scorerepo[i][j]=returnNormalizationValueOfSentences(sentencesList.get(i),sentencesList.get(j));
    				}
    			}
    			
    			//Creating Sentences dictionary with norm value.
    			sentDict = new HashMap<String,BigDecimal>();
    			for(int i=0;i<=sentencerepoLength-1;i++)
    			{
    				score=new BigDecimal(0);
    				for(int j=0;j<=sentencerepoLength-1;j++)
    				{
    					if(i==j){
    						continue;
    					}
    					score = score.add(scorerepo[i][j]);
    					
    				}
    				sentDict.put(formatSentences(sentencesList.get(i)), score);
    			}
    		}
    		else
    		{
    			System.out.println("Sentences repository is null.. Exiting..");
    			System.exit(0);
    		}
    	}
    	return sentDict;
    }
	
    public String getBestSentence(String paragraph, HashMap<String,BigDecimal> sentenceDict)
    {
    	
    	String bestSentence = null;
    	String sentenceKey = null;
    	BigDecimal maxValue = new BigDecimal(0);
    	List<String> sentencesList = null;
    	
    	
    	sentencesList = splitContentToSentences(paragraph);
    	
    	if(sentencesList.size() < 2)
    	{
    		return null;
    	}
    	
    	for(String s:sentencesList)
    	{
    		sentenceKey=formatSentences(s);
    		
    		try
    		{
    			if((!sentenceKey.isEmpty()) && (sentenceDict.get(sentenceKey).compareTo(maxValue)) == 1)
    			{
    				maxValue = sentenceDict.get(sentenceKey);
    				bestSentence = s;
    				
    				
    			}
        		else
        		{
        			continue;
        		}
    		}catch(NullPointerException ex)
    		{
    			ex.printStackTrace();
    		}
    		
    					
    	}
    	
    	return bestSentence;    	
     }
    
	 public String getSummary(String title, String content, HashMap<String,BigDecimal> sentenceDict)
	 {
		 
		 List<String> paragraphs = null;
		 String sentence = null;
		 StringBuffer bString = new StringBuffer();
		 
		 paragraphs=splitContentToParagraphs(content);
		 
		 
		
		 bString.append(title);
		 bString.append(" ");
		 
		 
		 for(String paragraph:paragraphs)
		 {
			 sentence = getBestSentence(paragraph,sentenceDict).trim();			 
			 bString.append(sentence).append(". ");
			 
		 }
		 
		 return bString.append("\n").toString();
	 }
}
