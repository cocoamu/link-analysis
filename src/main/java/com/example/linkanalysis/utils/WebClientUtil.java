package com.example.linkanalysis.utils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebClientUtil implements AutoCloseable {

    final Logger logger = LoggerFactory.getLogger(WebClient.class);


    private com.gargoylesoftware.htmlunit.WebClient webClient;
    private final String url;

    public WebClientUtil(String url) {
        initClient();
        this.url = url;
    }

    public Document execute() {
        try {
            //模拟浏览器打开一个目标网址
            HtmlPage page = webClient.getPage(this.url);
            //以xml形式获取响应文本
            String xml = page.asXml();
            //并转为Document对象return
            return Jsoup.parse(xml);

        } catch (Exception e) {
            logger.warn("get document title failed", e);
            return null;
        }
    }


    private void initClient() {
        //新建一个模拟谷歌Chrome浏览器的浏览器客户端对象
        webClient = new com.gargoylesoftware.htmlunit.WebClient(BrowserVersion.CHROME);
        //设置当HTTP的状态非200时不抛出异常
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        //设置不使用ActiveX
        webClient.getOptions().setActiveXNative(false);
        //禁用CSS
        webClient.getOptions().setCssEnabled(false);
        //启用JS
        webClient.getOptions().setJavaScriptEnabled(true);
        //设置js等待时间 该方法会阻塞线程
        webClient.waitForBackgroundJavaScript(3000);
        //设置JS运行错误时，不抛出异常
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        //设置支持ajax
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        //设置超时时间
        webClient.getOptions().setTimeout(10000);
        //设置不跟踪抓取
        webClient.getOptions().setDoNotTrackEnabled(true);
    }

    @Override
    public void close() throws Exception {
        webClient.close();
    }
}