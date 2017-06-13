package cn.lee.jason.room.sample;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class FangPageProcessor implements PageProcessor{

	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
	
	@Override
	public void process(Page page) {
		System.out.println(page.getRawText());
	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new FangPageProcessor()).addUrl("http://zu.fang.com").thread(5).run();
	}
}
