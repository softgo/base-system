package com.javabase.crawler.test;

/**
 * 请求参数设置
 */
public class RequestInfo {

	private String domain ; //请求的网站根目录 eg:  blog.sina.com.cn 
	
	private long sleepTime = 5000; //沉睡时间,单位:毫秒
	
	private String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31"; //主要是 web 网页使用.
	
	private String requestUrl; //要爬取数据的url.
	
	public RequestInfo() {
		super();
	}

	public RequestInfo(String domain, String requestUrl) {
		super();
		this.domain = domain;
		this.requestUrl = requestUrl;
	}

	public RequestInfo(String domain, long sleepTime, String userAgent, String requestUrl) {
		super();
		this.domain = domain;
		this.sleepTime = sleepTime;
		this.userAgent = userAgent;
		this.requestUrl = requestUrl;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public long getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	
}
