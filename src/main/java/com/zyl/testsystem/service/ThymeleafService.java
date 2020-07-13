package com.zyl.testsystem.service;

import java.io.IOException;

/**
 * @author jiangtingfeng
 * @description
 * @date 2020/6/25/025
 */

public interface ThymeleafService {
    void createHtml(String id,Integer type) throws IOException;
    void deleteHtml(String id);
}
