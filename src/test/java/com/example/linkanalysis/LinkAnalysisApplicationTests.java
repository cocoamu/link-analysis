package com.example.linkanalysis;

import com.example.linkanalysis.utils.LinkUtil;
import com.example.linkanalysis.vo.DocumentView;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

@SpringBootTest
class LinkAnalysisApplicationTests {

    @Test
    void cleanTest(){
        String strHTML = "<html>" +
                "<head>" +
                "<title> Clean HTML By Jsoup Whitelist</title>" +
                "</head>" +
                "<body bgcolor=\"000000\">" +
                "<center><img src=\"image.jpg\" align=\"bottom\"> </center>" +
                "<hr>" +
                "<a href=\"http://blog.csdn.net/dietime1943\">bluetata</a>" +
                "<h1 data-type='123'>heading tags H1</h1>" +
                "<h2 data-type='456' data-value='879'>heading tags H2</h2>" +
                "My email link <a href=\"mailto:dietime1943@gmail.com\">" +
                "dietime1943@gmail.com</a>." +
                "<p>Para tag</p>" +
                "<p><b>bold paragraph</b>" +
                "<br><b><i>bold italics text.</i></b>" +
                "<hr>Horizontal line" +
                "</body>" +
                "</html>";

        //clean HTML using none whitelist (remove all HTML tags)
        Whitelist whitelist = Whitelist.none();
        whitelist.addTags("h1");
        whitelist.addAttributes("h2","data-type");
        whitelist.addAttributes("h2","data-value");
        String cleanedHTML = Jsoup.clean(strHTML, whitelist);
        System.out.println("None whitelist");
        System.out.println(cleanedHTML);

        System.out.println("===================================");

        Whitelist whitelist2 =  Whitelist.relaxed();
//        whitelist.addTags("hr");
        //clean HTML using relaxed whitelist
        cleanedHTML = Jsoup.clean(strHTML,whitelist2);
        System.out.println("Relaxed whitelist");
        System.out.println(cleanedHTML);
    }

    @Test
    void contextLoads() {
        for (int i = 0; i < 1; i++) {
            StopWatch stopWatch = new StopWatch("LinkUtil");
            stopWatch.start();

            DocumentView documentView = LinkUtil.getDocView("https://www.toutiao.com/article/7090151248359440935/?log_from=03e17c060c8ad_1652321471017");
            System.out.println(documentView.toString());

            stopWatch.stop();
            System.out.println(stopWatch.getTotalTimeMillis());
        }
    }
}
