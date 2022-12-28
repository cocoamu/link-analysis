package com.example.linkanalysis.service;

import com.example.linkanalysis.vo.DocumentView;

public interface LinkStrategy {
    /**
     * 根据url获取文档信息
     *
     * @param url
     * @return 如果未获取到数据，则返回null
     */
    DocumentView execute(String url);
}
