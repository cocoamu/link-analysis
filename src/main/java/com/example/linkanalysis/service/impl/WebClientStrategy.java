package com.example.linkanalysis.service.impl;

import com.example.linkanalysis.service.LinkStrategy;
import com.example.linkanalysis.utils.WebClientUtil;
import com.example.linkanalysis.vo.DocumentView;
import org.jsoup.nodes.Document;

public class WebClientStrategy extends JSoupStrategyAbstract {

    public static final LinkStrategy INSTANCE = new WebClientStrategy();

    @Override
    public DocumentView execute(String url) {
        try (WebClientUtil webClient = new WebClientUtil(url)) {
            Document document = webClient.execute();
            if (document == null) return null;
            return parse(document);
        } catch (Exception ex) {
            return null;
        }
    }
}
