package com.zyl.testsystem.vo;

/**
 * @author jiangtingfeng
 * @description
 * @date 2020/6/14/014
 */
public interface BaseErrorCode {
    int HTTP_BAD_SERVER = 500;
    int getHttpCode();
    String getErrorMessage();
    String getInternalErrorCode();
}
