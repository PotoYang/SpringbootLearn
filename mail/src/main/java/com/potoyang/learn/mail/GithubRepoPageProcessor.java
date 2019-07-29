package com.potoyang.learn.mail;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/7/29 10:42
 * Modified:
 * Description:
 */
public class GithubRepoPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
//        page.addTargetRequests(page.getHtml().links().regex("https://www.amazon.com/s?k=bluetooth+headset&page=1&ref=sr_pg_1").all());
        page.putField("desc", page.getHtml().xpath("//span[@class='a-size-medium a-color-base a-text-normal']/text()").all());
        page.putField("data-asin", page.getHtml().xpath("//div[@data-index]/@data-asin").all());
        page.putField("data-index", page.getHtml().xpath("//div[@data-asin]/@data-index").all());

        // //a[contains(@class,'b')]/img/@src
//        data-asin="B00W3TAMG8"
        String nameResult = page.getResultItems().get("data-index").toString();
        int count = nameResult.indexOf("data-index");
        System.out.println(count);
//        if (page.getResultItems().get("name") == null) {
//            page.setSkip(true);
//        }
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/div/article/h1/text()"));
    }

//<div data-asin="B00W3TAMG8" data-index="3" class="sg-col-

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor())
                .setDownloader(new MyHttpClientDownloader())
                .addUrl("https://www.amazon.com/s/ref=nb_sb_noss_2?url=search-alias=aps&field-keywords=bluetooth+headset")
                .addPipeline(new FilePipeline("E:\\Document\\WebMagic"))
                .thread(5)
                .run();
    }
}
