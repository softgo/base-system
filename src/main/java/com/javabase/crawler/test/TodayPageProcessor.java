package com.javabase.crawler.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.javabase.crawler.demo.HttpClientUtil;


public class TodayPageProcessor {

	static String url = "http://mp.weixin.qq.com/s?__biz=MzA5Mjk1NDI2MA==&mid=211143696&idx=1&sn=de534c4cf2e32633793c82fb23757e8d&mpshare=1&scene=1&srcid=02103xMGGRvf4siT2D0dczGz#rd";

	public static void main(String[] args) {
		String context = HttpClientUtil.getPage(url, null);
		System.out.println("url = " + url );
		List<String> imgs =  getImgs(context);
		for (String img : imgs) {
			System.out.println("img = " + img);
		}
		List<String> contexts = getContexts(context); 
		for (String cont : contexts) {
			System.out.println("context = " + cont);
		}
	}

	public static List<String> getImgs(String context) {
		Set<String> pics = new HashSet<String>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        //     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(context);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        List<String> imgs = new ArrayList<String>(); 
        for (String pic : pics) {
			imgs.add(pic);
		}
        return imgs;
	}

	public static List<String> getContexts(String context) {
		//String regex = "<span.*?</span>";
		String regex = "<span.*?</span>";
		List<String> list = new ArrayList<String>();
		final Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		final Matcher ma = pa.matcher(context);
		while (ma.find()) {
			list.add(ma.group());
		}
		return list;
	}
}
