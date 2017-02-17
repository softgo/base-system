package com.javabase.crawler.test;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.selector.Selectable;

public class DemoPageProcessor2 implements PageProcessor {
	
	public static final String URL_LIST = "http://blog\\.sina\\.com\\.cn/s/articlelist_1936042535_0_\\d+\\.html";
	public static final String URL_POST = "http://blog\\.sina\\.com\\.cn/s/blog_\\w+\\.html";

	private static List<Selectable> urlList = new ArrayList<Selectable>();
	private static List<Html> htmlList = new ArrayList<Html>();
	private static List<Json> jsonList = new ArrayList<Json>();

	private Site site;
	private RequestInfo requestInfo;
	
	public void setSite(Site site) {
		this.site = site;
	}
	public Site getSite() {
		return site;
	}
	public RequestInfo getRequestInfo() {
		return requestInfo;
	}
	public void setRequestInfo(RequestInfo requestInfo) {
		this.requestInfo = requestInfo;
	}

	public void process(Page page) {
		// 列表页
		if (page.getUrl().regex(URL_LIST).match()) {
			page.addTargetRequests(page.getHtml().xpath("//div[@class=\"articleList\"]").links().regex(URL_POST).all());
			page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
			// 文章页
		}
		else {
			urlList.add(page.getUrl());
			htmlList.add(page.getHtml());
			jsonList.add(page.getJson());
			System.out.println(page.getHtml());
			//System.err.println("=======================================================================================================================================");
			//System.out.println(page.getJson());
			
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		DemoPageProcessor2  processor = new DemoPageProcessor2();
		processor.setRequestInfo(new RequestInfo("blog.sina.com.cn", "http://blog.sina.com.cn/s/articlelist_1936042535_0_1.html"));
		processor.setSite(Site.me().setDomain(processor.getRequestInfo().getDomain()).setSleepTime(3000).setUserAgent(processor.getRequestInfo().getUserAgent()));
		Spider.create(processor).addUrl(processor.getRequestInfo().getRequestUrl()).run();
		for (Selectable url : urlList) {
			System.out.println(url.toString());
		}
		for (Html html : htmlList) {
			System.out.println(html.toString());
		}
		for (Json json : jsonList) {
			System.out.println(json.toString());
		}
	}
}
