package com.apps.summly.httpUtilities;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.xml.sax.SAXException;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.document.TextDocument;
import de.l3s.boilerpipe.extractors.CommonExtractors;
import de.l3s.boilerpipe.sax.BoilerpipeSAXInput;
import de.l3s.boilerpipe.sax.HTMLDocument;
import de.l3s.boilerpipe.sax.HTMLFetcher;

public class ContentExtractorFromHtml
{

	public static void main(String args[]) throws MalformedURLException, IOException, BoilerpipeProcessingException, SAXException
	{
		try
		{
			final HTMLDocument htmlDoc = HTMLFetcher.fetch(new URL("http://www.nytimes.com/reuters/2016/03/24/sports/basketball/24reuters-nba-thunder.htm"));
			final TextDocument doc = new BoilerpipeSAXInput(htmlDoc.toInputSource()).getTextDocument();
			
			String content = CommonExtractors.ARTICLE_EXTRACTOR.getText(doc);
			System.out.println(content);
		}catch(IOException ex)
		{
			ex.printStackTrace();
		}
		catch(BoilerpipeProcessingException exx)
		{
			exx.printStackTrace();
		}
		
	}
	
	
}
