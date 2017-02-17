package com.javabase.crawler.demo;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 描 述：抓取xxx-国际频道历史数据 创建时间：2016-11-9
 * 
 * @author Jibaole
 */
public class HQNewsDao implements PageProcessor {
	public static final String index_list = "(.*).huanqiu.com/(.*)pindao=(.*)";// 校验地址正则
	public static String pic_dir = "/Users/rocky/Desktop/img/";// 获取图片保存路径

	// 部分一：抓取网站的相关配置，包括编码、重试次数、抓取间隔、超时时间、请求消息头、UA信息等
	private Site site = Site.me().setRetryTimes(3).
			setSleepTime(1000).setTimeOut(6000).addHeader("Accept-Encoding", "/")
			.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.59 Safari/537.36");

	public Site getSite() {
		return site;
	}

	public void process(Page page) {
		try {
			// 列表页
			if (page.getUrl().regex(index_list).match()) {
				List<String> Urllist = new ArrayList<String>();
				String url = page.getUrl().toString();
				String pageUrl = url.substring(0, url.lastIndexOf("?"));
				String pindaoId = url.substring(url.lastIndexOf("=") + 1);
				Urllist = saveNewsListData(pageUrl, pindaoId);
				page.addTargetRequests(Urllist);// 添加地址，根据url对该地址处理
			}
			// 可增加else if 处理不同URL地址
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<String> saveNewsListData(String pageUrl, String pindaoId) {
		List<String> urlList = new ArrayList<String>();
		Document docList = null;
		String newsIdFirst = "";
		// HttpClientUtil方式获取网页内容
		String pageListStr = HttpClientUtil.getPage(pageUrl,null);
		if (StringUtils.isNotEmpty(pageListStr)) {
			try {
				docList = Jsoup.parse(pageListStr);
				Elements fallsFlow = docList.getElementsByClass("fallsFlow");
				if (!fallsFlow.isEmpty()) {
					Elements liTag = fallsFlow.get(0).getElementsByTag("li");
					if (!liTag.isEmpty()) {
						for (int i = 0; i < liTag.size(); i++) {
							String title = "", contentUrl = "", newsId = "", pic = "", absContent = "", pushTime = "",
									timeFalg = "";
							Element obj = liTag.get(i);
							try {
								contentUrl = obj.getElementsByTag("h3").select("a").attr("href");
								if (StringUtils.isNotEmpty(contentUrl)) {
									title = obj.getElementsByTag("h3").select("a").attr("title");// 标题
									
									/**
									 * 校验新闻标题 略
									 */

									System.err.println("<<<<<<--DAO------当前抓取文章为(xxx历史):" + title + "------------");
									newsId = contentUrl.substring(contentUrl.lastIndexOf("/") + 1,
											contentUrl.lastIndexOf(".html"));
									if (!pageUrl.contains(".htm") && i == 0) {
										newsIdFirst = newsId;
									}
									// 图片
									if (!obj.getElementsByTag("img").attr("src").isEmpty()) {
										pic = obj.getElementsByTag("img").first().attr("src");
										
										/**
										 *  获取列表图片，保存本地 略
										 */

									}
									if (!obj.getElementsByTag("h5").isEmpty()) {
										// 简介
										absContent = obj.getElementsByTag("h5").first().text();
										if (StringUtils.isNotEmpty(absContent) && absContent.indexOf("[") > 0) {
											absContent = absContent.substring(0, absContent.indexOf("["));
										}
									}

									if (!obj.getElementsByTag("h6").isEmpty()) {
										pushTime = obj.getElementsByTag("h6").text();
										timeFalg = pushTime.substring(0, 4);
									}
									String hrmlStr = HttpClientUtil.getPage(contentUrl,null);
									if (StringUtils.isNotEmpty(hrmlStr)) {
										Document docPage = Jsoup.parse(hrmlStr);
										Elements pageContent = docPage.getElementsByClass("conText");
										if (!pageContent.isEmpty()) {
											String comefrom = pageContent.get(0).getElementsByClass("fromSummary")
													.text();// 来源
											if (StringUtils.isNotEmpty(comefrom) && comefrom.contains("环球")) {
												String author = pageContent.get(0).getElementsByClass("author").text();// 作者
												Element contentDom = pageContent.get(0).getElementById("text");
												if (!contentDom.getElementsByTag("a").isEmpty()) {
													contentDom.getElementsByTag("a").removeAttr("href");// 移除外跳连接
												}
												if (!contentDom.getElementsByClass("reTopics").isEmpty()) {
													contentDom.getElementsByClass("reTopics").remove();// 推荐位
												}
												if (!contentDom.getElementsByClass("spTopic").isEmpty()) {
													contentDom.getElementsByClass("spTopic").remove(); // 去除排行榜列表
												}
												if (!contentDom.getElementsByClass("editorSign").isEmpty()) {
													contentDom.getElementsByClass("editorSign").remove();// 移除编辑标签
												}

												String content = contentDom.toString();
												if (!StringUtils.isEmpty(content)) {
													content = content.replaceAll("\r\n|\r|\n|\t|\b|~|\f", "");// 去掉回车换行符
													/**
													 * 替换内容中的图片
													 */
												}
												else {
													break;
												}
											}
										}
									}
								}
							}
							catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return urlList;
	}

	public static void main(String[] args) {
		List<String> strList = new ArrayList<String>();
		// 滚动新闻
		strList.add("http://blog.sina.com.cn/s/articlelist_1487828712_0_1.html");

		for (String str : strList) {
			Spider.create(new HQNewsDao()).addUrl(str).thread(1).run();
		}
	}
	
}