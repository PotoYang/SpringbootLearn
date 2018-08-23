package com.potoyang.learn.getsomenovel.service.impl;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.potoyang.learn.getsomenovel.dao.CateFirstRepository;
import com.potoyang.learn.getsomenovel.dao.NaviBarRepository;
import com.potoyang.learn.getsomenovel.dao.NovelFirstRepository;
import com.potoyang.learn.getsomenovel.entity.CateAll;
import com.potoyang.learn.getsomenovel.entity.NaviBar;
import com.potoyang.learn.getsomenovel.entity.NovelAll;
import com.potoyang.learn.getsomenovel.service.NovelDownloadService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/7/10 11:59
 * Modified By:
 * Description:
 */
@Service
public class NovelDownloadServiceImpl implements NovelDownloadService {
    private static final String TAG = NovelDownloadServiceImpl.class.getSimpleName();

    private static final Logger log = LoggerFactory.getLogger(NovelDownloadServiceImpl.class);

    private final NaviBarRepository naviBarRepository;
    private final CateFirstRepository cateFirstRepository;
    private final NovelFirstRepository novelFirstRepository;

    @Value("${myconfig.host}")
    public String host;

    @Autowired
    public NovelDownloadServiceImpl(NaviBarRepository naviBarRepository,
                                    CateFirstRepository cateFirstRepository,
                                    NovelFirstRepository novelFirstRepository) {
        this.naviBarRepository = naviBarRepository;
        this.cateFirstRepository = cateFirstRepository;
        this.novelFirstRepository = novelFirstRepository;
    }

    @Override
    public String getNavi() {
        try {
            Document content = Jsoup.connect(host).get();
            Element element = content.getElementById("navber");
            Elements links = element.getElementsByTag("a");
            List<NaviBar> naviBarList = new ArrayList<>();
            for (Element link : links) {
                if (link.attr("href").length() < 2) {
                    continue;
                }
                NaviBar naviBar = new NaviBar();
                naviBar.setName(link.text());
                naviBar.setUrl(link.attr("href"));
                naviBarList.add(naviBar);
            }
            naviBarRepository.saveAll(naviBarList);
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @Override
    public String getAllCate() {
        List<NaviBar> naviBarList = naviBarRepository.findAll();
        executeAsync(naviBarList);
        return "success";
    }

    @Override
    public String getAllNovel() {
//        try {
        List<CateAll> cateAllList = cateFirstRepository.findAll();
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("my-pool-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(10, 30,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(100),
                threadFactory, new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 10; i++) {
            final int seed = i;
            executorService.execute(() -> cateAllList.subList(seed * 1180, (seed + 1) * 1180 - 1).forEach(cateAll -> {
                try {
                    Document content = Jsoup.connect(host + cateAll.getUrl()).get();
                    Elements elements = content.getElementsByAttributeValue("class", "downAddress_li");

                    String html = elements.html();
                    content = Jsoup.parse(html);
                    elements = content.getElementsByTag("a");
                    String downloadLink = elements.attr("href");

                    content = Jsoup.connect(host + downloadLink).get();
                    Element element = content.getElementById("Frame");
                    elements = element.getElementsByTag("a");
                    String txtUrl = elements.get(elements.size() - 2).attr("href");
                    String zipUrl = elements.get(elements.size() - 1).attr("href");

                    NovelAll novelAll = new NovelAll();
                    novelAll.setName(cateAll.getName());
                    novelAll.setTxtUrl(txtUrl);
                    novelAll.setZipUrl(zipUrl);
                    novelFirstRepository.save(novelAll);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
        }
        return "success";
            /*
            CateAll cateAll = cateFirstRepository.getOne(1);
            Document content = Jsoup.connect(host + cateAll.getUrl()).get();
            Elements elements = content.getElementsByAttributeValue("class", "downAddress_li");

            String html = elements.html();
            content = Jsoup.parse(html);
            elements = content.getElementsByTag("a");
            String downloadLink = elements.attr("href");

            content = Jsoup.connect(host + downloadLink).get();
            Element element = content.getElementById("Frame");
            elements = element.getElementsByTag("a");
            String txtUrl = elements.get(elements.size() - 2).attr("href");
            String zipUrl = elements.get(elements.size() - 1).attr("href");

            NovelAll novelAll = new NovelAll();
            novelAll.setName(cateAll.getName());
            novelAll.setTxtUrl(txtUrl);
            novelAll.setZipUrl(zipUrl);
            novelFirstRepository.save(novelAll);*/
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "fail";
//        }
    }

    private void executeAsync(List<NaviBar> naviBarList) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("my-pool-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(2, 5,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(25),
                threadFactory, new ThreadPoolExecutor.AbortPolicy());
        naviBarList.forEach(naviBar -> executorService.execute(() -> {
            try {
                Document content = Jsoup.connect(host + naviBar.getUrl() + "index_1.html").get();
                Element element = content.getElementById("catalog");

                int pageCount = 1;
                while (element.hasText()) {
                    Elements links = element.getElementsByTag("a");
                    Elements imgs = element.getElementsByTag("img");
                    List<CateAll> cateAllList = new ArrayList<>();
                    for (int i = 0; i < links.size(); i = i + 2) {
                        CateAll cateAll = new CateAll();
                        Element e = links.get(i);
                        cateAll.setUrl(e.attr("href"));
                        cateAll.setName(e.attr("title"));
                        cateAll.setImg(imgs.get(i / 2).attr("src"));
                        cateAllList.add(cateAll);
                    }
                    cateFirstRepository.saveAll(cateAllList);

                    pageCount++;

                    content = Jsoup.connect(host + naviBar.getUrl() + "index_" + pageCount + ".html").get();
                    element = content.getElementById("catalog");
                }
                System.out.println(Thread.currentThread().getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        executorService.shutdown();
    }
}
