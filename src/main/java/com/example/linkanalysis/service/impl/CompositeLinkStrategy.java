package com.example.linkanalysis.service.impl;

import com.example.linkanalysis.service.LinkStrategy;
import com.example.linkanalysis.vo.DocumentView;

import java.util.List;

public class CompositeLinkStrategy implements LinkStrategy {

    private final List<LinkStrategy> engines;

    public CompositeLinkStrategy(List<LinkStrategy> linkEngines) {
        this.engines = linkEngines;
    }

    @Override
    public DocumentView execute(String url) {
        for (LinkStrategy engine : this.engines) {
            DocumentView documentView = engine.execute(url);
            if (documentView != null) {
                return documentView;
            }
        }
        return null;
    }
}