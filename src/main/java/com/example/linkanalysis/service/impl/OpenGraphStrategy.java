package com.example.linkanalysis.service.impl;

import com.example.linkanalysis.service.LinkStrategy;
import com.example.linkanalysis.vo.DocumentView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class OpenGraphStrategy implements LinkStrategy {

    private static final String TAG_HEAD = "head";
    private static final String ATTR_PROPERTY = "property";
    private static final String OG_TITLE = "og:title";
    private static final String OG_IMAGE = "og:image";
    private static final String ATTR_CONTENT = "content";

    public static final LinkStrategy INSTANCE = new OpenGraphStrategy();

    final Logger logger = LoggerFactory.getLogger(OpenGraphStrategy.class);

    @Override
    public DocumentView execute(String url) {
        //判断特定域名才走这个策略，否则直接返回空，走webclient策略，这边先默认返回null
        if (url.contains("weixin")) {
            return getDocument(url)
                    .map(d -> d.getElementsByTag(TAG_HEAD))
                    .map(Elements::first)
                    .map(this::convert)
                    .orElse(null);
        } else {
            return null;
        }

    }

    private DocumentView convert(Element first) {
        Elements title = first.getElementsByAttributeValue(ATTR_PROPERTY, OG_TITLE);
        Elements image = first.getElementsByAttributeValue(ATTR_PROPERTY, OG_IMAGE);
        if (title.isEmpty() && image.isEmpty()) return null;
        DocumentView documentView = new DocumentView();
        documentView.setImageUrl(image.isEmpty() ? null : image.first().attr(ATTR_CONTENT));
        documentView.setTitle(title.isEmpty() ? null : title.first().attr(ATTR_CONTENT));
        return documentView;
    }

    private Optional<Document> getDocument(String url) {
        try {
            return Optional.of(Jsoup.connect(url).get());
        } catch (Exception e) {
            logger.error(String.format("获取外链地址信息异常, url=%s", url), e);
            return Optional.empty();
        }
    }
}