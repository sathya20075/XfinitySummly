package com.apps.summly.provider;

public class ActiveNews
{
  
	public String newsId;
	public String newsURL;
	public String summarizedContent;
	public String newsHeadline;
	public String thumbNailImage;
	public String wideImage;
	public String leadParagraph;
	public String source;
	public ActiveNews(String newsId, String newsURL, String summarizedContent, String newsHeadline,
			String thumbNailImage, String wideImage, String leadParagraph, String source) {
		super();
		this.newsId = newsId;
		this.newsURL = newsURL;
		this.summarizedContent = summarizedContent;
		this.newsHeadline = newsHeadline;
		this.thumbNailImage = thumbNailImage;
		this.wideImage = wideImage;
		this.leadParagraph = leadParagraph;
		this.source = source;
	}
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public String getNewsURL() {
		return newsURL;
	}
	public void setNewsURL(String newsURL) {
		this.newsURL = newsURL;
	}
	public String getSummarizedContent() {
		return summarizedContent;
	}
	public void setSummarizedContent(String summarizedContent) {
		this.summarizedContent = summarizedContent;
	}
	public String getNewsHeadline() {
		return newsHeadline;
	}
	public void setNewsHeadline(String newsHeadline) {
		this.newsHeadline = newsHeadline;
	}
	public String getThumbNailImage() {
		return thumbNailImage;
	}
	public void setThumbNailImage(String thumbNailImage) {
		this.thumbNailImage = thumbNailImage;
	}
	public String getWideImage() {
		return wideImage;
	}
	public void setWideImage(String wideImage) {
		this.wideImage = wideImage;
	}
	public String getLeadParagraph() {
		return leadParagraph;
	}
	public void setLeadParagraph(String leadParagraph) {
		this.leadParagraph = leadParagraph;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	
	
	
	
}
