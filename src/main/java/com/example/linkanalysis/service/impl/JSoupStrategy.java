package com.example.linkanalysis.service.impl;

import com.example.linkanalysis.vo.DocumentView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URL;

public class JSoupStrategy extends JSoupStrategyAbstract {

    public static final JSoupStrategy INSTANCE = new JSoupStrategy();

    @Override
    public DocumentView execute(String url) {
        try {
            Document document = Jsoup.parse(new URL(url), 5000);
            if (document == null) return null;
            return parse(document);
        } catch (Exception e) {
            return null;
        }
    }
}