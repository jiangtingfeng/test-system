package com.zyl.testsystem.common;

import java.io.File;

/**
 * @author jiangtingfeng
 * @description  定义常量的接口类
 * @date 2020/6/14/014
 */
public interface AppConstat {

    public final String PROJECT_NAME = "/test_system";
    public final String VERSION = "/v1";
    public final String PATH_URL = PROJECT_NAME + "/api" + VERSION;

    public final String TOKEN = "TOKEN";
    public final String STATIC_FILE_PATH = "/var/www/html/test_system";

    public final String LOCAL_PATH = "A:\\";

    //前景图
    public final String LOCAL_OBJ_DIR = "objImages";
    //背景图
    public final String LOCAL_BACKIMAGE_DIR = "backImages";
    //原图
    public final String LOCAL_SOURCE_DIR = "sourceImages";
    //假图
    public final String LOCAL_FAKE_DIR = "fakeImages";
    public final Integer LOCAL_FAKE_START = 851;
    public final Integer LOCAL_FAKE_END = 860;
    //文件分割符
    public final String FILE_SEPARATOR = File.separator;

    //图片尾缀
    public final String PIC_SUFFIX = ".jpg";

    //用户Id Key
    public final String USER_ID = "userId";
    //分组对象 Key
    public final String GROUP_OBJ_STRNING = "objString";

    int LOCAL_FAKE_SIZE = 10;
    int LOCAL_TRUE_SIZE = 200;

    //前缀  -v /var/www/html/test_system:/var/www/html/test_system
    String FILE_LEARN_PREFIX = "learn_";
    String FILE_TEST_PREFIX = "test_";
}
