package com.example.linkanalysis.utils;

import com.example.linkanalysis.Strategy.impl.DefaultLinkStrategyFactory;
import com.example.linkanalysis.vo.DocumentView;

public class LinkUtil {
    public static String getDocumentTitle(String url) {
        DocumentView documentView = DefaultLinkStrategyFactory.getInstance().get().execute(url);
        if (documentView != null) return documentView.getTitle();
        return null;
    }

    public static DocumentView getDocView(String url) {
        return DefaultLinkStrategyFactory.getInstance().get().execute(url);
    }
}
