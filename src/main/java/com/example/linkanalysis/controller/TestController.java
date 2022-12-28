package com.example.linkanalysis.controller;

import com.example.linkanalysis.utils.LinkUtil;
import com.example.linkanalysis.vo.DocumentView;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/")
    public String test(){
        String testUrl = "https://www.toutiao.com/article/7087559480132551180/?log_from=de8425284e52b_1650250842569";
        DocumentView documentView =  LinkUtil.getDocView(testUrl);
        return documentView.toString();
    }
}
