package com.potoyang.learn.getsomenovel.controller;

import com.potoyang.learn.getsomenovel.service.NovelDownloadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/7/10 12:14
 * Modified By:
 * Description:
 */
@Api("获取小说网址")
@RestController
public class NovelDownloadController {
    private static final String TAG = NovelDownloadController.class.getSimpleName();

    private static Logger logger = LoggerFactory.getLogger(NovelDownloadController.class);

    private final NovelDownloadService novelDownloadService;

    @Autowired
    public NovelDownloadController(NovelDownloadService novelDownloadService) {
        this.novelDownloadService = novelDownloadService;
    }

    @ApiOperation(value = "获取导航栏")
    @GetMapping("getNavi")
    public String getNave() {
        return novelDownloadService.getNavi();
    }

    @ApiOperation(value = "获取全部分类数据")
    @GetMapping("getFirstCate")
    public String getFirstCate() {
        return novelDownloadService.getAllCate();
    }

    @ApiOperation(value = "获取第全部小说链接")
    @GetMapping("getFirstNovel")
    public String getFirstNovel() {
        return novelDownloadService.getAllNovel();
    }

    /**
     * 文件下载
     *
     * @param response
     * @throws UnsupportedEncodingException
     */
    @ApiOperation("下载1")
    @GetMapping(value = "/exportUI")
    public void exportUI(HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException, MalformedURLException {
        String path = "http://www.jjxsw.com/e/DownSys/doaction.php?enews=DownSoft&classid=41&id=24485&pathid=0&pass=ee247a67a5adcf1dfb1abecbd1ff5635&p=:::";
        response.reset();
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("娱乐圈之风水不好.txt", "UTF-8"));
        response.setHeader("Connection", "close");
        response.setHeader("Content-Type", "charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/octet-stream");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置超时间为3秒
            conn.setConnectTimeout(3 * 1000);
            // 防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            // 得到输入流
            InputStream inputStream = conn.getInputStream();
            try {
                ServletOutputStream out = response.getOutputStream();
                request.setCharacterEncoding("UTF-8");
                int BUFFER = 1024 * 10;
                byte data[] = new byte[BUFFER];
                BufferedInputStream bis = null;
                //获取文件输入流
                InputStream fis = conn.getInputStream();
                int read;
                bis = new BufferedInputStream(fis, BUFFER);
                while ((read = bis.read(data)) != -1) {
                    out.write(data, 0, read);
                }
                fis.close();
                bis.close();
            } catch (IOException e) {
                logger.error("文件下载异常", "娱乐圈之风水不好.txt", e.fillInStackTrace());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("抛出异常！！");
        }
    }
}
