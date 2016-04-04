package com.apps.summly.Interface;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import java.io.FileNotFoundException;
import org.rosuda.JRI.RBool;
import javax.script.*;

public class RInterface
{
	
	RConnection connection = null;
	String articleContent = null;
	public static final String RSERVERHOST = "localhost";
	public static final int RSERVERPORT =6311;
	
	public static void main(String args[]) throws REXPMismatchException, FileNotFoundException
	{
		RConnection rconnection = null;
		RInterface callInterface = new RInterface();
		rconnection =callInterface.initializeRServer(RSERVERHOST,RSERVERPORT);
		callInterface.callHTMLContentExtractor("http://www.nytimes.com/2016/03/24/business/pinnacle-foods-ceo-named-as-next-chief-of-keurig.html",rconnection);
	}
	
	public RConnection initializeRServer(String host,int port)
	{
		RConnection connobj=null;
		try
		{
			connobj = new RConnection(host,port);	
					
		}catch(RserveException ex)
		{
			ex.printStackTrace();
		}
		return connobj;
	}
	
	public String callHTMLContentExtractor(String htmlURL,RConnection connection) throws REXPMismatchException, FileNotFoundException
	{
		String[] content=null;		
		try
		{
			
			
			if(connection.isConnected())
			{
				//System.out.println("Connected to RServer through Java interface..");
				connection.eval("source('D:\\\\DataMining.R')");
				
				connection.eval("library('XML')");
				connection.eval("library('httr')");
				connection.eval("p <- GET('"+htmlURL+"')");				
				connection.eval("html <- content(p, 'text')");
			    content = connection.eval("parseArticleBody(html)").asStrings();
				//System.out.println(content[0]);
			}
			else
			{
				System.out.println("Not connected to RServer..");
				System.exit(0);
			}
			
		}catch(RserveException ex)
		{
			ex.printStackTrace();
		}		
		finally{
			if(connection != null)
			{
				
				
			}
		}
		return content[0];
	}
	
}
