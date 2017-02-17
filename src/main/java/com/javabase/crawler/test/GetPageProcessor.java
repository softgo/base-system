package com.javabase.crawler.test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class GetPageProcessor implements PageProcessor {

	public static final String URL_LIST = "http://blog\\.sina\\.com\\.cn/s/articlelist_1487828712_0_\\d+\\.html";
	public static final String URL_POST = "http://blog\\.sina\\.com\\.cn/s/blog_\\w+\\.html";

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
		System.out.println("page url = " + page.getUrl() );

		// 列表页
		if (page.getUrl().regex(URL_LIST).match()) {
			page.addTargetRequests(page.getHtml().xpath("//div[@class=\"articleList\"]").links().regex(URL_POST).all());
			page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
			// 文章页
		}
		else {
			//page.putField("title", page.getHtml().xpath("//div[@class='articalTitle']/h2"));
			//page.putField("content", page.getHtml().xpath("//div[@id='articlebody']//div[@class='articalContent']"));
			//page.putField("date",page.getHtml().xpath("//div[@id='articlebody']//span[@class='time SG_txtc']").regex("\\((.*)\\)"));
			
			System.err.println("title = " + page.getHtml().xpath("//div[@class='articalTitle']/h2"));
			System.err.println("content = " + page.getHtml().xpath("//div[@id='articlebody']//div[@class='articalContent']"));
			System.err.println("date = " + page.getHtml().xpath("//div[@id='articlebody']//span[@class='time SG_txtc']").regex("\\((.*)\\)"));
			
			//System.exit(0);
		}
	}



	public static void main(String[] args) {
		GetPageProcessor  processor = new GetPageProcessor();
		processor.setRequestInfo(new RequestInfo("blog.sina.com.cn", "http://blog.sina.com.cn/s/articlelist_1487828712_0_1.html"));
		processor.setSite(Site.me().setDomain(processor.getRequestInfo().getDomain()).setSleepTime(3000).setUserAgent(processor.getRequestInfo().getUserAgent()));
		Spider.create(processor).addUrl(processor.getRequestInfo().getRequestUrl()).run();
	}
}
