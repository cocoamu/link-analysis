package com.example.linkanalysis.service.impl;

import com.example.linkanalysis.service.LinkStrategy;
import com.example.linkanalysis.vo.DocumentView;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

public abstract class JSoupStrategyAbstract implements LinkStrategy {

    protected DocumentView parse(Document document) {
        DocumentView docView = new DocumentView();
        docView.setTitle(document.title());
        Elements elements = document.getElementsByTag("img");
        if (elements.isEmpty()) return docView;
        // 获取图片的绝对路径
        int index = 0;
        for (Element element : elements) {
            Element firstImage = element;
            String src = getUrl(firstImage, "src");
            if (!StringUtils.hasText(src)) {
                src = getUrl(firstImage, "data-src");
            }

            if (StringUtils.hasText(src) && (src.contains("http") || (src.contains("//") && isNotBase64(src)))) {
                docView.setImageUrl(src);
                break;
            }
            // 避免img 的标签太多，一直在循环处理
            if (index > 15) break;
            index++;

        }
        return docView;
    }

    protected boolean isNotBase64(String url) {
        // data:image/png:base64
        return !url.startsWith("data:");
    }

    protected String getUrl(Element element, String attributeKey) {
        String src = element.absUrl(attributeKey);
        if (!StringUtils.hasText(src)) {
            src = element.attr(attributeKey);
        }
        return src;
    }
}