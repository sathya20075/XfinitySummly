package com.apps.summly.provider;

public class News 
{

	public String web_url;
	public String headline;
	public String[] multimedia;
	public String lead_paragraph;	
	public String source;
	
	public News(String web_url, String headline, String[] multimedia, String lead_paragraph, String source) {
		super();
		this.web_url = web_url;
		this.headline = headline;
		this.multimedia = multimedia;
		this.lead_paragraph = lead_paragraph;
		this.source = source;
	}

	public String getWeb_url() {
		return web_url;
	}

	public void setWeb_url(String web_url) {
		this.web_url = web_url;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String[] getMultimedia() {
		return multimedia;
	}

	public void setMultimedia(String[] multimedia) {
		this.multimedia = multimedia;
	}

	public String getLead_paragraph() {
		return lead_paragraph;
	}

	public void setLead_paragraph(String lead_paragraph) {
		this.lead_paragraph = lead_paragraph;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	
}
