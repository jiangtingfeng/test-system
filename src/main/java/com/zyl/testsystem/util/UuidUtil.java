package com.zyl.testsystem.util;

import java.util.UUID;

/**
 * @author jiangtingfeng
 * @description uuid工具
 * @date 2020/6/15/015
 */

public class UuidUtil {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
