package com.example.linkanalysis.Strategy.impl;

import com.example.linkanalysis.Strategy.LinkStrategyFactory;
import com.example.linkanalysis.service.LinkStrategy;
import com.example.linkanalysis.service.impl.CompositeLinkStrategy;
import com.example.linkanalysis.service.impl.JSoupStrategy;
import com.example.linkanalysis.service.impl.OpenGraphStrategy;
import com.example.linkanalysis.service.impl.WebClientStrategy;

import java.util.Arrays;

public class DefaultLinkStrategyFactory implements LinkStrategyFactory {
    private static final LinkStrategyFactory INSTANCE = new DefaultLinkStrategyFactory();
    private static final LinkStrategy COMPOSITE_ENGINE = new CompositeLinkStrategy(Arrays.asList(OpenGraphStrategy.INSTANCE, WebClientStrategy.INSTANCE));

    @Override
    public LinkStrategy get() {
        return COMPOSITE_ENGINE;
    }


    public static LinkStrategyFactory getInstance() {
        return INSTANCE;
    }

}